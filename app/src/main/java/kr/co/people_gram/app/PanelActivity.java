package kr.co.people_gram.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class PanelActivity extends AppCompatActivity {

    private WebView panel_webview;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        panel_webview = (WebView) findViewById(R.id.panel_webview);

        WebSettings webSettings = panel_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        panel_webview.addJavascriptInterface(new WebAppInterface(this), "Android");

        //panel_webview.setWebViewClient(new CustomWebViewClient()); // 각종 알림 및 요청을 받는 웹뷰
        //panel_webview.setWebChromeClient(new MyWebChromeClient());



        //uid_view

        panel_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                panel_webview.loadUrl("javascript:uid_view('"+SharedPreferenceUtil.getSharedPreference(PanelActivity.this, "uid")+"')");
               super.onPageFinished(view, url);
            }
        });


        panel_webview.loadUrl("http://121.162.209.41:81/panel/panelForm");
    }

    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String point) {
            SharedPreferenceUtil.putSharedPreference(PanelActivity.this, "point", point);
            SharedPreferenceUtil.putSharedPreference(PanelActivity.this, "panelYN", "Y");

            Toast.makeText(PanelActivity.this, "패널가입을 축하드립니다.", Toast.LENGTH_LONG).show();
            finish();

        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void toastMsg(String msg) {
            Toast.makeText(PanelActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }



    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }


}
