package org.talkspace.automation.utils;

import java.io.File;

public final class FileUtil {

    private FileUtil(){}

    public static String getFile(String path){
        return new File(System.getProperty("user.dir")).getParent() + path;
    }
}

