package nirma.finna_be_meme;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajiv on 04-09-2015.
 */
public class AdapterPatientList extends RecyclerView.Adapter<AdapterPatientList.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    List<InformationPatient> data = Collections.emptyList();

    public AdapterPatientList(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctorlistlayout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void setList(List<InformationPatient> data){
        this.data=data;
        Log.d("doctor1","here");
        notifyItemRangeChanged(0,data.size());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InformationPatient current = data.get(position);
        holder.Doctor_name.setText(current.Doctorname);
        holder.Date.setText(current.Date);
        holder.Appointmentstatus_patient.setText("You: "+current.Appointmentstatus_patient);
        holder.Appointmentstatus_doctor.setText("Doctor" +current.Appointmentstatus_doctor);

    }

    @Override
    public int getItemCount() {
        Log.d("doctor"," "+data.size());
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Doctor_name;
        TextView Date;
        TextView Appointmentstatus_doctor;
        TextView Appointmentstatus_patient;
        public MyViewHolder(View itemView) {
            super(itemView);

            Doctor_name=(TextView)itemView.findViewById(R.id.Doctor_name);
            Date=(TextView) itemView.findViewById(R.id.Date);
            Appointmentstatus_doctor=(TextView) itemView.findViewById(R.id.Appointmentstatus_doctor);
            Appointmentstatus_patient = (TextView) itemView.findViewById(R.id.Appointmentstatus_patient);
        }

    }
}

