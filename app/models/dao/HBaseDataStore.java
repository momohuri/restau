package models.dao;

import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;


public class HBaseDataStore extends DataStore {

    @Override
    public String getValue(String table, String rowKey, String colName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean putValue(String table, String rowKey, String colName,
            String value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getProperty(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, String> getAllColumnNameValue(String table, String rowKey) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean putValue(String table, String rowKey,
            Map<String, JsonNode> compositeColumn) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Map<String, ObjectNode> getAllCompositeValues(String columnFamily,
            String rowKey) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectNode getCompositeValues(String columnFamily, String rowKey,
            String compositeName) {
        // TODO Auto-generated method stub
        return null;
    }

}
