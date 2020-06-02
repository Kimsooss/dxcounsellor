package com.example.myapplication.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;
import com.example.myapplication.util.PreferenceManager;

import org.altbeacon.beacon.startup.RegionBootstrap;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    private static final String TAG = "AndroidProximityReferenceApplication";
    private static final String PREF_TAG = "MAC";
    private RegionBootstrap regionBootstrap;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        preferenceManager = PreferenceManager.getInstance(getApplicationContext());
        if(isJoin()){
            Handler hd = new Handler();
            hd.postDelayed(new splashHd(),1700);
        }else{
            startActivity(new Intent(getApplication(),JoinActivity.class));
            IntroActivity.this.finish();
        }
    }

    private class splashHd implements Runnable{
        @Override
        public void run() {
            startActivity(new Intent(getApplication(),MainActivity.class));
            IntroActivity.this.finish();
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }

    @Override
    public void onBackPressed() {

    }


    public boolean isJoin(){
        String mac  = preferenceManager.getValue(PREF_TAG,"");
        if("".equals(mac)){
            return false;
        }
            return true;
    }
}
