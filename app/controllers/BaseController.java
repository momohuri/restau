package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.Controller;
import play.mvc.Result;

public class BaseController extends Controller {
    
    //TODO : Need to setup proper logging. Need to study what play provides.
    // This method is being copied from Leonid's AcctMgmt project. 
    // Other option is to use sl4j.
    private static Logger logger = LoggerFactory.getLogger("play");
    
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;


    public static Logger logger() {
        return logger;
    }
    
    // TODO : Ask Limelight/PIM Portal how they want to get notified of errors.
    // Currently PCS 1.5 returns a plain text string.
    protected static Result invalidJsonData(String output) {
        
        /*
        ObjectNode result = Json.newObject();
        result.put("status", "NG");
        result.put("message", string);
        return badRequest(result);
        */
        
        return badRequest(output);
    }   
    
    protected static Result customStatus(int returnCode, String returnString, Exception e) {
        
        if (e != null) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return status(returnCode, returnString);
    }
    
    protected static void setCORS() {
        
        response().setHeader("Access-Control-Allow-Origin", "*");
       
    }
    
}
