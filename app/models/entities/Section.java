package models.entities;

import java.util.Comparator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@JsonWriteNullProperties(false)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Section {

    @JsonIgnore
    private static Logger log = LoggerFactory.getLogger(Section.class);

    @JsonProperty("id")
    String id;
    
    @JsonProperty("name")
    String name;
    
    @JsonProperty("desc")
    String desc;
    
    @JsonProperty("url")
    String url;
    
    @JsonProperty("displayRank")
    Integer displayRank;
    
    @JsonProperty("enabled")
    Boolean enabled;
    
    @JsonProperty("deleted")
    Boolean deleted;
    
    @JsonProperty("items")
    List<Item> items;
    
    public Section() {} 
    
    public Section(String name) {
        this.name  = name;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
//    @JsonIgnore
//    public int getOrderRankInt() {
//        return orderRank;
//    }
//
//    @JsonProperty("orderrank")
//    public String getOrderRank() {
//        return Integer.toString(orderRank);
//    }
//    
//    @JsonIgnore
//    public void setOrderRank(int orderRank) {
//        this.orderRank = orderRank;
//    }
//
//    @JsonProperty("orderrank")
//    public void setOrderRank(String orderRank) {
//        try {
//            this.orderRank = Integer.parseInt(orderRank);
//        } catch (NumberFormatException e) {
//            log.error(e.getMessage(), e);
//            log.error("OrderRank of Section : " + getId() + " is being initialized with faulty String : " + orderRank);
//        }
//    }
    
    public Integer getDisplayRank() {
        return displayRank;
    }

    public void setDisplayRank(Integer displayRank) {
        this.displayRank = displayRank;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public static Comparator<Section> SectionDisplayRankComparator = new Comparator<Section>() { 
        public int compare(Section section1, Section section2) {
            Integer rank1 = section1.getDisplayRank();
            Integer rank2 = section2.getDisplayRank();
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
