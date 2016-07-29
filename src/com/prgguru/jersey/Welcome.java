package com.prgguru.jersey;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// Welcome Page 
@Path("/")
public class Welcome {
	
	  // Welcome page html
	  private static final String welcomeHtml = "<html><title>Automated AdBlocking</title>" +
			  "<body style=\"background.jpg\"" +
			  "<h1>This work is a part of the Master's Thesis of Metehan Ozsoy.</h1>"+
			  "<p><a href=\"http://adlist.herokuapp.com/urlpublish\">Url List</a></p>"+
			  "</body></html>";		  
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
		  
	    return welcomeHtml;
	  }
			  
	 // This method is called if TEXT_PLAIN is request
	 /* @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
		  String welcomeMessage = System.getenv("JDBC_DATABASE_URL");
	    return welcomeMessage;
	  }
*/
	/*
	  // This method is called if XML is request
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	  }
*/
	  /*
	  // This method is called if HTML is request
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
		  String welcomeMessage = System.getenv("JDBC_DATABASE_URL");
		    
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + welcomeMessage + "</body></h1>" + "</html> ";
	  }
	 */
}
