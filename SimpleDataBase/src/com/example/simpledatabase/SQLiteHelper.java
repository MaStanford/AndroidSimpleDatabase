package com.example.simpledatabase;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
 
public class SQLiteHelper extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
 
    // Contacts table name
    private static final String TABLE_NAME = "contacts";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    
    //Contacts allColumns, for Database queries.  It is an array of all the columns
    private static final String[] allColumns = {KEY_ID,KEY_NAME,KEY_PH_NO};
 
    //Constructor
    public SQLiteHelper(Context context) {
    	//call to super constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	//Create a SQL create table command.  Use constants.
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    Contacts addContact(Contacts contact) {
    	//get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
 
        //Inserting Row
        //the long is the ID returned from inserting into the table
        //this is needed to update the listView
        long rowId = db.insert(TABLE_NAME, null, values);
        //String ids = String.valueOf(rowId);
        //Log.d("ID number", ids);
        
        
        /*get the new contact from the database so I can pass it on to w/e may need it.
        I create a cursor that will point to a list of contacts that match my query.
        The arguments are table name, array of columns, _id = id, null null null null*/
        Cursor cursor = db.query(TABLE_NAME,allColumns,KEY_ID + " = " + rowId, null, null, null ,null);
        
        //move the cursor to the first item of the query, should be the only item
        cursor.moveToFirst();
        
        //create a contact from a cursor.  It's a simple operation but it would be better to have a
        //function do it, so it can be used in other classes if needed
        contact = cursorToContacts(cursor);
        
        //close the database
        db.close(); // Closing database connection
        
        //return the contact that was added for use in other operations that may need it
		return contact;
    }
 
    // Getting single contact
    Contacts getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Contacts contact = new Contacts(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        
        // Closing database connection
        db.close(); 
        // return contact
        return contact;
    }
 
    // Getting All Contacts
    public List<Contacts> getAllContacts() {
        List<Contacts> contactList = new ArrayList<Contacts>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts contact = new Contacts();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        db.close();
        // return contact list
        return contactList;
    }
 
    // Updating single contact
    public int updateContact(Contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        

    }
 
    // Deleting single contact
    public void deleteContact(Contacts contact) {
    	//Log.d("Delete called ", String.valueOf(contact.getID()));
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
 
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();
        // return count
        return cursor.getCount();
    }
    
    
    //needed for returning a Contact from a cursor
    Contacts cursorToContacts(Cursor cursor){
    	//create a new contact
    	Contacts contact = new Contacts();
    	
    	//set contact data by pulling it from the cursor
    	contact.setID(cursor.getInt(0));
    	contact.setName(cursor.getString(1));
    	contact.setPhoneNumber(cursor.getString(2));
    	
    	return contact;
    }
 
}