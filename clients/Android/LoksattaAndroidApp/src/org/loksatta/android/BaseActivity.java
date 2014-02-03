package org.loksatta.android;

import org.loksatta.android.util.DataProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Base for all Activities
 * 
 * @author PrasannaKumar
 * 
 */
public abstract class BaseActivity extends Activity {

	private int viewResId;

	public BaseActivity(int viewResId) {
		// View to show in this Activity
		this.viewResId = viewResId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(viewResId);
		init();
	}

	/**
	 * Initializes controls on Activity
	 * 
	 */
	protected abstract void init();

	@Override
	protected void onStart() {
		super.onStart();
		initData();
	}

	/**
	 * Initializes Data to the Activity
	 */
	protected abstract void initData();

	protected DataProvider getDataProvider() {
		return DataProvider.getInstance();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Create ActionBar Here
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// On ActionBar Item Selected
		return super.onOptionsItemSelected(item);
	}
}
