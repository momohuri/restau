package models.dao;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.StorageBackendException;

public class StorageBackendImpl implements StorageBackend {

    private static Logger log = LoggerFactory
            .getLogger(StorageBackendImpl.class);
    
    private static DataStore dataStore;

    private static ObjectMapper mapper;

    private static final StorageBackendImpl storageBackend = new StorageBackendImpl();
    
    private StorageBackendImpl() {
        dataStore = new CassandraDataStore();
        mapper = new ObjectMapper();
    }
    
    public static StorageBackendImpl getInstance() {
        return storageBackend;
    }

    @Override
    public String getValue(String table, String rowKey, String colName) throws StorageBackendException {
        
        
        String input = "getValue() input: table=" + table + " ; rowKey=" +rowKey + " ; colName=" + colName;
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(rowKey) || StringUtils.isEmpty(colName)) {
            log.error("Null i/p arguments to getValue(). " + input);
            throw new StorageBackendException("Null i/p arguments to getValue(). " + input);
        }
        
        String response = null;
        try {
            log.info("Reading data for " + input);
            response = dataStore.getValue(table, rowKey, colName);
            // TODO : Cleanup need to handle more specific exceptions. or make
            // dataStore directly throw an exception.
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new StorageBackendException(e);
        }

        if (response == null) {
            // No data found in cassandra
            log.info("Response is NULL: No data for " + input);
            return null;
        }

        log.info("Response = " + response + " for " + input);
        return response;
    }
    
    @Override
    public String getCompositeValue(String table, String rowKey, String colName1, String colName2) throws StorageBackendException {
        
        
        String input = "getCompositeValue() input: table=" + table + " ; rowKey=" +rowKey + " ; colName1=" + colName1 + " ; colName2=" +colName2;
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(rowKey) || StringUtils.isEmpty(colName1) || StringUtils.isEmpty(colName2)) {
            log.error("Null i/p arguments to getCompositeValue(). " + input);
            throw new StorageBackendException("Null i/p arguments to getCompositeValue(). " + input);
        }
        
        String response = null;
        try {
            log.info("Reading data for " + input);
            response = dataStore.getCompositeValue(table, rowKey, colName1, colName2);
            // TODO : Cleanup need to handle more specific exceptions. or make
            // dataStore directly throw an exception.
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new StorageBackendException(e);
        }

        if (response == null) {
            // No data found in cassandra
            log.info("Response is NULL: No data for " + input);
            return null;
        }

        log.info("Response = " + response + " for " + input);
        return response;
    }

    @Override
    public boolean putValue(String table, String rowKey, String colName,
            String colValue) throws StorageBackendException {
        
        String input = "putValue() input: table=" + table + " ; rowKey=" +rowKey + " ; colName=" + colName + " ; colValue=" + colValue;
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(rowKey) || StringUtils.isEmpty(colName)) {
            log.error("Null i/p arguments to putValue(). " + input);
            throw new StorageBackendException("Null i/p arguments to putValue(). " + input);
        }
        
        boolean response = false;
        try {
            log.info("Writing data for " + input);
            response = dataStore.putValue(table, rowKey, colName, colValue);
            // TODO : Cleanup need to handle more specific exceptions. or make
            // dataStore directly throw an exception.
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new StorageBackendException(e);
        }   
        
        return response;
    }

    @Override
    public String getProperty(String key) {
        // TODO Auto-generated method stub
        return dataStore.getProperty(key);
    }

    @Override
    public Map<String, String> getAllColumnNameValue(String table,
            String rowKey) throws StorageBackendException {
        // TODO Auto-generated method stub
        
        String input = "getAllColumnNameValue() input: table=" + table + " ; rowKey=" +rowKey;
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(rowKey)) {
            log.error("Null i/p arguments to getAllColumnNameValue(). " + input);
            throw new StorageBackendException("Null i/p arguments to getAllColumnNameValue(). " + input);
        }
        
        Map<String, String> response = null;
        try {
            log.info("Reading data for " + input);
            response = dataStore.getAllColumnNameValue(table, rowKey);
            // TODO : Cleanup need to handle more specific exceptions. or make
            // dataStore directly throw an exception.
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new StorageBackendException(e);
        }

        if (response == null) {
            // No data found in cassandra
            log.info("Response is NULL: No data for " + input);
            return null;
        }

        log.info("Response = " + response + " for " + input);
        return response;
    }

    @Override
    public boolean putValue(String table, String rowKey,
            Map<String, JsonNode> compositeColumn)
            throws StorageBackendException {
        
        String input = "putValue compositeColumn() input: table=" + table + " ; rowKey=" +rowKey + " ; compositeColumn=" + compositeColumn.toString();
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(rowKey) || compositeColumn == null || compositeColumn.size() < 1 ) {
            log.error("Null i/p arguments to putValue(). " + input);
            throw new StorageBackendException("Null i/p arguments to putValue(). " + input);
        }
        
        boolean response = false;
        try {
            log.info("Writing data for " + input);
            response = dataStore.putValue(table, rowKey, compositeColumn);
            // TODO : Cleanup need to handle more specific exceptions. or make
            // dataStore directly throw an exception.
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new StorageBackendException(e);
        }   
        
        return response;
    }

    @Override
    public Map<String, ObjectNode> getAllCompositeValues(String table,
            String rowKey) throws StorageBackendException {
        String input = "getAllCompositeValues() input: table=" + table + " ; rowKey=" +rowKey;
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(rowKey)) {
            log.error("Null i/p arguments to getCompositeValues(). " + input);
            throw new StorageBackendException("Null i/p arguments to getCompositeValues(). " + input);
        }
        
        Map<String, ObjectNode> response = null;
        try {
            log.info("Reading data for " + input);
            response = dataStore.getAllCompositeValues(table, rowKey);
            // TODO : Cleanup need to handle more specific exceptions. or make
            // dataStore directly throw an exception.
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new StorageBackendException(e);
        }

        if (response == null) {
            // No data found in cassandra
            log.info("Response is NULL: No data for " + input);
            return null;
        }

        log.info("Response = " + response + " for " + input);
        return response;
    } 

    @Override
    public ObjectNode getCompositeValues(String table, String rowKey,
            String compositeName) throws StorageBackendException {
        String input = "getCompositeValues() input: table=" + table + " ; rowKey=" +rowKey;
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(rowKey)) {
            log.error("Null i/p arguments to getCompositeValues(). " + input);
            throw new StorageBackendException("Null i/p arguments to getCompositeValues(). " + input);
        }
        
        ObjectNode response = null;
        try {
            log.info("Reading data for " + input);
            response = dataStore.getCompositeValues(table, rowKey, compositeName);
            // TODO : Cleanup need to handle more specific exceptions. or make
            // dataStore directly throw an exception.
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new StorageBackendException(e);
        }

        if (response == null) {
            // No data found in cassandra
            log.info("Response is NULL: No data for " + input);
            return null;
        }

        log.info("Response = " + response + " for " + input);
        return response;
    }   
    

