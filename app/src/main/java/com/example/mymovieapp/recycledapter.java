package com.example.mymovieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahmoud on 12/10/2017.
 */

public class recycledapter extends RecyclerView.Adapter<recycledapter.holder> {

    ArrayList<Movie> data =new ArrayList<>();
    Context con;
    public recycledapter(ArrayList<Movie> data, Context con ){
        this.data=data;
        this.con=con;
    }
    @Override
    public recycledapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moveitem,parent,false);
        holder holder =new holder(view);
        return holder;
    }
   ///image.tmdb.org/t/p/w185/kY2c7wKgOfQjvbqe7yVzLTYkxJO.jpg
    @Override
    public void onBindViewHolder(recycledapter.holder holder, final int position) {
        holder.txt_titel.setText(data.get(position).getTitle());

        holder.txt_details.setText(data.get(position).getDescription());


        /*Picasso.with(con).load("http://image.tmdb.org/t/p/w185"*/

        Picasso.with(con).load("http://image.tmdb.org/t/p/w185"+
                data.get(position).getImgurl()).into(holder.img_post);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(con,DetailActivity.class);
                intent.putExtra("titel",data.get(position).getTitle());
                intent.putExtra("detail",data.get(position).getDescription());
                intent.putExtra("poster",data.get(position).getImgurl());
                con.startActivity(intent);
            }
        });

        //   holder.img_post.setImageURI(Uri.parse(data.get(position).getPoster()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        TextView txt_titel,txt_details;
       ImageView img_post;
        public holder(View itemView) {
            super(itemView);
            txt_titel=(TextView)itemView.findViewById(R.id.titel);
            txt_details=(TextView)itemView.findViewById(R.id.details);
            img_post=(ImageView) itemView.findViewById(R.id.post);


          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion =getAdapterPosition();
                    commication commication = (com.mahmoud.movieapp.commication) con;
                  //  commication.sendData(data.get(postion).getId());
                    commication.sendData(data.get(postion));
                }
            });*/
        }
    }
    }

