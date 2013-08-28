package com.cm.tidetimer4;

import com.cm.tidetimer4.MainFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment{
	TextView myText;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		String tideinfos = launchMyIntent.getStringExtra("TIDE_STUFF");
		View tidalview =(View) inflater.inflate(R.layout.fragment_two, container, false);
		return tidalview;
		
	}

}
