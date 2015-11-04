package com.example.tanha.finna_be_meme1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity implements View.OnClickListener {
    String username;
    EditText name,sp,qual, time , day, ex,fee,mobile;
    String name1,sp1,qual1="",time1="",day1="", ex1,fee1,mobile1;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //username=getIntent().getExtras().getString("email");

        name=(EditText)findViewById(R.id.name);
        sp=(EditText)findViewById(R.id.speciality);
        qual=(EditText)findViewById(R.id.qual);
        time=(EditText)findViewById(R.id.time);
        day=(EditText)findViewById(R.id.cal);
        ex=(EditText)findViewById(R.id.exp);
        fee=(EditText)findViewById(R.id.fees);
        mobile=(EditText)findViewById(R.id.mobile);
        done=(Button)findViewById(R.id.done);
        done.setOnClickListener(this);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        ParseUser user = ParseUser.getCurrentUser();
        query.whereEqualTo("username", "dr_sanjiv@gmail.com");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {
                    Log.d("emailnmm", "Retrievedm " + list.size());
                    name1 = list.get(0).getString("Doctor_name");
                    sp1 = list.get(0).getString("Speciality");
                    ex1 = list.get(0).getString("Experience");
                    fee1 = list.get(0).getString("Fees");
                    mobile1 = Long.toString(list.get(0).getLong("Mobile"));
                    ArrayList<String> Days = (ArrayList<String>) list.get(0).get("Days");
                    for(String s: Days){
                        day1+=s + ",";
                    }
                    ArrayList<String> Degrees = (ArrayList<String>) list.get(0).get("Degrees");
                    for(String s: Degrees){
                        qual1+=s + ",";
                    }
                    ArrayList<String> Time = (ArrayList<String>) list.get(0).get("Time");
                    for(String s: Time){
                        time1+=s + "-";
                    }


                    name.setText(name1);
                    sp.setText(sp1);
                    qual.setText(qual1);
                    time.setText(time1);
                    day.setText(day1);
                    ex.setText(ex1);
                    fee.setText(fee1);
                    mobile.setText(mobile1);
                }
                else {
                    Log.d("dr_username", "Error: " + e.getMessage());
                }



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

    @Override
    public void onClick(View v) {
        if(v==findViewById(R.id.done)) {
            Intent i = new Intent(this, home.class);
            i.putExtra("email",username);
            ParseUser user=ParseUser.getCurrentUser();
            user.put("Doctor_name",name.getText().toString());
            user.put("Speciality", sp.getText().toString());
            user.put("Experience", ex.getText().toString());
            user.put("Fees", fee.getText().toString());
            user.put("Mobile", mobile.getText().toString());
            String[] q=qual.getText().toString().split(",");
            String[] t=time.getText().toString().split("-");
            String[] d=day.getText().toString().split(",");
            user.put("Degrees",q);
            user.put("Days",d);
            user.put("Time",t);
            user.saveInBackground();
            startActivity(i);
        }
    }
}
