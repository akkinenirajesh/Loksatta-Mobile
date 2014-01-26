package org.loksatta.android.util;

import java.util.ArrayList;
import java.util.Date;

import org.loksatta.android.core.Feed;

/**
 * Provides Data like Feeds, Leaders, Events etc.
 * 
 */
public class DataProvider {

	private static DataProvider instance;
	private ServerConnection c;

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
	}

	public void getFeeds(Callback<ArrayList<Feed>> callback) {
		getFeeds(null, callback);
	}

	public void getFeeds(Date since, Callback<ArrayList<Feed>> callback) {

	}

}
