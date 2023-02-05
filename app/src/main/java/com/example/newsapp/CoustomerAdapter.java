package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.News;
import com.example.newsapp.Models.NewsApiResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoustomerAdapter extends RecyclerView.Adapter<CoustomerViewHolder> {
    private Context context;
    private List<News> headline;
    private Select listener;

    public CoustomerAdapter(Context context, List<News> headline,Select listener) {
        this.context = context;
        this.headline = headline;
        this.listener=listener;
    }

    @NonNull
    @Override
    public CoustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoustomerViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_items,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull CoustomerViewHolder holder, int position) {

        holder.text_title.setText(headline.get(position).getTitle());
        holder.text_source.setText(headline.get(position).getSource().getName());

        if(headline.get(position).getUrlToImage()!=null)
        {
            Picasso.get().load(headline.get(position).getUrlToImage()).into(holder.img_headline);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(headline.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {

        return headline.size();
    }
}
