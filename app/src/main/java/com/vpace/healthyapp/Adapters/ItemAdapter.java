package com.vpace.healthyapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vpace.healthyapp.Models.DietModel;
import com.vpace.healthyapp.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    Context context;
    List<DietModel> dietModels;

    public ItemAdapter(Context context, List<DietModel> dietModels) {
        this.context = context;
        this.dietModels = dietModels;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.current_diet_list,parent,false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        DietModel model=dietModels.get(position);


       // holder.web.loadData(model.getText1(),"","");
      //  holder.title.setText(model.getTitle());


//

      holder.item1.setText(model.getText1());




    }


    @Override
    public int getItemCount() {
        return dietModels.size();
    }

    public  class ItemHolder extends RecyclerView.ViewHolder{

        RelativeLayout title_layout,items_layout;
        TextView title,item1,item2,item3;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);

         //  title=itemView.findViewById(R.id.item_title);
            item1=itemView.findViewById(R.id.item1);
//            item2=itemView.findViewById(R.id.item2);
//            item3=itemView.findViewById(R.id.item3);
//            title_layout=itemView.findViewById(R.id.title_layout);
//            items_layout=itemView.findViewById(R.id.items_layout);

//            title.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DietModel dietModel=dietModels.get(getAdapterPosition());
//                    dietModel.setExpanded(!dietModel.isExpanded());
//                    notifyItemChanged(getAdapterPosition());
//                }
//            });

        }
    }
}
