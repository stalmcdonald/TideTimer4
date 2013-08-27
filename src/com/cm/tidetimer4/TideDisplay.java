/*
 * 
 * Crystal McDonald
 * Java II
 * 1308
 * Week 4
 * 
 */

package com.cm.tidetimer4;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

public class TideDisplay extends GridLayout{

	
	TextView _tideSite;//pretty
	TextView tzname;//tideSite
	TextView _units;// swell height
	TextView _type; //high or Low tide
	Context _context;
	
	
	public TideDisplay(Context context){
		super(context);
		
		//assigning a value
		_context = context;
		
		//creating columns/rows  1 for labels and 1 for values
		this.setColumnCount(2);
		
		TextView dateTimeLabel = new TextView(_context);
		dateTimeLabel.setText("Date & Time: ");
		_tideSite = new TextView(_context);
		
		TextView locLabel = new TextView(_context);
		locLabel.setText("Location: ");
		tzname = new TextView(_context);
		
		TextView heightLabel = new TextView(_context);
		heightLabel.setText("Swell: ");
		_units = new TextView(_context);
		
		TextView HorLLabel = new TextView(_context);
		HorLLabel.setText("Tidal Prediction: ");
		_type = new TextView(_context);
		
		
		//add views to display
		this.addView(dateTimeLabel);
		this.addView(_tideSite);
		this.addView(locLabel);
		this.addView(tzname);
		this.addView(heightLabel);
		this.addView(_units);
		this.addView(HorLLabel);
		this.addView(_type);
		
		
		
		
	}
}
