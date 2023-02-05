package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Models.News;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    News news;
    TextView txt_title,txt_author,txt_time,txt_deatil,txt_content;
    ImageView img_news;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        button=findViewById(R.id.button);

        txt_title=findViewById(R.id.text_detail_title);
        txt_author=findViewById(R.id.text_detail_author);
        txt_time=findViewById(R.id.text_detail_time);
        txt_deatil=findViewById(R.id.text_detail_detail);
        txt_content=findViewById(R.id.text_detail_content);
        img_news=findViewById(R.id.img_detail_news);

        news = (News) getIntent().getSerializableExtra("data");

        txt_title.setText(news.getTitle());
        txt_author.setText(news.getAuthor());
        txt_time.setText(news.getPublishedAt());
        txt_content.setText(news.getDescription());
        Picasso.get().load(news.getUrlToImage()).into(img_news);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailActivity.this,webviewactivity.class);
                intent.putExtra("url",news.getUrl());
                startActivity(intent);
            }
        });





    }
}