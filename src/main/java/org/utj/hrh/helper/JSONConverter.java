package org.utj.hrh.helper;

import com.google.gson.Gson;

public class JSONConverter {
    private static final Gson gson = new Gson();

    public static String convert(Object object) {

        return gson.toJson(object);
    }
}
