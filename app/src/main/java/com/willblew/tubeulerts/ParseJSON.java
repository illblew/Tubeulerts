package com.willblew.tubeulerts;


/**
 * Created by frienz on 6/9/2016.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON {

    /**
     * [] for future use. Maybe a NEXT button.
     */
    public static String[] Url;
    public static String[] Name;
    public static String[] Artist;
    public static String[] Artwork;

    //json string
    private String json;

    //Keys
    public static final String KEY_URL = "url";
    public static final String KEY_NAME = "name";
    public static final String KEY_ARTIST = "name";
    //Nested
    public static final String KEY_ART = "#text";


    //song array
    public static final String JSON_ARRAY = "track"; //We should target nested value here
    private JSONArray song = null;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            song = jsonObject.getJSONObject("tracks").getJSONArray(JSON_ARRAY);

            //Construct things
            Url = new String[song.length()];
            Name = new String[song.length()];
            Artist = new String[song.length()];
            Artwork = new String[song.length()];

            for(int i=0;i<song.length();i++) {
                JSONObject jo = song.getJSONObject(i);
                Url[i] = jo.getString(KEY_URL);
                Name[i] = jo.getString(KEY_NAME);
                Artist[i] = jo.getJSONObject("artist").getString(KEY_ARTIST);
                JSONArray arts = jo.getJSONArray("image");
                for (int j = 0; j < 3 ; j++) {
                    //get #3 (large) use loop so I can expand on this for the next? button thing.
                    JSONObject a = (JSONObject)arts.get(j);
                    Artwork[0] = a.getString(KEY_ART);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
