package org.loksatta.android.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.loksatta.android.core.Feed;
import org.loksatta.android.core.Leader;
import org.loksatta.android.io.FeedDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Provides Data like Feeds, Leaders, Events etc.
 * 
 * @author PrasannaKumar
 * 
 */
public class DataProvider {

	private static DataProvider instance;
	private ServerConnection c;
	private Gson gson;

	public static DataProvider getInstance() {
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
		this.c = new ServerConnection(UrlUtility.BASE_URL);

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Feed.class, new FeedDeserializer());
		this.gson = builder.create();
	}

	/**
	 * Get Feed since default(2months)
	 * 
	 * @param callback
	 */
	public void getFeeds(Callback<List<Feed>> callback) {
		Date defaultSince = Utility.getDefaultFeedSince();
		getFeeds(defaultSince, callback);
	}

	/**
	 * Get News Feed since given date
	 * 
	 * @param since
	 * @param callback
	 */
	public void getFeeds(Date since, final Callback<List<Feed>> callback) {
		c.makeRequest(new Request(UrlUtility.FEEDS) {

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

	/**
	 * Get Leaders from Server with given filters
	 * 
	 * @param callback
	 * @param state
	 * @param district
	 * @param constituency
	 */
	public void getLeaders(Callback<List<Leader>> callback, String state,
			String district, String constituency) {
		String url = UrlUtility.leaderURL(state, district, constituency);
		c.makeRequest(new Request(url) {

			@Override
			public void onResponse(String result) {
			}

			@Override
			public void onFailure(Exception e) {

			}
		});
	}
}
