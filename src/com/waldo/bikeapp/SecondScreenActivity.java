package com.waldo.bikeapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SecondScreenActivity extends Activity {

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // stel tweede scherm in
        setContentView(R.layout.bikefind);
        // Plaats up pijl naast icon
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // Maak databasehelper voor de database
        DataBaseHelper myDbHelper = new DataBaseHelper(null);
        myDbHelper = new DataBaseHelper(this);
        
        // Lees fietsinfo fietsenrek
        FietsInfoData info = myDbHelper.getFietsInfo(1);
        info.getFietsenRek_id();
       
        // Plaats FietsenRekID in een int
		int entry1 = info.getFietsenRek_id();
		
		FietsenRekData rek = myDbHelper.getFietsenRek(entry1);
		 Toast.makeText(this, 
         		 "FietsenRek data:" + "\n" +
                 "FietsenRek_id: " + rek.getID() + "\n" +
                 "Fietsenstalling ID: " + rek.getFietsenStalling_id() + "\n" +
                 "Loactie: " + rek.getLocatie() + "\n" +
                 "Hoogte:  " + rek.getHoogte() + "\n" +
                 "Fietsinfo ID: "+ info.getID() + "\n" +
                 "Fietsenrek ID UPDATED: " + info.getFietsenRek_id(),
                 
                 Toast.LENGTH_LONG).show();     
	}
	
	 public void onClick(View arg0) {
         //Closing SecondScreen Activity
         finish();
	 }
	 
	 public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		    // Respond to the action bar's Up/Home button
		    case android.R.id.home:
		        NavUtils.navigateUpFromSameTask(this);
		        return true;
		    }
		    return super.onOptionsItemSelected(item);
		}
}
