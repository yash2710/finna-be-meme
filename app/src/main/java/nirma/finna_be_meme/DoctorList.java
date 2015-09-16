package nirma.finna_be_meme;

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
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DoctorList extends AppCompatActivity {

    private RecyclerView recyclerview;
    private AdapterDoctorlist adapter;
    private List<InformationDoctorlist> data = Collections.emptyList();
    private String speciality;
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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {

                    for (int i = 0; i < list.size(); i++) {
                        String Speciality = (String) list.get(i).get("Speciality");
                        //if (speciality.equals(Speciality.toUpperCase())) {
                            String Doctor_name = (String) list.get(i).get("Doctor_name");
                            String Degrees = (String) list.get(i).get("Degrees");
                            String Fees = (String) list.get(i).get("Fees");
                            String Experience = (String) list.get(i).get("Experience");
                            String Likes=(String)list.get(i).get("Likes");
                            InformationDoctorlist current = new InformationDoctorlist();
                            current.Doctor_name = Doctor_name;
                            current.Speciality = Speciality;
                            current.Degrees = Degrees;
                            current.Fees = Fees;
                            current.Experience = Experience;
                            current.Likes = Likes;
                            //current.uri=uri;
                            data.add(current);
                            Log.d("doctor1", "hello this is katha123" + data.size());
                            // }
                        //}
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

}


