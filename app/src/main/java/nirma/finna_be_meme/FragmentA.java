package nirma.finna_be_meme;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {

    private Adapter adapter;
    private RecyclerView recyclerview;

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
        recyclerview = (RecyclerView) layout.findViewById(R.id.tablist);
        adapter = new Adapter(getActivity(), getData());
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return layout;

    }


}
