package com.waldo.bikeapp;

import java.io.IOException;
//importeer IntentIntegrator voor het afhandelen van het event
import com.google.zxing.integration.android.IntentIntegrator;
//importeer de afhandeling van het resultaat
import com.google.zxing.integration.android.IntentResult;
//importeer overige benodigdheden
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private Button scanBtn;
	private Button findBtn;
	private TextView formatTxt, contentTxt;
	
	//maak een nieuw databasehelper object
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setup voor de scanbutton en toevoegen onclick listner
        scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(this);
        // setup voor de textfields
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        // setup voor de findbutton en toevoegen onclick listner      
        findBtn = (Button)findViewById(R.id.find_button);
        findBtn.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), SecondScreenActivity.class);
 
                startActivity(nextScreen);
 
            }
        });
        
        DataBaseHelper myDbHelper = new DataBaseHelper(null);
        myDbHelper = new DataBaseHelper(this);
        
        try {
 
        	myDbHelper.createDataBase();
 
        	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
        	}
 
        try {
 
 		myDbHelper.openDataBase();
 
        }
        
        catch(SQLException sqle){
 
 		throw sqle;
 
        }
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //Als er geklikt is op de knop scan start deze functie
    public void onClick(View v){
    	
    	if(v.getId()==R.id.scan_button){
    		//Als de waarde voldaan is doe dit
    		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
    		scanIntegrator.initiateScan();
    		}
    	}
   
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	
    	//Haal het resultaat op van de geïmporteerde library
    	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	
    	if (scanningResult != null) {
    		//Er is een resultaat
    		
    	    DataBaseHelper myDbHelper = new DataBaseHelper(null);
            myDbHelper = new DataBaseHelper(this);
    		
    		String scanContent = scanningResult.getContents();
    		String scanFormat = scanningResult.getFormatName();
    		
    		//Splits de string op in verschillende stukken met /
    		String test[] = scanContent.split("/");
    		
    		//laat de resultaten zien in de textboxes
    		formatTxt.setText("FORMAT: " + scanFormat);
    		contentTxt.setText("CONTENT: " + test[0]);
    		
    		//Debug: Laat gesplitste string zien
    		Log.d ("FietsenStalling_id", test[0]);
    		Log.d ("FietsenRek_id", test[1]);
    
    		//Zet de id's om in een int
    		int entry1 = 0;
    		entry1 = Integer.parseInt(test[0].toString());
    		
    		int entry2 = 0;
    		entry2 = Integer.parseInt(test[1].toString());
    		    		
    		// Lees de fietsenstalling
            Log.d("LEES UIT DATABASE ", "READING FIETSDATA");
      		FietsData stalling = myDbHelper.getFietsenStalling(entry1);
      		// Lees het fietsenrek
            Log.d("LEES UIT DATABASE ", "READING REKDATA");
      		// Maak een var aan voor de info van het type FietsenRekData
            FietsenRekData rek = myDbHelper.getFietsenRek(entry1);
      		
      		Log.d("SCHRIJF IN DATABASE", "SCHRIJVEN VAN FIETSENREK_ID NAAR FIETSINFODATA");
      		myDbHelper.updateFietsInfoData(new FietsInfoData (1, entry2, "test", "test", "test", "test"));
      		
      		// Lees fietsinfo
      		FietsInfoData info = myDbHelper.getFietsInfo(1);
      		
      		// Toast voor debug en test doeleinden
      		Log.d("MAAK TOAST", "MAKEN VAN DE TOAST");
      		
            Toast.makeText(this, 
            		"Fietsenstalling data:" + "\n" +
                    "Fietsenstalling_id: " + stalling.getID() + "\n" +
                    "Naam stalling: " + stalling.getNaam() + "\n" +
                    "Plaats: " + stalling.getPlaats() + "\n" +
                    "Adres:  " + stalling.getAdres() + "\n" +
                    "FietsenRek data:" + "\n" +
                    "FietsenRek_id: " + rek.getID() + "\n" +
                    "Fietsenstalling ID: " + rek.getFietsenStalling_id() + "\n" +
                    "Loactie: " + rek.getLocatie() + "\n" +
                    "Hoogte:  " + rek.getHoogte() + "\n" +
                    "Fietsinfo ID: "+ info.getID() + "\n" +
                    "Fietsenrek ID UPDATED: " + info.getFietsenRek_id(),
                    
                    Toast.LENGTH_LONG).show();       
            
            
            
    		}
    	
    	else{
    		//er is geen resultaat, maak er een melding van
    	    Toast toast = Toast.makeText(getApplicationContext(),
    	        "Geen scandata ontvangen!", Toast.LENGTH_SHORT);
    	    toast.show();
    	}
    	
    	}
    
}