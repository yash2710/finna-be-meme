package com.example.tanha.finna_be_meme1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

public class edit extends AppCompatActivity implements View.OnClickListener{
    EditText p_id,p_name,date,time,contact;
    Button done;
    Bundle i;
    String obid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        i=getIntent().getExtras();
        String email=i.getString("email");
        String name=i.getString("name");
        String date1=i.getString("date");
        String time1=i.getString("time");
        String contact1=i.getString("contact");
        obid=i.getString("obid");

        p_id=(EditText)findViewById(R.id.ep_id);
        p_name=(EditText)findViewById(R.id.ep_name);
        date=(EditText)findViewById(R.id.edate);
        time=(EditText)findViewById(R.id.etime);
        contact=(EditText)findViewById(R.id.econtact);
        done=(Button)findViewById(R.id.done_btn);
        done.setOnClickListener(this);

        p_id.setText(email);
        p_name.setText(name);
        date.setText(date1);
        time.setText(time1);
        contact.setText(contact1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
        if(v==findViewById(R.id.done_btn)){
            ParseQuery<ParseObject> query= ParseQuery.getQuery("appointment_user");
            query.getInBackground(obid, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject parseObject, ParseException e) {
                    parseObject.put("App_date",new Date(date.getText().toString()));
                    parseObject.saveInBackground();
                }
            });
            Toast.makeText(edit.this,"Done!", Toast.LENGTH_LONG).show();
        }

    }
}
