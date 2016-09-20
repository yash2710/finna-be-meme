package nirma.finna_be_meme;

import android.content.Context;
import android.graphics.BitmapFactory;
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

    List<InformationDoctorlist> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public AdapterDoctorlist(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = inflater.inflate(R.layout.appointment_user, parent, false);
        View view = inflater.inflate(R.layout.doctorlistlayout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void setList(List<InformationDoctorlist> data) {
        this.data = data;
        Log.d("doctor1", "here");
        notifyItemRangeChanged(0, data.size());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InformationDoctorlist current = data.get(position);
        holder.Doctor_name.setText(current.Doctor_name);
        holder.Fees.setText("Fees:" + current.Fees);
        String degree = "";
        for (String s : current.Degrees) {
            degree += s;
        }
        holder.Degrees.setText(degree);
        holder.Experience.setText(current.Experience + " yrs exp.");
        holder.Likes.setText(current.Likes);
        try {
            holder.Photo.setImageBitmap(BitmapFactory.decodeByteArray(current.photo, 0, current.photo.length));
        } catch (Exception e) {
        }
    }

    public void setPhoto(int position) {

    }

    @Override
    public int getItemCount() {
        Log.d("doctor", " " + data.size());
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener
        TextView Doctor_name;
        TextView Speciality;
        TextView Degrees;
        TextView Fees;
        TextView Experience;
        TextView Likes;
        ImageView Photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            Photo = (ImageView) itemView.findViewById(R.id.Photo);
            Doctor_name = (TextView) itemView.findViewById(R.id.Doctor_name);
            Degrees = (TextView) itemView.findViewById(R.id.Degrees);
            Fees = (TextView) itemView.findViewById(R.id.Fees);
            Experience = (TextView) itemView.findViewById(R.id.Experience);
            Likes = (TextView) itemView.findViewById(R.id.likes);
        }
    }
}

