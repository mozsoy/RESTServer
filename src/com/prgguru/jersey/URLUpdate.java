package com.prgguru.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
//Path: http://localhost/<appln-folder-name>/urlupdate
@Path("/urlupdate")
public class URLUpdate {
	@GET
    // Path: http://localhost/<appln-folder-name>/urlupdate/doupdate
    @Path("/doupdate")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/urlupdate/doupdate?user_id=abc&urldata=xyz
    public String doUpdate(@QueryParam("user_id") String uid, @QueryParam("urldata") String urldata){
        String response = "";
        if(checkCredentials(uid)){
        	System.out.println(urldata);
        	if(updateUrl(urldata) == 0){
        		response = Utility.constructJSON("update",true);
        	} else {
        		response = Utility.constructJSON("update", false, "bad url map");
        	}
        }else{
            response = Utility.constructJSON("update", false, "not logged in or not registered");
        }
    return response;        
    }
	
	 /**
     * Method to check whether the entered credential is valid
     * 
     * @param uid
     * @return
     */
    private boolean checkCredentials(String uid){
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if(uid != null){
            try {
                result = DBConnection.checkLogin(uid);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //System.out.println("Inside checkCredentials catch");
                result = false;
            }
        }else{
            //System.out.println("Inside checkCredentials else");
            result = false;
        }
        return result;
    }
    
    private int updateUrl(String urlStr) {
    	int result = -1;
    	try {
			JSONObject jUrlData = new JSONObject(urlStr);
			if(DBConnection.insertUrl(jUrlData)) {
				System.out.println("Updated url table");
				result = 0;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("JSON error");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
}
