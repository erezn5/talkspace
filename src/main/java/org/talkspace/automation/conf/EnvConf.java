package org.talkspace.automation.conf;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Map;

public class EnvConf {

    private static Configuration properties;

    static {
        load();
    }

    private EnvConf() {}

    private static void load() {
        Configurations configs = new Configurations();
        try {
            properties = configs.properties(getEnvPropFilePath());
            for(Map.Entry p : System.getProperties().entrySet()){
                String key = (String)p.getKey();
                properties.setProperty(key , p.getValue());
            }
        } catch (ConfigurationException e) {
            System.err.println("failed to load '" + getEnvPropFilePath() + "' resource file");
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){
        return properties.getString(key);
    }

    public static boolean getAsBoolean(String key){
        return Boolean.parseBoolean(getProperty(key));
    }

    private static String getEnvPropFilePath(){
        String envPath = System.getProperty("env.conf");
        envPath = (envPath == null) ? "env/env.properties" : envPath;
        return envPath;
    }

}
