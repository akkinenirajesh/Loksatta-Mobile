package org.loksatta.android.leader;

import org.loksatta.android.BaseActivity;
import org.loksatta.android.R;
import org.loksatta.android.core.Leader;
import org.loksatta.android.util.Utility;

public class LeaderDetails extends BaseActivity {

	public LeaderDetails() {
		super(R.layout.leader_details);
	}

	@Override
	protected void init() {
	}

	@Override
	protected void initData() {
		Leader leader = (Leader) getIntent().getExtras().get(Utility.LEADER);
	}
}
