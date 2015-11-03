package nirma.finna_be_meme;


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
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


public class User_login extends AppCompatActivity implements View.OnClickListener {
    EditText lemail;
    EditText lpassword;
    Button llogin, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("USER LOGIN");
        lemail = (EditText) findViewById(R.id.email);
        lpassword = (EditText) findViewById(R.id.password);
        llogin = (Button) findViewById(R.id.loginb);
        llogin.setOnClickListener(this);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
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
        if (v == findViewById(R.id.loginb)) {
            final String email = lemail.getText().toString();
            final String password = lpassword.getText().toString();
            Log.d("login", email + " " + password);
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Patient");
            query.whereEqualTo("email", email);
            query.whereEqualTo("Password", password);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {
                        if (list.size() == 0)
                            Toast.makeText(User_login.this, "account does not exist or wrong combination of emailid and password",
                                    Toast.LENGTH_LONG).show();
                        else {
                            Intent i = new Intent(User_login.this, MainActivity.class);
                            i.putExtra("email", email);
                            startActivity(i);
                        }

                    } else {
                        Toast.makeText(User_login.this, "Sign up error" + " " + e,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Intent i = new Intent(User_login.this, Registration.class);
            startActivity(i);
        }
    }
}
