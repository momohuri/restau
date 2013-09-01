package models.dao;


public abstract class DataStore {
    
    public abstract String getValue(String table, String rowKey, String colName);   // colName, rowKey, colName.
    
    public abstract boolean putValue(String table, String rowKey, String colName, String value); // colName, rowKey, colName, colValue

    //public abstract void bulkUpload(String fpath, LineParser lineParser);

    public abstract String getProperty(String key);

}

