package models.dao;

import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;


public abstract class DataStore {
    
    public abstract String getValue(String table, String rowKey, String colName);   // colName, rowKey, colName.
    
    public abstract boolean putValue(String table, String rowKey, String colName, String value); // colName, rowKey, colName, colValue

    public abstract boolean putValue(String table, String rowKey, Map<String,JsonNode> compositeColumn); // colName, rowKey, Map<composite_Key, JsonNode>

    public abstract Map<String,String> getAllColumnNameValue(String table, String rowKey);

    public abstract Map<String, ObjectNode> getAllCompositeValues(String columnFamily, String rowKey);
    
    public abstract ObjectNode getCompositeValues(String columnFamily, String rowKey, String compositeName);

    //public abstract void bulkUpload(String fpath, LineParser lineParser);

    public abstract String getProperty(String key);

}

