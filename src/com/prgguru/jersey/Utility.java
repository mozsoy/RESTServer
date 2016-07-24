package com.prgguru.jersey;
 
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
 
public class Utility {
    /**
     * Null check Method
     * 
     * @param txt
     * @return
     */
    public static boolean isNotNull(String txt) {
        // System.out.println("Inside isNotNull");
        return txt != null && txt.trim().length() >= 0 ? true : false;
    }
 
    /**
     * Method to construct JSON
     * 
     * @param tag
     * @param status
     * @return
     */
    public static String constructJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
    
    /**
     * Method to construct JSON
     * 
     * @param tag
     * @param status
     * @return
     */
    public static String constructJSON(String tag, String uid) {
        JSONObject obj = new JSONObject();
        try {
        	obj.put("tag", tag);
            obj.put("uid", uid);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
 
    /**
     * Method to construct JSON with Error Msg
     * 
     * @param tag
     * @param status
     * @param err_msg
     * @return
     */
    public static String constructJSON(String tag, boolean status,String err_msg) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", err_msg);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
    
    /**
     * Method to parse HashMap.toString() back to HashMap
     * 
     * @param mapStr
     * @return map
     */
    public static HashMap<String, Long> toHashMap(String mapStr) {
    	HashMap<String, Long> map = new HashMap<String, Long>();
    	// Remove '{','}' at the beginning and at the end 
    	mapStr = mapStr.substring(1,mapStr.length()-1);
    	for(String keyValue : mapStr.split(" *, *")) {
    	   String[] pairs = keyValue.split(" *= *");
    	   map.put(pairs[0].replaceAll("www.",""), Long.valueOf(pairs[1]));
    	}
    	return map;
    } 
    
    // Return url as a string without the http and www parts
    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        if(domain == null) {
        	return url.replaceAll("www.","");
        }        
        return domain.replaceAll("www.","");
        //return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
