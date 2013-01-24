package com.example.simpledatabase;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class SQLDatabase extends BaseListActivity  {

	//make the database global.
	SQLiteHelper db;
	
	//SET THE EDITTEXT TO BLANK
	private final String BLANK = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqldatabase);

		//initialize the database
		db = new SQLiteHelper(this);
		 
		List<Contacts> values = db.getAllContacts();
		

		
		// Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Contacts> adapter = new ArrayAdapter<Contacts>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);

        /*
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contacts> contacts = db.getAllContacts();       
        

        //write contact to LogCat
        for (Contacts cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
        }
        */
	    
		/*
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contacts("Ravi", "9100000000"));
        db.addContact(new Contacts("Srinivas", "9199999999"));
        db.addContact(new Contacts("Tommy", "9522222222"));
        db.addContact(new Contacts("Karthik", "9533333333"));
        */
	}
	
	public void onClick(View view){
		
		//gets the listAdapter so I can update it
		@SuppressWarnings("unchecked")
		ArrayAdapter<Contacts> adapter = (ArrayAdapter<Contacts>) getListAdapter();
		
		//retrieve the name from the edit text field
		//convert input to a string, also after getting the string then set field to blank
		EditText editTextName = (EditText) findViewById(R.id.SQL_edit_name);
		String stringName = editTextName.getText().toString();
		editTextName.setText(BLANK);
		
		//retrieve the number from the edit text field
		//convert input to a string, also after getting the string then set field to blank
		EditText editTextNumber = (EditText) findViewById(R.id.SQL_edit_number);
		String stringNumber = editTextNumber.getText().toString();
		editTextNumber.setText(BLANK);
		
		Contacts contact = new Contacts();
		contact.setName(stringName);
		contact.setPhoneNumber(stringNumber);
		
		//switch for the onClick
		switch (view.getId()) {
		//if the XML object that called id is add
	    case R.id.add:
	      // Save the new comment to the database
	      contact = db.addContact(contact);
	      adapter.add(contact);
	      break;
	    //if the XML object that was called id is delete
	    case R.id.delete:
	      //make sure that the list isn't empty
	      if (getListAdapter().getCount() > 0) {
	        contact = (Contacts)getListAdapter().getItem(0);
	    	db.deleteContact(contact);
	        adapter.remove(contact);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	}
}
