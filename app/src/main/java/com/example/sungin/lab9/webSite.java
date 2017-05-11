package com.example.sungin.lab9;

import android.webkit.JavascriptInterface;

/**
 * Created by SungIn on 2017-05-10.
 */

public class webSite {
    public String siteName;
    public String url;

    public webSite(String siteName, String url) {
        this.siteName = siteName;
        this.url = url;
    }



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
}
