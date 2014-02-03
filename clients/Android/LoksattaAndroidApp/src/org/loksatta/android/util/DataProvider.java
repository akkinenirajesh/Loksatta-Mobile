package org.loksatta.android.util;

import java.util.ArrayList;
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

	private static final boolean TEST_DATE = true;

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
		if (TEST_DATE) {
			callback.onResponse(getSampleFeed());
			return;
		}
		c.makeRequest(new Request(UrlUtility.FEEDS) {

			@Override
			public void onResponse(String result) {
				// Feed[] fromJson = gson.fromJson(result, Feed[].class);
				// callback.onResponse(Arrays.asList(fromJson));
			}

			@Override
			public void onFailure(Exception e) {

			}
		});
	}

	public ArrayList<Feed> getSampleFeed() {
		Feed f = new Feed();
		f.setTitle("Dr.JP's Letter to The President of India on AP Reorganization Bill");
		f.setSummary("30th January, 2014 To Shri Pranab Mukherjee Honourable President of India Rashtrapati Bhavan, New Delhi –"
				+ " 110004 Esteemed Rashtrapati Shri Pranab Mukherjee ji, The Andhra Pradesh Legislative Assembly has,"
				+ " on January 30, 2014, adopted a resolution opposing the Andhra Pradesh...");

		Feed f2 = new Feed();
		f2.setTitle("Lok Satta organizing satyagraha by women victims of liquor");
		f2.setSummary("The Lok Satta Party is organizing a satyagraha by women victims of the liquor menace from 10 am to 2 pm on Saturday, February 1 at Picket Grounds be...");
		ArrayList<Feed> list = new ArrayList<Feed>();
		list.add(f);
		list.add(f2);
		return list;
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
