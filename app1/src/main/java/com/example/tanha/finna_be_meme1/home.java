package com.example.tanha.finna_be_meme1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class home extends AppCompatActivity implements ClickListener {
    private RecyclerView view;
    String id,obid;
    Toolbar toolbar;
    Date date;
    //private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    List<Appoint> appoint1=Collections.emptyList();
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        view=(RecyclerView)findViewById(R.id.recycler_view);

        view.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        view.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(this);
        view.setAdapter(mAdapter);
        view.addOnItemTouchListener(new RecyclerTouchListener(this,view,this));
        appoint1=new ArrayList<>();
         username=ParseUser.getCurrentUser().getUsername();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("appointment_user");
        query.whereEqualTo("D_email",username);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("D_email", "Retrieved " + list.size());
                    for (int i = 0; i < list.size(); i++) {
                         id = (String) list.get(i).get("P_email");
                        //String name = (String) list.get(i).get("p_name");
                         date = list.get(i).getDate("App_date");
                        Log.d("date",date.toString());
                        //String contact=(String) list.get(i).get("contact");
                        obid=list.get(i).getObjectId();
                        Appoint a = new Appoint(id, date,obid);
                        //Appoint a = new Appoint(id,name,date,contact);
                        appoint1.add(a);
                        Log.d("hi", a.id + a.date);
                        Log.d("Hello", appoint1.get(i).id + appoint1.get(i).date);
                    }
                } else {
                    Log.d("dr_username", "Error: " + e.getMessage());
                }
                mAdapter.notifyDataSetChanged();
                mAdapter.setList(appoint1);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        if (id == R.id.profile){
            Intent i=new Intent(home.this,profile.class);
            i.putExtra("email",username);
            startActivity(i);

            return true;
        }
        if (id == R.id.pending){
            Intent i=new Intent(home.this,PendingActivity.class);
            i.putExtra("email",username);
            startActivity(i);

            return true;
        }
        if(id == R.id.logout){
            ParseUser.logOut();
            startActivity(new Intent(this, login.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onClick(View v, int position) {
//        Intent i=new Intent(home.this,patient_details.class);
//        i.putExtra("email",appoint1.get(position).id);
//        startActivity(i);
//
//    }

    @Override
    public void onClick(View v, int position) {
        Intent i=new Intent(home.this,patient_details.class);
        i.putExtra("email",appoint1.get(position).id);
        //i.putExtra("name",appoint1.get(position).name);
        i.putExtra("date",appoint1.get(position).date.toString());
        i.putExtra("obid",appoint1.get(position).obid);
        //i.putExtra("contact",appoint1.get(position).contact);
        startActivity(i);

    }

    @Override
    public void onLongClick(View v, int position) {

    }

    //    @Override
//    public void onClick(View v, int position) {
//       Intent i=new Intent(home.this,patient_details.class);
//       i.putExtra("email",appoint1.get(position).id);
//       startActivity(i);
//
//
//    }
}
