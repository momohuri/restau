package models.entities;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
public class OrderItem {

    @JsonProperty("id")
    String itemId;
    
    @JsonProperty("sectionId")
    String sectionId;
    
    @JsonProperty("name")
    String name;
    
    @JsonProperty("quantity")
    Integer quantity;

    @JsonProperty("pricePerUnit")
    Double pricePerUnit;
    
    @JsonProperty("originalpricePerUnit")
    Double originalpricePerUnit;
    
    @JsonProperty("displayRank")
    Integer displayRank;
    
    @JsonProperty("customize")
    List<List<String>> customize;
    
    @JsonProperty("questions")
    List<Map<String,String>> questions;
    
    @JsonProperty("comments")
    List<String> comments;
    
    @JsonProperty("togo")
    List<Boolean> togo;
    
    public OrderItem() {} 

    

    public String getItemId() {
        return itemId;
    }



    public void setItemId(String itemId) {
        this.itemId = itemId;
    }



    public String getSectionId() {
        return sectionId;
    }



    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Integer getQuantity() {
        return quantity;
    }



    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



    public Double getPricePerUnit() {
        return pricePerUnit;
    }



    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }



    public Double getOriginalpricePerUnit() {
        return originalpricePerUnit;
    }



    public void setOriginalpricePerUnit(Double originalpricePerUnit) {
        this.originalpricePerUnit = originalpricePerUnit;
    }



    public Integer getDisplayRank() {
        return displayRank;
    }



    public void setDisplayRank(Integer displayRank) {
        this.displayRank = displayRank;
    }



    public List<List<String>> getCustomize() {
        return customize;
    }



    public void setCustomize(List<List<String>> customize) {
        this.customize = customize;
    }



    public List<Map<String, String>> getQuestions() {
        return questions;
    }



    public void setQuestions(List<Map<String, String>> questions) {
        this.questions = questions;
    }



    public List<String> getComments() {
        return comments;
    }



    public void setComments(List<String> comments) {
        this.comments = comments;
    }



    public List<Boolean> getTogo() {
        return togo;
    }



    public void setTogo(List<Boolean> togo) {
        this.togo = togo;
    }



    public static Comparator<OrderItem> ItemDisplayRankComparator = new Comparator<OrderItem>() { 
        public int compare(OrderItem item1, OrderItem item2) {
            Integer rank1 = item1.getDisplayRank();
            Integer rank2 = item2.getDisplayRank();
            if (rank1 == null) {
                rank1 = Integer.MAX_VALUE;
            }
            if (rank2 == null) {
                rank2 = Integer.MAX_VALUE;
            }
            
            return rank1.compareTo(rank2);
        }
    };
}
