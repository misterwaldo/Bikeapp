package com.waldo.bikeapp;

public class FietsInfoData {

    int _id;
    int _FietsenRek_id;
    String _Merk;
    String _Type;
    String _Kleur;
    String _FrameNummer;
    
//    // Lege constructor
    public FietsInfoData(int entry1, int entry2, Object object, Object object2, Object object3, Object object4){
         
   }
    // constructor
    public FietsInfoData(int id, int FietsenRek_id, String Merk, String Type, String Kleur, String FrameNummer){
        this._id = id;
        this._FietsenRek_id = FietsenRek_id;
        this._Merk = Merk;
        this._Type = Type;
        this._Kleur = Kleur;
        this._FrameNummer = FrameNummer;
    }
     
    // Getting ID
    public int getID(){
        return this._id;
    }
     
    // Getting id
    public void setID(int id){
        this._id = id;
    }
     
    // Getting FietsenRek
    public int getFietsenRek_id(){
        return this._FietsenRek_id;
    }
     
    // Setting FietsenRek
    public void setFietsenRek_id(int FietsenRek_id){
        this._FietsenRek_id = FietsenRek_id;
    }
     
    // Get Merk
    public String getMerk(){
        return this._Merk;
    }
     
    // Set Merk
    public void setMerk(String Merk){
		this._Merk = Merk;
    }
    
    // Get Type
    public String getType(){
        return this._Type;
    }
     
    // Set Type
    public void setType(String Type){
		this._Type = Type;
    }
	
    // Get Kleur
    public String getKleur(){
        return this._Kleur;
    }
     
    // Set Kleur
    public void setKleur(String Kleur){
		this._Kleur = Kleur;
    }
    
    // Get FrameNummer
    public String getFrameNummer(){
        return this._FrameNummer;
    }
     
    // Set Kleur
    public void setFrameNummer(String FrameNummer){
		this._FrameNummer = FrameNummer;
    }
}
