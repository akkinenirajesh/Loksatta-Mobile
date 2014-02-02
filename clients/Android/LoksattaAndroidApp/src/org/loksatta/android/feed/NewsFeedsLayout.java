package org.loksatta.android.feed;

import java.util.List;

import org.loksatta.android.R;
import org.loksatta.android.core.Feed;

import android.app.Activity;
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

	/**
	 * Adds Feed to the List View
	 * 
	 * @param f
	 */
	public void addFeed(Feed f) {
		Activity activity = (Activity) getContext();
		FeedLayout feedLayout = (FeedLayout) activity.getLayoutInflater()
				.inflate(R.layout.feed_layout, null);
		feedLayout.setFeed(f);

		this.addView(feedLayout);
	}

}
