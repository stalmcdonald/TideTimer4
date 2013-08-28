/*
 * Crystal McDonald
 * Java II
 * 1308
 * Week 4
 */
package com.cm.tidetimer4;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class MainFragment extends ListFragment{

	private final String [] tidalList = {"Tidal Prediction", "Tide Information","Contact Us",};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter(getActivity() .getApplicationContext(), android.R.layout.activity_list_item, tidalList));
	}//end SavedInstanceState
	
	@Override
	public void onListItemClick(){
		int position;
		super.onListItemClick(l, v, position, id);
		Intent launchMyIntent = new Intent(getActivity(), DetailActivity.class);
		launchMyIntent.putExtra("TIDE_STUFF", tidalList[position]);
		startActivity(launchMyIntent);
	}

}//end
