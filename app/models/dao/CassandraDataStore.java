package models.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import me.prettyprint.cassandra.model.ConfigurableConsistencyLevel;
import me.prettyprint.cassandra.serializers.CompositeSerializer;
import me.prettyprint.cassandra.serializers.DateSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ColumnFamilyUpdater;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.HConsistencyLevel;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.ResultStatus;
import me.prettyprint.hector.api.beans.AbstractComposite.ComponentEquality;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import conf.ApplicationConfig;

public class CassandraDataStore extends DataStore {

    private static Logger log = LoggerFactory
            .getLogger(CassandraDataStore.class);

    protected Cluster storageCluster;
    protected Keyspace storageKeyspace;


    protected Properties properties;
    private static String configFilename = System.getProperty("com.mycompany.myproduct.storage.config","storage.conf");


    protected void init() {
        
        properties = new Properties();
//        try {
//            properties.load(CassandraDataStore.class.getResourceAsStream("/"+configFilename.trim()));
//        } catch (IOException e) {
//            log.error("FATAL:: Cant load storage.conf")
//            log.error(e.getMessage(),e);
//        }
        
        // To modify the default ConsistencyLevel of QUORUM, create a
        // me.prettyprint.hector.api.ConsistencyLevelPolicy and use the
        // overloaded form:
        // tutorialKeyspace = HFactory.createKeyspace("Tutorial",
        // tutorialCluster, consistencyLevelPolicy);
        // see also
        // me.prettyprint.cassandra.model.ConfigurableConsistencyLevelPolicy[Test]
        // for details
        
        String clusterName = ApplicationConfig.getCassadraClusterName(); // properties.getProperty("cassandra.cluster.name", "Test Cluster"); 
        String clusterHosts = ApplicationConfig.getCassadraClusterHosts(); // properties.getProperty("cassandra.cluster.hosts", "127.0.0.1:9160");
        String keyspace =  ApplicationConfig.getCassadraKeyspace(); // properties.getProperty("cassandra.keyspace", "ProductGenomeDev");
        log.info("Storage config :: clusterName = " + clusterName + ", clusterHosts = " + clusterHosts + ", keyspace = " + keyspace);


        storageCluster = HFactory.getOrCreateCluster(clusterName, clusterHosts);
        
        ConfigurableConsistencyLevel ccl = new ConfigurableConsistencyLevel();
        ccl.setDefaultReadConsistencyLevel(HConsistencyLevel.ONE);
        
        storageKeyspace = HFactory.createKeyspace(keyspace, storageCluster,ccl);
    }

    public CassandraDataStore() {
        init();
    }

    @Override
    public String getValue(String columnFamily, String rowKey, String colName) {
        // TODO Auto-generated method stub
        ColumnQuery<String, String, String> columnQuery = HFactory
                .createStringColumnQuery(storageKeyspace);
        columnQuery.setColumnFamily(columnFamily);
        columnQuery.setKey(rowKey);
        columnQuery.setName(colName);
        QueryResult<HColumn<String, String>> result = columnQuery.execute();
        if (result != null && result.get() != null) {
            return result.get().getValue();
        } else
            return null;
    }
    
    // NOT BEING USED.
    public String getVersionValue(String table, String rowKey, Date d,
            String colName) {
        // TODO Auto-generated method stub
        String res = null;

        SliceQuery<String, Composite, String> sliceQuery = HFactory
                .createSliceQuery(storageKeyspace,
                        StringSerializer.get(), new CompositeSerializer(),
                        StringSerializer.get());
        sliceQuery.setColumnFamily(table);
        sliceQuery.setKey(rowKey);

        Composite startRange = new Composite();
        startRange.addComponent(0, d, ComponentEquality.EQUAL);

        Composite endRange = new Composite();
        endRange.addComponent(0, new Date(0L),
                ComponentEquality.GREATER_THAN_EQUAL);

        sliceQuery.setRange(startRange, endRange, false, 1);

        QueryResult<ColumnSlice<Composite, String>> result = sliceQuery
                .execute();
        ColumnSlice<Composite, String> cs = result.get();
        for (HColumn<Composite, String> col : cs.getColumns()) {

            Date changeDate = col.getName().get(0, DateSerializer.get());
            log.info("Querying NodeVersion for " + d.toString()
                    + ", Got data at time " + changeDate.toString());
            res = col.getValue();
            break;
        }

        return res;

    }

    // NOT BEING USED.
    @SuppressWarnings("unchecked")
    private static void printResults(ResultStatus result) {
        log.info("+-------------------------------------------------");
        log.info("| Result executed in: {} microseconds against host: {}",
                result.getExecutionTimeMicro(), result.getHostUsed().getName());
        log.info("+-------------------------------------------------");
        // nicer display of Rows vs. HColumn or ColumnSlice
        if (result instanceof QueryResult) {
            System.out.println(((QueryResult) result).get());
            QueryResult<?> qr = (QueryResult) result;
            if (qr.get() instanceof Rows) {

                Rows<?, ?, ?> rows = (Rows) qr.get();

                for (Row row : rows) {
                    log.info("| Row key: {}", row.getKey());
                    for (Iterator iter = row.getColumnSlice().getColumns()
                            .iterator(); iter.hasNext();) {
                        log.info("|   col: {}", iter.next());
                    }

                }
            } else if (qr.get() instanceof ColumnSlice) {
                for (Iterator iter = ((ColumnSlice) qr.get()).getColumns()
                        .iterator(); iter.hasNext();) {
                    log.info("|   col: {}", iter.next());
                }
            } else {
                log.info("| Result: {}", qr.get());
            }
        }
        log.info("+-------------------------------------------------");
    }

    @Override
    public boolean putValue(String colFam, String rowKey, String colName, String value) {
    
//      if (StringUtils.isEmpty(colFam) == true)
//          throw new RuntimeException("colFam must not be null");

        ColumnFamilyTemplate<String, String> template = new ThriftColumnFamilyTemplate<String, String>(storageKeyspace, colFam, StringSerializer.get(), StringSerializer.get());

        ColumnFamilyUpdater<String, String> updater = template.createUpdater(rowKey);
        updater.setString(colName, value);
        
        template.update(updater);

        return true;
    }

    @Override
    public String getProperty(String key) {
        if (key == null) {
            return null;
        } else {
            return properties.getProperty(key.trim());
        }
    }

    /*
     * (non-Javadoc) Be nice : close connections before CassandraDataStore is GC'ed
     * @see java.lang.Object#finalize()
     */
    @Override
    public void finalize() {
        if (storageCluster != null) {
            if (storageCluster.getConnectionManager() != null) {
                storageCluster.getConnectionManager().shutdown();
            }
        }
    }


}
