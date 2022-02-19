package com.adsapp.adstool.ADCall;

import com.adsapp.adstool.MoreApps.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdInterface {

	String Data = "sample1"+".json";

	@GET("ads/"+Data)
	Call<AdsModel> getCourse();

	@GET("moreapp/com.skin.emotestools.json")
	Call<JSONResponse> getJSON();

}
