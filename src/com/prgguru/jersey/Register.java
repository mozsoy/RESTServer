package com.prgguru.jersey;
 
import java.sql.SQLException;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//Path: http://localhost/<appln-folder-name>/register
@Path("/register")
public class Register {
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doregister")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
    public String doRegister(@QueryParam("name") String name, @QueryParam("key") String key){
        String response = "";
        boolean isAppName = (name.equals(Constants.app_name));
        boolean isKey = (key.equals(Constants.app_key));
        if(isAppName && isKey){
        	String uid = "sudebaby";
        	registerUser(uid);
            response = Utility.constructJSON("register", uid);
        }else if(!isAppName){
            response = Utility.constructJSON("register",false, "Wrong app name");
        }else if(!isKey){
            response = Utility.constructJSON("register",false, "Wrong app key");
        }
        return response;
    }
 
    private int registerUser(String uid){
        System.out.println("Inside checkCredentials");
        int result = 3;
        if(Utility.isNotNull(uid)){
            try {
                if(DBConnection.insertUser(uid)){
                    System.out.println("RegisterUser if");
                    result = 0;
                }
            } catch(SQLException sqle){
                System.out.println("RegisterUser catch sqle");
                //When Primary key violation occurs that means user is already registered
                if(sqle.getErrorCode() == 1062){
                    result = 1;
                } 
                //When special characters are used in uid
                else if(sqle.getErrorCode() == 1064){
                    System.out.println(sqle.getErrorCode());
                    result = 2;
                }
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside checkCredentials catch e ");
                result = 3;
            }
        }else{
            System.out.println("Inside checkCredentials else");
            result = 3;
        }
 
        return result;
    }
 
}
