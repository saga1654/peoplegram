package kr.co.people_grame.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class SubPeopleContents_Activity extends AppCompatActivity {

    private WebView people_content_webview;
    private String matchNum = "0";

    private String uid, mood, myname, mytype;
    private String people_name, people_type, people_mood, people_uid, people_gubun1, people_gubun2;
    private PeopleData pd;

    private TextView popup_contents_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_contents_);

        popup_contents_title = (TextView) findViewById(R.id.popup_contents_title);

        /*
        Intent intent = getIntent();
        if(intent != null) {
            matchNum = intent.getStringExtra("matchNum");
        }
        */
        matchNum = "3";

        switch (matchNum) {
            case "1":
                popup_contents_title.setText("상호 관계의 특징");
                break;
            case "2":
                popup_contents_title.setText("나와 상대방의 차이점");
                break;
            case "3":
                popup_contents_title.setText("두 사람 관계에서 내가 개선해야 할 점");
                break;
            case "4":
                popup_contents_title.setText("좋은 관계를 맺을려면");
                break;
            case "5":
                popup_contents_title.setText("많이 할수록 좋은 것(Do More)");
                break;
            case "6":
                popup_contents_title.setText("피해야 할 것(Don't Do it)");
                break;
        }

        uid = SharedPreferenceUtil.getSharedPreference(this, "uid");
        myname = SharedPreferenceUtil.getSharedPreference(this, "username");
        mytype = SharedPreferenceUtil.getSharedPreference(this, "mytype");

        pd = new PeopleData();
        people_uid = pd.get_people_uid();
        people_name = pd.get_people_username();
        people_type = pd.get_people_type();
        people_mood = pd.get_people_mood();
        people_gubun1 = pd.get_people_gubun1();
        people_gubun2 = pd.get_people_gubun2();


        people_content_webview = (WebView) findViewById(R.id.people_content_webview);
        WebSettings webSettings = people_content_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        people_content_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                people_content_webview.loadUrl("javascript:nameView('" + myname + "','" + people_name + "')");
                super.onPageFinished(view, url);
            }
        });

        people_content_webview.loadUrl("file:///android_asset/people_result/" + people_gubun1 + "/" + people_gubun1 + "_" + people_gubun2 + "_" + mytype + people_type + "_" + matchNum + ".html");

    }

    public void closeBtn(View v) {
        finish();
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

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
