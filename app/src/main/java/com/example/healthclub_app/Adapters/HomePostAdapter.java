package com.example.healthclub_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthclub_app.Activities.Postdetails;
import com.example.healthclub_app.Models.PostModel;
import com.example.healthclub_app.R;

import java.util.List;

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.PostHolder> {
    Context context;
    List<PostModel> postModelList;

    public HomePostAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.post_list,parent,false);

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        PostModel postModel=postModelList.get(position);

        Glide.with(context)
                .load(postModel.getImgUrl())
                .into(holder.postimg);
        holder.posttitle.setText(postModel.getTitle());
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
     PostData postData=new PostData(postModel.getImgUrl(),postModel.getTitle(),postModel.getDescription());

               Intent intent=new Intent(context, Postdetails.class);
               intent.putExtra("PostData",postData);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class  PostHolder extends  RecyclerView.ViewHolder{
        CardView cardView;
        ImageView postimg;
        TextView posttitle;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.card_post);
            postimg=itemView.findViewById(R.id.post_img);
            posttitle=itemView.findViewById(R.id.post_title);
        }
    }
}
