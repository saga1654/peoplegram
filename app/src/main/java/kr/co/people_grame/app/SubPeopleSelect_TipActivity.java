package kr.co.people_grame.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class SubPeopleSelect_TipActivity extends AppCompatActivity {

    private TextView popup_contents_title;
    private WebView people_content_webview;

    private PeopleData pd;
    private String gubun1 = "";
    private String gubun2 = "";
    private String mytype = "";
    private String peopletype = "";
    private String people_username = "";
    private String my_speed = "";
    private String my_control = "";
    private String people_speed = "";
    private String people_control = "";

    private String viewType;
    private String tipType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_select_tip_);

        pd = new PeopleData();
        Intent intent = getIntent();
        if(intent != null) {
            viewType = intent.getStringExtra("viewType");
            tipType = intent.getStringExtra("tipType");
        }

        peopletype = pd.get_people_type();
        gubun1 = pd.get_people_gubun1();
        gubun2 = pd.get_people_gubun2();
        people_username = pd.get_people_username();

        people_content_webview = (WebView) findViewById(R.id.people_content_webview);
        WebSettings webSettings = people_content_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        people_content_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                people_content_webview.loadUrl("javascript:nameView('" + SharedPreferenceUtil.getSharedPreference(SubPeopleSelect_TipActivity.this, "username") + "','"+people_username+"')");
                super.onPageFinished(view, url);
            }
        });
        if(tipType.equals("")){
            people_content_webview.loadUrl("file:///android_asset/tip/DEFAULT/tip1_YOU_" + peopletype + ".html");
        } else {
            if(gubun1.equals("F")) {
                if(tipType.equals("2")) {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 + "_MY_" + peopletype + ".html");
                } else {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 + "_" + viewType + "_" + peopletype + ".html");
                }
            } else if(gubun1.equals("PD")) {
                if(tipType.equals("2")) {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 + "_MY_" + peopletype + ".html");
                } else {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 + "_" + viewType + "_" + peopletype + ".html");
                }

            } else {
                people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 + "_YOU_" + peopletype + ".html");
            }
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void closeBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
