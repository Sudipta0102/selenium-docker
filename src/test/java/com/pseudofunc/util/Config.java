package com.pseudofunc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static final Logger log = LoggerFactory.getLogger(Config.class);
    public static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    /*
    1. Initialize will load the default properties.
    2. Also It will check for the command-line override, meaning in the default.properties
    file, browser is by default set as chrome. However, if somebody wants override that via command
    line, they can do that. For example, browser can be changed to firefox and grid run can be made true
    by the following command:
    mvn clean test -Dbrowser=firefox -Dselenium-grid-enabled=true
     */
    public static void initialize(){

        // 1. load default properties
        properties = loadProperties();
        // 2. check for any override
        // if overriding is done via command-line, we can know about it via System.getProperties("property")
        // first, we will loop through all the property keys defined in default.properties file, if this matches
        // something, then set that property.
        for (String key:properties.stringPropertyNames()){
            if (System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }
        // 3. print for debugging purpose
        log.info("Test Properties");
        log.info("------------------------------------");
        for(String key: properties.stringPropertyNames()){
            log.info("{}={}", key, properties.getProperty(key));
        }
        log.info("------------------------------------");
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();
        try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            properties.load(stream);
        }catch(Exception e){
            log.error("Unable to read the property file {}:  ", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }

}
