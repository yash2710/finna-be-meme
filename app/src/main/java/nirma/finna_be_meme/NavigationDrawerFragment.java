package nirma.finna_be_meme;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private Adapter adapter;
    private RecyclerView recyclerview;
    public static final String PREF_FILE_NAME = "test_pref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mdrawerLayout;
    private boolean mUserLearenDrawer;
    private boolean mFromSavedInstaceState;
    private View containerview;
    private boolean isDrawerOpened=false;
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearenDrawer = Boolean.valueOf(readFromPreference(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstaceState = true;
        }
    }

    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.mipmap.plus, R.mipmap.plus, R.mipmap.plus,R.mipmap.plus};
        String[] titles = {"hello", "katha", "tanha", "yash"};
        for (int i = 0; i < titles.length && i < icons.length; i++) {
            Information current = new Information();
            current.Iconid = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerview = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new Adapter(getActivity(), getData());
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setup(int fragment_id, DrawerLayout drawerlayout, final Toolbar toolbar) {
        containerview = getActivity().findViewById(fragment_id);
        mdrawerLayout = drawerlayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearenDrawer) {
                    mUserLearenDrawer = true;
                    saveToPreference(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearenDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            public void onDrawerSlide(View drawerView, float slideofset) {
                super.onDrawerSlide(drawerView, slideofset);
                if (slideofset < 0.6) {
                    toolbar.setAlpha(1 - slideofset);
                }
            }
        };
        mdrawerLayout.setDrawerListener(mDrawerToggle);
        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        if (!mUserLearenDrawer && !mFromSavedInstaceState) {
            mdrawerLayout.openDrawer(containerview);
        }
    }

    public static void saveToPreference(Context context, String prefrencename, String preferencevalue) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(prefrencename, preferencevalue);
        editor.apply();
    }

    public static String readFromPreference(Context context, String prefrencename, String defaultvalue) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedpreferences.getString(prefrencename, defaultvalue);
    }
}
