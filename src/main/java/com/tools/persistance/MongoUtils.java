package com.tools.persistance;

import com.mongodb.DBObject;

public class MongoUtils {

    public static String checkField(DBObject currentObject, String fieldKey) {
        String result = "";
        if (currentObject.containsField(fieldKey) && currentObject.get(fieldKey) != null) {
            result = currentObject.get(fieldKey).toString();
        }
        if(String.valueOf(result).contentEquals("null"))
        	result = "";
        
        return result;
    }
}
