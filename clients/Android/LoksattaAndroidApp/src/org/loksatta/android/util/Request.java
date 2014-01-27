package org.loksatta.android.util;

import java.util.HashMap;

public abstract class Request {

	private String url;

	private HashMap<String, String> params = new HashMap<String, String>();

	public Request(String url) {
		this.url = url;
	}

	/**
	 * Adds Request Parameter
	 * 
	 * @param name
	 * @param val
	 */
	public void addParam(String name, String val) {
		params.put(name, val);
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public abstract void onResponse(String result);

	public abstract void onFailure(Exception e);

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

}
