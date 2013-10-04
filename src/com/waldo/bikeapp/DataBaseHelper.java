package com.waldo.bikeapp;
// Importeer benodigdheden
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Toegevoegd door eclipse
@SuppressLint("SdCardPath") public class DataBaseHelper extends SQLiteOpenHelper {


	
	//Pad van de database
    private static String DB_PATH = "/data/data/com.waldo.bikeapp/databases/";
    //Naam van de Database
    private static String DB_NAME = "Fietsdata";
    //Namen van de tables
    private static String TABLE_FIETSENSTALLING = "FietsenStalling";
    private static String TABLE_FIETSENREK = "FietsenRek";
    private static String TABLE_FIETSINFO = "FietsInfo";
    // Algemene column 
    private static String KEY_ID = "_id";
    // Fietsenstalling columns:
    private static String KEY_NAAM = "Naam";
    private static String KEY_PLAATS = "Plaats";
    private static String KEY_ADRES = "Adres";
    // FietsenRek columns
    private static String KEY_FIETSENSTALLING_ID = "FietsenStalling_id";
    private static String KEY_LOCATIE = "Locatie";
    private static String KEY_HOOGTE = "Hoogte";
    // FietsInfo columns
    private static final String KEY_FIETSENREK_ID = "FietsenRek_id";
    private static final String KEY_MERK ="Merk";
    private static final String KEY_TYPE ="Type";
    private static final String KEY_KLEUR ="Kleur";
    private static final String KEY_FRAMENUMMER ="FrameNummer";
    
    private SQLiteDatabase myDataBase; 
    private final Context myContext;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
 
    // Maak een lege database aan welke overschreven kan worden met de prefab database
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		// Database bestaat al
    		Log.d ("DATABASE", "DATABASE BESTAAT AL");
    		
    	}else{
    		Log.d ("DATABASE", "DATABASE BESTAAT NOG NIET!");
    		Log.d ("DATABASE", "AANMAKEN LEGE DATABASE");
    		//By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
        		
        		Log.d ("DATABASE", "OPEN KOPIEEROPDRACHT");
    			copyDataBase();
 
    		} catch (IOException e) {
 
    			throw new RuntimeException(e);
 
        	}
    	}
 
    }
 
    // Check of de database al bestaat
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
 
    	}catch(SQLiteException e){
 
    		// Database bestaat nog niet
    		Log.d ("DATABASE", "DATABASE BESTAAT NIET");
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    private void copyDataBase() throws IOException{
 
    	// Open de locale database als input stream
    	Log.d ("Database", DB_PATH + DB_NAME);
		Log.d ("Database", "OPENEN VANN DE DATABASE");
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
    	
 
    	// Open de lege database als output stream
    	String outFileName = DB_PATH + DB_NAME;
    	
    	// Open the empty db as the output stream
    	Log.d ("Database", "Open the empty db as the output stream");
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	// Verplaats bytes van de inputfile naar de outputfile
    	Log.d ("Database", "daadwerkelijk kopiëren");
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	// Sluit de input en output streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
 
    	// Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    	Log.d ("Database", "OPENEN DATABASE IS GELUKT");
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
 
        // Add your public helper methods to access and get content from the database.
        // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
	
	// Haal enkele fietsenstalling op
	FietsData getFietsenStalling (int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_FIETSENSTALLING,  new String[] { KEY_ID,
	            KEY_NAAM, KEY_PLAATS, KEY_ADRES }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
        
		FietsData fietsdata = new FietsData(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return fietsdata
		cursor.close();
		return fietsdata ;
		
	}
	
	// Haal een enkel fietsenrek op
	FietsenRekData getFietsenRek (int id) {
		
		Log.d("DATABASEHELPER", "HAAL DATABASE OP");
		SQLiteDatabase db = this.getReadableDatabase();
		
		Log.d("DATABASEHELPER", "DOE DE QUERY");
		Cursor cursor = db.query (TABLE_FIETSENREK,  new String[] { KEY_ID,
	            KEY_FIETSENSTALLING_ID, KEY_LOCATIE, KEY_HOOGTE }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
				
		Log.d("DATABASEHELPER", "QUERY GELUKT");
		
		if( cursor != null && cursor.moveToFirst() )
			
		Log.d("DATABASEHELPER", "DE CURSOR IS GEEN NULL");
		FietsenRekData fietsenrek = new FietsenRekData(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
		
        // return fietsenrek
		Log.d("DATABASEHELPER", "RETURN FIETSENREK");
		return fietsenrek ;
	}
	
	// Haal huidige fietsinformatie op
	FietsInfoData getFietsInfo (int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_FIETSINFO,  new String[] { KEY_ID,
	            KEY_FIETSENREK_ID, KEY_MERK, KEY_TYPE, KEY_KLEUR, KEY_FRAMENUMMER,  }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
        
		FietsInfoData fietsinfo = new FietsInfoData(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        // return fietsdata
		cursor.close();
		return fietsinfo ;
		
	}
	
	// Updaten van de fietsenrek id waarde
	public int updateFietsInfoData(FietsInfoData fietsinfo){
		SQLiteDatabase db = this.getWritableDatabase();
		
		Log.d("FIETSINFODATA WRITER","test");
		
		ContentValues values = new ContentValues ();
		values.put(KEY_FIETSENREK_ID, fietsinfo.getFietsenRek_id());
		values.put(KEY_MERK, fietsinfo.getMerk());
		values.put(KEY_TYPE, fietsinfo.getType());
		values.put(KEY_KLEUR, fietsinfo.getKleur());
		values.put(KEY_FRAMENUMMER, fietsinfo.getFrameNummer());
		Log.d("FIETSINFODATA WRITER", "SCHRIJVEN NAAR DE DATABASE");
		return db.update(TABLE_FIETSINFO, values, KEY_ID + " = ?",
                new String[] { String.valueOf(fietsinfo.getID()) });
    }
		
}


	
