package com.vpace.healthyapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vpace.healthyapp.Activities.FullScreen;
import com.vpace.healthyapp.Models.PromoVidModel;
import com.vpace.healthyapp.R;

import java.util.List;

public class MoreVideosAdapter extends RecyclerView.Adapter<MoreVideosAdapter.PromoHolder> {
    Context context;
    List<PromoVidModel> vidList;

    public MoreVideosAdapter(Context context, List<PromoVidModel> vidList) {
        this.context = context;
        this.vidList = vidList;
    }

    @NonNull
    @Override
    public PromoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.more_vid_list_item,parent,false);
        return new PromoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoHolder holder, int position) {

        PromoVidModel model=vidList.get(position);
        Glide.with(context).load("https://img.youtube.com/vi/"+ model.getLink()+"/maxresdefault.jpg").into(holder.imageView);

       holder.textView.setText(model.getTitie());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Presses", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,FullScreen.class);
                intent.putExtra("vid_id",model.getLink());
              //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return vidList.size();
    }

    public class PromoHolder extends  RecyclerView.ViewHolder{

     ImageView imageView;
     CardView cardView;
     TextView textView;

        public PromoHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.iv_info_image);
            cardView=itemView.findViewById(R.id.card_plan);
            textView=itemView.findViewById(R.id.title);

        }
    }
}
