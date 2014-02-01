package org.loksatta.android.leader;

import org.loksatta.android.R;
import org.loksatta.android.core.Leader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LeaderItem extends LinearLayout {

	public LeaderItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void loadData(Leader leader) {
		// Set Name
		TextView name = (TextView) findViewById(R.id.leader_name);
		name.setText(leader.getName());
	}

}
