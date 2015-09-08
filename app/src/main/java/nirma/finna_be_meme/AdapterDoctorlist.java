package nirma.finna_be_meme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by rajiv on 04-09-2015.
 */
public class AdapterDoctorlist extends RecyclerView.Adapter<AdapterDoctorlist.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;

    List<InformationDoctorlist> data = Collections.emptyList();

    public AdapterDoctorlist(Context context, List<InformationDoctorlist> data) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.appointment_user, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InformationDoctorlist current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.Iconid);
        holder.data.setText(current.data);
        holder.timestamp.setText(current.timestamp);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView data;
        ImageView icon;
        TextView timestamp;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.doctorname);
            icon = (ImageView) itemView.findViewById(R.id.doctordp);
            data = (TextView) itemView.findViewById(R.id.doctordetails);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
            data.setOnClickListener(this);
            timestamp.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("katha", "hello111");
            Intent i = new Intent(context,DoctorDetails.class);
            i.putExtra("hhi", "hi");
            context.startActivity(i);
        }
    }
}
