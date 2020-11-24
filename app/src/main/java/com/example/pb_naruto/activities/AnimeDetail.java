package com.example.pb_naruto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pb_naruto.R;
import com.squareup.picasso.Picasso;

public class AnimeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detail);

        Intent intent = getIntent();
        String image_url = intent.getStringExtra("image_url");
        String title = intent.getStringExtra("title");
//        String episodes = intent.getStringExtra("episodes");
        int episodes = intent.getIntExtra("episodes", 0);
        String score = intent.getStringExtra("score");
        final String url = intent.getStringExtra("url");
        String synopsis = intent.getStringExtra("synopsis");
        String type = intent.getStringExtra("type");
        String start_date = intent.getStringExtra("start_date");
        String end_date = intent.getStringExtra("end_date");

        int members = intent.getIntExtra("members", 0);




        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView tv_title = findViewById(R.id.anime_judul);
        TextView tv_episodes = findViewById(R.id.anime_episode);
        TextView tv_score = findViewById(R.id.anime_score);
        TextView tv_url = findViewById(R.id.anime_url);
        TextView tv_synopsis = findViewById(R.id.anime_synopsis);
        TextView tv_type = findViewById(R.id.anime_type);
        TextView tv_start_date = findViewById(R.id.start_date);
        TextView tv_end_date = findViewById(R.id.end_date);
        TextView tv_members = findViewById(R.id.member);

        tv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            }
        });

//        tv_url.setText(Html.fromHtml(url));
//        tv_url.setMovementMethod(LinkMovementMethod.getInstance());



        Picasso.get().load(image_url).fit().centerInside().into(imageView);
        tv_title.setText(title);
        tv_episodes.setText("Episode :" + episodes);
        tv_score.setText("Rate :" + score);
//        tv_url.setText(url);
        tv_synopsis.setText(synopsis);
        tv_type.setText(type);
        tv_start_date.setText(start_date);
        tv_end_date.setText(end_date);
        tv_members.setText("Member :" +members);
    }
}

//text_view.setOnClickListener(new OnClickListener(){
//
//        String url = textView.getText().toString();
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);
//
//        });
