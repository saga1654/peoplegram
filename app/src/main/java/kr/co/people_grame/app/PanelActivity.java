package kr.co.people_grame.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PanelActivity extends AppCompatActivity {

    private WebView panel_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        panel_webview = (WebView) findViewById(R.id.panel_webview);

        WebSettings webSettings = panel_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        panel_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
               super.onPageFinished(view, url);
            }
        });


        panel_webview.loadUrl("http://121.162.209.41:81/panel/panelForm");


    }

}
