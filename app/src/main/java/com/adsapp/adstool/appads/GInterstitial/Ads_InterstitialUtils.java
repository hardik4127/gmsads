package com.adsapp.adstool.appads.GInterstitial;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.adsapp.adstool.ApplicationManager;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


public class Ads_InterstitialUtils {
    private static Ads_InterstitialUtils sharedInstance;
    private InterstitialAd mInterstitialAd;
    private AdCloseListener adCloseListner;
    private boolean isReloaded = false;
    private boolean AdLoading = false;
    String TAG = "InterstitialUtils";
    Context context;

    public static Ads_InterstitialUtils getSharedInstance()
    {
        if (sharedInstance==null)
        {
            sharedInstance = new Ads_InterstitialUtils();
        }
        return sharedInstance;
    }

    public void init(Context context)
    {
        this.context = context;

        Load_Ad();

    }
    public void showInterstitialAd(Activity activity, AdCloseListener adCloseLis)
    {
       /* if( adsManager.Get_Ad_Count() % 2 ==0) {*/
            if (canShowInterstitialAd()) {
                Log.e("Handler : ","Start");
              //  Ads_CustomSpin customSpin = new Ads_CustomSpin(activity, "Ad Loading...");
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Handler : ","finish");

                     //   customSpin.stop();
                        isReloaded = false;
                        adCloseListner = adCloseLis;
                        mInterstitialAd.show(activity);

                    }
                }, 0);


            } else {
                loadInterstitialAd();
                adCloseLis.onAdClosed();
            }
    }
    public boolean canShowInterstitialAd()
    {
        return mInterstitialAd != null;
    }
    public void loadInterstitialAd()
    {
        if(!AdLoading)
        {
            Load_Ad();
            Log.e("InterstitialUtils","is call to load");

        }else
        {
            Log.e("InterstitialUtils","is loding");

        }
    }
    public interface AdCloseListener
    {
        public void onAdClosed();

    }
    public void Load_Ad()
    {
        AdLoading = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, ApplicationManager.getgoogle_interstrial(),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.e("InterstitialUtils", "onAdLoaded");
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.e("InterstitialUtils", "The ad was dismissed.");
                                AdLoading = false;
                                if (adCloseListner != null)
                                {
                                    adCloseListner.onAdClosed();
                                }
                                loadInterstitialAd();

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.e("InterstitialUtils", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
                                Log.e("InterstitialUtils", "The ad was shown.");
                            }
                        });

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.e(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                        AdLoading = false;
                    }
                });

    }

}
