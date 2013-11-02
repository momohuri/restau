package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.dao.StorageBackend;
import models.dao.StorageBackendImpl;
import models.entities.Section;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.BodyParser;
import play.mvc.Result;
import utils.JsonUtils;
import exceptions.StorageBackendException;

/*
 * Create Section sample : 
 * I/P : curl --header "Content-type: application/json" --request POST --data '{"name":"section 2"}' localhost:9000/section
 * O/P : {"id":"d25e5b5d-bd4a-445c-848f-21bc757265b0","name":"section 2","orderrank":"0"}
 * 
 * Get Section : 
 * I/P : curl localhost:9000/section/d25e5b5d-bd4a-445c-848f-21bc757265b0 
 * O/P : {"id":"d25e5b5d-bd4a-445c-848f-21bc757265b0","name":"section 2","orderrank":"0"} 
 * 
 * Update Section Sample : 
 * I/P : curl --header "Content-type: application/json" --request PUT --data '{"id":"d25e5b5d-bd4a-445c-848f-21bc757265b0", "name":"section 4"}' localhost:9000/section
 * O/P : {"id":"d25e5b5d-bd4a-445c-848f-21bc757265b0","name":"section 4","orderrank":"0"}
 *  
 * Get All Sections: 
 * I/P : sgandh1-mac:productgenome sgandh1$ curl localhost:9000/sections
 * O/P : [{"id":"32efa46d-e79c-407c-91a8-13d6c2f59a85","name":"section 3","orderrank":"0"},{"id":"d25e5b5d-bd4a-445c-848f-21bc757265b0","name":"section 4","orderrank":"0"}]
 */
public class SectionController extends BaseController {
    
    private static Logger log = LoggerFactory.getLogger(SectionController.class);

    private static final String ColFamily_Section = "Section";

    public static Result getSection(String id) {

        String restaurantId = "1"; // GET THIS FROM USER SESSION
        
        if (id == null || id.isEmpty()) {
            return badRequest("id can't be null/empty");
        }

        StorageBackend sb = StorageBackendImpl.getInstance();

        //Section section = null;
        String sectionJson = null;
        
        try {

            sectionJson = sb.getValue(ColFamily_Section, restaurantId, id);

        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 

        if (sectionJson == null) {
            return customStatus(HTTP_NOT_FOUND, "No data found in Storage",
                    null);
        }

        try {
            Section section = JsonUtils.getObject(sectionJson, Section.class);
            JsonNode jsonResponse = JsonUtils.getJsonWithException(section);
            setCORS();
            return ok(jsonResponse);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }

    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result upsertSection() {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            return invalidJsonData("Expecting Json data");
        }
        
        
        
        log.info("Request Object received :" + jnode.toString());
        Section section =  JsonUtils.getObject(jnode.toString(), Section.class);
        if (section == null) {
            return invalidJsonData("Invalid Json data");
        }
        
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        if (section.getId() == null || section.getId().length() < 1) {
            section.setId(UUID.randomUUID().toString());
        }
        
        try {

            String sectionJson = JsonUtils.getJson(section).toString();
            sb.putValue(ColFamily_Section, restaurantId, section.getId(), sectionJson);

        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        try {
            JsonNode jsonResponse = JsonUtils.getJsonWithException(section);
            setCORS();
            return ok(jsonResponse);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }
    }
    
    public static Result getAllSections() {

        String restaurantId = "1"; // GET THIS FROM USER SESSION
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        //Section section = null;
        Map<String, String> sectionMapJson = null;
        List<Section> sections = new ArrayList<Section>();
         
        try {

            sectionMapJson = sb.getAllColumnNameValue(ColFamily_Section, restaurantId);
            
        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 

        if (sectionMapJson == null) {
            return customStatus(HTTP_NOT_FOUND, "No data found in Storage",
                    null);
        }

        try {
            for (String sectionJson : sectionMapJson.values()) {
                sections.add(JsonUtils.getObject(sectionJson, Section.class));
            }

            JsonNode jsonResponse = JsonUtils.getJsonWithException(sections);
            setCORS();
            return ok(jsonResponse);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }

    }

}
