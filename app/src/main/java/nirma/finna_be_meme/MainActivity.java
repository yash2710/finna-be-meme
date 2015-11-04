package nirma.finna_be_meme;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerview;
    private Adapter adapter;
    private View containerview;
    private ViewPager mPager;
    Bundle bundle;
    Button go;
    public static String email;
    private SlidingTabLayout mTabs;
    CharSequence Titles[] = {"Home", "Events"};
    int Numboftabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//check
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        bundle = getIntent().getExtras();
        go=(Button)findViewById(R.id.button);
        email = bundle.getString("email");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setCustomTabView(R.layout.custom_tab_layout, R.id.tabText);
        this.setTitle("Appoint Me!");
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);
        Log.d("email", email + "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // aumtomatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
        }

//        if (id == R.id.navigate) {
//            startActivity(new Intent(this, SubActivity.class));
//        }

        if (id == R.id.details) {
            Intent i = new Intent(MainActivity.this, AppointmentDetail.class);
            i.putExtra("hhi", "hi");
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    //
    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] tabsText = getResources().getStringArray(R.array.tabs);
        int[] icons = {R.mipmap.ic_launcher, R.mipmap.gyn, R.mipmap.der};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabsText = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            //MyFragment myFragment = MyFragment.getInstance(position);
            Fragment fragment = null;
            if (position == 0) {
                fragment = new FragmentA();
            } else {
                fragment = new FragmentA();
            }
            return fragment;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Home";
            } else return "Appointment";
//            Drawable drawable = getResources().getDrawable(icons[position],null);
//            //drawable.setBounds(0,0,40,40);
//            ImageSpan imagespan = new ImageSpan(drawable);
//            SpannableString spannablestring = new SpannableString(" ");
//            spannablestring.setSpan(imagespan, 0, spannablestring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            return spannablestring;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    public static class MyFragment extends Fragment {

        private TextView textview;

        public static MyFragment getInstance(int position) {
            MyFragment myfragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myfragment.setArguments(args);
            return myfragment;
        }


        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_my, container, false);
            textview = (TextView) layout.findViewById(R.id.position);
            Bundle bundle = getArguments();
            if (bundle != null) {
                textview.setText("page currenty selected is " + bundle.getInt("position"));
            }
            return layout;
        }
    }
}
