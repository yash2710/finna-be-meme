package com.example.tanha.finna_be_meme1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tanha on 8/12/2015.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private LayoutInflater inflator;
    private Context context;
    List<Appoint> appoint = Collections.emptyList();


    /*public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }*/


    public MyAdapter(Context context) {
        this.context = context;
        inflator = LayoutInflater.from(context);

    }

    public void setList(List<Appoint> appoint) {
        this.appoint = appoint;
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("disp", "display");
        View v = inflator.inflate(R.layout.recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {

        final String Id = (appoint.get(position)).id;
        final String name = (appoint.get(position)).date;
        holder.txtHeader.setText(Id);
        holder.txtFooter.setText(name);

    }

    @Override
    public int getItemCount() {

        Log.d("hfhd", "a" + appoint.size());

        return appoint.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.recycler_view2);
            txtFooter = (TextView) v.findViewById(R.id.recycler_view1);
        }


    }
}
