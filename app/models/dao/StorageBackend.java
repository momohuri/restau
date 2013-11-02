package models.dao;

import java.util.Map;

import exceptions.StorageBackendException;

public interface StorageBackend {
    
    public String getValue(String table, String rowKey, String colName) throws StorageBackendException; // colName, rowKey, colName.
    
    public boolean putValue(String table, String rowKey, String colName, String value)  throws StorageBackendException; // colName, rowKey, colName, colValue
   
    public Map<String,String> getAllColumnNameValue(String columnFamily, String rowKey)  throws StorageBackendException; 

    //public void bulkUpload(String fpath, LineParser lineParser) throws StorageBackendException, InternalException;

    public String getProperty(String key);

}
