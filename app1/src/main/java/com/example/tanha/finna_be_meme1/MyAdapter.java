package com.example.tanha.finna_be_meme1;

import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tanha on 8/12/2015.
 */
class Appoint {
    String id;
    String name;
    public Appoint(){}
    public Appoint(String id,String name){
        this.id=id;
        this.name=name;

    }
}

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //private ArrayList<String> mDataset;
    private List<Appoint> appoint= Collections.emptyList();
    /*private ArrayList<ParseObject> mDataset;
    private ArrayList<String> p_id=new ArrayList<String>();
    private ArrayList<String> p_name;*/

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.recycler_view1);
            txtFooter = (TextView) v.findViewById(R.id.recycler_view2);
        }
    }

    /*public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }*/


    public MyAdapter() {
        //mDataset= new ArrayList<String>(20);
        appoint=new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("appointment");
        query.whereEqualTo("dr_username", "dr_def@appointme.com");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("dr_username", "Retrieved " + list.size());
                    for(int i=0;i<list.size();i++) {

                        String id = (String) list.get(i).get("p_id");
                        String name=(String)list.get(i).get("p_name");

                        Appoint a=new Appoint(id,name);
                        appoint.add(a);
                        Log.d("hi", a.id + a.name);
                        Log.d("Hello",appoint.get(i).id+appoint.get(i).name);
                    }
                } else {
                    Log.d("dr_username", "Error: " + e.getMessage());
                }

            }
        });
       //mDataset=new ArrayList<String>(20);
        /*for(int i=0;i<20;i++)
        {
            mDataset.add(new String("$"+(i+1)+"$"));
        }*/
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("disp", "display");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {

        final String Id =(appoint.get(position)).id;
        final String name = (appoint.get(position)).name;
        holder.txtHeader.setText("ID: "+Id);
        holder.txtFooter.setText("Patient's Name: "+name);

    }

    @Override
    public int getItemCount() {
        
        Log.d("hfhd", "a" + appoint.size());

        return appoint.size();
    }
}
