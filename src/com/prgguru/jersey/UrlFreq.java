package com.prgguru.jersey;

public class UrlFreq {
	private String url;
	private long freq;
	
	public UrlFreq(String url,long freq) {
		this.url = url;
		this.freq = freq;
	}
	
	public String getUrl() {
		return url;
	}
	public long getFreq() {
		return freq;
	}
}
