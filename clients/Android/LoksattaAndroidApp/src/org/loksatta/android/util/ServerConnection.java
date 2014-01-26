package org.loksatta.android.util;

/**
 * Connection to the server
 * 
 * @author PrasannaKumar
 * 
 */
public class ServerConnection {

	private String baseUrl;

	private boolean isConnected;

	public ServerConnection(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void connect() {

	}

	public void makeRequest(Request req) {
		makeRequestInternal(req, baseUrl + req);
	}

	private void makeRequestInternal(Request req, String string) {

	}

}
