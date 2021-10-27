package com.inhouse.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {
    private static Map<String, String> props = null;
    
    public static String getProp(String name) throws IOException {
        if(props != null){
            return props.get(name);
        } else {
            props = new HashMap<String, String>();
            Properties pps = new Properties();
            pps.load(Config.class.getClassLoader().getResourceAsStream("prop.properties"));
            Enumeration<?> enum1 = pps.propertyNames();
            while (enum1.hasMoreElements()) {
                String strKey = (String) enum1.nextElement();
                String strValue = pps.getProperty(strKey);
                props.put(strKey, strValue);
            }
            return props.get(name);
        }
    }
}
