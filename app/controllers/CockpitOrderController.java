package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.dao.StorageBackend;
import models.dao.StorageBackendImpl;
import models.entities.Order;
import models.entities.OrderItem;
import models.entities.OrderStatus;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import utils.JsonUtils;
import exceptions.InternalException;
import exceptions.StorageBackendException;


public class CockpitOrderController extends BaseController {
    
    private static Logger log = LoggerFactory.getLogger(CockpitOrderController.class);

    private static final String ColFamily_ORDER = "Order";
    private static final String CF_USER_ORDER_INDEX = "UserOrderIndex";

    private static Boolean isRestaurantAuthorized(String restaurantId, String orderId) {
        StorageBackend sb = StorageBackendImpl.getInstance();
        try {
            String result = sb.getCompositeValue(ColFamily_ORDER, orderId, orderId, "restaurantId");
            JsonNode valueJson = JsonUtils.getObject(result, JsonNode.class);
            String cassRestaurantId = valueJson.asText();
            if (!restaurantId.equals(cassRestaurantId)) {
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
        
//        Boolean isAuthorized = isDeviceAuthorized(deviceId, orderId);
//        if (isAuthorized == null) {
//            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", null);
//        } else if (Boolean.FALSE.equals(isAuthorized)) {
//            return customStatus(UNAUTHORIZED, "Not Authorized", null);
//        }
            
        
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
     * Example : 
     * sgandh1-mac:MapTracker sgandh1$ curl localhost:9000/client/order/288d7baa-cd03-4c9b-b939-33c53067ce01
     * Sample ANS :: {"createdAt":1385695665493,"deviceId":"ABCDEFGHI","id":"288d7baa-cd03-4c9b-b939-33c53067ce01","price":12.97,"restaurantId":"1","status":"RECEIVED","tableId":"10","userId":"1233456789","orderItems":[{"comments":["Room Temperature"],"itemId":"123","name":"Orange Juice","pricePerUnit":2.99,"quantity":1,"questions":[{"ice-no ice?":"ice"}],"sectionId":"SectionId-Drinks"},{"comments":[null,"Veg. Allergic to onion n garlic"],"customize":[["No Mushrooms"],["No Onion","No Garlic","No Carrot"]],"itemId":"456","name":"sandwich","pricePerUnit":4.99,"quantity":2,"questions":[{"How much cooked":"Dark","Type of Meat":"Chicken"},{"How much cooked":"Medium"}],"sectionId":"SectionId-Breakfast","togo":[true,false]}]}
     */
    public static Result getOrder(String orderId) {

        String restaurantId = "1"; // GET THIS FROM USER SESSION .. Ensure not null
        
        if (orderId == null || orderId.isEmpty()) {
            return badRequest("id can't be null/empty");
        }

        Boolean isAuthorized = isRestaurantAuthorized(restaurantId, orderId);
        if (isAuthorized == null) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", null);
        } else if (Boolean.FALSE.equals(isAuthorized)) {
            return customStatus(UNAUTHORIZED, "Not Authorized", null);
        }
           
        try {
            ObjectNode orderJson = UserOrderController.getInternalOrder(orderId);
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
    public static Result updateOrder(String orderId) {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            log.error("Returning BadRequest : Expecting Json data.");
            return invalidJsonData("Expecting Json data");
        }
        
        log.info("Request Object received :" + jnode.toString());
        
        String restaurantId = "1"; // GET THIS FROM USER SESSION .. Ensure not null

        Boolean isAuthorized = isRestaurantAuthorized(restaurantId, orderId);
        if (isAuthorized == null) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", null);
        } else if (Boolean.FALSE.equals(isAuthorized)) {
            return customStatus(UNAUTHORIZED, "Not Authorized", null);
        }
        
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
        
        
        int quantity;
        double itemprice, price = 0;
        for (OrderItem item : order.getOrderItems()) {
            quantity = item.getQuantity();
            itemprice = item.getPricePerUnit();
            price += quantity*itemprice;
        }
        
        Double discount = order.getDiscount();
        
        if (discount == null) {
            order.setPrice(price);
        }else {
            order.setPrice(price - discount);
        }
        
        StorageBackend sb = StorageBackendImpl.getInstance();
        
        try {
            ObjectNode origOrderJson = UserOrderController.getInternalOrder(orderId);
            if (origOrderJson == null) {
                return customStatus(HTTP_NOT_FOUND, "No data found in Storage", null);
            }
            log.info("Original Order for id: " + orderId + " " + origOrderJson.toString());
            
            String statusStr = origOrderJson.path("status").asText();
            /*
             * TODO : USE this data to delete the index based on the status 
             * 
             */
            sb.deleteRow(ColFamily_ORDER, orderId);
            
        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error: talking to StorageBackend", e);
        } catch (InternalException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, e.getReason(), e);
        }
        
        

        
        try {
            List<OrderItem> items = order.getOrderItems();
            order.setOrderItems(null);

            Map<String,ObjectNode> compositeColumn = new HashMap<String, ObjectNode>();

            ObjectNode orderJson = (ObjectNode) JsonUtils.getJson(order);
            compositeColumn.put(order.getId(), orderJson);
            
            for (OrderItem item : items) {
                compositeColumn.put(item.getItemId(), (ObjectNode) JsonUtils.getJson(item));
            }
            
            // UPDATE THE ORDER
            sb.putValue(ColFamily_ORDER, order.getId(), compositeColumn);
            
            // UPDATE THE INDEXES 
            // TODO : More indexes here.

            sb.putCompositeValue(CF_USER_ORDER_INDEX, order.getDeviceId(), restaurantId, order.getId(), order.getStatus().toString());

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
