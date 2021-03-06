package nirma.finna_be_meme;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.parse.FindCallback;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Searchlist extends AppCompatActivity implements ClickListener {

    private RecyclerView recyclerview;
    private AdapterDoctorlist adapter;
    private List<InformationDoctorlist> data = Collections.emptyList();
    private String Doctorname;
    RadioGroup dis_select;
    Bundle i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("List of Doctors");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerview = (RecyclerView) findViewById(R.id.doctorlist_user);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterDoctorlist(this);//, getData());
        recyclerview.setAdapter(adapter);
        recyclerview.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerview, this));
        data = new ArrayList<>();
        i = getIntent().getExtras();
        Doctorname = i.getString("dname", "Dr.Sanjiv HariBhakti");
        //////location of user////////
        GPSTracker gps = new GPSTracker(this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        Log.d("GPS", latitude + "," + longitude);
        ParseGeoPoint point = new ParseGeoPoint(latitude, longitude);
        //////////////////////////////

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
        if (latitude != 0.0 && longitude != 0.0)
            query.whereWithinKilometers("location", point, 10);////static value 10km
        query.whereEqualTo("Doctor_name", Doctorname);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {

                    for (int i = 0; i < list.size(); i++) {
                        String Speciality = (String) list.get(i).get("Speciality");
                        int Doctorid = (int) list.get(i).get("Doctorid");
                        String Doctor_name = (String) list.get(i).get("Doctor_name");
                        ArrayList<String> Degrees = (ArrayList<String>) list.get(i).get("Degrees");
                        String Fees = (String) list.get(i).get("Fees");
                        String email = (String) list.get(i).get("email");
                        String Experience = (String) list.get(i).get("Experience");
                        String Likes = (String) list.get(i).get("Likes");
                        ArrayList<String> Days = (ArrayList<String>) list.get(i).get("Days");
                        ArrayList<String> Time = (ArrayList<String>) list.get(i).get("Time");
                        InformationDoctorlist current = new InformationDoctorlist();
                        current.Doctor_name = Doctor_name;
                        current.Speciality = Speciality;
                        current.Degrees = Degrees;
                        try {
                            current.photo = list.get(i).getParseFile("Photo").getData();
                        } catch (com.parse.ParseException e1) {
                            e1.printStackTrace();
                        }
                        current.Fees = Fees;
                        current.Experience = Experience;
                        current.Likes = Likes;
                        current.Days = Days;
                        current.Time = Time;
                        current.Doctorid = Doctorid;//current.uri=uri;
                        current.email = email;
                        data.add(current);
                        Log.d("doctor1", "hello this is katha123" + data.size());

                    }
                } else {
                    Log.d("doctor", "Error: " + e.getMessage());
                }
                adapter.setList(data);
            }
        });

        Log.d("doctor1", "data size" + data.size());
    }


    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_doctor_list, menu);
//        return true;
//    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(Searchlist.this, DoctorDetails.class);
        i.putExtra("name", data.get(position).Doctor_name);
        i.putExtra("qual", data.get(position).Degrees);
        i.putExtra("Speciality", data.get(position).Speciality);
        i.putExtra("open_hours", data.get(position).Time);
        i.putExtra("open_days", data.get(position).Days);
        i.putExtra("hhi", "hi");
        i.putExtra("email", data.get(position).email);
        //put all other extras that can be passed to the activity
        startActivity(i);
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}


