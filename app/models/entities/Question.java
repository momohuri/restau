package models.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@JsonWriteNullProperties(false)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Question {

    @JsonProperty("id")
    String id;
    
    @JsonProperty("q")
    String q;
    
    @JsonProperty("options")
    List<String> options;
    
    public Question() {} 

    public Question(String q, List<String> options) {
        this.q = q ;
        this.options = options;
        
    } 
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    @Override
    public String toString() {
        return "Q = " + q + " : Options = " + options;
    }
    

    
}
