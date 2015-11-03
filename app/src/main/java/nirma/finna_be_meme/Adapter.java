package nirma.finna_be_meme;

/**
 * Created by rajiv on 20-07-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    String email=MainActivity.email;
    List<Information> data = Collections.emptyList();

    public Adapter(Context context, List<Information> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.Iconid);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.Listicon);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("email","hello"+ email);
            Log.d("katha", "hello111");
            //context.startActivity(new Intent(context,DoctorList.class));
            Intent i = new Intent(context, DoctorList.class);
            i.putExtra("Speciality",(String)title.getText());
            Log.d("doctor1", (String)title.getText());
            context.startActivity(i);

        }
    }
}
