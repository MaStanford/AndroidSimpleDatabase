package com.example.simpledatabase;

import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class BaseListActivity extends ListActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void goToMenu1() {
		Intent intent = new Intent(this, SharedPrefs.class);
		startActivity(intent);
	}

	public void goToMenu2() {
		Intent intent = new Intent(this, SQLDatabase.class);
		startActivity(intent);
	}
	
	public void goToMenu3() {
		Intent intent = new Intent(this, Settings.class);
		startActivity(intent);
	}

	public void goHome() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.button_1:
			goToMenu1();
			return true;
		case R.id.button_2:
			goToMenu2();
			return true;
		case R.id.menu_settings:
			goToMenu3();
			return true;
		case R.id.button_home:
			goHome();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
