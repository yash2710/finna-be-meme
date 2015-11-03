package com.example.tanha.finna_be_meme1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PendingActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<Appoint> list = new ArrayList<>();
    String id,obid;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i=new Intent(PendingActivity.this,patient_details.class);
                i.putExtra("email",list.get(position).id);
                i.putExtra("date",list.get(position).date.toString());
                i.putExtra("obid",list.get(position).obid);
                startActivity(i);
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));
        String username=getIntent().getExtras().getString("email");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("appointment_user");
        query.whereEqualTo("D_email", username);
        query.whereEqualTo("doctor_confirm",false);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> listi, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("D_email", "Retrieved " + listi.size());
                    for (int i = 0; i < listi.size(); i++) {

                        id = (String) listi.get(i).get("P_email");
                        //String name = (String) list.get(i).get("p_name");
                        date = listi.get(i).getDate("App_date");
                        Log.d("date", date.toString());
                        //String contact=(String) list.get(i).get("contact");
                        obid = listi.get(i).getObjectId();
                        Appoint a = new Appoint(id, date, obid);
                        //Appoint a = new Appoint(id,name,date,contact);
                        list.add(a);
                        Log.d("hi", a.id + a.date);
                        Log.d("Hello", list.get(i).id + list.get(i).date);
                    }
                } else {
                    Log.d("dr_username", "Error: " + e.getMessage());
                }
                adapter.notifyDataSetChanged();
                adapter.setList(list);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pending, menu);
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
}
