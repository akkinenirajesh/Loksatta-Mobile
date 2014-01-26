package org.loksatta.android.util;

public abstract class Request {

	private String url;

	public Request(String url) {
		this.url = url;
	}

	public abstract void onResponse(String result);

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

}
