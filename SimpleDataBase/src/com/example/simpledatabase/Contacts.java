package com.example.simpledatabase;

/*
 * This class is used in the SQL database
 * It has standard getters and setters
 * There are 3 constructors, no arguments, full arguments, and no ID arguments.
 * The toString is used by the SQL ListAdapter to write to the View
 */
public class Contacts {
 
    //private variables
    int _id;
    String _name;
    String _phone_number;
 
    // Empty constructor
    public Contacts(){
 
    }
    // constructor
    public Contacts(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }
 
    // constructor
    public Contacts(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting name
    public String getName(){
        return this._name;
    }
 
    // setting name
    public void setName(String name){
        this._name = name;
    }
 
    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }
 
    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
    
    //Returns a string
    public String toString(){
    	return "#" + this._id + " " + this._name + " Cell #" + this._phone_number;
    }
}
