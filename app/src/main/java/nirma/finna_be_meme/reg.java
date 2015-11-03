package nirma.finna_be_meme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.Date;
import java.util.Calendar;

public class reg extends AppCompatActivity implements View.OnClickListener {
    Button request;
    EditText pname, email, password, cpassword;
    Button dob;
    RadioGroup gender;
    Calendar date;
    static int DOB_ID = 0;
    Calendar cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        request = (Button) findViewById(R.id.registration);
        request.setOnClickListener(this);
        pname = (EditText) findViewById(R.id.patient_name);
        email = (EditText) findViewById(R.id.email);
        dob = (Button) findViewById(R.id.dob);
        gender = (RadioGroup) findViewById(R.id.gendergroup);
        password = (EditText) findViewById(R.id.password);
        cpassword = (EditText) findViewById(R.id.cpassword);
        cal = Calendar.getInstance();

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

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DOB_ID);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    protected Dialog onCreateDialog(int id) {
        if (id == DOB_ID)
            return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    date.set(i, i1, i2);
                    dob.setText(i2 + "/" + (i1 + 1) + "/" + i);
                }
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));
        return super.onCreateDialog(id);
    }

    @Override
    public void onClick(View v) {
        final String usernametxt;
        final String emailtxt;
        final String passwordtxt;
        final String confirmpasswordtxt;
        final Date dob;
        final String Gender;
        if (v == findViewById(R.id.registration)) {
            usernametxt = pname.getText().toString();
            emailtxt = email.getText().toString();
            passwordtxt = password.getText().toString();
            confirmpasswordtxt = cpassword.getText().toString();
            dob = new Date(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));
            Gender = ((RadioButton) gender.getChildAt(gender.indexOfChild(gender.findViewById(gender.getCheckedRadioButtonId())))).getText().toString();
            if (usernametxt.equals("") || passwordtxt.equals("") || emailtxt.equals("") || confirmpasswordtxt.equals("")) {
                Toast.makeText(getApplicationContext(), "Please complete the sign up form", Toast.LENGTH_LONG).show();
            } else if (!confirmpasswordtxt.equals(passwordtxt)) {
                Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG).show();
            } else {
                ParseUser user = new ParseUser();
                user.setUsername(emailtxt);
                user.setPassword(passwordtxt);
                user.setEmail(emailtxt);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            ParseObject patient = new ParseObject("Patient");
                            patient.put("P_name", usernametxt);
                            patient.put("Password", passwordtxt);
                            patient.put("email", emailtxt);
                            patient.put("DOB", dob);
                            patient.put("Gender", Gender);
                            patient.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e != null)
                                        Toast.makeText(getApplicationContext(), e + "Error in Sign up", Toast.LENGTH_LONG).show();
                                    else {
                                        Toast.makeText(getApplicationContext(), "Successfully Signed up, please log in.", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(reg.this, User_login.class);
                                        startActivity(i);
                                    }
                                }
                            });

                        }
                        else
                            Toast.makeText(getApplicationContext(), e + "Error in Sign up Account already exist", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}
