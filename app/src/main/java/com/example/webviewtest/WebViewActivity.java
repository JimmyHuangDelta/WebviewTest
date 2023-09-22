package com.example.webviewtest;


import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    String url = "file:///android_asset/index.html";
    String errMessage = "";
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.mWebView);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允許JavaScript
        webSettings.setDomStorageEnabled(true); //允許localStorage
        webSettings.setAllowFileAccess(true); //允許對系統文件訪問
        webSettings.setAllowContentAccess(true);//允許對系統url文件訪問
        webSettings.setUseWideViewPort (true);//支持html"viewport"標籤
        webSettings.setLoadWithOverviewMode (true); //允許縮小畫面適應螢幕
        webSettings.setDatabaseEnabled (true);//數據庫儲存API是否可用
        webSettings.setAllowUniversalAccessFromFileURLs (true); //訪問其他URL環境的內容
        webSettings.setAllowFileAccessFromFileURLs(true);//訪問任何來源的內容
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new  WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage){
                android.util.Log.d("WebView", consoleMessage.message());
                errMessage = consoleMessage.message();
                Log.d("錯誤", "onConsleMessage: " + consoleMessage.message());
                return true;
            }
        });
        webView.loadUrl(url);
    }

    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }
}