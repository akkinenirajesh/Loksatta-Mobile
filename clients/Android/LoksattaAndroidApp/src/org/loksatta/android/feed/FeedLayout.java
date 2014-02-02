package org.loksatta.android.feed;

import org.loksatta.android.R;
import org.loksatta.android.core.Feed;
import org.loksatta.android.util.Utility;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Layout that shows Single Feed
 * 
 * @author PrasannaKumar
 * 
 */
public class FeedLayout extends LinearLayout implements OnClickListener {

	private Feed feed;

	public FeedLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	public void setFeed(Feed f) {
		this.feed = f;
		// Set Title
		TextView title = (TextView) findViewById(R.id.feed_title);
		title.setText(f.getTitle());

		// Set Description
		TextView desc = (TextView) findViewById(R.id.feed_desc);
		desc.setText(f.getSummary());
	}

	@Override
	public void onClick(View v) {
		// Open Feed Details on Click of this View
		Intent intent = new Intent(getContext(), FeedDetailsActivity.class);
		intent.putExtra(Utility.NEWS_FEED, feed);
		getContext().startActivity(intent);
	}

}
