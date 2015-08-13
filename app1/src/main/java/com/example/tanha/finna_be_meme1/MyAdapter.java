package com.example.tanha.finna_be_meme1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tanha on 8/12/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
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
            //txtFooter.setText("df");
        }
    }

    /*public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }*/


    public MyAdapter() {
        mDataset= new ArrayList<String>(20);
//        p_id=new ArrayList<String>(10);
//        p_name=new ArrayList<String>(10);
//        ParseObject p=new ParseObject("appointment");
//        p.pinInBackground();
//        ParseQuery query=new ParseQuery("appointment");
//        query.whereEqualTo("Dr_username", ParseUser.getCurrentUser().getUsername());
//        query.fromLocalDatastore();
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> list, com.parse.ParseException e) {
//                if (e == null) {
//                     Results were successfully found from the local datastore.
//
//                    for(int i=0;i<list.size();i++)
//                    {
//                        mDataset.add(list.get(i));
//                        p_id.add(mDataset.get(i).getString("p_id"));
//                        p_name.add(mDataset.get(i).getString("p_name"));
//                    }
//                } else {
//                     There was an error.
//                    e.printStackTrace();
//                }
//            }


//        });
        mDataset=new ArrayList<String>(20);
        for(int i=0;i<20;i++)
        {
            mDataset.add(new String("$"+(i+1)+"$"));
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name1 = mDataset.get(position);
       // final String name1 = p_id.get(position);
        //final String name2 = p_name.get(position);
        holder.txtHeader.setText("ID: "+position);
        holder.txtFooter.setText("Patient's Name: " + name1);

    }

    @Override
    public int getItemCount() {
        //return p_id.size();
        return mDataset.size();
    }
}
