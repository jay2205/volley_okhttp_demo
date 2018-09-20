package com.example.jayanthdevulapally.volley_demo.adpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.jayanthdevulapally.volley_demo.R;
import com.example.jayanthdevulapally.volley_demo.model.Anime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jayanthdevulapally on 3/27/18.
 */

public class AnimeAdpter extends RecyclerView.Adapter<AnimeAdpter.AnimeViewHolder> {

    List<Anime> animeList = new ArrayList<>();
    Context context;
    RequestOptions requestOptions;

    public AnimeAdpter(List<Anime> animeList, Context context){
        this.animeList = animeList;
        this.context = context;
        requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image);
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.anime_item_row,parent,false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {

        Anime anime = animeList.get(position);

        holder.nameTextView.setText(anime.getName());
        holder.catogeryTextView.setText(anime.getCategorie());
        holder.ratingTextview.setText(anime.getRating());
        holder.studioTextView.setText(anime.getStudio());

        Glide.with(context).load(anime.getImageUrl()).apply(requestOptions).into(holder.animeThumbnail);

    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView, catogeryTextView, ratingTextview, studioTextView;
        ImageView animeThumbnail;

        public AnimeViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            catogeryTextView = itemView.findViewById(R.id.tv_category);
            ratingTextview = itemView.findViewById(R.id.tv_rating);
            studioTextView = itemView.findViewById(R.id.tv_studio);
            animeThumbnail = itemView.findViewById(R.id.iv_thumbnail);
        }
    }
}
