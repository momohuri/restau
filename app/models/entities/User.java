package models.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@JsonWriteNullProperties(false)
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    @JsonProperty("id")
    String id;
    
    @JsonProperty("name")
    String name;
    
    @JsonProperty("password")
    String password;

    public User() {} 
    
    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
