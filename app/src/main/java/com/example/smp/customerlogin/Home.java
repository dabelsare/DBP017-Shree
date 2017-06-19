package com.example.smp.customerlogin;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import java.util.Arrays;
import java.util.List;
/**
 * Created by smp on 17/04/2017.
 */
public class Home extends Activity{
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	//this is home
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter.open();
        String detail =loginDataBaseAdapter.getAllDetail("1");
        Toast.makeText(Home.this, detail,Toast.LENGTH_LONG).show();
        Intent ii=new Intent(Home.this,AgentProfile.class);
        startActivity(ii);
        //Log.d("GET",detail);

        //Toast.makeText(Home.this, Arrays.toString(detail), Toast.LENGTH_LONG).show();

    }
}
