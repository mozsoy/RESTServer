package com.prgguru.jersey;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Path: http://localhost/<appln-folder-name>/urlupdate
@Path("/urlpublish")
public class URLPublish {
	@GET
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_XML) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/urlupdate/doupdate?user_id=abc&urldata=xyz
    public String doPublish(){
        ArrayList<UrlFreq> urlFreqList = DBConnection.selectUrl();
        return "<html> " + "<title>" + "Hello Jersey" + "</title>" +
        		"<body>" + urlFreqList.get(0).getUrl() + "</body>" + "</html> ";        
    }
	   
}
