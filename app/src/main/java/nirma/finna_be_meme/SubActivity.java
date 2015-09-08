package nirma.finna_be_meme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
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
        adapter = new AdapterDoctorlist(this,getData());
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
    public static List<InformationDoctorlist> getData() {
        List<InformationDoctorlist> dataz = new ArrayList<>();
        int[] icons = {R.mipmap.dr};
        String[] name = {"Dr.Sanjiv Haribhakti"};
        String[] data = {"GYNECOLOGIST"};
        String[] timestamp = {"20july2015-10:20:00"};
        for (int i = 0; i < name.length && i < icons.length; i++) {
            InformationDoctorlist current = new InformationDoctorlist();
            current.Iconid = icons[i];
            current.title = name[i];
            current.data=data[i];
            current.timestamp=timestamp[i];
            dataz.add(current);
        }
        return dataz;
    }

}
