package com.prgguru.jersey;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Path: http://localhost/<appln-folder-name>/urlupdate
@Path("/urlpublish")
public class URLPublish {
	ArrayList<UrlFreq> urlFreqList;
	ArrayList<UrlFreq> urlFreqListThresholded;
	int freqThreshold = 50;
	
	@GET
    // Produces JSON as response
    @Produces(MediaType.TEXT_HTML) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/urlupdate/doupdate?user_id=abc&urldata=xyz
    public String doPublish(){
        urlFreqList = DBConnection.selectUrl();
        return this.prepareUrlPublishHtml();        
    }
	/**
	 * Publish thresholded url list
	 * @return
	 */
	@GET
    // Produces JSON as response
	@Path("/thresholded")
    @Produces(MediaType.TEXT_HTML) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/urlupdate/doupdate?user_id=abc&urldata=xyz
    public String doPublishByThreshold(){
        urlFreqListThresholded = DBConnection.selectUrlByThreshold(freqThreshold);
        return this.prepareUrlPublishHtml();        
    }
	/**
	 * Prepares an html script that publishes the url-freq as a table 
	 * @return String
	 */
	public String prepareUrlPublishHtml() {
		String urlPublishHtml = "<table style=\"width:100%\">" + getTableTag("URL","FREQUENCY");
		for(UrlFreq urlFreq:urlFreqListThresholded) {
			urlPublishHtml += this.getTableTag(urlFreq.getUrl(), String.valueOf(urlFreq.getFreq()));
		}
		return urlPublishHtml;
	}
	
	public String prepareUrlPublishHtmlThresholded() {
		String urlPublishHtml = "<table style=\"width:100%\">" + getTableTag("URL","FREQUENCY");
		for(UrlFreq urlFreq:urlFreqListThresholded) {
			urlPublishHtml += this.getTableTag(urlFreq.getUrl(), String.valueOf(urlFreq.getFreq()));
		}
		return urlPublishHtml;
	}
	
	public String getTableTag(String s1, String s2) {
		return "<tr><th>" + s1 + "</th><th>" + s2 + "</th></tr>";
	}
}
