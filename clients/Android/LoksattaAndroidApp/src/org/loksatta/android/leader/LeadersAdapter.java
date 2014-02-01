package org.loksatta.android.leader;

import java.util.Collection;

import org.loksatta.android.R;
import org.loksatta.android.core.Leader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class LeadersAdapter extends ArrayAdapter<Leader> implements ListAdapter {

	public LeadersAdapter(Context context) {
		super(context, R.layout.leader_item);
	}

	public void init(Collection<Leader> items) {
		for (Leader item : items) {
			add(item);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Leader leader = getItem(position);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LeaderItem leaderView = null;
		if (convertView != null) {
			leaderView = (LeaderItem) convertView;
		} else {
			leaderView = (LeaderItem) inflater.inflate(R.layout.leader_item,
					null);
		}

		leaderView.loadData(leader);

		return leaderView;
	}
}
