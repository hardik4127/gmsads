package com.adsapp.adstool;

import android.app.Application;
import android.content.SharedPreferences;

// Hardik Gopani File Developer

public class ApplicationManager extends Application {

    static SharedPreferences preferences;
    static SharedPreferences.Editor prefEditor;

    ApplicationManager AppContext;
    public static String myPackage;

    private static ApplicationManager instance = null;

    public static ApplicationManager getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        preferences = getSharedPreferences("i3", MODE_PRIVATE);

        AppContext = (ApplicationManager) getApplicationContext();
        myPackage = AppContext.getPackageName();

    }

//    Application SDA DATA

    public static void sethardikgopani(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("hardikgopani", str).commit();
    }

    public static String gethardikgopani() {
        return preferences.getString("hardikgopani", (String) null);
    }

    //    Start SDA ON-OFF

    public static void setisgoogle(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("isgoogle", str).commit();
    }

    public static String getisgoogle() {
        return preferences.getString("isgoogle", (String) null);
    }

    public static void setisfacebook(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("isfacebook", str).commit();
    }

    public static String getisfacebook() {
        return preferences.getString("isfacebook", (String) null);
    }

    public static void setisunity(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("isunity", str).commit();
    }

    public static String getisunity() {
        return preferences.getString("isunity", (String) null);
    }

    public static void setisstartapp(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("isstartapp", str).commit();
    }

    public static String getisstartapp() {
        return preferences.getString("isstartapp", (String) null);
    }

    //    Start SDA ON-OFF

    //    Start SDA SDI
    public static void setgoogle_app_id(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("google_app_id", str).commit();
    }

    public static String getgoogle_app_id() {
        return preferences.getString("google_app_id", (String) null);
    }

    public static void setgoogle_banner(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("google_banner", str).commit();
    }

    public static String getgoogle_banner() {
        return preferences.getString("google_banner", (String) null);
    }

    public static void setgoogle_interstrial(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("google_interstrial", str).commit();
    }

    public static String getgoogle_interstrial() {
        return preferences.getString("google_interstrial", (String) null);
    }

    public static void setgoogle_native(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("google_native", str).commit();
    }

    public static String getgoogle_native() {
        return preferences.getString("google_native", (String) null);
    }

    public static void setgoogle_app_open(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("google_app_open", str).commit();
    }

    public static String getgoogle_app_open() {
        return preferences.getString("google_app_open", (String) null);
    }

//    DSA BF

    public static void setfb_banner(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("fb_banner", str).commit();
    }

    public static String getfb_banner() {
        return preferences.getString("fb_banner", (String) null);
    }

    public static void setfb_interstrial(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("fb_interstrial", str).commit();
    }

    public static String getfb_interstrial() {
        return preferences.getString("fb_interstrial", (String) null);
    }

    public static void setfb_native(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("fb_native", str).commit();
    }

    public static String getfb_native() {
        return preferences.getString("fb_native", (String) null);
    }

    // unity
    public static void setunity_banner(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("unity_banner", str).commit();
    }

    public static String getunity_banner() {
        return preferences.getString("unity_banner", (String) null);
    }

    public static void setunity_interstrial(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("unity_interstrial", str).commit();
    }

    public static String getunity_interstrial() {
        return preferences.getString("unity_interstrial", (String) null);
    }

    public static void setunity_native(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("unity_native", str).commit();
    }

    public static String getunity_native() {
        return preferences.getString("unity_native", (String) null);
    }

    // start_app

    public static void setstart_appid(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("start_appid", str).commit();
    }

    public static String getstart_appid() {
        return preferences.getString("start_appid", (String) null);
    }

    public static void setstart_adid(String str) {
        prefEditor = preferences.edit();
        prefEditor.putString("start_adid", str).commit();
    }

    public static String getstart_adid() {
        return preferences.getString("start_adid", (String) null);
    }

}