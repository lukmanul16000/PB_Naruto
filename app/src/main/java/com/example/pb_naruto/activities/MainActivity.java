package com.example.pb_naruto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.pb_naruto.R;
import com.example.pb_naruto.adapters.OnboardingAdapter;
import com.example.pb_naruto.model.OnboardingItem;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (restorePrefData()) {

            Intent HomeActivity = new Intent(getApplicationContext(),HomeActivity.class );
            startActivity(HomeActivity);
            finish();
        }

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);


        setupOnboardingItem();

        final ViewPager2 onboardingViewPager = findViewById(R.id.onBoardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem()+1 < onboardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem()+1);
                }else{
                    Intent explicit = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(explicit);
                    savePrefsData();
                    finish();
                }
            }
        });

    }

    private void setupOnboardingItem(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemtitle = new OnboardingItem();
        itemtitle.setTitle("aplikasi Naruto");
        itemtitle.setDescription("mau nonton anime naruto gratis?di aplikasi naruto aja");
        itemtitle.setImage(R.drawable.naruto2);

        OnboardingItem itemdesc = new OnboardingItem();
        itemdesc.setTitle("Boruto");
        itemdesc.setDescription("tonton anime boruto gratis hanya di aplikasi naruto");
        itemdesc.setImage(R.drawable.naruto3);

        OnboardingItem itemimg = new OnboardingItem();
        itemimg.setTitle("aplikasi Naruto");
        itemimg.setDescription("menyediakan streaming anime Naruto shipuden dan Boruto gratis");
        itemimg.setImage(R.drawable.naruto4);

        onboardingItems.add(itemtitle);
        onboardingItems.add(itemdesc);
        onboardingItems.add(itemimg);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);


    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8,0,8,0);

        for(int i=0; i < indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(), R.drawable.onboarding_indicator_active));
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index ==onboardingAdapter.getItemCount()-1){
            buttonOnboardingAction.setText("Start");
        }else{
            buttonOnboardingAction.setText("Next");
        }
    }

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;



    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();


    }

}