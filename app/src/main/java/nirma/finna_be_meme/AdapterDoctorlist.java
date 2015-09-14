
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
class Appoint {
    int Doctorid;
    String Doctor_name;
    String Speciality;
    String Degrees;
    int Fees;
    int Experience;
    public Appoint(){}
    public Appoint(int Doctorid,String Doctor_name,String Speciality,String Degrees,int Fees,int Experience){
        this.Doctorid=Doctorid;
        this.Doctor_name=Doctor_name;
        this.Speciality=Speciality;
        this.Degrees=Degrees;
        this.Fees=Fees;
        this.Experience=Experience;
    }
}
public class AdapterDoctorlist extends RecyclerView.Adapter<AdapterDoctorlist.MyViewHolder> {


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

        }
    }
    //private final ArrayList<Object> appoint;
    private List<Appoint> appoint = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    public AdapterDoctorlist(Context context) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        appoint=new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                Log.d("doctor1","size"+list.size());

                if (e == null) {
                    for(int i=0;i<list.size();i++){
                        int Doctorid = (int)list.get(i).get("Doctorid");
                        String Doctor_name = (String)list.get(i).get("Doctor_name");
                        String Speciality = (String)list.get(i).get("Speciality");
                        String Degrees = (String)list.get(i).get("Degrees");
                        String city = (String)list.get(i).get("city");
                        int Fees = (int)list.get(i).get("Fees");
                        int Experience = (int)list.get(i).get("Experience");
                        //ImageView photo = (ImageView)list.get(i).get("Photo");
                        //Drawable photo = (Drawable)list.get(i).get("Photo");
                        Appoint a = new Appoint(Doctorid,Doctor_name,Speciality,Degrees,Experience,Fees);
                        Log.d("doctor1","a"+a);
                        appoint.add(a);
                    }
                } else {
                    Log.d("doctor", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = inflater.inflate(R.layout.appointment_user, parent, false);
        View view = inflater.inflate(R.layout.doctorlistlayout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //int Doctorid = (appoint.get(position).Doctorid;
        String Doctor_name =(appoint.get(position)).Doctor_name;
        String Speciality = (appoint.get(position)).Speciality;
        String Degrees = (appoint.get(position)).Degrees;
        int Fees = (appoint.get(position)).Fees;
        int Experience =(appoint.get(position)).Experience;

        holder.Doctor_name.setText(Doctor_name);
        holder.Fees.setText(Fees);
        holder.Degrees.setText(Degrees);
        holder.Experience.setText(Experience);
        holder.Speciality.setText(Speciality);
    }

    @Override
    public int getItemCount() {
        Log.d("doctor1","size is"+appoint.size());
        return appoint.size();
    }
}
