package com.adsapp.adstool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.adsapp.adstool.ADCall.AdCallFunction;
import com.adsapp.adstool.appads.GInterstitial.Ads_AppOpenSplashManager;
import com.adsapp.adstool.appads.GInterstitial.Ads_InterstitialUtils;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new AdCallFunction().AdsDataCall(SplashActivity.this);

    }


}