package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.Models.News;
import com.example.newsapp.Models.NewsApiResponse;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Select, View.OnClickListener{
    RecyclerView recyclerView;
    CoustomerAdapter adapter;
    ProgressDialog progressDialog;
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;
    String s[];
    String s1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=findViewById(R.id.search_view);

        s=new String[5];
        s=getResources().getStringArray(R.array.Regions);

        ArrayAdapter adapter= new ArrayAdapter(this,R.layout.dropdown,s);

        AutoCompleteTextView autoCompleteTextView=findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

       autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Log.d("New Select","Select"+s[i]);

           }
       });








        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching news articles of "+query);
                progressDialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNews(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Fetching the data..");
        progressDialog.show();

        b1=findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.btn_7);
        b7.setOnClickListener(this);


        


        RequestManager manager=new RequestManager(this);
        manager.getNews(listener, "general",null);

    }
    private final OnFetchDataListener<NewsApiResponse> listener=new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<News> list, String message) {
            if(list.isEmpty())
            {
                Toast.makeText(MainActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            else {
                showNews(list);
                progressDialog.dismiss();


            }
        }

        private void showNews(List<News> list) {
            recyclerView=findViewById(R.id.recycler_main);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,
                    1));
            adapter=new CoustomerAdapter(MainActivity.this,list,MainActivity.this);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void OnNewsClicked(News news) {
        startActivity(new Intent(MainActivity.this,DetailActivity.class)
               .putExtra("data",news));
    }

    @Override
    public void onClick(View view) {
        Button button=(Button) view;
        String category=button.getText().toString();
        progressDialog.setTitle("Fetching news article of "+category);
        progressDialog.show();
        RequestManager manager=new RequestManager(this);
        manager.getNews(listener,category,null);

    }
}