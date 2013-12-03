package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;

import models.entities.Item;
import models.entities.Order;
import models.entities.OrderItem;
import models.entities.Question;
import models.entities.Section;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Double d = null;
        double ad = d;
        System.out.println(ad);
        
        OrderItem item1 = new OrderItem();
        item1.setItemId("123");
        item1.setName("Orange Juice");
        item1.setSectionId("SectionId-Drinks");
        item1.setPricePerUnit(2.99);
        item1.setQuantity(1);
        List<String> comments = new ArrayList<String>();
        comments.add("Room Temperature");
        item1.setComments(comments);

        List<Map<String,String>> questions11 = new ArrayList<Map<String,String>>();
        Map<String, String> a1 = new HashMap<String, String>();  a1.put("ice-no ice?", "ice");
        questions11.add(a1);
        item1.setQuestions(questions11);
        
        OrderItem item2 = new OrderItem();
        item2.setItemId("456");
        item2.setName("sandwich");
        item2.setSectionId("SectionId-Breakfast");
        item2.setPricePerUnit(4.99);
        item2.setQuantity(2);
        List<String> comments2 = new ArrayList<String>();
        comments2.add(null);
        comments2.add("Veg. Allergic to onion n garlic");
        item2.setComments(comments2);
        
        List<Map<String,String>> questions1 = new ArrayList<Map<String,String>>();
        Map<String, String> a2 = new HashMap<String, String>();  a2.put("Type of Meat", "Chicken");a2.put("How much cooked", "Dark");
        Map<String, String> a3 = new HashMap<String, String>();  a3.put("How much cooked", "Medium");

        questions1.add(a2);
        questions1.add(a3);
        item2.setQuestions(questions1);
        
        List<List<String>> customize = new ArrayList<List<String>>();
        List<String> c1 = new ArrayList<String>();
        c1.add("No Mushrooms");
        customize.add(c1);
        
        List<String> c2 = new ArrayList<String>();
        c2.add("No Onion"); c2.add("No Garlic"); c2.add("No Carrot");
        
        customize.add(c2);
        item2.setCustomize(customize);
        
        List<Boolean> togo = new ArrayList<Boolean>();
        togo.add(true);
        togo.add(false);
        item2.setTogo(togo);

        List<OrderItem> items = new ArrayList<OrderItem>();
        items.add(item1);
        items.add(item2);
        
        Order o = new Order();
        o.setOrderItems(items);
        
        JsonNode j = JsonUtils.getJson(item2);
        
        System.out.println(JsonUtils.getJson(o));
        
        Section s = new Section();
        Item i = new Item();
        
        i.setCalories(22);
        i.setName("Tofu Masala");
        i.setDesc("Most amazing yummy thing you will have.");
        i.setDisplayRank(1);
        i.setEnabled(true);
        i.setIsVegan(true);
        Map<String, Boolean> ingredients = new HashMap<String, Boolean>();
        ingredients.put("tofu", false);
        ingredients.put("coconut milk/ soy milk", false);
        ingredients.put("garlic", true);
        ingredients.put("eggs", true);
        
        i.setIngredients(ingredients );
        i.setIsVegetarian(true);
        i.setPrice(11.49);
        i.setUrl("http://www.takepart.com/sites/default/files/styles/tp_gallery_slide/public/Coconut-Ginger-Tofu-with-Rice-Namely-Marly-ARTICLE.jpg");
        i.setSpicy(2);
        i.setSectionId("101");
        List<Question> questions = new ArrayList<Question>();
        
        Question q1 = new Question();
        q1.setQ("How spicy will you like from 1 to 5 ( 5 being very hot)");
        List<String> options = new ArrayList<String>();
        options.add("1"); options.add("2"); options.add("3"); options.add("4"); options.add("5");
        q1.setOptions(options);
        
        questions.add(q1);
        
        Question q2 = new Question();
        q2.setQ("Option for Gravy");
        List<String> options2 = new ArrayList<String>();
        options2.add("coconut milk"); options2.add("soy milk"); 
        q2.setOptions(options2);
        questions.add(q2);

        i.setQuestions(questions );
        
        
        System.out.println(i.getCalories());
        System.out.println(i.getEnabled());
        
        System.out.println(s.getDisplayRank());
        System.out.println(JsonUtils.getJson(i));
    }

}
