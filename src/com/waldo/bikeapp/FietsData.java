package com.waldo.bikeapp;
public class FietsData {
	     
	    //private variables
	    int _id;
	    String _Naam;
	    String _Plaats;
	    String _Adres;
	     
	    // Lege constructor
	    public FietsData(){
	         
	    }
	    // constructor
	    public FietsData(int id, String Naam, String Plaats, String Adres){
	        this._id = id;
	        this._Naam = Naam;
	        this._Plaats = Plaats;
	        this._Adres = Adres;
	    }
	     
	    // constructor
//	    public FietsData(String Naam, String Plaats, String Adres){
//	        this._Naam = Naam;
//	        this._Plaats = Plaats;
//	        this._Adres = Adres;
	        
//	    }
	    // getting ID
	    public int getID(){
	        return this._id;
	    }
	     
	    // setting id
	    public void setID(int id){
	        this._id = id;
	    }
	     
	    // getting name
	    public String getNaam(){
	        return this._Naam;
	    }
	     
	    // setting name
	    public void setNaam(String Naam){
	        this._Naam = Naam;
	    }
	     
	    // getting plaats
	    public String getPlaats(){
	        return this._Plaats;
	    }
	     
	    // setting plaats
	    public void setPlaats(String Plaats){
			this._Plaats = Plaats;
	    }
	    
	    // getting adres
	    public String getAdres(){
	        return this._Adres;
	    }
	     
	    // setting adres
	    public void setAdres(String Adres){
			this._Adres = Adres;
	    }
	    
	    
}
