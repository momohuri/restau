package controllers;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        List<ObjectNode> list = new ArrayList<ObjectNode>();

        ObjectNode result = Json.newObject() ;
        ObjectNode result2 = Json.newObject() ;
        result.put("name","section0");
        result.put("id",0);
        list.add(result);
        result2.put("name","section1");
        result2.put("id",1);
        list.add(result2);

        return ok(views.html.index.render());
    }

    public static Result section(Integer id) {
        JsonNode result = Json.newObject();
        result = Json.parse("{\"items\":[{\"name\":\"name1\",\"description\":\"myDesc\",\"calories\":\"calories\",\"price\":\"2\",\"spicy\":\"0\",\"vegetarian\":\"yes\",\"enable\":\"yes\"},{\"name\":\"name2\",\"description\":\"\",\"calories\":\"\",\"price\":\"10\",\"spicy\":\"2\",\"vegetarian\":\"no\",\"enable\":\"yes\"}],\"name\":\"myNesction\"}");
        return ok(sectionEdit.render());
    }
}
