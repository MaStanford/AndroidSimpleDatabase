package com.example.simpledatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences.Editor;

public class Settings extends BaseActivity {

	/*
	 * Filename Globals
	 */
	// global filename
	String fileName;

	/*
	 * Shared Pref globals
	 */
	// decided to try an event listener for this section
	EditText editText1, editText2;
	TextView textSavedMem1, textSavedMem2;
	Button buttonSaveMem1, buttonSaveMem2;
	String key1,key2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		/********************************************************************
		 * FILENAME
		 ********************************************************************/

		// get the filename
		fileName = getFileName();

		// display the filename
		TextView tv = (TextView) findViewById(R.id.settings_file_name_output);
		tv.setText(fileName);

		// Set the text of the filename in edit text.
		EditText edit1 = (EditText) findViewById(R.id.settings_file_name_in);
		edit1.setText(fileName);

		/*******************************************************************
		 * SHARED PREFS
		 *******************************************************************/
		// grab references to the textView
		textSavedMem1 = (TextView) findViewById(R.id.Settings_shared_out1);
		textSavedMem2 = (TextView) findViewById(R.id.Settings_shared_out2);
		// grab the values in the editText fields
		editText1 = (EditText) findViewById(R.id.Settings_pref1);
		editText2 = (EditText) findViewById(R.id.Settings_pref2);
		// get references to the buttons, for use with the listener
		buttonSaveMem1 = (Button) findViewById(R.id.settings_shared1_button);
		buttonSaveMem2 = (Button) findViewById(R.id.settings_shared2_button);
		// sets an unClickListener. Gives the function to call as the argument.
		buttonSaveMem1.setOnClickListener(buttonSaveOnClickListener1);
		buttonSaveMem2.setOnClickListener(buttonSaveOnClickListener2);
		// retrieves the key names

		key1 = getKey1();
		key2 = getKey2();
		// set them to the sharedKeys
		textSavedMem1.setText(key1);
		textSavedMem2.setText(key2);
		//set the editText
		editText1.setText(key1);
		editText2.setText(key2);

		/*******************************************************************
		 * SQL Database
		 *******************************************************************/

	}

	/*
	 * The method called when the user clicks on the filename button
	 * 
	 * @param view view is automatically supplied by android when an onClick is
	 * called
	 */
	public void fileNameAddClick(View view) {

		// get what the user has in the editText field
		EditText userInputEdit = (EditText) findViewById(R.id.settings_file_name_in);

		// creates a string from the user input
		String UserInput = userInputEdit.getText().toString();

		// grabs a sharedPreferences editor
		// I grab a getSharedPreferences editor so that it can save it to the
		// group in other activities
		Editor editor = getSharedPreferences(getString(R.string.PREFERENCE_KEY), MODE_PRIVATE).edit();

		// calls the putString() method from the editor. Key and Value are the arguments
		// The key will be the name I have in strings for the key and the value
		// will be the user supplied key
		editor.putString(getString(R.string.file_name_pref), UserInput);

		// change the default bool.
		editor.putBoolean(getString(R.string.default_file_name_bool), false);

		// commit changes.
		editor.commit();

		// get the filename
		fileName = getFileName();

		// display the filename
		TextView tv = (TextView) findViewById(R.id.settings_file_name_output);
		tv.setText(fileName);

		// Set the text of the filename in edit text.
		EditText edit1 = (EditText) findViewById(R.id.settings_file_name_in);
		edit1.setText(fileName);
	}
	
	
	/*
	 * I think I am overwriting OnClickListener()
	 * I think I am also getting a reference to the new button.onclickListerner object
	 * and sending it to the setOnClickListener method in my onCreate method.
	 */
	Button.OnClickListener buttonSaveOnClickListener1 = new Button.OnClickListener() {
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			//Gets a reference to the editor.  SharedPref.edit() returns an editor object
			Editor editor = getSharedPreferences(getString(R.string.PREFERENCE_KEY), MODE_PRIVATE).edit();
			//calls the putString() method from the editor.  Key and Value are the arguments
			editor.putString(getString(R.string.Shared_key_1),editText1.getText().toString());
			// change the default bool.
			editor.putBoolean(getString(R.string.default_key1_name_bool), false);
			//This commits the changes, I guess similar to a version software commit
			editor.commit();
			
			//zero out the edit text
			editText1.setText(R.string.blank);
			//updates the textViews
			key1 = getKey1();
			// set them to the sharedKeys
			textSavedMem1.setText(key1);
			editText1.setText(key1);

		}
	};

	/*
	 * I think I am overwriting OnClickListener()
	 * I think I am also getting a reference to the new button.onclickListerner object
	 * and sending it to the setOnClickListener method in my onCreate method.
	 */
	Button.OnClickListener buttonSaveOnClickListener2 = new Button.OnClickListener() {
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			//Gets a reference to the editor.  SharedPref.edit() returns an editor object
			Editor editor = getSharedPreferences(getString(R.string.PREFERENCE_KEY), MODE_PRIVATE).edit();
			//calls the putString() method from the editor.  Key and Value are the arguments
			editor.putString(getString(R.string.Shared_key_2),editText2.getText().toString());
			// change the default bool.
			editor.putBoolean(getString(R.string.default_key2_name_bool), false);
			//This commits the changes, I guess similar to a version software commit
			editor.commit();
			
			//zero out the edit text
			editText2.setText(R.string.blank);
			//updates the textViews
			key2 = getKey2();
			// set them to the sharedKeys
			textSavedMem2.setText(key2);
			editText2.setText(key2);

		}
	};
}
