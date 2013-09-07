package controllers;

import models.dao.StorageBackend;
import models.dao.StorageBackendImpl;
import models.entities.User;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.BodyParser;
import play.mvc.Result;
import utils.JsonUtils;
import views.html.index;
import exceptions.StorageBackendException;

public class MenuController extends BaseController {
    
    private static Logger log = LoggerFactory.getLogger(MenuController.class);

    private static final String ColFamily_User = "User";
    private static final String ColName_Name = "Name";
    private static final String ColName_Pwd = "Password";

    public static Result getUser(String id) {

        if (id == null || id.isEmpty()) {
            return badRequest("id can't be null/empty");
        }

        StorageBackend sb = StorageBackendImpl.getInstance();

        User user = null;
        
        try {

            String name = sb.getValue(ColFamily_User, id, ColName_Name);
            String password = sb.getValue(ColFamily_User, id, ColName_Pwd);

            if (name != null) {
                user = new User(id, name, password);
            }
        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
//        catch (InternalException e) {
//            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error", e);
//        }

        if (user == null) {
            return customStatus(HTTP_NOT_FOUND, "No data found in Storage",
                    null);
        }

        try {
            JsonNode jsonResponse = JsonUtils.getJsonWithException(user);
            setCORS();
            return ok(jsonResponse);
        } catch (Exception e) {
            return customStatus(HTTP_INTERNAL_SERVER_ERROR, "Internal Error", e);

        }

    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createMenu() {
        JsonNode  jnode = request().body().asJson();
        if (jnode == null) {
            return invalidJsonData("Expecting Json data");
        }
        
        
        log.info("Request Object received :" + jnode.toString());
        User user =  JsonUtils.getObject(jnode.toString(), User.class);
        if (user == null) {
            return invalidJsonData("Invalid Json data");
        }
        
        
        String id = user.getId();
        String name = user.getName();
        String password = user.getPassword();
        
        StorageBackend sb = StorageBackendImpl.getInstance();

        try {

            String existingUser = sb.getValue(ColFamily_User, id, ColName_Name);

            if (existingUser != null) {
                return customStatus(BAD_REQUEST, "User already exists", null);
            }
        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        } 
        
        try {
            sb.putValue(ColFamily_User, id, ColName_Name, name);
            sb.putValue(ColFamily_User, id, ColName_Pwd, password);
        } catch (StorageBackendException e) {
            // TODO Auto-generated catch block
            return customStatus(HTTP_INTERNAL_SERVER_ERROR,
                    "Internal Error: talking to StorageBackend", e);
        }
        
        return ok("User Successfully created");
    }

}
