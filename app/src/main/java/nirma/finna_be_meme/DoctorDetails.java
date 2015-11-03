package nirma.finna_be_meme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Yash on 12-Aug-15.
 */
public class DoctorDetails extends AppCompatActivity implements View.OnClickListener {

    Bundle i;
    GoogleMap mMap;
    TextView name,qualification,time,days,speciality;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_doctor_details);
        setContentView(R.layout.activity_doctor_details);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        i = getIntent().getExtras();
        setUpMapIfNeeded();
        name = (TextView) findViewById(R.id.name);
        qualification = (TextView) findViewById(R.id.qual);
        String degree="";
        for(String s: i.getStringArrayList("qual")){
            degree+=s + " ";
        }
        time = (TextView) findViewById(R.id.time);
        days = (TextView) findViewById(R.id.cal);
        speciality = (TextView) findViewById(R.id.speciality);
        name.setText(i.getString("name", "Dr. Sanjiv Haribhakti"));
        qualification.setText(degree);
        time.setText(i.getString("open_hours", "-"));
        String day="";
        for(String s: i.getStringArrayList("open_days")){
            day+=s + " ";
        }
        days.setText(day);
        speciality.setText(i.getString("Speciality", "Gynecologist"));
        Button book = (Button) findViewById(R.id.book);
        book.setOnClickListener(this);
        this.setTitle(i.getString("name", "Dr. Sanjiv Haribhakti"));
        Databasehandler db = new Databasehandler(this);
        db.addPatient(new Data(0, "Katha"));
        List<Data> data = db.getAllData();

        for (Data cn : data) {
            String log = "Id: " + cn.getPatient_id() + " ,Name: " + cn.getPatient_name();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap(i.getDouble("lat",23.0470357),i.getDouble("lon",72.5437723));
            }
        }
    }

    private void setUpMap(double lat, double lon) {
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(0,0));
        boundsBuilder.include(new LatLng(lat, lon));
// pan to see all markers on map:
        LatLngBounds bounds = boundsBuilder.build();
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(i.getString("name")));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .zoom(16)
                .target(new LatLng(lat,lon))
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onClick(View v) {
     if(v==findViewById(R.id.book)){
         Intent intent=new Intent(this,Login.class);
         intent.putExtra("email",i.getString("email"));
         startActivity(intent);
     }
    }
}
