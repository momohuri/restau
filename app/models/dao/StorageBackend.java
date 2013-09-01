package models.dao;

import exceptions.StorageBackendException;

public interface StorageBackend {
    
    public String getValue(String table, String rowKey, String colName) throws StorageBackendException; // colName, rowKey, colName.
    
    public boolean putValue(String table, String rowKey, String colName, String value)  throws StorageBackendException; // colName, rowKey, colName, colValue
   
    //public void bulkUpload(String fpath, LineParser lineParser) throws StorageBackendException, InternalException;

    public String getProperty(String key);

}
