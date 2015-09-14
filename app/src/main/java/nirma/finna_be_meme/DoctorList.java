package nirma.finna_be_meme;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class DoctorList extends AppCompatActivity {

    private RecyclerView recyclerview;
    private AdapterDoctorlist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("List of Doctors");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerview = (RecyclerView)findViewById(R.id.doctorlist_user);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new AdapterDoctorlist(this,getData());
        adapter = new AdapterDoctorlist(this);
        recyclerview.setAdapter(adapter);
//        recyclerview = (RecyclerView)findViewById(R.id.list);
//        recyclerview.setLayoutManager(new GridLayoutManager(this,2));
//        adapter = new Adapter(this, getData());
//        recyclerview.setAdapter(adapter);
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
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
//        public static List<Information> getData() {
//        List<Information> data = new ArrayList<>();
//        int[] icons = {R.mipmap.den, R.mipmap.gyn, R.mipmap.der, R.mipmap.auy, R.mipmap.auy1, R.mipmap.car, R.mipmap.ges, R.mipmap.neu};
//        String[] titles = {"        DENTIST", "GYNECOLOGIST", "DERMITOLOGIST", "HOMEOPETHIC", "    AYURVEDIC", "CARDIOLOGIST", "GESTROENTERLOGIST", "  NEUROLOGIST"};
//        for (int i = 0; i < titles.length && i < icons.length; i++) {
//            Information current = new Information();
//            current.Iconid = icons[i];
//            current.title = titles[i];
//            data.add(current);
//        }
//        return data;
//    }ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");


}


