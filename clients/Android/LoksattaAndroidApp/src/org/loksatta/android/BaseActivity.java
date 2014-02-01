package org.loksatta.android;

import org.loksatta.android.util.DataProvider;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class BaseActivity extends Activity {

	private int viewResId;

	public BaseActivity(int viewResId) {
		this.viewResId = viewResId;
	}

	@Override
	protected void onChildTitleChanged(Activity childActivity,
			CharSequence title) {
		super.onChildTitleChanged(childActivity, title);
		setContentView(viewResId);
	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		init();
		return super.onCreateView(name, context, attrs);
	}

	protected abstract void init();

	@Override
	protected void onStart() {
		initData();
	}

	protected abstract void initData();

	protected DataProvider getDataProvider() {
		return DataProvider.getInstance();
	}

}
