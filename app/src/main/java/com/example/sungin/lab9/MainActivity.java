package com.example.sungin.lab9;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ListView listView;
    ArrayList<webSite> webList = new ArrayList<>();
    urlAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_add) {
            listView.setVisibility(View.GONE);
            webView.loadUrl("file:///android_asset/www/urladd.html");
            webView.setVisibility(View.VISIBLE);

        } else if (item.getItemId() == R.id.menu_list)
            listView.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webList.add(new webSite("한양", "http://www.hanyang.ac.kr"));
        webList.add(new webSite("서강", "http://sugang.ac.kr"));

        listView = (ListView) findViewById(R.id.listview);
        webView = (WebView) findViewById(R.id.WebView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        setListView();

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);


        webView.addJavascriptInterface(adapter, "myApp");

        Intent intent1 = getIntent();
        webSite a = new webSite(intent1.getStringExtra("site"), intent1.getStringExtra("url"));
        String t= new String();

        for (int i = 0; i < webList.size(); i++) {
            if (webList.get(i).getUrl() == a.getUrl()) t="true";
        }

        if(t=="true") webView.loadUrl("javascript:siteAdd("+t+")");
        if ( t!="true" && a.getSiteName() != null) {
            webList.add(a);
        }
        adapter.notifyDataSetChanged();


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.setMessage("Loading..");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 100) progressDialog.dismiss();
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return super.onJsAlert(view, url, message, result);

            }
        });

        Intent intent = getIntent();
        adapter.notifyDataSetChanged();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);


    }

    public void setListView() {
        adapter = new urlAdapter(webList, this);
        listView.setAdapter(adapter);
    }
}
