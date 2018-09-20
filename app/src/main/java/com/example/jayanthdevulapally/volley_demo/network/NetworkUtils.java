package com.example.jayanthdevulapally.volley_demo.network;

import android.content.Context;
import android.os.Looper;
import android.view.animation.AnimationUtils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jayanthdevulapally.volley_demo.model.Anime;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by jayanthdevulapally on 3/27/18.
 */

public class NetworkUtils {

    RequestQueue requestQueue;
    boolean isSuccess = false;
    final Gson gson;

    public static final String ANIME_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";

    public interface ResponceListener{
        void onResponceListener(List<Anime> animeList);
        void onFailureListener();
    }

    ResponceListener responceListener;

    public NetworkUtils(ResponceListener listener) {
        responceListener = listener;
        gson = new Gson();
    }

    public void performVolleyRequest(String url, Context context){

        JsonArrayRequest arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                List<Anime> animes = Arrays.asList(gson.fromJson(response.toString(),Anime[].class));
                /*for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);

                        Anime anime = new Anime();
                        anime.setName(jsonObject.getString("name"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setImageUrl(jsonObject.getString("img"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        animeList.add(anime);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }*/
                responceListener.onResponceListener(animes);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                isSuccess = false;
                responceListener.onFailureListener();
            }
        });

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(arrayRequest);

    }

    public void perfromWithOkHttp(String url, Context context){
        OkHttpClient client = new OkHttpClient();


        final Request request = new Request.Builder().url(url).build();
        final android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());

       client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                responceListener.onFailureListener();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    final List<Anime> animeList = Arrays.asList(gson.fromJson(jsonData,Anime[].class));

                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           responceListener.onResponceListener(animeList);
                       }
                   });

                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            responceListener.onFailureListener();
                        }
                    });
                }
            }
        });
    }
}
