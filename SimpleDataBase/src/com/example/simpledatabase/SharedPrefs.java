package com.example.simpledatabase;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends BaseActivity {

	//make references global.  Java is pass by reference language, not pass by value like real languages.
	EditText editText1, editText2;
	TextView textSavedMem1, textSavedMem2;
	Button buttonSaveMem1, buttonSaveMem2;

	//These are the keys
	String key1;
	String key2;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_prefs);

		//grab references to the textView
		textSavedMem1 = (TextView) findViewById(R.id.savedmem1);
		textSavedMem2 = (TextView) findViewById(R.id.savedmem2);
		//grab the values in the editText fields
		editText1 = (EditText) findViewById(R.id.edittext1);
		editText2 = (EditText) findViewById(R.id.edittext2);
		//get references to the buttons, for use with the listener
		buttonSaveMem1 = (Button) findViewById(R.id.save_mem1);
		buttonSaveMem2 = (Button) findViewById(R.id.save_mem2);
		//sets an unClickListener.  Gives the function to call as the argument.
		buttonSaveMem1.setOnClickListener(buttonSaveMem1OnClickListener);
		buttonSaveMem2.setOnClickListener(buttonSaveMem2OnClickListener);
		
		//retrieves the key names
		key1 = getKey1();
		key2 = getKey2();
		
		//grab the textViews for the labels
		TextView textLabel1 = (TextView) findViewById(R.id.Shared_label1);
		TextView textLabel2 = (TextView) findViewById(R.id.Shared_label2);
		//set them to the sharedKeys 
		textLabel1.setText("Shared Key: " + key1);
		textLabel2.setText("Shared Key: " + key2);
		
		//populate my variables
		LoadPreferences();

	}

	/*
	 * I think I am overwriting OnClickListener()
	 * I think I am also getting a reference to the new button.onclickListerner object
	 * and sending it to the setOnClickListener method in my onCreate method.
	 */
	Button.OnClickListener buttonSaveMem1OnClickListener = new Button.OnClickListener() {
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//Supplies the key, then grabs the editText value for the value
			SavePreferences(key1, editText1.getText().toString());
			//zero out the edit text
			editText1.setText(R.string.blank);
			//updates the textViews
			LoadPreferences();
		}
	};

	/*
	 * I think I am overwriting OnClickListener()
	 * I think I am also getting a reference to the new button.onclickListerner object
	 * and sending it to the setOnClickListener method in my onCreate method.
	 */
	Button.OnClickListener buttonSaveMem2OnClickListener = new Button.OnClickListener() {
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//Supplies the key, then grabs the editText value for the value
			SavePreferences(key2, editText2.getText().toString());
			//zero out the edit text
			editText2.setText(R.string.blank);
			//updates the textViews
			LoadPreferences();
		}
	};

	/*
	 * Saves the key value pair into the SharedPreferences file structure
	 * 
	 * @param String The key of the key,value pair
	 * @param String The value of the Key,value pair
	 */
	private void SavePreferences(String key, String value) {
		//Gets a reference to the editor.  SharedPref.edit() returns an editor object
		Editor editor = getSharedPreferences(getString(R.string.PREFERENCE_KEY), MODE_PRIVATE).edit();
		//calls the putString() method from the editor.  Key and Value are the arguments
		editor.putString(key, value);
		//This commits the changes, I guess similar to a version software commit
		editor.commit();
	}

	/*
	 * Gets the value of the SharedPrefences
	 * Loads the SharedPreferences into the textViews
	 */
	private void LoadPreferences() {
		//gets a reference to the sharedPreferences file
		SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREFERENCE_KEY), MODE_PRIVATE);
		//gets the string value of the key,value pair.  
		String strSavedMem1 = sharedPreferences.getString(key1, "");
		//gets the string value of the key,value pair
		String strSavedMem2 = sharedPreferences.getString(key2, "");
		//sets the TextViews with the strings
		textSavedMem1.setText("Value: " + strSavedMem1);
		textSavedMem2.setText("Value: " + strSavedMem2);
	}
}