package org.talkspace.automation.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class FileUtil {

    private final static Logger Log = Logger.getLogger(FileUtil.class.getName());

    public static Properties createPropertiesFromResource(Class clazz, String relativePath){
        try(InputStream ips = clazz.getClassLoader().getResourceAsStream(relativePath)){
            Properties prop = new Properties();
            prop.load(ips);
            return prop;
        }catch (IOException e){
            System.err.printf("Failed to convert resource %s + stream to properties, cause: %s%n", relativePath, e.getMessage());
            return null;
        }
    }

    private FileUtil(){}

    public static String getFile(String path){
        return new File(System.getProperty("user.dir")).getParent() + path;
    }

    public static void createFolder(File folder , boolean recursive){
        if(folder.exists() && folder.isDirectory()){
            Log.info(folder.getName() + " directory already exist");
        }else if((recursive ? folder.mkdirs() : folder.mkdir())){
            Log.info(folder.getName() + " directory created successfully");
        }else{
            Log.error("failed to create '" + folder.getName() + "' directory");
        }
    }
}

