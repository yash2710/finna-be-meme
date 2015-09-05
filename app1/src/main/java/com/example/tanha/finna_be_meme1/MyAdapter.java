package com.example.tanha.finna_be_meme1;

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
import java.util.List;

/**
 * Created by Tanha on 8/12/2015.
 */
class Appoint {
    String id;
    String name;

    public Appoint(String id,String name){
        this.id=id;
        this.name=name;

    }
}

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //private ArrayList<String> mDataset;
    private ArrayList<Appoint> appoint;
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
                        Log.d("hi",name);
                        Appoint a=new Appoint(id,name);
                        appoint.add(a);
                    }
                } else {
                    Log.d("dr_username", "Error: " + e.getMessage());
                }

            }
        });
       /*mDataset=new ArrayList<String>(20);
        for(int i=0;i<20;i++)
        {
            mDataset.add(new String("$"+(i+1)+"$"));
        }*/
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.d("disp", "display");
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
        //return p_id.size();
        return appoint.size();
    }
}
