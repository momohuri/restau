package models.dao;

import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import exceptions.StorageBackendException;

public interface StorageBackend {
    
    public boolean putValue(String table, String rowKey, String colName, String value)  throws StorageBackendException; // colName, rowKey, colName, colValue
    public String getValue(String table, String rowKey, String colName) throws StorageBackendException; // colName, rowKey, colName.
    public Map<String,String> getAllColumnNameValue(String columnFamily, String rowKey) throws StorageBackendException; 

    public boolean putCompositeValue(String table, String rowKey, String colName1, String colName2, String value) throws StorageBackendException; // colName, rowKey, colName.
    public String getCompositeValue(String table, String rowKey, String colName1, String colName2) throws StorageBackendException; // colName, rowKey, colName.
    public Map<String,String> getCompositeValuesStr(String table, String rowKey, String colName1) throws StorageBackendException; // colName, rowKey, colName.
 
    public ObjectNode getCompositeValues(String columnFamily, String rowKey, String compositeName) throws StorageBackendException;
    public boolean putValue(String table, String rowKey, Map<String,ObjectNode> compositeColumn) throws StorageBackendException; // colName, rowKey, Map<composite_Key, JsonNode>
    public Map<String, ObjectNode> getAllCompositeValues(String columnFamily, String rowKey) throws StorageBackendException;
    

    //public void bulkUpload(String fpath, LineParser lineParser) throws StorageBackendException, InternalException;

    public String getProperty(String key);

}
