package models.entities;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonWriteNullProperties(false)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {
    @JsonIgnore
    private static Logger log = LoggerFactory.getLogger(Item.class);

    @JsonProperty("id")
    String id;
    
    @JsonProperty("name")
    String name;
    
    @JsonProperty("desc")
    String desc;
    
    @JsonProperty("displayRank")
    Integer displayRank;

    @JsonProperty("price")
    Double price;
    
    @JsonProperty("sectionId")
    String sectionId;
    
    @JsonProperty("questions")
    List<Question> questions;
    
    @JsonProperty("ingredients")
    Map<String, Boolean> ingredients;
    
    @JsonProperty("calories")
    Integer calories;
    
    @JsonProperty("spicy")
    Integer spicy;
        
    @JsonProperty("isVegetarian")
    Boolean isVegetarian;
    
    @JsonProperty("isVegan")
    Boolean isVegan;
    
    @JsonProperty("enabled")
    Boolean enabled;
    
    @JsonProperty("url")
    String url;
    
    @JsonProperty("deleted")
    Boolean deleted;
    
    @JsonProperty("createdAt")
    Date createdAt;
    
    @JsonProperty("updatedAt")
    Date updatedAt;
    
    public Item() {} 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(Integer displayRank) {
        this.displayRank = displayRank;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Map<String, Boolean> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Boolean> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getSpicy() {
        return spicy;
    }

    public void setSpicy(Integer spicy) {
        this.spicy = spicy;
    }

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Comparator<Item> ItemDisplayRankComparator = new Comparator<Item>() { 
        public int compare(Item item1, Item item2) {
            Integer rank1 = item1.getDisplayRank();
            Integer rank2 = item2.getDisplayRank();
            if (rank1 != null) {
                return rank1.compareTo(rank2);
            }
            return 0; 
        }
    };
}
