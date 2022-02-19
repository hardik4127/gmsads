package com.adsapp.adstool.ADCall;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdApiClient {

    public static Retrofit retrofit = null;

    public static Retrofit getData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AdParameter.AppUL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
