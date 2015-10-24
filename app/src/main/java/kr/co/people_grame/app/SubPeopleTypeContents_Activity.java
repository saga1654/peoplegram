package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class SubPeopleTypeContents_Activity extends AppCompatActivity {

    private TextView popup_contents_title;
    private WebView people_content_webview;

    private String gubun1 = "";
    private String mytype = "";
    private String peopletype = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_type_contents_);

        popup_contents_title = (TextView) findViewById(R.id.popup_contents_title);

        Intent intent = getIntent();
        if(intent != null) {
            popup_contents_title.setText("");

            mytype = intent.getStringExtra("mytype");
            peopletype = intent.getStringExtra("people_type");
            gubun1 = intent.getStringExtra("gubun1");

        }

        people_content_webview = (WebView) findViewById(R.id.people_content_webview);
        WebSettings webSettings = people_content_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        people_content_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                people_content_webview.loadUrl("javascript:nameView('" + SharedPreferenceUtil.getSharedPreference(SubPeopleTypeContents_Activity.this, "username") + "','')");
                super.onPageFinished(view, url);
            }
        });


        people_content_webview.loadUrl("file:///android_asset/peopletype_my/" + gubun1 + "/" + gubun1 + "_MY_" + mytype + peopletype + ".html");

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
        overridePendingTransition(R.anim.slide_clode_up_info, R.anim.slide_close_down_info);
    }

}
