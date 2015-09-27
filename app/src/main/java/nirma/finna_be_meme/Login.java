package nirma.finna_be_meme;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;


public class Login extends AppCompatActivity implements View.OnClickListener{
    Button request;
    EditText pname,dob,appointmentdate;
    EditText problem;
    RadioGroup gender;
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
        dob=(EditText)findViewById(R.id.dob);
        problem=(EditText)findViewById(R.id.problem);
        gender=(RadioGroup)findViewById(R.id.gendergroup);
        appointmentdate=(EditText)findViewById(R.id.appointmentdate);
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
            if(pname.getText().toString().equals("") || dob.getText().toString().equals("") || appointmentdate.getText().toString().equals("")){
                Toast.makeText(Login.this, "some fields are empty :)",
                        Toast.LENGTH_LONG).show();
            }
            else {
                Log.d("doctor1", "P_name" + pname.getText().toString());
                ParseObject patient = new ParseObject("Patient");
                patient.put("P_id","9408756916");
                patient.put("Doctorid","1");
                patient.put("appointmentid","1");
                patient.put("doctor_confirm","false");
                patient.put("patient_confirm","false");
                patient.put("P_name",pname.getText().toString());
                patient.put("DOB",dob.getText().toString());
                patient.put("prob_desc", problem.getText().toString());
                patient.put("Gender", ((RadioButton)gender.getChildAt(gender.indexOfChild(gender.findViewById(gender.getCheckedRadioButtonId())))).getText().toString());
                patient.saveInBackground();
            }
        }
    }
}
