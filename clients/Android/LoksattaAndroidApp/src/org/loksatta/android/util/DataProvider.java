package org.loksatta.android.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.loksatta.android.core.Feed;
import org.loksatta.android.io.FeedDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Provides Data like Feeds, Leaders, Events etc.
 * 
 */
public class DataProvider {

	private static DataProvider instance;
	private ServerConnection c;
	private Gson gson;

	public DataProvider getInstance() {
		if (instance == null) {
			instance = new DataProvider();
		}
		return instance;
	}

	private DataProvider() {
		init();
	}

	/**
	 * Initializes the Data Provider
	 */
	public void init() {
		// Initializes Connection
		this.c = new ServerConnection(Utility.BASE_URL);

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Feed.class, new FeedDeserializer());
		this.gson = builder.create();
	}

	public void getFeeds(Callback<List<Feed>> callback) {
		getFeeds(null, callback);
	}

	public void getFeeds(Date since, final Callback<List<Feed>> callback) {
		c.makeRequest(new Request(Utility.FEEDS) {

			@Override
			public void onResponse(String result) {
				Feed[] fromJson = gson.fromJson(result, Feed[].class);
				callback.onResponse(Arrays.asList(fromJson));
			}

			@Override
			public void onFailure(Exception e) {

			}
		});
	}
}
