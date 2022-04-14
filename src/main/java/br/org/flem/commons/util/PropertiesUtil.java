package br.org.flem.commons.util;

import java.io.IOException;
import java.util.Properties;


/**
 *
 * @author fcsilva
 */
public class PropertiesUtil {
    private static Properties properties;

    public static Properties getProperties() throws IOException {
        if(properties == null){
            properties = new Properties();
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        return properties;
    }

    public static String getProperty(String key){
        try{
            return getProperties().getProperty(key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
