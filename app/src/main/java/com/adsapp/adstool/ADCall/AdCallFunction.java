package com.adsapp.adstool.ADCall;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.adsapp.adstool.ApplicationManager;
import com.adsapp.adstool.MainActivity;
import com.adsapp.adstool.SplashActivity;
import com.adsapp.adstool.appads.GInterstitial.Ads_AppOpenSplashManager;
import com.adsapp.adstool.appads.GInterstitial.Ads_InterstitialUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdCallFunction {

    Ads_AppOpenSplashManager appOpenSplashManager;
    ApplicationManager myApplication;

    public void AdsDataCall(Activity activity) {

        myApplication = ApplicationManager.getInstance();
        appOpenSplashManager = new Ads_AppOpenSplashManager(myApplication, new Ads_AppOpenSplashManager.AppOpenListener() {
            @Override
            public void appOpenAdsClose() {
                activity.startActivity(new Intent(activity, MainActivity.class));
            }
        });

        AdInterface service = AdApiClient.getData().create(AdInterface.class);
        Call<AdsModel> call = service.getCourse();

        call.enqueue(new Callback<AdsModel>() {
            @Override
            public void onResponse(Call<AdsModel> call, Response<AdsModel> response) {
                if (response.isSuccessful()) {
                    AdsModel result = response.body();

//                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();

                    String app_ads_status = result.getAds_status();

                    if (app_ads_status.equals("1")){
                        String app_isgoogle = result.getAds_status();
                        String app_isfacebook = result.getAds_status();
                        String app_isunity = result.getAds_status();
                        String app_isstartapp = result.getAds_status();
                        if (app_isgoogle.equals("1")){
                            ApplicationManager.setgoogle_app_id(result.getGoogle_app_id());
                            ApplicationManager.setgoogle_banner(result.getGoogle_banner());
                            ApplicationManager.setgoogle_native(result.getGoogle_native());
                            ApplicationManager.setgoogle_interstrial(result.getGoogle_interstrial());
                            ApplicationManager.setgoogle_app_open(result.getGoogle_app_open());

                            Ads_InterstitialUtils.getSharedInstance().init(activity);
                            appOpenSplashManager.fetchAd(ApplicationManager.getgoogle_app_open());
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("AppOpenAd", " finish Handler ");

                                    if (appOpenSplashManager.loadAdsSuccess()) {

                                        appOpenSplashManager.active_lis = true;
                                        appOpenSplashManager.showAppOpenAds();

                                    } else {
                                        appOpenSplashManager.active_lis = false;
                                        activity.startActivity(new Intent(activity, MainActivity.class));
                                    }
                                }
                            }, 3000);

                        }
                        if (app_isfacebook.equals("1")){
                            ApplicationManager.setfb_banner(result.getFb_banner());
                            ApplicationManager.setfb_native(result.getFb_native());
                            ApplicationManager.setfb_interstrial(result.getFb_interstrial());
                        }
                        if (app_isunity.equals("1")){
                            ApplicationManager.setunity_banner(result.getUnity_banner());
                            ApplicationManager.setunity_native(result.getUnity_native());
                            ApplicationManager.setunity_interstrial(result.getUnity_interstrial());
                        }
                        if (app_isstartapp.equals("1")){
                            ApplicationManager.setstart_appid(result.getStart_appid());
                            ApplicationManager.setstart_adid(result.getStart_adid());
                        }

//                        activity.startActivity(new Intent(activity,MainActivity.class));
//                        activity.finish();

                    }else if (app_ads_status.equals("0")){

                        ApplicationManager.setgoogle_app_id("NODATA");
                        ApplicationManager.setgoogle_banner("NODATA");
                        ApplicationManager.setgoogle_native("NODATA");
                        ApplicationManager.setgoogle_interstrial("NODATA");
                        ApplicationManager.setgoogle_app_open("NODATA");
                        ApplicationManager.setfb_banner("NODATA");
                        ApplicationManager.setfb_native("NODATA");
                        ApplicationManager.setfb_interstrial("NODATA");
                        ApplicationManager.setunity_banner("NODATA");
                        ApplicationManager.setunity_native("NODATA");
                        ApplicationManager.setunity_interstrial("NODATA");
                        ApplicationManager.setstart_appid("NODATA");
                        ApplicationManager.setstart_adid("NODATA");

//                        activity.startActivity(new Intent(activity,MainActivity.class));
//                        activity.finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<AdsModel> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(activity, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
