package org.loksatta.android.feed;

import java.util.Collection;

import org.loksatta.android.R;
import org.loksatta.android.core.Feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class FeedAdaper extends ArrayAdapter<Feed> {

	public FeedAdaper(Context context) {
		super(context, R.layout.feed_layout);
	}

	public void init(Collection<Feed> items) {
		for (Feed item : items) {
			add(item);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Feed feed = getItem(position);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		FeedLayout feedLayout = null;
		if (convertView != null) {
			feedLayout = (FeedLayout) convertView;
		} else {
			feedLayout = (FeedLayout) inflater.inflate(R.layout.feed_layout,
					null);
		}

		feedLayout.setFeed(feed);

		return feedLayout;
	}

}
