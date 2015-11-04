package nirma.finna_be_meme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class Login extends AppCompatActivity implements View.OnClickListener {
    Button request;
    Button appointmentdate;
    EditText problem;
    Calendar appd;
    static int APP_ID = 1;
    Calendar cal;
    String email = MainActivity.email;
    Bundle bundle;
    int appid;
    private List<InformationAppointmentlist> data = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        data = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("USER LOGIN");
        request = (Button) findViewById(R.id.request);
        request.setOnClickListener(this);
        problem = (EditText) findViewById(R.id.problem);
        appointmentdate = (Button) findViewById(R.id.appointmentdate);
        bundle = getIntent().getExtras();
        cal = Calendar.getInstance();
        appd = new Calendar() {
            @Override
            public void add(int i, int i1) {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            protected void computeTime() {

            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public void roll(int i, boolean b) {

            }
        };
        appointmentdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(APP_ID);
            }
        });
        appd.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case 1:
                return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        appd.set(i, i1, i2);
                        appointmentdate.setText(i2 + "/" + (i1 + 1) + "/" + i);
                        showDialog(2);
                    }
                }, appd.get(Calendar.YEAR), appd.get(Calendar.MONTH), appd.get(Calendar.DATE));

            case 2:
                return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        appd.set(Calendar.HOUR,i);
                        appd.set(Calendar.MINUTE, i1);
                        appd.set(Calendar.ZONE_OFFSET, cal.get(Calendar.ZONE_OFFSET));
                        appointmentdate.setText(appointmentdate.getText().toString() + " " + i + ":" + i1);
                    }
                }, appd.get(Calendar.HOUR_OF_DAY),appd.get(Calendar.MINUTE),true);
        }
        return super.onCreateDialog(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
    public void onClick(View v) {

        if (v == findViewById(R.id.request)) {
            if (problem.getText().toString().equals("")) {
                Toast.makeText(Login.this, "some fields are empty :)",
                        Toast.LENGTH_LONG).show();
            } else {
                    final ParseObject patient = new ParseObject("appointment_user");
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("appointment_user");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> list, ParseException e) {
                            if (e == null) {
                                appid = list.size() + 1;
                                Log.d("login", "" + appid);
                                patient.put("P_email", email);
                                patient.put("D_email", bundle.getString("email", ""));
                                patient.put("Problem_description", problem.getText().toString());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                try {
                                    patient.put("App_date", dateFormat.parse(appointmentdate.getText().toString()));
                                } catch (java.text.ParseException e1) {
                                    e1.printStackTrace();
                                }
                                Log.d("login", "" + appid);
                                patient.put("App_ID", appid);
                                patient.put("doctor_confirm", false);
                                patient.put("patient_confirm", true);
                                patient.put("Doctor_name",DoctorDetails.dname);
                            } else {

                            }
                        }
                    });
                    ParseObject patient = new ParseObject("appointment_user");
                    patient.put("P_email", email);
                    patient.put("D_email", bundle.getString("email"));
                    patient.put("Problem_description", problem.getText().toString());
                    patient.put("App_date", appd);
                    patient.put("doctor_confirm", false);
                    patient.put("patient_confirm", true);


                    patient.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null)
                                Toast.makeText(getApplicationContext(),"appointment register", Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText(getApplicationContext(), "Sorry for inconvenionce!! There is some error :(", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Login.this, User_login.class);
                                startActivity(i);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("error", "error");
                }
            }
        }
    }

}
