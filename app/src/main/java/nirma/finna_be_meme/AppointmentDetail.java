package nirma.finna_be_meme;

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
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AppointmentDetail extends AppCompatActivity implements ClickListener {

    private RecyclerView recyclerview;
    private AdapterAppointment adapter;
    private List<InformationAppointmentlist> data = Collections.emptyList();
    Bundle i;
    String email=MainActivity.email;
    String Doctor_name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("Appointments");
        i = getIntent().getExtras();
       // email = i.getString("email");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerview = (RecyclerView) findViewById(R.id.appointment);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterAppointment(this);
        recyclerview.setAdapter(adapter);
        recyclerview.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerview, this));
        data = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("appointment_user");
        query.whereEqualTo("P_email", email);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        String Timestamp = df.format(list.get(i).get("App_date"));
                        Doctor_name=(String)list.get(i).get("Doctor_name");
                        String confirm = "";
                        if ((boolean) list.get(i).get("doctor_confirm") && (boolean) list.get(i).get("patient_confirm"))
                            confirm = "Confirmed";
                        else if ((boolean) list.get(i).get("doctor_confirm"))
                            confirm = "Pending";
                        else
                            confirm = "Requested";
                        InformationAppointmentlist current = new InformationAppointmentlist();
                        current.Doctor_name = Doctor_name;
                        current.Timestamp = Timestamp;
                        current.confirm = confirm;
                        data.add(current);
                        Log.d("doctor1", "hello this is katha123" + data.size());
                    }
                } else {
                    Log.d("doctor", "Error: " + e.getMessage());
                }
                adapter.setList(data);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointment_detail, menu);
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

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
