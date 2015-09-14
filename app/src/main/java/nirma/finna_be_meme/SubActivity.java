

package nirma.finna_be_meme;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SubActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private AdapterDoctorlist adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("Appointments");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerview = (RecyclerView)findViewById(R.id.appointmentlist_user);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new AdapterDoctorlist(this,getData());
        adapter = new AdapterDoctorlist(this);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

//    public static List<InformationDoctorlist> getData() {
//
//        final List<InformationDoctorlist> data = new ArrayList<>();
//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
//        query.findInBackground(new FindCallback<ParseObject>() {
//
//            @Override
//            public void done(List<ParseObject> list, com.parse.ParseException e) {
//
//                if (e == null) {
//
//                    for (int i = 0; i < list.size(); i++) {
//                        InformationDoctorlist current = new InformationDoctorlist();
//                        int Doctorid = (int) list.get(i).get("Doctorid");
//                        String Doctor_name = (String) list.get(i).get("Doctor_name");
//                        String Speciality = (String) list.get(i).get("Speciality");
//                        String Degrees = (String) list.get(i).get("Degrees");
//                        //String city = (String) list.get(i).get("city");
//                        int Fees = (int) list.get(i).get("Fees");
//                        int Experience = (int) list.get(i).get("Experience");
//                        //Image photo = (Image)list.get(i).get("Photo");
//                        //Drawable photo = (Drawable) list.get(i).get("Photo");
//                        current.Doctorid = Doctorid;
//                        current.Doctor_name = Doctor_name;
//                        current.Speciality = Speciality;
//                        current.Degrees = Degrees;
//                        current.Fees = Fees;
//                        current.Experience = Experience;
//                        //current.photo = photo;
//                        data.add(current);
//                        Log.d("doctor1", "hello this is katha123" + data.size());
//                    }
//                } else {
//                    Log.d("doctor", "Error: " + e.getMessage());
//                }
//            }
//        });
//
//            Log.d("doctor1", "data size" + data.size());
//            return data;
//
//    }

}
