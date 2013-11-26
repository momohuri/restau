package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.dao.StorageBackend;
import models.dao.StorageBackendImpl;
import models.entities.Section;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.libs.Json;
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

    public static Result getSection(String sectionId) {

        String restaurantId = "1"; // GET THIS FROM USER SESSION
        
        if (sectionId == null || sectionId.isEmpty()) {
            return badRequest("id can't be null/empty");
        }

        StorageBackend sb = StorageBackendImpl.getInstance();

        //Section section = null;
        ObjectNode sectionJson = null;
        
        try {

            sectionJson = sb.getCompositeValues(ColFamily_Section, restaurantId, sectionId);

        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 

        if (sectionJson == null) {
            return customStatus(HTTP_NOT_FOUND, "No data found in Storage",
                    null);
        }

        try {
            setCORS();
            return ok(sectionJson);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }

    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result insertSection() {
        return upsertSection();
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateSection(String sectionId) {
        return upsertSection();
    }
        
    @BodyParser.Of(BodyParser.Json.class)
    public static Result upsertSection() {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            log.error("Returning BadRequest : Expecting Json data.");
            return invalidJsonData("Expecting Json data");
        }
        
        log.info("Request Object received :" + jnode.toString());
        
        Section section = null;
        try {
            section =  JsonUtils.getObjectWithException(jnode, Section.class);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        if (section == null) {
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        /*
         * TODO: Ensure the client is allowed to do this action.
         */
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        if (section.getId() == null || section.getId().length() < 1) {
            section.setId(UUID.randomUUID().toString());
        }
        
        try {
            section.setDeleted(false);

            JsonNode sectionJson = JsonUtils.getJson(section);
            Map<String,JsonNode> compositeColumn = new HashMap<String, JsonNode>();
            compositeColumn.put(section.getId(), sectionJson);
            sb.putValue(ColFamily_Section, restaurantId, compositeColumn);

        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        try {
            ObjectNode result = Json.newObject();
            result.put("id", section.getId());
            setCORS();
            return ok(result);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result upsertBulkSections() {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            log.error("Returning BadRequest : Expecting Json data.");
            return invalidJsonData("Expecting Json data");
        }
                
        log.info("Request Object received :" + jnode.toString());
        
        Section[] sections = null;
        try {
            sections =  JsonUtils.getObjectWithException(jnode.toString(), Section[].class);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        if (sections == null || sections.length < 1) {
            log.error("Returning BadRequest : Invalid Json data.");
            return invalidJsonData("Invalid Json data");
        }
        
        String restaurantId = "1"; // GET THIS FROM USER SESSION
        /*
         * TODO : Ensure that the User is working on the section that he owns for his restaurant only.
         * 
         */
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        Map<String,JsonNode> compositeColumns = new HashMap<String, JsonNode>();
        Date currentDate = new Date();
        
        for (Section section : sections) {
            if (section.getId() == null || section.getId().length() < 1) {
                section.setId(UUID.randomUUID().toString());
                //section.setCreatedAt(currentDate);
            }
            
            //section.setUpdatedAt(currentDate);
            /*
             * We dont update to ever cause a delete. Hence the when upsert is called
             * delete = false always.
             */
            section.setDeleted(false);
            
            JsonNode sectionJson = JsonUtils.getJson(section);
            compositeColumns.put(section.getId(), sectionJson);
            
        }
        
        try {
            
            sb.putValue(ColFamily_Section, restaurantId, compositeColumns);

        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        try {
            setCORS();
            JsonNode jsonResponse = JsonUtils.getJsonWithException(sections);
            return ok(jsonResponse);
            //return ok();
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }
    }
    
    public static Result getAllSections(Boolean items) {

        String restaurantId = "1"; // GET THIS FROM USER SESSION
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        Map<String, ObjectNode> sectionsJson = null;
        try {

            sectionsJson = sb.getAllCompositeValues(ColFamily_Section, restaurantId);

        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 

        if (sectionsJson == null) {
            return customStatus(HTTP_NOT_FOUND, "No data found in Storage",
                    null);
        }

        try {
            List<Section> sections = new ArrayList<Section>();
            for (ObjectNode json : sectionsJson.values()) {
                Section section = JsonUtils.getObject(json, Section.class);
                if (Boolean.FALSE.equals(section.getDeleted())) {
                    if (Boolean.TRUE.equals(items)) {
                        /*
                         * /sections?items=true implies restaurant eater is making a request 
                         * and hence we need to give only those sections which are enabled. 
                         */
                        if (Boolean.TRUE.equals(section.getEnabled())) {
                            section.setItems(MenuController.getBulkItemsInternal(section.getId(), true));
                            sections.add(section);
                        }
                    } else {
                        /*
                         * This path is used by the cockit and hence 
                         * we return all the sections whether enabled or disabled.
                         */
                        sections.add(section);
                    }
                }
            }
            Collections.sort(sections, Section.SectionDisplayRankComparator);
            setCORS();
            JsonNode jsonResponse = JsonUtils.getJsonWithException(sections);
            return ok(jsonResponse);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }

    }
    
    public static Result deleteSection(String sectionId) {

        String restaurantId = "1"; // GET THIS FROM USER SESSION
        /*
         * TODO : Ensure that the Cockpit is deleting the section that he owns for his restaurant only.
         * 
         */
        
        if (StringUtils.isEmpty(sectionId)) {
            return badRequest("id can't be null/empty");
        }

        StorageBackend sb = StorageBackendImpl.getInstance();

        Section section = new Section();
        section.setId(sectionId);
        section.setDeleted(true);
        //section.setUpdatedAt(new Date());
        
        try {

            JsonNode sectionJson = JsonUtils.getJson(section);
            Map<String,JsonNode> compositeColumn = new HashMap<String, JsonNode>();
            compositeColumn.put(section.getId(), sectionJson);
            sb.putValue(ColFamily_Section, restaurantId, compositeColumn);
            
        } catch (StorageBackendException e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 

        try {
            // Item item = JsonUtils.getObjectWithException(itemJson.toString(), Item.class);
            setCORS();
            return ok();
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error- Malformed Json", e);

        }

    }

}
