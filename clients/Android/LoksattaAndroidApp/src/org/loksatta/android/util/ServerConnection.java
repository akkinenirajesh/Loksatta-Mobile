package org.loksatta.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * Connection to the server which makes request to the server
 * 
 * @author PrasannaKumar
 * 
 */
public class ServerConnection {

	private String baseUrl;

	private boolean isConnected;

	private DefaultHttpClient client;

	public ServerConnection(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * Initializes the Connection
	 */
	public void connect() {
		isConnected = true;
		this.client = new DefaultHttpClient();
	}

	/**
	 * Makes Request to the Server
	 * 
	 * @param req
	 */
	public void makeRequest(Request req) {
		makeRequestInternal(req);
	}

	private void makeRequestInternal(Request req) {
		if (!isConnected) {
			req.onFailure(new IllegalStateException(
					"Connection not initialized yet."));
			return;
		}
		// Prepare Http Request
		String url = papareUrl(req);
		HttpGet httpReq = new HttpGet(url);

		try {
			// Execute Request
			HttpResponse response = client.execute(httpReq);
			if (response == null) {
				req.onFailure(new IllegalArgumentException("Bad Request."));
				return;
			}
			// Read Response
			String content = readResponse(response);
			req.onResponse(content);
		} catch (Exception e) {
			req.onFailure(e);
		}
	}

	/**
	 * Prepares URL by adding requested params to url
	 * 
	 * @param req
	 * @return
	 */
	private String papareUrl(Request req) {
		String url = req.getUrl();
		url = baseUrl + url;

		// Prepare Params for Request
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Entry<String, String> param : req.getParams().entrySet()) {
			params.add(new BasicNameValuePair(param.getKey(), param.getValue()));
		}

		if (!params.isEmpty()) {
			// Add Params to Request
			String paramString = URLEncodedUtils.format(params, "utf-8");
			if (!url.endsWith("?")) {
				url += "?";
			}
			url += paramString;
		}

		return url;
	}

	/**
	 * Reads server Response
	 * 
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String readResponse(HttpResponse response)
			throws IllegalStateException, IOException {
		InputStream is = response.getEntity().getContent();
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}
		return total.toString();
	}
}
