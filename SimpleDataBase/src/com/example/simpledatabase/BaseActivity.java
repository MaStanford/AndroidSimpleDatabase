package com.example.simpledatabase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;

/*
 * Base Activity contains the menu 
 */
public class BaseActivity extends Activity {
	
	SharedPreferences sharedPreferences;
	
	public String getFileName(){
		/*
		 * Test if default is true or shared is blank
		 * the bool is is checking to see if the file_name_pref
		 * which is what the user changes in settings is equal to blank.
		 * It looks like a mess because I need to convert the R.String into a string
		 * Then call the isEqual() method.  Primitive dataTypes in java are encapsulated in objects
		 */
		
		//Have to call getSharedPreferences with a key for the whole group and mode, otherwise I cannot access
		//The preferences from different activities
		sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_KEY),MODE_PRIVATE);
		
		//The bool is just checking to see if the name is blank
		boolean bool = sharedPreferences.getString(getString(R.string.file_name_pref),"").contentEquals("");
		if(testDefaultName() || bool){
			//returns the string that is the default file name
			return  getResources().getString(R.string.default_file_name);
		}else{
			//returns the shared fileName
			return sharedPreferences.getString(getString(R.string.file_name_pref),"");
		}
	}
	
	/*
	 * Checks to see if the file name is the default name.  
	 * @return boolean True means that the FileName is default. 
	 */
	public boolean testDefaultName(){
		//once again i call the getPreferences with a key of the group of preferences
		//so i can use it across activities.
		sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_KEY),MODE_PRIVATE);
		return sharedPreferences.getBoolean(getResources().getString(R.string.default_file_name_bool), true);
	}
	
	
	/*
	 * Tests to see if the default key is being used or if it has been changed
	 * @return boolean True means that the default is in use.
	 */
	public boolean testDefaultKey1(){
		sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_KEY),MODE_PRIVATE);
		//gets the boolean of the key specified in the string resources.
		//If it hasn't been set, then returns true by default.
		String key = getResources().getString(R.string.default_key1_name_bool);
		return sharedPreferences.getBoolean(key, true);
	}
	
	/*
	 * Tests to see if the default key is being used or if it has been changed
	 * @return boolean True means that the default is in use.
	 */
	public boolean testDefaultKey2(){
		sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_KEY),MODE_PRIVATE);
		//gets the boolean of the key specified in the string resources.
		//If it hasn't been set, then returns true by default.
		String key = getResources().getString(R.string.default_key2_name_bool);
		return sharedPreferences.getBoolean(key, true);
	}
	
	/*
	 * Returns the shared preference key set in the settings
	 * If not set then it returns the default key
	 * @return String The shared preference key to be used to pull the value.
	 */
	public String getKey1(){
		//check to see if default
		sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_KEY),MODE_PRIVATE);
		if(testDefaultKey1()){
			//returns the string that is the default file name
			return  getResources().getString(R.string.default_key1_name);
		}else{
			//returns the shared fileName
			return sharedPreferences.getString(getString(R.string.Shared_key_1),"");
		}
	}
	
	/*
	 * Returns the shared preference key set in the settings
	 * If not set then it returns the default key
	 * @return String The shared preference key to be used to pull the value.
	 */
	public String getKey2(){
		//check to see if default
		sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_KEY),MODE_PRIVATE);
		if(testDefaultKey2()){
			//returns the string that is the default file name
			return  getResources().getString(R.string.default_key2_name);
		}else{
			//returns the shared fileName
			return sharedPreferences.getString(getString(R.string.Shared_key_2),"");
		}
	}

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
