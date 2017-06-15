package com.example.smp.customerlogin;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.preference.PreferenceManager;
import java.util.HashMap;



/**
 * Created by Manjiri on 5/9/2017.
 */
public class Session {
    private SharedPreferences prefs;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    public Session(Context context) {
        // TODO Auto-generated constructor stub
        this._context = context;
        prefs = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = prefs.edit();
    }

    public void setusename(String StoredID) {

        editor.putString("Agentid", StoredID);
        editor.commit();
    }

    public String getusename() {
        String Agentid = prefs.getString("Agentid","");
        return Agentid;
    }
}
