package com.example.navdrawerr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){
        com.example.navdrawerr.MainActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        com.example.navdrawerr.MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        com.example.navdrawerr.MainActivity.redirecActivity(this, com.example.navdrawerr.MainActivity.class);
    }
    public void ClickDashboard(View view){
        recreate();
    }
    public void ClickAboutUS(View view){
        com.example.navdrawerr.MainActivity.redirecActivity(this,AboutUs.class);
    }
    public void ClickLogout(View view){
        com.example.navdrawerr.MainActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        com.example.navdrawerr.MainActivity.closeDrawer(drawerLayout);
    }
}