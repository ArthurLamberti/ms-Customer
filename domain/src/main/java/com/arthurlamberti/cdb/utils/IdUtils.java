package com.arthurlamberti.cdb.utils;



import java.util.UUID;

public class IdUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }

    private IdUtils(){

    }
}
