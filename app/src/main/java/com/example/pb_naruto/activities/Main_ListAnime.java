package com.example.pb_naruto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pb_naruto.R;
import com.example.pb_naruto.adapters.AnimeAdapter;
import com.example.pb_naruto.helper.DBHelperApi;
import com.example.pb_naruto.model.AnimeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main_ListAnime extends AppCompatActivity  implements AnimeAdapter.OnItemClickListener{
//    handel

    public static final String EXTRA_URL = "image_url";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_EPISODE = "episodes";
    public static final String EXTRA_SCORE = "score";
    public static final String EXTRA_URLM = "url";
    public static final String EXTRA_SYNOPSIS = "synopsis";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_START = "start_date";
    public static final String EXTRA_END = "end_date";
    public static final String EXTRA_MEMBERS = "members";

//    handel
    private RecyclerView mRecyclerView;
    private AnimeAdapter mExampleAdapter;
    private ArrayList<AnimeList> animeLists;
    private RequestQueue mRequestQueue;
    DBHelperApi db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__list_batik);

        db = new DBHelperApi(this);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRequestQueue = Volley.newRequestQueue(this);
        animeLists = new ArrayList<>();


        if(haveNetwork()){
            parseJSON();
        }else if (!haveNetwork()) {
            db = new DBHelperApi(this);
            animeLists = new ArrayList<>();
            animeLists = db.ListAnime();
            if(animeLists.size() > 0){
                mExampleAdapter = new AnimeAdapter(Main_ListAnime.this, animeLists);
                mRecyclerView.setAdapter(mExampleAdapter);
                mExampleAdapter.setOnItemClickListener(Main_ListAnime.this);
            }
        };


    }

    private void parseJSON() {
        String url = "https://api.jikan.moe/v3/search/anime?q=naruto";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String mimage_url = hit.getString("image_url");
                                String mtitle = hit.getString("title");
                                String murl  = hit.getString("url");
                                String mairing  = hit.getString("airing");
                                String msynopsis  = hit.getString("synopsis");
                                String mtype  = hit.getString("type");
                                String mscore  = hit.getString("score");
                                String mstart_date  = hit.getString("start_date");
                                String mend_date  = hit.getString("end_date");
                                int mmembers  = hit.getInt("members");
                                String mrated  = hit.getString("rated");
                                int mepisodes = hit.getInt("episodes");
                                int mal_id = hit.getInt("mal_id");
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                try {

                                    Boolean APIJSONSAVE = db.insertDataApi(mal_id,mimage_url, mtitle, mepisodes,murl,mairing,msynopsis,mtype,mscore,mstart_date,mend_date,mmembers,mrated);
                                    Date start1 = format.parse(mstart_date);
                                    String start_date = format.format(start1);
                                    Date end1 = format.parse(mend_date);
                                    String end_date = format.format(end1);
                                    animeLists.add(new AnimeList(mal_id,mimage_url, mtitle, mepisodes,murl,mairing,msynopsis,mtype,mscore,start_date,end_date,mmembers,mrated));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }


                            mExampleAdapter = new AnimeAdapter(Main_ListAnime.this, animeLists);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(Main_ListAnime.this);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, AnimeDetail.class);
        AnimeList clickedItem = animeLists.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getMimage_url());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getMtitle());
        detailIntent.putExtra(EXTRA_EPISODE, clickedItem.getMepisodes());
        detailIntent.putExtra(EXTRA_SCORE, clickedItem.getMscore());
        detailIntent.putExtra(EXTRA_URLM, clickedItem.getMurl());
        detailIntent.putExtra(EXTRA_SYNOPSIS, clickedItem.getMsynopsis());
        detailIntent.putExtra(EXTRA_TYPE, clickedItem.getMtype());
        detailIntent.putExtra(EXTRA_START, clickedItem.getMstart_date());
        detailIntent.putExtra(EXTRA_END, clickedItem.getMend_date());
        detailIntent.putExtra(EXTRA_MEMBERS, clickedItem.getMmembers());
        startActivity(detailIntent);

    }

    private boolean haveNetwork(){
        boolean have_WIFI= false;
        boolean have_MobileData = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI"))if (info.isConnected())have_WIFI=true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE DATA"))if (info.isConnected())have_MobileData=true;
        }
        return have_WIFI||have_MobileData;
    }



}