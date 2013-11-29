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
public class Order {

    @JsonProperty("id")
    String id;
    
    @JsonProperty("restaurantId")
    String restaurantId;
    
    @JsonProperty("tableId")
    String tableId;
    
    @JsonProperty("deviceId")
    String deviceId;
    
    @JsonProperty("userId")
    String userId;
    
    /*
     * tablet_acct , waiter_acct 
     * When we use IPADs/Tablets for ordering 
     * 
     * History : <TS, Status>
     * 
     */
    
    @JsonProperty("price")
    Double price;
    
    @JsonProperty("togo")
    Boolean togo;
    
    @JsonProperty("createdAt")
    Date createdAt;
    
    @JsonProperty("cookedAt")
    Date cookedAt;
    
    @JsonProperty("billGeneratedAt")
    Date billGeneratedAt;
    
    @JsonProperty("closedAt")
    Date closedAt;
    
    @JsonProperty("updatedAt")
    Date updatedAt;
    
    @JsonProperty("orderItems")
    List<OrderItem> orderItems;
    
    @JsonProperty("status")
    Status status;
    
    public Order() {}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getTogo() {
        return togo;
    }

    public void setTogo(Boolean togo) {
        this.togo = togo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCookedAt() {
        return cookedAt;
    }

    public void setCookedAt(Date cookedAt) {
        this.cookedAt = cookedAt;
    }

    public Date getBillGeneratedAt() {
        return billGeneratedAt;
    }

    public void setBillGeneratedAt(Date billGeneratedAt) {
        this.billGeneratedAt = billGeneratedAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    } 

    
}
