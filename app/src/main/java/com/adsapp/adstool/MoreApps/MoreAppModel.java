package com.adsapp.adstool.MoreApps;

public class MoreAppModel {

    private String Name;
    private String Package;
    private String Logo;
    private String IsSpecial;

    public MoreAppModel(String name, String aPackage, String logo, String isSpecial) {
        Name = name;
        Package = aPackage;
        Logo = logo;
        IsSpecial = isSpecial;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String aPackage) {
        Package = aPackage;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getIsSpecial() {
        return IsSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        IsSpecial = isSpecial;
    }

}
