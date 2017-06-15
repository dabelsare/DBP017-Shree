package com.example.smp.customerlogin;
import android.app.Activity;
import android.os.Bundle;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import android.content.Intent;
/**
 * Created by Manjiri on 5/11/2017.
 */
public class Termsandservices extends Activity {
    Button agree,disagree;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms);
        agree=(Button)findViewById(R.id.agree_btn);
        disagree=(Button)findViewById(R.id.dis_btn);
        agree.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                // Intent i=new Intent(MainActivity.this,Registration.class);
                Intent ii=new Intent(Termsandservices.this,Registration.class);
                startActivity(ii);
            }
        });

        disagree.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                // Intent i=new Intent(MainActivity.this,Registration.class);
                Intent ii=new Intent(Termsandservices.this,MainActivity.class);
                startActivity(ii);
            }
        });
    }
}
