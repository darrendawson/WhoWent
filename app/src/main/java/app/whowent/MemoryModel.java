package app.whowent;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import java.util.HashMap;

/**
 * Created by bear on 11/16/17.
 */

public class MemoryModel {

    public MemoryModel() {}

    //---VARIABLES----------------------------------------------------------------------------------

    public final String nameKey_PUBLIC = "nameKey_PUBLIC";
    public final String bioKey_PUBLIC = "bioKey_PUBLIC";
    public final String emailKey_PUBLIC = "emailKey_PUBLIC";
    public final String phoneKey_PUBLIC = "phoneKey_PUBLIC";

    //==============================================================================================
    // READ
    //==============================================================================================
    /*
        Functions for reading from memory
     */

    //---readPublicProfileFromMemory----------------------------------------------------------------

    // reads public profile from shared preferences
    HashMap<String, String> readPublicProfileFromMemory(Activity activity) {
        HashMap<String, String> map = new HashMap<>();

        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        map.put(nameKey_PUBLIC, sharedPreferences.getString(nameKey_PUBLIC, "Enter name"));
        map.put(bioKey_PUBLIC, sharedPreferences.getString(bioKey_PUBLIC, "Enter bio"));
        map.put(emailKey_PUBLIC, sharedPreferences.getString(emailKey_PUBLIC, "Enter email"));
        map.put(phoneKey_PUBLIC, sharedPreferences.getString(phoneKey_PUBLIC, "Enter phone number"));

        return map;
    }


    //==============================================================================================
    // SAVE
    //==============================================================================================
    /*
        Functions for saving to memory
     */

    //---savePublicProfileToMemory------------------------------------------------------------------

    // takes in a dictionary of public profile values and saves them to shared preferences
    void savePublicProfileToMemory(Activity activity, HashMap<String, String> values) {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(nameKey_PUBLIC, values.get(nameKey_PUBLIC));
        editor.putString(bioKey_PUBLIC, values.get(bioKey_PUBLIC));
        editor.putString(emailKey_PUBLIC, values.get(emailKey_PUBLIC));
        editor.putString(phoneKey_PUBLIC, values.get(phoneKey_PUBLIC));


        editor.commit();
    }

}
