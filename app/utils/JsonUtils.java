package utils;


    import java.io.File;
import java.io.IOException;

import models.dao.CassandraDataStore;

    import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


    public class JsonUtils {
        
        private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
        
        private static final ObjectMapper mapper = new ObjectMapper();
     
        static {
            mapper.setAnnotationIntrospector(AnnotationIntrospector.pair(new JaxbAnnotationIntrospector(), new JacksonAnnotationIntrospector()));
            mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(Inclusion.NON_NULL);
        }
        

        public static <T> T getObject(String json, Class<T> className) {
            // TODO Auto-generated method stub
            T obj = null;
            try {
                obj = getObjectWithException(json, className);
                
                // TODO :: log the exceptions.
            } catch (JsonParseException e) {
                log.error(e.getMessage(), e);
            } catch (JsonMappingException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
            return obj;
        }
        
        public static <T> T getObject(JsonNode jsonNode, Class<T> className) {
            // TODO Auto-generated method stub
            T obj = null;
            try {
                obj = getObjectWithException(jsonNode, className);
                
                // TODO :: log the exceptions.
            } catch (JsonParseException e) {
                log.error(e.getMessage(), e);
            } catch (JsonMappingException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
            return obj;
        }

        public static <T> T getObjectFromFile(String fname, Class<T> className) {
            // TODO Auto-generated method stub
            T obj = null;
            try {
                obj = mapper.readValue(new File(fname), className);
                // TODO :: log the exceptions.
            } catch (JsonParseException e) {
                log.error(e.getMessage(), e);
            } catch (JsonMappingException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } 
            return obj;
        }


        public static <T> T getObjectWithException(String json, Class<T> className)
                throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
            T obj = (T)mapper.readValue(json, className);
            return obj;
        }


        public static <T> T getObjectWithException(JsonNode jsonNode, Class<T> className)
                throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
            T obj = (T)mapper.readValue(jsonNode, className);
            return obj;
        }

        
        public static <T> JsonNode getJson(T pgdf) {
            JsonNode jsonResponse = null;
            try {
                
                jsonResponse = getJsonWithException(pgdf);
                
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage(), e);
            }
            return jsonResponse;
        }

        public static <T> JsonNode getJsonWithException(T obj)
                throws IllegalArgumentException {
            // TODO Auto-generated method stub
            JsonNode jsonResponse = mapper.valueToTree(obj);
            return jsonResponse;
        }
        
        public static <T> void writeOjectToFile(T obj, final String fname) {
            try {
                
                mapper.writeValue(new File(fname), obj);
                
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage(), e);
            } catch (JsonGenerationException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage(), e);
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage(), e);
            }
            
        }

    }
