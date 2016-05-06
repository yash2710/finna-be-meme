package com.example.tanha.finna_be_meme1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class login extends AppCompatActivity {
    protected EditText lemail;
    protected EditText lpassword;
    protected Button llogin;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ParseUser.getCurrentUser() == null) {
            setContentView(R.layout.activity_login);
            lemail = (EditText) findViewById(R.id.email);
            lpassword = (EditText) findViewById(R.id.password);
            lemail.setText("dr_sanjiv@gmail.com");
            lpassword.setText("dr_sanjiv");
            ParseUser user = new ParseUser();
            //user.setUsername("dr_abc@yahoo.com");
            //user.setPassword("abc");
            //user.setEmail("abc@appointme.com");

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Hooray! Let them use the app now.
                        //Toast.makeText(login.this, "hi", Toast.LENGTH_LONG).show();
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                        e.printStackTrace();
                    }
                }
            });

            llogin = (Button) findViewById(R.id.loginb);
            llogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String email = lemail.getText().toString();
                    String password = lpassword.getText().toString();
                    p = new ProgressDialog(login.this);
                    p.setMessage("Verifying Credentials");
                    p.setCancelable(false);
                    p.show();
                    ParseUser.logInInBackground(email, password, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            hidePDialog();
                            if (user != null) {
                                // Hooray! The user is logged in.
                                Intent i = new Intent(login.this, home.class);
                                i.putExtra("email", email);
                                startActivity(i);
                            } else {
                                // Signup failed. Look at the ParseException to see what happened.
                                Toast.makeText(login.this, "Failed to login...try again!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            });
        }else{
            Intent i = new Intent(login.this, home.class);
            i.putExtra("email", ParseUser.getCurrentUser().getUsername());
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void hidePDialog(){
        if(p!=null){
            p.dismiss();
            p = null;
        }
    }
}
