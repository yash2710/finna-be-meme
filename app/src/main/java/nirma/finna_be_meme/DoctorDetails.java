package nirma.finna_be_meme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Yash on 12-Aug-15.
 */
public class DoctorDetails extends AppCompatActivity {

    Bundle i;
    GoogleMap mMap;
    TextView name,qualification,time,days;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        i = getIntent().getExtras();
        setUpMapIfNeeded();
        name = (TextView) findViewById(R.id.name);
        qualification = (TextView) findViewById(R.id.qual);
        time = (TextView) findViewById(R.id.time);
        days = (TextView) findViewById(R.id.cal);
        name.setText(i.getString("name","Dr. Sanjiv Haribhakti"));
        qualification.setText(i.getString("qual","-"));
        time.setText(i.getString("open_hours","-"));
        days.setText(i.getString("open_days","-"));

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
}