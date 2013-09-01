package controllers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

public class HelloWorld extends BaseController {

    /*
     * Test using : 
     * curl --header "Content-type: application/json" --request POST --data '{"name":"shreyansh"}' localhost:9000/sayhello
     * 
     */
    @BodyParser.Of(BodyParser.Json.class)
    public static Result sayHello() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        }
        ObjectNode result = Json.newObject();
        String name = json.findPath("name").getTextValue();
        
        if (name == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter [name]");
            return badRequest(result);
        } else {
            result.put("status", "OK");
            result.put("message", "Hello " + name);
            return ok(result);
        }
    }

}
