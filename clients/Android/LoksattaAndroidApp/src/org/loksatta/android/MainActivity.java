package org.loksatta.android;

import org.loksatta.android.feed.NewsFeedActivity;
import org.loksatta.android.leader.LeadersActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {
	
	//DEFAULT NEES FEED TAB
	private static final int DEFAULT_TAB = 0;

	private TabHost tabHost;
	private Resources resources;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		this.resources = getResources();
		this.tabHost = (TabHost) findViewById(R.id.tabhost);

		// News tab
		addTab("News", R.drawable.tab_news_feed, NewsFeedActivity.class);

		// Leaders tab
		addTab("Leaders", R.drawable.tab_leaders, LeadersActivity.class);

		// Donate tab
		addTab("Donate", R.drawable.tab_donate, DonateActivity.class);

		// Volunteer tab
		addTab("Volunteer", R.drawable.tab_volunteer, VolunteersActivity.class);

		// About tab
		addTab("About", R.drawable.tab_about, AboutActivity.class);

		// Gallery tab
		addTab("Gallery", R.drawable.tab_gallery, GalleryActivity.class);

		// set News tab as default (zero based)
		tabHost.setCurrentTab(DEFAULT_TAB);
	}

	private void addTab(String name, int drawableId, Class<?> target) {
		Intent intentAndroid = new Intent().setClass(this, target);
		TabSpec tab = tabHost.newTabSpec(name)
				.setIndicator("", resources.getDrawable(drawableId))
				.setContent(intentAndroid);
		tabHost.addTab(tab);
	}
	
	/**
	 * OnClick Of Top Banner
	 * 
	 * @param banner
	 */
	public void onBannerClick(View banner){
		tabHost.setCurrentTab(DEFAULT_TAB);
	}
	

	/**
	 * OnClick of 2014 Elections
	 * 
	 * @param btn
	 */
	public void onElections(View btn){
		//TODO Link to Election Agenda page (Link is TBD)
	}
	
}
