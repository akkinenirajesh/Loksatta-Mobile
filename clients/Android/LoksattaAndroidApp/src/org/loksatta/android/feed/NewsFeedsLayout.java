package org.loksatta.android.feed;

import java.util.List;

import org.loksatta.android.R;
import org.loksatta.android.core.Feed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Contains all News feeds that can be Website,Facebook,Twitter or GooglePlus
 * 
 * Layout that shows all list of Feeds
 * 
 * @author PrasannaKumar
 * 
 */
public class NewsFeedsLayout extends LinearLayout {

	private ProgressBar pregressBar;

	public NewsFeedsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.pregressBar = (ProgressBar) findViewById(R.id.progressBar);
	}

	public void showFeed(List<Feed> feeds) {
		// Hide Loading
		pregressBar.setVisibility(View.GONE);
		// Add Feeds
		for (Feed f : feeds) {
			addFeed(f);
		}
	}

	public void addFeed(Feed f) {
		switch (f.getType()) {
		case FACE_BOOK:
			addFacebookFeed(f);
			break;
		case TWITTER:
			addTwitterFeed(f);
			break;
		case GOOGLE_PLUS:
			addGooglPlusFeed(f);
			break;
		default:
			addWebsiteFeed(f);
			break;
		}
	}

	/**
	 * Adds Website feed to the List
	 * 
	 * @param f
	 */
	private void addWebsiteFeed(Feed f) {
		// TODO Auto-generated method stub

	}

	/**
	 * Adds GooglePlus feed to the List
	 * 
	 * @param f
	 */
	private void addGooglPlusFeed(Feed f) {
		// TODO Auto-generated method stub

	}

	/**
	 * Adds Twitter Feed to the List
	 * 
	 * @param f
	 */
	private void addTwitterFeed(Feed f) {
		// TODO Auto-generated method stub

	}

	/**
	 * Adds FaceBook Feed to the List
	 * 
	 * @param f
	 */
	private void addFacebookFeed(Feed f) {
		// TODO Auto-generated method stub

	}

}
