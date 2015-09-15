package nirma.finna_be_meme;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajiv on 04-09-2015.
 */
public class AdapterDoctorlist extends RecyclerView.Adapter<AdapterDoctorlist.MyViewHolder> {
    //private final ArrayList<Object> appoint;
    private LayoutInflater inflater;
    private Context context;
    List<InformationDoctorlist> data = Collections.emptyList();

    //    class Appoint {
//        int Doctorid;
//        String Doctor_name;
//        Stringxmlns:android="http://schemas.android.com/apk/res/android" Speciality;
//        String Degrees;
//        String city;
//        int Fees;
//        int Experience;
//        Drawable photo;
//        public Appoint(){}
//        public Appoint(int Doctorid,String Doctor_name,String Speciality,String Degrees,String city,int Fees,int Experience,Drawable photo){
//            this.Doctorid=Doctorid;
//            this.Doctor_name=Doctor_name;
//            this.Speciality=Speciality;
//            this.Degrees=Degrees;
//            this.city=city;
//            this.Fees=Fees;
//            this.Experience=Experience;
//            this.photo=photo;
//        }
//    }
    public AdapterDoctorlist(Context context){//, List<InformationDoctorlist> data) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        //----------this.data = data;-----

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = inflater.inflate(R.layout.appointment_user, parent, false);
        View view = inflater.inflate(R.layout.doctorlistlayout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void setList(List<InformationDoctorlist> data){
        this.data=data;
        Log.d("doctor1","here");
        notifyItemRangeChanged(0,data.size());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InformationDoctorlist current = data.get(position);
        holder.Doctor_name.setText(current.Doctor_name);
        holder.Fees.setText(current.Fees);
        holder.Degrees.setText(current.Degrees);
        holder.Experience.setText(current.Experience);
        holder.Speciality.setText(current.Speciality);
    }

    @Override
    public int getItemCount() {
        Log.d("doctor"," "+data.size());
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder  {//implements View.OnClickListener
        TextView Doctor_name;
        TextView Speciality;
        TextView Degrees;
        TextView Fees;
        TextView Experience;

        public MyViewHolder(View itemView) {
            super(itemView);

            Doctor_name=(TextView)itemView.findViewById(R.id.Doctor_name);
            Speciality=(TextView) itemView.findViewById(R.id.Speciality);
            Degrees=(TextView) itemView.findViewById(R.id.Degrees);
            Fees=(TextView) itemView.findViewById(R.id.Fees);
            Experience=(TextView) itemView.findViewById(R.id.Experience);
            //photo=(ImageView) itemView.findViewById(R.id.photo);
//            Doctor_name.setOnClickListener(this);
//            Speciality.setOnClickListener(this);
//            Degrees.setOnClickListener(this);
//            Fees.setOnClickListener(this);
//            Experience.setOnClickListener(this);
//            photo.setOnClickListener(this);

        }

//        @Override
//        public void onClick(View v) {
//            Log.d("katha", "hello111");
//            Intent i = new Intent(context,DoctorDetails.class);
//            i.putExtra("hhi", "hi");
//            context.startActivity(i);
//        }
    }
}

