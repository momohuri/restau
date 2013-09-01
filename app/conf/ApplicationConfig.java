package conf;

import org.codehaus.jackson.map.ObjectMapper;

import play.Configuration;
import play.Play;

public class ApplicationConfig {

    private static Configuration config;
    
    private static ObjectMapper mapper = new ObjectMapper();

    
    private synchronized static Configuration getSyncronizedConfig() {
        if(config != null) { return config; }
        config = Play.application().configuration();
        return config;
    }
    
    private static Configuration getConfig() {
        if(config != null)
            return config;
        else
            return getSyncronizedConfig();
    }
    
    public static String getCassadraClusterName() {
        Configuration c = getConfig();
        return c.getString("cassadra.cluster.name");
    }
    
    public static String getCassadraClusterHosts() {
        Configuration c = getConfig();
        return c.getString("cassadra.cluster.hosts");
    }
    
    public static String getCassadraKeyspace() {
        return getConfig().getString("cassadra.keyspace");
    }
    
    // TODO : FIND BETTER PLACE FOR THIS METHOD. THIS DOESNT BELONG HERE
    public static ObjectMapper getMapper() {
        return mapper;
    }
    
}
