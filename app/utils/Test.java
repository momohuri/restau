package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.entities.Item;
import models.entities.Question;
import models.entities.Section;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

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
        
        System.out.println(s.getOrderRank());
        System.out.println(JsonUtils.getJson(i));
    }

}
