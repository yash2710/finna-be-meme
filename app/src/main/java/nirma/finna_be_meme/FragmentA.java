package nirma.finna_be_meme;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment implements View.OnClickListener {
Context context;
    private Adapter adapter;
    private RecyclerView recyclerview;
    Button go;
    EditText dname;

    public FragmentA() {
        // Required empty public constructor
    }

    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.mipmap.den, R.mipmap.gyn, R.mipmap.der, R.mipmap.auy, R.mipmap.auy1, R.mipmap.car, R.mipmap.ges, R.mipmap.neu};
        String[] titles = {"      DENTIST", "GYNECOLOGIST", "DERMATOLOGIST", "HOMEOPETHIC", "  AYURVEDIC", "CARDIOLOGIST", "GESTROENTERLOGIST", "  NEUROLOGIST"};
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
        View layout = inflater.inflate(R.layout.fragment_a, container, false);
        context = layout.getContext();
        go = (Button) layout.findViewById(R.id.button);
        go.setOnClickListener(this);
        dname=(EditText)layout.findViewById(R.id.etext);
        recyclerview = (RecyclerView) layout.findViewById(R.id.tablist);
        adapter = new Adapter(getActivity(), getData());
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return layout;

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(context, Searchlist.class);
        i.putExtra("dname",dname.getText().toString());
        startActivity(i);
    }
}
