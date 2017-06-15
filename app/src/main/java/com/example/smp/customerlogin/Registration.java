package com.example.smp.customerlogin;

/**
 * Created by smp on 17/04/2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
public class Registration extends Activity {
    String mobilenopattern = "[0-9]{10}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText password,repassword,fname,lname,emailid,mobilenumber,city,refmob;
    Button register,cancel,reg_btn;
    CheckBox check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        password=(EditText)findViewById(R.id.password_edt);
        repassword=(EditText)findViewById(R.id.repassword_edt);
        fname=(EditText)findViewById(R.id.firstname);
        lname=(EditText)findViewById(R.id.lastname);
        emailid=(EditText)findViewById(R.id.email);
        mobilenumber=(EditText)findViewById(R.id.mobilenumber);
        city=(EditText)findViewById(R.id.city);
        refmob=(EditText)findViewById(R.id.referredmob_edt);
        register=(Button)findViewById(R.id.register_btn);
        cancel=(Button)findViewById(R.id.cancel_btn);

       // check=(CheckBox)findViewById(R.id.checkBox1);

    /*    check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// TODO Auto-generated method stub

                if(!isChecked)
                {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        }); */

        register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                String Pass=password.getText().toString();
                String Repass=repassword.getText().toString();
                String firstname=fname.getText().toString();
                String lastname=lname.getText().toString();
                String email=emailid.getText().toString();
                String mobileno=mobilenumber.getText().toString();
                String referredmob=refmob.getText().toString();
                String cityname=city.getText().toString();
                String storedmobileno=loginDataBaseAdapter.getmobileno(mobileno);
                String storedrefno=loginDataBaseAdapter.getmobileno(referredmob);
                String storedemail=loginDataBaseAdapter.getemail(email);

                if(Pass.equals("")||Repass.equals("")||firstname.equals("")  ||lastname.equals("")||email.equals("")||mobileno.equals("")||cityname.equals("")|| referredmob.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!email.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(email.equals(storedemail))
                {
                    Toast.makeText(getApplicationContext(), email+"has been  already registered,please try another", Toast.LENGTH_LONG).show();
                    return;
                }
                 if(!mobileno.matches(mobilenopattern))
                 {
                     Toast.makeText(getApplicationContext(), "Mobile No Should Be 10 Digit Number", Toast.LENGTH_LONG).show();
                     return;
                 }

                if(!Pass.equals(Repass))
                {
                    Toast.makeText(getApplicationContext(), "Password Does Not Match", Toast.LENGTH_LONG).show();
                    return;
                }
                if( mobileno.equals(storedmobileno))
                {
                    Toast.makeText(getApplicationContext(),mobileno+"has been  already registered,please try another", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!referredmob.equals(storedrefno))
                {
                    Toast.makeText(getApplicationContext(),referredmob+"not exist please enter correct referred mobile number", Toast.LENGTH_LONG).show();
                    return;
                }

                else
                {
// Save the Data in Database
                    loginDataBaseAdapter.insertEntry(Pass,firstname,lastname,email,mobileno,cityname);

// reg_btn.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Log.d("PASSWORD", Pass);
                    Log.d("FIRSTNAME",firstname);
                    Log.d("LASTNAME",lastname);
                    Log.d("EMAIL",email);
                    Log.d("MOBILE",mobileno);
                    Log.d("CITY",cityname);
                    Intent i=new Intent(Registration.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent ii=new Intent(Registration.this,MainActivity.class);
                startActivity(ii);
            }
        });
    }
}