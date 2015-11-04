package nirma.finna_be_meme;

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
 * Created by rajiv on 11/3/2015.
 */
public class AdapterAppointment extends RecyclerView.Adapter<AdapterAppointment.MyViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<InformationAppointmentlist> data= Collections.emptyList();
    public AdapterAppointment(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.appointment_user, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void setList(List<InformationAppointmentlist> data){
        this.data=data;
        notifyItemRangeChanged(0,data.size());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InformationAppointmentlist current = data.get(position);
        holder.Doctor_name.setText(current.Doctor_name);
        holder.Timestamp.setText(current.Timestamp);
        holder.confirm.setText(current.confirm);
    }

    @Override
    public int getItemCount() {
        Log.d("doctor"," "+data.size());
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Doctor_name;
        TextView Timestamp;
        TextView confirm;
        public MyViewHolder(View itemView) {
            super(itemView);
            Doctor_name=(TextView)itemView.findViewById(R.id.Doctor_name);
            Timestamp=(TextView) itemView.findViewById(R.id.Timestamp);
            confirm=(TextView) itemView.findViewById(R.id.confirm);
        }
    }
}
