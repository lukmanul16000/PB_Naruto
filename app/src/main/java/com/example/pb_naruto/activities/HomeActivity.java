package com.example.pb_naruto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pb_naruto.R;

public class HomeActivity extends AppCompatActivity {

    private CardView card1,card2,card3,card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        card1 =(CardView) findViewById(R.id.card1);
        card2 =(CardView) findViewById(R.id.card2);
        card3 =(CardView) findViewById(R.id.card3);
        card4 =(CardView) findViewById(R.id.card4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(getApplicationContext(), Main_ListBatik.class);
                startActivity(explicit);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(getApplicationContext(), about_team.class);
                startActivity(explicit);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicit = new Intent(getApplicationContext(), AboutApp.class);
                startActivity(explicit);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             Intent explicit = new Intent(getApplicationContext(), about_team.class);
//             startActivity(explicit);
            }
        });

    }
}