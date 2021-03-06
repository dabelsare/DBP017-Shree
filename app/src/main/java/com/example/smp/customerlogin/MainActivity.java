package com.example.smp.customerlogin;

import android.database.Cursor;
import android.os.AsyncTask;
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

import org.apache.http.message.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    Session sessionid=null;
    Button login;
    Button registerr;
    EditText enterpassword,username;
    TextView forgetpass;

    String URL= "http://smartbizit.com/aquasmart/index.php";

    JSONParser jsonParser = new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.login_btn);
        registerr=(Button)findViewById(R.id.register_btn);
        username=(EditText)findViewById(R.id.username_edt);
        enterpassword=(EditText)findViewById(R.id.password_edt);
        forgetpass=(TextView)findViewById(R.id.textView2);

        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter.open();

        registerr.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

             // Intent i=new Intent(MainActivity.this,Registration.class);
                Intent i=new Intent(MainActivity.this,Termsandservices.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                String Password=enterpassword.getText().toString();
                String Username=username.getText().toString();
                if(Password.equals("")||Username.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter Username or Password", Toast.LENGTH_LONG).show();
                }
                else {
                            AttemptLogin
                            attemptLogin = new AttemptLogin();
                            attemptLogin.execute(Username, Password);
                     }
//                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(Username);
//                String StoredID=loginDataBaseAdapter.getId(Username);
//                if(Password.equals(storedPassword))
//                {
//                    // sessionid.setusename(StoredID);
//                    //sessionid.getusename();
//                  //  Toast.makeText(getApplicationContext(),StoredID, Toast.LENGTH_LONG).show();
//                    Toast.makeText(MainActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
//                    Intent ii=new Intent(MainActivity.this,Home.class);
//                    startActivity(ii);
//                }
//                else
//                if(Password.equals("")){
//                    Toast.makeText(MainActivity.this, "Please Enter Your Password", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
//                }

            }
        });

        forgetpass.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forget_search);
                dialog.show();

                final EditText security=(EditText)dialog.findViewById(R.id.securityhint_edt);
                final TextView getpass=(TextView)dialog.findViewById(R.id.textView3);

                Button ok=(Button)dialog.findViewById(R.id.getpassword_btn);
                Button cancel=(Button)dialog.findViewById(R.id.cancel_btn);

                ok.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        String userName=security.getText().toString();
                        if(userName.equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Please enter your securityhint", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String storedPassword=loginDataBaseAdapter.getAllTags(userName);
                            if(storedPassword==null)
                            {
                                Toast.makeText(getApplicationContext(), "Please enter correct securityhint", Toast.LENGTH_SHORT).show();
                            }else{
                                Log.d("GET PASSWORD",storedPassword);
                                getpass.setText(storedPassword);
                            }
                        }
                    }
                });
                cancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
// TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }

    private class AttemptLogin extends AsyncTask<String,String,JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String[] args) {
            String password = args[1];
            String name= args[0];

            ArrayList params = new ArrayList();
            params.add(new org.apache.http.message.BasicNameValuePair("username", name));
            params.add(new org.apache.http.message.BasicNameValuePair("password", password));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
            return json;
        }

        protected void onPostExecute(JSONObject result) {
            // dismiss the dialog once product deleted
            try {
                if (result.getString("success").equals("null")) {
                    Toast.makeText(getApplicationContext(), "LogIn Fail", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),Registration.class);
                    //intent.putExtra("UserName", "Varad");
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}