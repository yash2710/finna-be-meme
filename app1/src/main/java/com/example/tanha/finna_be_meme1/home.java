package com.example.tanha.finna_be_meme1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


public class home extends AppCompatActivity implements ClickListener {
    private RecyclerView view;
    //private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    List<Appoint> appoint1=Collections.emptyList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        view=(RecyclerView)findViewById(R.id.recycler_view);

        view.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        view.setLayoutManager(mLayoutManager);


        mAdapter = new MyAdapter(this);
        view.setAdapter(mAdapter);
        view.addOnItemTouchListener(new RecyclerTouchListener(this,view,this));
        appoint1=new ArrayList<>();
        String username=getIntent().getExtras().getString("email");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("appointment_user");
        query.whereEqualTo("D_email",username);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("D_email", "Retrieved " + list.size());
                    for (int i = 0; i < list.size(); i++) {

                        String id = (String) list.get(i).get("p_id");
                        String name = (String) list.get(i).get("p_name");
                        Date date = list.get(i).getDate("App_date");
                        String contact=(String) list.get(i).get("contact");

                        Appoint a = new Appoint(id, date);
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
        i.putExtra("name",appoint1.get(position).name);
        i.putExtra("date",appoint1.get(position).date.toString());
        i.putExtra("contact",appoint1.get(position).contact);
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
