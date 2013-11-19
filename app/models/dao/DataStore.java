package models.dao;

import java.util.Map;

import org.codehaus.jackson.JsonNode;


public abstract class DataStore {
    
    public abstract String getValue(String table, String rowKey, String colName);   // colName, rowKey, colName.
    
    public abstract boolean putValue(String table, String rowKey, String colName, String value); // colName, rowKey, colName, colValue

    public abstract boolean putValue(String table, String rowKey, Map<String,JsonNode> compositeColumn); // colName, rowKey, Map<composite_Key, JsonNode>

    public abstract Map<String,String> getAllColumnNameValue(String table, String rowKey);

    //public abstract void bulkUpload(String fpath, LineParser lineParser);

    public abstract String getProperty(String key);

}

