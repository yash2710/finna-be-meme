package nirma.finna_be_meme;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;


public class Login extends AppCompatActivity implements View.OnClickListener{
    Button request;
    EditText pname;
    Button dob,appointmentdate;
    EditText problem;
    RadioGroup gender;
    Calendar appd, date;
    static int DOB_ID = 0;
    static int APP_ID = 1;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("USER LOGIN");
        request=(Button)findViewById(R.id.request);
        request.setOnClickListener(this);
        pname=(EditText)findViewById(R.id.patient_name);
        dob=(Button)findViewById(R.id.dob);
        problem=(EditText)findViewById(R.id.problem);
        gender=(RadioGroup)findViewById(R.id.gendergroup);
        appointmentdate=(Button)findViewById(R.id.appointmentdate);

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
        date = new Calendar() {
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

        appd.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));
        date.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DOB_ID);
            }
        });
        appointmentdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(APP_ID);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==DOB_ID)
            return new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    date.set(i, i1, i2);
                    dob.setText(i2+"/"+(i1+1)+"/"+i);
                }
            },date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE));
        if(id==APP_ID)
            return new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    appd.set(i,i1,i2);
                    appointmentdate.setText(i2+"/"+(i1+1)+"/"+i);
                }
            },appd.get(Calendar.YEAR),appd.get(Calendar.MONTH),appd.get(Calendar.DATE));
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
        if(v==findViewById(R.id.request)){
            if(pname.getText().toString().equals("") || (date.get(Calendar.MONTH) == cal.get(Calendar.MONTH) && date.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && date.get(Calendar.DATE) == cal.get(Calendar.DATE))){
                Toast.makeText(Login.this, "some fields are empty :)",
                        Toast.LENGTH_LONG).show();
            }
            else {
                Log.d("doctor1", "P_name" + pname.getText().toString());
                ParseObject patient = new ParseObject("Patient");
                patient.put("P_id","9408756917");
                patient.put("Doctorid","1");
                patient.put("appointmentid","1");
                patient.put("doctor_confirm",false);
                patient.put("patient_confirm",true);
                patient.put("P_name",pname.getText().toString());
                patient.put("DOB",new Date(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE)));
                patient.put("prob_desc", problem.getText().toString());
                patient.put("Gender", ((RadioButton)gender.getChildAt(gender.indexOfChild(gender.findViewById(gender.getCheckedRadioButtonId())))).getText().toString());
                patient.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e!=null)
                            e.printStackTrace();
                    }
                });
            }
        }
    }

}
