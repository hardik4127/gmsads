package com.adsapp.adstool.appads.GInterstitial;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.adsapp.adstool.ApplicationManager;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

import static androidx.lifecycle.Lifecycle.Event.ON_START;


public class Ads_AppOpenSplashManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks{
    private static boolean isShowingAd = false;
    private int adStatus = 0;
    public static AppOpenAd appOpenAd = null;
    private AppOpenListener listener;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private long loadTime = 0;
    private final ApplicationManager myApplication;
    private Activity currentActivity;
    public boolean active_lis = false;
    public interface AppOpenListener {
        void appOpenAdsClose();
    }

    public Ads_AppOpenSplashManager(ApplicationManager myApplication, AppOpenListener appOpenListener) {
        this.myApplication = myApplication;
         this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        this.listener = appOpenListener;
    }


    @OnLifecycleEvent(ON_START)
    public void onStart() {
        showAdIfAvailable();
        Log.e("AppOpenAd : ","onStart");

    }

    public boolean loadAdsSuccess() {
        return this.adStatus == 1;
    }

    public boolean isLoadFailedAds() {
        return this.adStatus == 2;
    }

    public void resetAdStatus() {
        this.adStatus = 0;
    }

    public void showAdIfAvailable() {
        if (!isShowingAd && isAdAvailable()) {
            appOpenAd.show(currentActivity);
            appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    Ads_AppOpenSplashManager.isShowingAd = true;

                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Ads_AppOpenSplashManager.this.appOpenAd = null;
                    Ads_AppOpenSplashManager.isShowingAd = false;
                    Ads_AppOpenSplashManager.this.adStatus = 0;
                    if (active_lis)
                    {
                        Ads_AppOpenSplashManager.this.listener.appOpenAdsClose();
                        active_lis = false;
                    }
                    fetchAd(OPE_ID);

                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }
            });
        }
    }
    String OPE_ID;
    public void fetchAd(String str) {
        OPE_ID = str;
        if (!isAdAvailable() && this.adStatus != 4) {
            this.adStatus = 4;
            this.loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {

                @Override
                public void onAdLoaded(AppOpenAd appOpenAd) {
                   Ads_AppOpenSplashManager.this.adStatus = 1;
                   Ads_AppOpenSplashManager.this.appOpenAd = appOpenAd;
                   Ads_AppOpenSplashManager.this.loadTime = new Date().getTime();

                    Log.e("AppOpenAd : ","Loaded");

                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                   Ads_AppOpenSplashManager.this.adStatus = 2;
                    Log.e("AppOpenAd : ","LoadAdError" + loadAdError.getMessage());

                }
            };
            AppOpenAd.load(myApplication, str, getAdRequest(), 1, this.loadCallback);
        }
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    private boolean wasLoadTimeLessThanNHoursAgo(long j) {
        return new Date().getTime() - this.loadTime < j * 3600000;
    }

    public boolean isAdAvailable() {
        return this.appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    public void showAppOpenAds() {
        showAdIfAvailable();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivityPaused(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {}

    @Override
    public void onActivityDestroyed(Activity activity) {
        currentActivity = null;
    }
}
