package com.example.tanha.finna_be_meme1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;


public class patient_details extends AppCompatActivity implements View.OnClickListener{
    Bundle i;
    TextView p_id,p_name,date,time,contact;
    Button edit;
    String email,name,time1,date1,contact1,surname,obid;
    String h,d;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        i=getIntent().getExtras();
        email=i.getString("email");
        date1=i.getString("date");
        obid=i.getString("obid");
        Log.d("date",date1);
        p = new ProgressDialog(this);
        p.setMessage("Loading");
        p.setCancelable(false);
        p.show();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Patient");
        query.whereEqualTo("email",email);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                hidePDialog();
                if (e == null) {


                    name=list.get(0).getString("P_name");
                    surname=list.get(0).getString("surname");
                    contact1=Long.toString(list.get(0).getLong("mobile"));
                    name=name+surname;

                    Date a = new Date(date1);
                    d=a.getDate()+"/"+a.getMonth()+"/"+(a.getYear()+1900);

                    h=a.getHours()+":"+a.getMinutes();
                    Log.d("date",list.get(0).getInt("mobile")+"");
                    p_id.setText(email);
                    p_name.setText(name);
                    date.setText(d);
                    time.setText(h);
                    contact.setText(contact1);
                } else {
                    Log.d("doctor", "Error: " + e.getMessage());
                }

            }
        });


        p_id=(TextView)findViewById(R.id.p_id);
        p_name=(TextView)findViewById(R.id.p_name);
        date=(TextView)findViewById(R.id.date);
        time=(TextView)findViewById(R.id.time);
        contact=(TextView)findViewById(R.id.contact);
        edit=(Button)findViewById(R.id.edit_btn);
        edit.setOnClickListener(this);


    }

    void hidePDialog(){
        if(p!=null){
            p.dismiss();
            p = null;
        }
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
            i.putExtra("date",d);
            i.putExtra("time",h);
            i.putExtra("contact",contact1);
            i.putExtra("obid",obid);
            startActivity(i);


        }
    }
}
