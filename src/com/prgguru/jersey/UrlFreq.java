package com.prgguru.jersey;

public class UrlFreq {
	private String url;
	private long freq;
	private String timestamp = "";
	
	public UrlFreq(String url,long freq) {
		this.url = url;
		this.freq = freq;
	}
	
	public UrlFreq(String url,long freq,String timestamp) {
		this.url = url;
		this.freq = freq;
		this.timestamp = timestamp;
	}
	
	public String getUrl() {
		return url;
	}
	public long getFreq() {
		return freq;
	}
	public String getTimestamp() {
		return timestamp;
	}
}
