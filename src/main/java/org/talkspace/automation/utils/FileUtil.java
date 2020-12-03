package org.talkspace.automation.utils;

import org.apache.log4j.Logger;

import java.io.File;

public final class FileUtil {

    private final static Logger Log = Logger.getLogger(FileUtil.class.getName());

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

