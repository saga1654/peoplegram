package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class SubPeopleContentsType_Activity extends AppCompatActivity {

    private WebView people_content_webview;
    private String gubun, youtype, people_youtype, people_name;
    private String my_speed, my_control, people_speed, people_control;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_contents_type_);


        Log.d("people_gram", "성공");

        Intent intent = getIntent();
        if(intent != null) {
            youtype = intent.getStringExtra("youtype");
            people_youtype = intent.getStringExtra("people_youtype");
            people_name = intent.getStringExtra("people_name");
            gubun = intent.getStringExtra("gubun");

            my_speed = intent.getStringExtra("my_speed");
            my_control = intent.getStringExtra("my_control");

            people_speed = intent.getStringExtra("people_speed");
            people_control = intent.getStringExtra("people_control");

            Log.d("people_gram", youtype+ ":::" + people_youtype + ":::" + gubun);

            people_content_webview = (WebView) findViewById(R.id.people_content_webview);

            WebSettings webSettings = people_content_webview.getSettings();
            webSettings.setJavaScriptEnabled(true);

            people_content_webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {

                    people_content_webview.loadUrl("javascript:type_chart(" + my_speed + ", " + my_control + "," + people_speed + "," + people_control + ", '"+gubun+"')");
                    people_content_webview.loadUrl("javascript:nameView('" + SharedPreferenceUtil.getSharedPreference(SubPeopleContentsType_Activity.this, "username") + "','" + people_name + "')");
                    super.onPageFinished(view, url);
                }
            });


            people_content_webview.loadUrl("file:///android_asset/peopletype_you/" + gubun + "/" + gubun + "_PEOPLE_" + youtype + people_youtype + ".html");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
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
