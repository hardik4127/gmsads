package com.adsapp.adstool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.adsapp.adstool.MoreApps.MoreAppsActivity;
import com.adsapp.adstool.appads.GInterstitial.Ads_InterstitialUtils;
import com.adsapp.adstool.appads.GN_Native.Ads_TemplateView;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.LoadAdError;
import com.startapp.sdk.adsbase.StartAppSDK;

public class MainActivity extends AppCompatActivity {

    RelativeLayout banner_container;

    NativeAdLayout native_ad_container;
    Ads_TemplateView template;
    RelativeLayout relative_native;

    Button MoreApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartAppSDK.setTestAdsEnabled(BuildConfig.DEBUG);

        banner_container = (RelativeLayout) findViewById(R.id.relative_banner);
        Admob_BannerAd();

        native_ad_container = (NativeAdLayout) findViewById(R.id.native_ad_container);
        template = findViewById(R.id.my_template);
        relative_native = findViewById(R.id.relative_native1);

        Admob_native1();

        MoreApps=findViewById(R.id.MoreApps);
        MoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ads_InterstitialUtils.getSharedInstance().showInterstitialAd(MainActivity.this, new Ads_InterstitialUtils.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivity.this, MoreAppsActivity.class));
                    }
                });

            }
        });

    }

    public void Admob_BannerAd() {
        com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(this);
        adView.setAdUnitId(ApplicationManager.getgoogle_banner());
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);
        banner_container.addView(adView);
    }

    private AdSize getAdSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

//    GN_Native ADS
public void Admob_native1() {
    if (ApplicationManager.getgoogle_native().equals("null")) {
        relative_native.setVisibility(View.INVISIBLE);
        template.setVisibility(View.INVISIBLE);
    } else {
//        Toast.makeText(MainActivity.this,"GN_Native",Toast.LENGTH_SHORT).show();
        AdLoader adLoader = new AdLoader.Builder(this, ApplicationManager.getgoogle_native())
                .forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                        Log.e("NATIVE_ADS : ", "Loaded");
                        relative_native.setVisibility(View.VISIBLE);
                        template.setVisibility(View.VISIBLE);
                        template.setNativeAd(nativeAd);
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        Log.e("NATIVE_ADS : ", "LoadAdError : " + adError.toString());

                        // Handle the failure by logging, altering the UI, and so on.
                    }

                    @Override
                    public void onAdClicked() {
                        // Log the click event or other custom behavior.
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

}

}