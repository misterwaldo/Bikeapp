package com.waldo.bikeapp;

public class FietsenRekData {

    int _id;
    String _FietsenStalling_id;
    String _Locatie;
    String _Hoogte;
     
    // Lege constructor
    public FietsenRekData(){
         
    }
    // constructor
    public FietsenRekData(int id, String FietsenStalling_id, String Locatie, String Hoogte){
        this._id = id;
        this._FietsenStalling_id = FietsenStalling_id;
        this._Locatie = Locatie;
        this._Hoogte = Hoogte;
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
    public String getFietsenStalling_id(){
        return this._FietsenStalling_id;
    }
     
    // setting name
    public void setFietsenStalling_id(String FietsenStalling_id){
        this._FietsenStalling_id = FietsenStalling_id;
    }
     
    // Get locatie
    public String getLocatie(){
        return this._Locatie;
    }
     
    // Set locatie
    public void setLocatie(String Locatie){
		this._Locatie = Locatie;
    }
    
    // Get hoogte
    public String getHoogte(){
        return this._Hoogte;
    }
     
    // Set hoogte
    public void setHoogte(String Hoogte){
		this._Hoogte = Hoogte;
    }
	
}
