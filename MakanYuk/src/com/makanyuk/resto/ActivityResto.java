package com.makanyuk.resto;

import com.makanyuk.R;
import com.makanyuk.R.layout;
import com.makanyuk.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActivityResto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_resto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_resto, menu);
		return true;
	}

}
