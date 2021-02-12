package com.example.healthclub_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthclub_app.Models.TableModel;
import com.example.healthclub_app.R;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableHolder>{
    Context context;
    List<TableModel> tableModelList;

    public TableAdapter(Context context, List<TableModel> tableModelList) {
        this.context = context;
        this.tableModelList = tableModelList;
    }

    @NonNull
    @Override
    public TableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.table_items,parent,false);

        return new TableHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableHolder holder, int position) {

        TableModel model=tableModelList.get(position);
        holder.weight.setText(model.getWeight());
        holder.vfat.setText(model.getVfat());
        holder.tsf.setText(model.getWeight());
        holder.totalfatpercent.setText(model.getTotalfatpercent());
        holder.bmi.setText(model.getBmi());
        holder.bmr.setText(model.getBmr());
        holder.musclepercent.setText(model.getMusclepercent());
        holder.type.setText(model.getType());

    }

    @Override
    public int getItemCount() {
        return tableModelList.size();
    }

    public  class TableHolder extends RecyclerView.ViewHolder{

        TextView weight,vfat,tsf,totalfatpercent,bmi,bmr,musclepercent,type;

        public TableHolder(@NonNull View itemView) {
            super(itemView);
            weight=itemView.findViewById(R.id.weight);
            vfat=itemView.findViewById(R.id.vfat);
            tsf=itemView.findViewById(R.id.tsf);
            totalfatpercent=itemView.findViewById(R.id.totalfatpercent);
            bmi=itemView.findViewById(R.id.bmi);
            bmr=itemView.findViewById(R.id.bmr);
            musclepercent=itemView.findViewById(R.id.musclepercent);
            type=itemView.findViewById(R.id.type);
        }
    }
}
