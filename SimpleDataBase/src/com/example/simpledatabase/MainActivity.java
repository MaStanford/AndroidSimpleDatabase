package com.example.simpledatabase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	
	//Blank is used to zero out the edit fields and textViews after a button press.
	final String BLANK = "";
	//create variables so that they can be passed by reference to methods.
	String fileName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// get the filename
		fileName = getFileName();
		
		//display the filename
		TextView tv = (TextView) findViewById(R.id.main_file_name_output);
		tv.setText(fileName);
		
		
		//I need this here also, in order to populate the output on startup
		try {
			// OPEN FILE INPUT STREAM THIS TIME
			//Get a reference to a fileInputStream
			FileInputStream fis = openFileInput(fileName);
			//Get a reference to a fileInputStreamReader using the fis as the argument
			InputStreamReader isr = new InputStreamReader(fis);
			
			/*READ STRING OF UNKNOWN LENGTH
			* Get a reference to a string builder
			* Not sure why I would use a string builder instead of just a char[] or String
			*/
			StringBuilder sb = new StringBuilder();
			//make a char[] which in java is slightly different that a String
			//Again I'm not sure why this path is used, but this is what the google dev page says
			char[] inputBuffer = new char[2048];
			//make a counter.  Java initializes primitive types to 0 I believe.  
			int l;
			
			// FILL BUFFER WITH DATA
			while ((l = isr.read(inputBuffer)) != -1){
				sb.append(inputBuffer, 0, l);}

			// CONVERT BYTES TO STRING
			String readString = sb.toString();
			//get a reference to the textView
			TextView tv2 = (TextView) findViewById(R.id.main_file_edit_output);
			//Set the TextView
			tv2.setText(getString(R.string.main_file_edit_output) + readString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This is called by the Add button
	 */
	public void addToFile(View view) {		
		
		//get the filename
		fileName = getFileName();
		
		//display the filename
		TextView tv = (TextView) findViewById(R.id.main_file_name_output);
		tv.setText(fileName);
		
		// STRING TO BE WRITTEN TO FILE
		EditText editText = (EditText) findViewById(R.id.main_file_edit);
		//Convert the edit text to string
		String msg = editText.getText().toString() + " "; //add a space at the end.  
		//set the editText to Blank
		editText.setText(BLANK);
		
		/*
		 * This next try catch is for writing to the file what is in the editText 
		 * when the add button is pressed
		 */
		//try catch needed for file operations
		try {
			// CREATE THE FILE AND WRITE
			FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
			//Writes the msg string after converting it to bytes.  I assume the file operation needs it as bytes
			fos.write(msg.getBytes());
			//close the file
			fos.close();
			
			// outputs to screen what was written
			TextView tv2 = (TextView) findViewById(R.id.main_file_read_output);
			tv2.setText(msg);
		//Logic for catching an exception
		} catch (IOException e) {
			//prints the trace, 60% of the time my emulator fails to open trace file
			e.printStackTrace();
		}
		
		/*
		 * This next part reads the file and set it to the textView
		 */
		try {
			// OPEN FILE INPUT STREAM THIS TIME
			//Get a reference to a fileInputStream
			FileInputStream fis = openFileInput(fileName);
			//Get a reference to a fileInputStreamReader using the fis as the argument
			InputStreamReader isr = new InputStreamReader(fis);
			
			/*READ STRING OF UNKNOWN LENGTH
			* Get a reference to a string builder
			* Not sure why I would use a string builder instead of just a char[] or String
			*/
			StringBuilder sb = new StringBuilder();
			//make a char[] which in java is slightly different that a String
			//Again I'm not sure why this path is used, but this is what the google dev page says
			char[] inputBuffer = new char[2048];
			//make a counter.  Java initializes primitive types to 0 I believe.  
			int l;
			
			// FILL BUFFER WITH DATA
			while ((l = isr.read(inputBuffer)) != -1){
				sb.append(inputBuffer, 0, l);}

			// CONVERT BYTES TO STRING
			String readString = sb.toString();
			//get a reference to the textView
			TextView tv2 = (TextView) findViewById(R.id.main_file_edit_output);
			//Set the TextView
			tv2.setText(getString(R.string.main_file_edit_output) + readString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Encapsulates the deleteFile() method from the imported file library
	 * Sets views to blank also
	 */
	public void deleteFile(View view){
		
		//get the filename
		fileName = getFileName();
		//display the filename
		TextView tv = (TextView) findViewById(R.id.main_file_name_output);
		tv.setText(fileName);
		
		EditText editText = (EditText) findViewById(R.id.main_file_edit);
		editText.setText(BLANK);
		
		TextView tv2 = (TextView) findViewById(R.id.main_file_edit_output);
		tv2.setText(BLANK);
		
		TextView tv3 = (TextView) findViewById(R.id.main_file_read_output);
		tv3.setText(getString(R.string.main_file_edit_output));
		
		//This is the method from the imported file library.  Deletes the file supplied as an argument
		deleteFile(fileName);
	}
}
