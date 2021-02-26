package com.example.patientsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder>{
    ArrayList<PatientModel> dataModel;

    public PatientAdapter(ArrayList<PatientModel> dataModel) {
        this.dataModel = dataModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_rows,parent,false);
        view.setOnClickListener(MainActivity.versionClickListener);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       TextView patientname = holder.txtpname;
        TextView patientfathername = holder.txtpfathername;
        TextView patientgender=holder.txtgender;
        TextView patientage=holder.txtage;
        TextView patientconsultancytype=holder.txtconsultancytype;
        TextView patientstatus=holder.txtpatientstatus;

        patientname.setText(dataModel.get(position).getName());
        patientfathername.setText(dataModel.get(position).getFathername());
        patientgender.setText(dataModel.get(position).getGender());
        patientage.setText(dataModel.get(position).getAge());
        patientconsultancytype.setText(dataModel.get(position).getConsultancytype());
       //patientstatus.setText(dataModel.get(dataModel.get(position)۔patientstatus()));




    }

    @Override
    public int getItemCount() {
        return dataModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView txtpname,txtpfathername,txtgender,txtage,txtconsultancytype,txtpatientstatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtpname=itemView.findViewById(R.id.p_name);
            txtpfathername=itemView.findViewById(R.id.p_fname);
            txtgender=itemView.findViewById(R.id.p_gender);
            txtage=itemView.findViewById(R.id.p_age);
            txtconsultancytype=itemView.findViewById(R.id.p_consultancy);
            txtpatientstatus=itemView.findViewById(R.id.p_status);
        }
    }
}
