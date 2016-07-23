package com.prgguru.jersey;

public class UrlFreq {
	String url;
	long freq;
	
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
