package com.example.tanha.finna_be_meme1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class patient_details extends AppCompatActivity implements View.OnClickListener{
    Bundle i;
    TextView p_id,p_name,date,time,contact;
    Button edit;
    String email,name,date1,time1,contact1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        i=getIntent().getExtras();
        email=i.getString("email");
        name=i.getString("name");
        date1=i.getString("date");
        time1=i.getString("time");
        contact1=i.getString("contact");

        p_id=(TextView)findViewById(R.id.p_id);
        p_name=(TextView)findViewById(R.id.p_name);
        date=(TextView)findViewById(R.id.date);
        time=(TextView)findViewById(R.id.time);
        contact=(TextView)findViewById(R.id.contact);
        edit=(Button)findViewById(R.id.edit_btn);
        edit.setOnClickListener(this);

        p_id.setText(email);
        p_name.setText(name);
        date.setText(date1);
        time.setText(time1);
        contact.setText(contact1);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_patient_details, menu);
//        return true;
//    }

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
        if(v==findViewById(R.id.edit_btn)){
            Intent i=new Intent(this,edit.class);
            i.putExtra("email",email);
            i.putExtra("name",name);
            i.putExtra("date",date1);
            i.putExtra("time",time1);
            i.putExtra("contact",contact1);
            startActivity(i);


        }
    }
}
