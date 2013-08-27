package com.cm.tidetimer4;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cm.tidetimer4.DataFile;
import com.cm.tidetimer4.R;
import com.cm.tidetimer4.WebFile;
import com.cm.tidetimer4.TideActivity.LocRequest;
//import com.cm.tidetimer4.TideActivity.LocRequest;

public class TideActivity extends Activity {  // sdubin, removed the implements onClickListener,
    //  it could be done this way but it is easier to 
    //  define within the onCreate method

//Create my custom API URL
//pulling city tide prediction from the wunderground api
//string reference URL
static final String baseURL = "http://api.wunderground.com/api/3e64fa36c4f09bdd/tide/q/FL/";

//text view will change for tide text
TextView tvCity,tvPrediction, tvWater;
TextView calendar, tidepre, waveheight, tidesite;
EditText etCity;
Context _context;
//JSONObject results, type, tide, tideInfo;
HashMap<String, String> _history;
Boolean _connected = false;//want to assume not connected
private Button b;  //sdubin made button a global class member

/** Called when the activity is first created. */
@Override
public void onCreate(Bundle tidalcycle) {
super.onCreate(tidalcycle);
_context = this;
_history = getHistory();
Log.i("HISTORY READ",_history.toString());


setContentView(R.layout.tide);
b = (Button)findViewById(R.id.bPrediction);

etCity = (EditText)findViewById(R.id.etCity);

tvCity = (TextView)findViewById(R.id.tvCity);
tvPrediction = (TextView)findViewById(R.id.tvPrediction);
tvWater = (TextView)findViewById(R.id.tvWater);
tidesite = (TextView)findViewById(R.id.tidesite);//location
calendar = (TextView)findViewById(R.id.calendar);//date
tidepre = (TextView)findViewById(R.id.tidepre);//high or low tide
waveheight = (TextView)findViewById(R.id.waveheight);//swell height

//set a button for onclicklistener
b.setOnClickListener(new OnClickListener() {

//gets text entered in edit text and appends to textview along with data pulled from json
@SuppressWarnings("deprecation")
@Override
public void onClick(View v) {

// getting text edited and appending it to a string
String c = etCity.getText().toString();
String p = etCity.getText().toString();
String w = etCity.getText().toString();
String cal = calendar.getText().toString();//date
String ts = tidesite.getText().toString();//location
String tp = tidepre.getText().toString();//high/low
String wh = waveheight.getText().toString();//swell
StringBuilder URL = new StringBuilder(baseURL);

// this hides the keyboard after user selects the predict button
InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
imm.hideSoftInputFromWindow(b.getWindowToken(), 0);

// sdubin, changed this a little
//URL.append(c + ".json");    
String tempUrl = "";
tempUrl = new String(baseURL + c + ".json");
// sdubin, end changes/additions

URL finalURL;                       
try{

//fixed finalURL
finalURL = new URL(tempUrl);

// Log.i("my url:", baseURL + c + ".json");
// Log.i("City Entered:", c);
Log.i("FINAL URL", finalURL.toString());

// sdubin added the call to AsyncTask 
LocRequest lr = new LocRequest();
lr.execute(finalURL);
// sdubin End new code

//sdubin notes, remove the commented out code below
// You will update your UI after the call onPostExecute() after it has 
//  finished the doInBackground() method.


} catch (MalformedURLException e){

Log.e("BAD URL", "MALFORMED URL");
tvCity.setText("Can not provide information at this time");
tvPrediction.setText( p + " Tide Prediction: UNKNOWN");
tvWater.setText(w + ": Location: UNKOWN");
etCity.setText(URL);
//return "In " + etCity + " The tide prediction: High";
//URL = null;
} finally {
// This you do even if your try block fails
Log.i("LOG", "I have hit the finally statement");
}

}
});


}



public String dataToString(){
return "In " + etCity + " The tide prediction: High";
}

private JSONObject JSONObject(String result) {
// TODO Auto-generated method stub
return null;
}


//create method to get history from Hard drive
@SuppressWarnings("unchecked")
private HashMap<String, String> getHistory(){
Object stored = DataFile.readObjectFile(_context, "history", false);

HashMap<String, String> history;
if(stored == null){
Log.i("HISTORY", "NO HISTORY FILE FOUND");
history = new HashMap<String, String>();
}else{
history = (HashMap<String, String>)stored;
}
return history;
}

@SuppressWarnings("unused")
private class LocRequest extends AsyncTask<URL,Void,String>{
//override 2 separate functions
@Override
protected String doInBackground(URL...urls){
String response = "";

//pass an array even though it only holds one
for(URL url: urls){
Log.e("URL DOB", url.toString());
response = WebFile.getURLSTringResponse(url);
}
//Log.i("onPostExecute, response=", response);
return response;
}

//sdubin Moved onPOstExecute to inside the LocRequest class, it is a 
// required interface class for AsyncTask
@Override
protected void onPostExecute(String result){
Log.i("I hit this line", result);

//sdubin -> Take the JSON data in the result String and update your UI.
//  Comment out your writing the file code for now, you will need it later
//      but I want to get your JSON parsing and display done first.
// sdubin, end comment

//was planning to wrap this in a function but can't seem to get it to work.
//try{
//JSONObject json = JSONObject(result);
//JSONObject tObject = json.getJSONObject("tide");
//JSONArray tideList = tObject.getJSONArray("tideSummary");
//
//JSONObject TPObj = tideList.getJSONObject(0);
//
//JSONObject predict1 = TPObj.getJSONObject("date");
//String timedata = predict1.getString("pretty");
//Log.i("Date:", timedata.toString());
//
//JSONObject predict2 = TPObj.getJSONObject("data");
//String tidedata = predict2.getString("type");
//String heightdata = predict2.getString("height");
//Log.i("Tide Log:", tidedata.toString() + heightdata.toString());
//
//JSONObject predict3 = TPObj.getJSONObject("tideInfo");
//String citydata = predict3.getString("tideSite");
//Log.i("Tide Log:", citydata.toString());
//
//String city = tideList.getJSONObject(0).getString("tideSite");
//String date = tideList.getJSONObject(0).getString("pretty");
//String tidePrediction = tideList.getJSONObject(0).getString("type");
//String height = tideList.getJSONObject(0).getString("height");
//
//tidesite.setText(city);
//calendar.setText(date);
//tidepre.setText(tidePrediction);
//waveheight.setText(height);
//
//Log.i("Results", city + date + tidePrediction + height);
//
//   	
//}catch (JSONException e){
//Log.e("JSON","JSON OBJECT EXCEPTION" + e.toString());
//}	
//}

try{
//parsing through JSON Data accepts a string as a parameter
JSONObject json = new JSONObject("tide");
/*	JSONObject results = json.getJSONObject("tideInfo").getJSONObject("tideSummary");
if(results.getString("tide").compareTo("tideSite")==1){
Toast toast = Toast.makeText(_context, "Invalid City Entered ", Toast.LENGTH_LONG);
toast.show();
}else{
Toast toast = Toast.makeText(_context, "Tide Info" + results.get("pretty"), Toast.LENGTH_LONG);
toast.show();

//makes sure history is there
_history.put(results.getString("string"), results.toString());
//target file to write history to harddrive
DataFile.storeObjectFile(_context, "tideInfo", _history, false);
DataFile.storeStringFile(_context, "data", results.toString(), true);
}*/
} 
catch (JSONException e){
Log.e("JSON", "JSON OBJECT EXCEPTION " + e.toString());
}
}

}//end onPostExecute


public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
{
return inflater.inflate(R.layout.tide, container, false);
}

public void onViewCreated(View view, Bundle savedInstanceState)
{

view = view.findViewWithTag(R.id.class);
}

	

}//end activity