//    @Override
//    public void bulkUpload(String fpath, LineParser lineParser) throws StorageBackendException, InternalException {
//        // TODO Auto-generated method stub
//        dataStore.bulkUpload(fpath, lineParser);
//    }
//
    
//    @Override
//    public PGDF getQueryCacheResponse(String queryCacheKey,
//            String view) throws StorageBackendException, InternalException {
//        // TODO Auto-generated method stub
//        if (queryCacheKey == null || view == null) {
//            log.error("queryCacheKey/view is null. queryCacheKey = " + queryCacheKey + " , view = " + view);
//            return null;
//        }
//        
//        String response = null;
//        try {
//            log.info("Reading data for queryCacheKey : " + queryCacheKey + " view/ColName = " + view);
//            response = dataStore.getValue(CassandraDataStore.QUERY_CACHE_COL_FAMILY, queryCacheKey, view);
//        // TODO : Cleanup need to handle more specific exceptions. or make dataStore directly throw an exception.
//        } catch ( Exception e) {
//            log.error(e.toString());
//            e.printStackTrace();
//            throw new StorageBackendException(e);
//        }
//    
//        if (response == null) {
//            // No data found in cassandra
//            log.info("Response is NULL: No data found from data store");
//            return null;
//        }
//        
//        log.info("Response is NOT NULL. Data found in data store");
//        PGDF result = null;
//        try {
//            result = mapper.readValue(response, PGDF.class);
//        } catch (JsonParseException e) {
//            // TODO Auto-generated catch block
//            log.error("( alert Nathaniel :)! ) INTERNAL EXCEPTION: PGDF from Cassandra not in proper Json format");
//            log.error(e.toString());
//            e.printStackTrace();
//            throw new InternalException(e);
//        } catch (JsonMappingException e) {
//            // TODO Auto-generated catch block
//            log.error("( alert Nathaniel :)! ) INTERNAL EXCEPTION: PGDF from Cassandra not in proper Json format");
//            log.error(e.toString());
//            e.printStackTrace();
//            throw new InternalException(e);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            log.error("INTERNAL EXCEPTION: PGDF from Cassandra not in proper Json format");
//            log.error(e.toString());
//            e.printStackTrace();
//            throw new InternalException(e);
//        }
//        return result;
//    }
//
//    public Node getNode(String queryCacheKey,
//            String view) {
//        // TODO Auto-generated method stub
//        String response = dataStore.getValue(CassandraDataStore.QUERY_CACHE_COL_FAMILY, queryCacheKey, view);
//        Node result = null;
//        try {
//            result = mapper.readValue(response, Node.class);
//        } catch (JsonParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public com.walmart.productgenome.dataformat.PGDF getNode(String nodeId, Date d) throws StorageBackendException, InternalException {
//        
//        if (nodeId == null) {
//            log.error("nodeId is null.");
//            return null;
//        }
//        
//        String response = null;
//        try {
//            log.info("Reading data for NodeId : " + nodeId + " ColName = "
//                    + CassandraDataStore.NODE_PGDFJSON_COL_NAME);
//            if (d == null) {
//                response = dataStore.getValue(
//                        CassandraDataStore.NODE_COL_FAMILY, nodeId,
//                        CassandraDataStore.NODE_PGDFJSON_COL_NAME);
//                // TODO : Cleanup need to handle more specific exceptions. or
//                // make dataStore directly throw an exception.
//            } else {
//                log.info("Getting versioned copy for node at timestamp " + d.toString());
//                response = dataStore.getVersionValue(
//                        CassandraDataStore.NODE_VERSION_COL_FAMILY, nodeId, d,
//                        CassandraDataStore.NODE_PGDFJSON_COL_NAME);
//            }
//        } catch ( Exception e) {
//            log.error(e.toString());
//            e.printStackTrace();
//            throw new StorageBackendException(e);
//        }
//    
//        if (response == null) {
//            // No data found in cassandra
//            log.info("Response is NULL: No data found from data store");
//            return null;
//        }
//        
//        log.info("Response is NOT NULL. Data found in data store for NodeId : " + nodeId);
//        com.walmart.productgenome.dataformat.PGDF result = null;
// 
//        result = PgdfUtils.getObject(response, com.walmart.productgenome.dataformat.PGDF.class);
//        
//        return result;
//    }

}
