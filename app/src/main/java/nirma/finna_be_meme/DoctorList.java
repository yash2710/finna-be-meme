package nirma.finna_be_meme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DoctorList extends AppCompatActivity implements ClickListener{

    private RecyclerView recyclerview;
    private AdapterDoctorlist adapter;
    private List<InformationDoctorlist> data = Collections.emptyList();
    private String speciality;
    RadioGroup dis_select;
    Bundle i;
    ProgressDialog p = null;

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
        if (savedInstanceState == null) {
            i = getIntent().getExtras();
            if (i == null) {
                speciality = null;
            } else {
                speciality = i.getString("Speciality");
            }
        } else {
            speciality = (String) savedInstanceState.getSerializable("Speciality");
        }
        //////location of user////////
        GPSTracker gps = new GPSTracker(this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        Log.d("GPS",latitude+","+longitude);
        ParseGeoPoint point = new ParseGeoPoint(latitude,longitude);
        //////////////////////////////

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
                if(latitude!=0.0 && longitude!=0.0)
                    query.whereWithinKilometers("location",point,10);////static value 10km
        p=new ProgressDialog(this);
        p.setMessage("Loading");
        p.setCancelable(false);
        p.show();
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, com.parse.ParseException e) {
                        if (e == null) {

                            for (int i = 0; i < list.size(); i++) {
                                String Speciality = (String) list.get(i).get("Speciality");
                                Log.d("doctor1", speciality + Speciality );
                                if (speciality.equalsIgnoreCase(Speciality)) {
                                    int Doctorid = (int) list.get(i).get("Doctorid");
                                    String Doctor_name = (String) list.get(i).get("Doctor_name");
                                    ArrayList<String> Degrees = (ArrayList<String>) list.get(i).get("Degrees");
                                    String Fees = (String) list.get(i).get("Fees");
                                    String Experience = (String) list.get(i).get("Experience");
                                    String Likes=(String)list.get(i).get("Likes");
                                    ArrayList<String> Days = (ArrayList<String>) list.get(i).get("Days");
                                    ArrayList<String> Time=(ArrayList<String>)list.get(i).get("Time");
                                    final InformationDoctorlist current = new InformationDoctorlist();
                                    current.Doctor_name = Doctor_name;
                                    try {
                                        current.photo = list.get(i).getParseFile("Photo").getData();
                                    } catch (com.parse.ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                    current.Speciality = Speciality;
                                    current.Degrees = Degrees;
                                    current.Fees = Fees;
                                    current.Experience = Experience;
                                    current.Likes = Likes;
                                    current.Days = Days;
                                    current.Time = Time;
                                    current.Doctorid=Doctorid;//current.uri=uri;
                                    data.add(current);
                                    Log.d("doctor1", "hello this is katha123" + data.size());
                                    // }
                                }
                            }
                        } else {
                            Log.d("doctor", "Error: " + e.getMessage());
                        }
                        adapter.setList(data);
                        hidePDialog();
            }
        });

        Log.d("doctor1", "data size" + data.size());
    }

    void hidePDialog(){
        if(p!=null){
            p.dismiss();
            p=null;
        }
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
        Intent i = new Intent(DoctorList.this,DoctorDetails.class);
        i.putExtra("name",data.get(position).Doctor_name);
        i.putExtra("qual",data.get(position).Degrees);
        i.putExtra("Speciality",data.get(position).Speciality);
        i.putExtra("open_hours",data.get(position).Time);
        i.putExtra("open_days",data.get(position).Days);
        i.putExtra("photo",data.get(position).photo);
        //put all other extras that can be passed to the activity
        startActivity(i);
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}


