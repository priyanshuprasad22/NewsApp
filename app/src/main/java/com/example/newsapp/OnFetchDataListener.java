package com.example.newsapp;

import com.example.newsapp.Models.News;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {

    void onFetchData(List<News> list, String message);
    void onError(String message);


}
