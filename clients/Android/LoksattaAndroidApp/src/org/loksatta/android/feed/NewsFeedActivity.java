package org.loksatta.android.feed;

import java.util.List;

import org.loksatta.android.BaseActivity;
import org.loksatta.android.R;
import org.loksatta.android.VolunteersActivity;
import org.loksatta.android.core.Feed;
import org.loksatta.android.leader.LeadersActivity;
import org.loksatta.android.util.Callback;

import android.content.Intent;
import android.view.View;

/**
 * Activity for HomeScreen of the App
 * 
 * @author PrasannaKumar
 * 
 */
public class NewsFeedActivity extends BaseActivity implements
		Callback<List<Feed>> {

	private NewsFeedsLayout newsFeed;

	public NewsFeedActivity() {
		super(R.layout.activity_feed);
	}

	@Override
	protected void init() {
		this.newsFeed = (NewsFeedsLayout) findViewById(R.id.news_feed);
	}

	@Override
	protected void initData() {
		getDataProvider().getFeeds(this);
	}

	@Override
	public void onResponse(List<Feed> result) {
		newsFeed.showFeed(result);
	}

	public void onLeadersClick(View view) {
		// Open Leaders List
		Intent intent = new Intent(this, LeadersActivity.class);
		startActivity(intent);
	}

	public void onVolunteersClick(View view) {
		// Open Volunteers List
		Intent intent = new Intent(this, VolunteersActivity.class);
		startActivity(intent);
	}

	public void onGalleryClick(View view) {

	}

	public void onMoreClick(View view) {

	}

}
