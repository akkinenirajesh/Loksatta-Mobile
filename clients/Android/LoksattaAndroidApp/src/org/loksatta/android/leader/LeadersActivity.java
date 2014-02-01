package org.loksatta.android.leader;

import java.util.ArrayList;
import java.util.List;

import org.loksatta.android.BaseActivity;
import org.loksatta.android.R;
import org.loksatta.android.core.Leader;
import org.loksatta.android.util.Callback;
import org.loksatta.android.util.Utility;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

public class LeadersActivity extends BaseActivity implements
		Callback<List<Leader>>, OnItemSelectedListener {

	private Spinner states;
	private Spinner districts;
	private Spinner constituencies;
	private GridView leadersList;

	public LeadersActivity() {
		super(R.layout.activity_leaders);
	}

	@Override
	protected void init() {
		// Init States Spinner
		this.states = (Spinner) findViewById(R.id.states);
		states.setOnItemSelectedListener(this);

		// Init District Spinner
		this.districts = (Spinner) findViewById(R.id.districts);
		districts.setOnItemSelectedListener(this);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districts.setAdapter(dataAdapter);

		// Init Constituency Spinner
		this.constituencies = (Spinner) findViewById(R.id.constituencies);
		constituencies.setOnItemSelectedListener(this);
		dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districts.setAdapter(dataAdapter);

		// Leaders List
		this.leadersList = (GridView) findViewById(R.id.leaders_grid);
		leadersList.setOnItemSelectedListener(this);
		leadersList.setAdapter(new LeadersAdapter(this));
	}

	protected void initData() {

	}

	/**
	 * Initializes Leaders List
	 */
	private void getLeaders() {
		String state = getState();
		String district = getDistrict();
		String constituency = getConstituency();
		if (state == null || district == null || constituency == null) {
			return;
		}
		// Get Leaders from Server
		getDataProvider().getLeaders(this, state, district, constituency);
	}

	/**
	 * get Selected State
	 * 
	 * @return
	 */
	private String getState() {
		return (String) states.getSelectedItem();
	}

	/**
	 * get Selected District
	 * 
	 * @return
	 */
	private String getDistrict() {
		return (String) districts.getSelectedItem();
	}

	/**
	 * get Selected Constituency
	 * 
	 * @return
	 */
	private String getConstituency() {
		return (String) constituencies.getSelectedItem();
	}

	/**
	 * Initialize Leaders List Here
	 * 
	 * @param leaders
	 */
	private void initLeaders(List<Leader> leaders) {

	}

	@Override
	public void onResponse(List<Leader> result) {
		// Init Leaders
		initLeaders(result);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
			long id) {
		switch (parent.getId()) {
		case R.id.states:
			stateSelected();
			break;
		case R.id.districts:
			districtSelected();
			break;
		case R.id.constituencies:
			constituencySelected();
			break;
		case R.id.leaders_grid:
			leaderSelected(pos);
			break;
		}
	}

	/**
	 * Leaders Selected, start intent to open leader details
	 * 
	 * @param position
	 */
	private void leaderSelected(int position) {
		LeadersAdapter adapter = (LeadersAdapter) leadersList.getAdapter();
		Leader leader = adapter.getItem(position);

		// Prepare Intent
		Intent openLeader = new Intent(this, LeaderDetails.class);
		openLeader.putExtra(Utility.LEADER, leader);
		startActivity(openLeader);
	}

	/**
	 * Reinit Districts of Selected State and clear Constituency(if selected
	 * any)
	 */
	@SuppressWarnings("unchecked")
	private void stateSelected() {
		// REINIT DISTRICTS
		ArrayAdapter<String> districtAdapter = (ArrayAdapter<String>) districts
				.getAdapter();
		districtAdapter.clear();
		ArrayList<String> districts = getDistrictsOfState(getState());
		for (String dt : districts) {
			districtAdapter.add(dt);
		}
		districtAdapter.notifyDataSetChanged();

		// CLEAR CONSTITUENCIES
		ArrayAdapter<String> cAdapter = (ArrayAdapter<String>) constituencies
				.getAdapter();
		cAdapter.clear();
		cAdapter.notifyDataSetChanged();

	}

	private ArrayList<String> getDistrictsOfState(String state) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Reinit Constituencies of Selected District
	 */
	@SuppressWarnings("unchecked")
	private void districtSelected() {
		// CLEAR CONSTITUENCIES
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) constituencies
				.getAdapter();
		adapter.clear();
		ArrayList<String> constituencies = getConstituenciesOfDistrict(getDistrict());
		for (String c : constituencies) {
			adapter.add(c);
		}
		adapter.notifyDataSetChanged();
	}

	private ArrayList<String> getConstituenciesOfDistrict(String district) {
		// TODO Auto-generated method stub
		return null;
	}

	private void constituencySelected() {
		// Get and Init Leaders List
		getLeaders();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// NOTHING TO DO HERE
	}

}
