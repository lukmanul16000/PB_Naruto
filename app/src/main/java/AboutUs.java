package com.example.navdrawerr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import static com.example.navdrawerr.MainActivity.*;

public class AboutUs extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        redirecActivity(this, com.example.navdrawerr.MainActivity.class);
    }
    public void ClickDashboard(View view){
        redirecActivity(this, com.example.navdrawerr.Dashboard.class);

    }
    public void ClickAboutUS(View view){
        recreate();
    }
    public void ClickLogout(View view){
        logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}