package com.example.sungin.lab9;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by SungIn on 2017-05-10.
 */

public class webSite implements Parcelable{
    public String siteName;
    public String url;


    public webSite(String siteName, String url) {
        this.siteName = siteName;
        this.url = url; }

    public webSite() {
        this.siteName = "";
        this.url = ""; }

    protected webSite(Parcel in) {
        siteName = in.readString();
        url = in.readString();
    }

    public static final Creator<webSite> CREATOR = new Creator<webSite>() {
        @Override
        public webSite createFromParcel(Parcel in) {
            return new webSite(in);
        }

        @Override
        public webSite[] newArray(int size) {
            return new webSite[size];
        }
    };

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(siteName);
        dest.writeString(url);
    }
}
