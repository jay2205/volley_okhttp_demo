package com.example.jayanthdevulapally.volley_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jayanthdevulapally.volley_demo.adpters.AnimeAdpter;
import com.example.jayanthdevulapally.volley_demo.model.Anime;
import com.example.jayanthdevulapally.volley_demo.network.NetworkUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkUtils.ResponceListener {

    RecyclerView recyclerView;
    TextView errorTextView;
    ProgressBar progressBar;
    AnimeAdpter adpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        errorTextView = findViewById(R.id.textview_error);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NetworkUtils utils = new NetworkUtils(this);

       utils.performVolleyRequest(NetworkUtils.ANIME_URL,this);
//        utils.perfromWithOkHttp(NetworkUtils.ANIME_URL,this);
       progressBar.setVisibility(View.VISIBLE);

    }


    @Override
    public void onResponceListener(List<Anime> animeList) {
        progressBar.setVisibility(View.INVISIBLE);
        if (animeList != null){
            adpter = new AnimeAdpter(animeList,this);
            recyclerView.setAdapter(adpter);
            recyclerView.setVisibility(View.VISIBLE);
            errorTextView.setVisibility(View.INVISIBLE);

        } else {
            recyclerView.setVisibility(View.INVISIBLE);
            errorTextView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onFailureListener() {
        recyclerView.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
    }
}
