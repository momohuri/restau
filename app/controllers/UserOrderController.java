package controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import models.dao.StorageBackend;
import models.dao.StorageBackendImpl;
import models.entities.Order;
import models.entities.OrderItem;
import models.entities.OrderStatus;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import utils.JsonUtils;
import exceptions.InternalException;
import exceptions.StorageBackendException;


public class UserOrderController extends BaseController {
    
    private static Logger log = LoggerFactory.getLogger(UserOrderController.class);

    private static final String ColFamily_ORDER = "Order";
    private static final String CF_USER_ORDER_INDEX = "UserOrderIndex";

    private static Boolean isDeviceAuthorized(String deviceId, String orderId) {
        StorageBackend sb = StorageBackendImpl.getInstance();
        try {
            String result = sb.getCompositeValue(ColFamily_ORDER, orderId, orderId, "deviceId");
            JsonNode valueJson = JsonUtils.getObject(result, JsonNode.class);
            String cassDeviceId = valueJson.asText();
            if (!deviceId.equals(cassDeviceId)) {
                return false;
            }
            return true;
        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(), e);
            return null;
        }
    }
    
    /*
     * curl localhost:9000/client/order/4633fe01-124d-4197-b5de-afdda3087dfb/status
     * 
     */
    public static Result getOrderStatus(String orderId) {

        String deviceId = "ABCDEFGHI"; // GET THIS FROM USER SESSION .. ENSURE not NULL;
        if (orderId == null || orderId.isEmpty()) {
            return badRequest("id can't be null/empty");
        }
        
        Boolean isAuthorized = isDeviceAuthorized(deviceId, orderId);
        if (isAuthorized == null) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", null);
        } else if (Boolean.FALSE.equals(isAuthorized)) {
            return customStatus(UNAUTHORIZED, "Not Authorized", null);
        }
            
        
        StorageBackend sb = StorageBackendImpl.getInstance();
        try {            
            String status = sb.getCompositeValue(ColFamily_ORDER, orderId, orderId, "status");
            return ok(status);
        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(), e);
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", e);
        }

    }
    
    private static Map<String,String> getDeviceOrders(String deviceId, String restaurantId) throws StorageBackendException {


        StorageBackend sb = StorageBackendImpl.getInstance();

        return sb.getCompositeValuesStr(CF_USER_ORDER_INDEX, deviceId, restaurantId);

    }
    
    
    public static Result generateBill() {
        String deviceId = "ABCDEFGHI"; // GET THIS FROM USER SESSION .. Ensure not null;
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        try {
            
            Map<String,String> orders = getDeviceOrders(deviceId, restaurantId);
            for (String orderId : orders.keySet()) {
                sb.putCompositeValue(CF_USER_ORDER_INDEX, deviceId, restaurantId, orderId, OrderStatus.BILL_GENERATED.toString());
                sb.putCompositeValue(ColFamily_ORDER, orderId, orderId, "status", OrderStatus.BILL_GENERATED.toString());
            }
        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        return ok();
    }
    
    /*
     * Returns /client/orders?isAllBillGenerated 
     * true : if all the bills are generated
     */
    public static Result getBulkOrderStatus() {
        String deviceId = "ABCDEFGHI"; // GET THIS FROM USER SESSION .. Ensure not null;
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        
        Boolean result = true;

        try {
            
            Map<String,String> orders = getDeviceOrders(deviceId, restaurantId);
            for (String status : orders.values()) {
                if (!OrderStatus.BILL_GENERATED.toString().equals(status)) {
                    result = false;
                    break;
                }
                
            }
        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        return ok(result.toString());
    }

    public static Result getBulkOrders() {
        
        String deviceId = "ABCDEFGHI"; // GET THIS FROM USER SESSION .. Ensure not null;
        String restaurantId = "1"; // GET THIS FROM USER SESSION

        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        try {
            
            Map<String,String> orders = getDeviceOrders(deviceId, restaurantId);
            
            for (String orderId : orders.keySet()) {
                ObjectNode orderJson = getInternalOrder(orderId);
                if (orderJson == null) {
                    log.error("ERROR : getting bulkorders. OrderID present in Index but not in Order. DeviceId=" + deviceId + " ; OrderId=" + orderId);
                    continue;
                }
                result.add(orderJson);
            }
        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        }  catch (InternalException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, e.getReason(), e);
        }
        
        setCORS();
        return ok(result);

    }
    
    /*
     * TODO : Move this to COMMON CLASS. THIS IS USED BY COCKPIT_ORDER_CONTROLLER
     */
    public static ObjectNode getInternalOrder(String orderId) throws StorageBackendException, InternalException {
        StorageBackend sb = StorageBackendImpl.getInstance();

        Map<String, ObjectNode> resultJson = null;

        try {

            resultJson = sb.getAllCompositeValues(ColFamily_ORDER, orderId);

        } catch (StorageBackendException e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        if (resultJson == null) {
            return null;
        }

        try {
            ObjectNode orderJson = resultJson.get(orderId);
            ArrayNode itemsJson = orderJson.putArray("orderItems");
            for (Entry<String, ObjectNode> entry : resultJson.entrySet()) {
                String key = entry.getKey();
                if (!orderId.equals(key)) {
                    itemsJson.add(entry.getValue());
                }
            }
            return orderJson;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InternalException("Internal Error- Malformed Json");
        }
    }
    
    /*
     * Example : 
     * sgandh1-mac:MapTracker sgandh1$ curl localhost:9000/client/order/288d7baa-cd03-4c9b-b939-33c53067ce01
     * Sample ANS :: {"createdAt":1385695665493,"deviceId":"ABCDEFGHI","id":"288d7baa-cd03-4c9b-b939-33c53067ce01","price":12.97,"restaurantId":"1","status":"RECEIVED","tableId":"10","userId":"1233456789","orderItems":[{"comments":["Room Temperature"],"itemId":"123","name":"Orange Juice","pricePerUnit":2.99,"quantity":1,"questions":[{"ice-no ice?":"ice"}],"sectionId":"SectionId-Drinks"},{"comments":[null,"Veg. Allergic to onion n garlic"],"customize":[["No Mushrooms"],["No Onion","No Garlic","No Carrot"]],"itemId":"456","name":"sandwich","pricePerUnit":4.99,"quantity":2,"questions":[{"How much cooked":"Dark","Type of Meat":"Chicken"},{"How much cooked":"Medium"}],"sectionId":"SectionId-Breakfast","togo":[true,false]}]}
     */
    public static Result getOrder(String orderId) {

        String deviceId = "ABCDEFGHI"; // GET THIS FROM USER SESSION .. Ensure not null;
        
        if (orderId == null || orderId.isEmpty()) {
            return badRequest("id can't be null/empty");
        }

        Boolean isAuthorized = isDeviceAuthorized(deviceId, orderId);
        if (isAuthorized == null) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", null);
        } else if (Boolean.FALSE.equals(isAuthorized)) {
            return customStatus(UNAUTHORIZED, "Not Authorized", null);
        }
           
        try {
            ObjectNode orderJson = getInternalOrder(orderId);
            if (orderJson == null) {
                return customStatus(HTTP_NOT_FOUND, "No data found in Storage", null);
            }
            setCORS();
            return ok(orderJson);
        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", e);
        } catch (InternalException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, e.getReason(), e);
        }

    }
    
    /*
     * Example : 
     * sgandh1-mac:MapTracker sgandh1$ curl --header "Content-type: application/json" --request POST --data '{"orderItems":[{"itemId":"123","sectionId":"SectionId-Drinks","name":"Orange Juice","quantity":1,"pricePerUnit":2.99,"questions":[{"ice-no ice?":"ice"}],"comments":["Room Temperature"]},{"itemId":"456","sectionId":"SectionId-Breakfast","name":"sandwich","quantity":2,"pricePerUnit":4.99,"customize":[["No Mushrooms"],["No Onion","No Garlic","No Carrot"]],"questions":[{"How much cooked":"Dark","Type of Meat":"Chicken"},{"How much cooked":"Medium"}],"comments":[null,"Veg. Allergic to onion n garlic"],"togo":[true,false]}]}' localhost:9000/client/order
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createOrder() {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            log.error("Returning BadRequest : Expecting Json data.");
            return invalidJsonData("Expecting Json data");
        }
        
        log.info("Request Object received :" + jnode.toString());
        
        Order order = null;
        try {
            order =  JsonUtils.getObjectWithException(jnode, Order.class);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        if (order == null) {
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        
        /*
         * TODO : sanity testing the data. Each item quantity is > 0 (also check some max)
         * and each item price > 0.
         * 
         */
        
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        String tableId = "10"; // GET THIS FROM USER SESSION
        String userId = "1233456789"; // GET THIS FROM USER SESSION
        String deviceId = "ABCDEFGHI"; // GET THIS FROM USER SESSION


        order.setRestaurantId(restaurantId);
        order.setDeviceId(deviceId);
        order.setTableId(tableId);
        order.setUserId(userId);
        
        int quantity;
        double itemprice, price = 0;
        for (OrderItem item : order.getOrderItems()) {
            quantity = item.getQuantity();
            itemprice = item.getPricePerUnit();
            price += quantity*itemprice;
        }
        order.setPrice(price);
        
        Date d = new Date();
        order.setCreatedAt(d);
        
        order.setId(UUID.randomUUID().toString());

        order.setStatus(OrderStatus.RECEIVED);
        StorageBackend sb = StorageBackendImpl.getInstance();
        
        try {
            List<OrderItem> items = order.getOrderItems();
            order.setOrderItems(null);

            Map<String,ObjectNode> compositeColumn = new HashMap<String, ObjectNode>();

            ObjectNode orderJson = (ObjectNode) JsonUtils.getJson(order);
            compositeColumn.put(order.getId(), orderJson);
            
            for (OrderItem item : items) {
                compositeColumn.put(item.getItemId(), (ObjectNode) JsonUtils.getJson(item));
            }
            
            sb.putValue(ColFamily_ORDER, order.getId(), compositeColumn);
            
//            Map<String,ObjectNode> userOrderIndex = new HashMap<String, ObjectNode>();
//            ObjectNode oNode = JsonNodeFactory.instance.objectNode();
//            oNode.put(order.getId(), new TextNode(order.getStatus().toString()));
//            userOrderIndex.put(restaurantId, oNode);
//            sb.putValue(CF_USER_ORDER_INDEX, deviceId, userOrderIndex);
            sb.putCompositeValue(CF_USER_ORDER_INDEX, deviceId, restaurantId, order.getId(), order.getStatus().toString());

        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        try {
            ObjectNode result = Json.newObject();
            result.put("id", order.getId());
            /*
             * TODO : Need to return the price too
             */
            setCORS();
            return ok(result);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }
    }
    
}
