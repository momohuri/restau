package controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import models.dao.StorageBackend;
import models.dao.StorageBackendImpl;
import models.entities.Item;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.BodyParser;
import play.mvc.Result;
import utils.JsonUtils;
import exceptions.StorageBackendException;

public class MenuController extends BaseController {
    
    private static Logger log = LoggerFactory.getLogger(MenuController.class);

    private static final String ColFamily_Menu = "Menu";

    
    /*
     * Sample Creation : 
     * curl --header "Content-type: application/json" --request POST --data '{"name":"Tofu Masala","desc":"Most amazing yummy thing you will have.","displayRank":1,"price":11.49,"sectionId":"101","questions":[{"q":"How spicy will you like from 1 to 5 ( 5 being very hot)","options":["1","2","3","4","5"]},{"q":"Option for Gravy","options":["coconut milk","soy milk"]}],"ingredients":{"eggs":true,"garlic":true,"tofu":false,"coconut milk/ soy milk":false},"calories":22,"spicy":2,"isVegetarian":true,"isVegan":true,"enabled":true,"url":"http://www.takepart.com/sites/default/files/styles/tp_gallery_slide/public/Coconut-Ginger-Tofu-with-Rice-Namely-Marly-ARTICLE.jpg"}' localhost:9000/section/101/item
     * 
     * Sample Update : 
     * url --header "Content-type: application/json" --request PUT --data '{"id":"0b6a0d9d-05bb-4db1-8f3b-b972ca175266", "price":555.55}' localhost:9000/section/101/item
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result upsertItem(String sectionId) {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            log.error("Returning BadRequest : Expecting Json data.");
            return invalidJsonData("Expecting Json data");
        }
                
        log.info("Request Object received :" + jnode.toString());
        
        Item item = null;
        try {
            item =  JsonUtils.getObjectWithException(jnode.toString(), Item.class);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        if (item == null) {
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        /*
         * TODO : Ensure that the User is creating the item in the section that he owns for his restaurant only.
         * Also ensure that the section already exists.
         * 
         */
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        Date currentDate = new Date();
        if (item.getId() == null || item.getId().length() < 1) {
            item.setId(UUID.randomUUID().toString());
            item.setCreatedAt(currentDate);
        }
        
        try {

            item.setUpdatedAt(currentDate);
            item.setDeleted(false);
            
            JsonNode itemJson = JsonUtils.getJson(item);
            Map<String,JsonNode> compositeColumn = new HashMap<String, JsonNode>();
            compositeColumn.put(item.getId(), itemJson);
            sb.putValue(ColFamily_Menu, sectionId, compositeColumn);

        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        try {
            JsonNode jsonResponse = JsonUtils.getJsonWithException(item);
            setCORS();
            return ok(jsonResponse);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }
    }
    
    /*
     * Bulk Create Example : 
     * curl --header "Content-type: application/json" --request POST --data '[{"name":"Tofu Masala","displayRank":1,"price":11.49,"sectionId":"101","calories":22,"spicy":2,"isVegetarian":true,"enabled":true},{"name":"Paneer Masala","displayRank":3,"price":20.49,"sectionId":"101","calories":22,"spicy":3,"isVegetarian":true,"enabled":true}]' localhost:9000/section/101/items
     * 
     * Bulk Price Update Example : 
     * (Note : USE ID's that were created in the above call.)
     * curl --header "Content-type: application/json" --request PUT --data '[{"id":"0b6a0d9d-05bb-4db1-8f3b-b972ca175266", "price":50.50}, {"id":"d74c7134-e6fe-432a-a27a-538cd5cd68eb", "price":51.51}]' localhost:9000/section/101/items
     * 
     * 
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result upsertBulkItems(String sectionId) {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            log.error("Returning BadRequest : Expecting Json data.");
            return invalidJsonData("Expecting Json data");
        }
                
        log.info("Request Object received :" + jnode.toString());
        
        Item[] items = null;
        try {
            items =  JsonUtils.getObjectWithException(jnode.toString(), Item[].class);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        if (items == null || items.length < 1) {
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        /*
         * TODO : Ensure that the User is creating the item in the section that he owns for his restaurant only.
         * Also ensure that the section already exists.
         * 
         */
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        Map<String,JsonNode> compositeColumns = new HashMap<String, JsonNode>();
        Date currentDate = new Date();
        
        for (Item item : items) {
            if (item.getId() == null || item.getId().length() < 1) {
                item.setId(UUID.randomUUID().toString());
                item.setCreatedAt(currentDate);
            }
            
            item.setUpdatedAt(currentDate);
            /*
             * We dont update to ever cause a delete. Hence the when upsert is called
             * delete = false always.
             */
            item.setDeleted(false);
            
            JsonNode itemJson = JsonUtils.getJson(item);
            compositeColumns.put(item.getId(), itemJson);
            
        }

        
        try {
            
            sb.putValue(ColFamily_Menu, sectionId, compositeColumns);

        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        try {
            JsonNode jsonResponse = JsonUtils.getJsonWithException(items);
            setCORS();
            return ok(jsonResponse);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }
    }
    

}
