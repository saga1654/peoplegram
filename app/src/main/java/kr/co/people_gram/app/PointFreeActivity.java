package kr.co.people_gram.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PointFreeActivity extends AppCompatActivity {

    private WebView free;
    private WebViewInterface mWebViewInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_free);

        free = (WebView) findViewById(R.id.free);

        WebSettings webSettings = free.getSettings();
        webSettings.setJavaScriptEnabled(true);

        free.loadUrl(HttpClient.BASE_URL + "/survey/sList");
        mWebViewInterface = new WebViewInterface(PointFreeActivity.this, free); //JavascriptInterface 객체화
        free.addJavascriptInterface(mWebViewInterface, "Android");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("people_gram", "성공");
        free.loadUrl(HttpClient.BASE_URL + "/survey/sList");
    }

    public void free_prevBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
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


    public class WebViewInterface {

        private WebView mAppView;
        private Activity mContext;

        /**
         * 생성자.
         * @param activity : context
         * @param view : 적용될 웹뷰
         */
        public WebViewInterface(Activity activity, WebView view) {
            mAppView = view;
            mContext = activity;
        }

        @JavascriptInterface
        public void campaignView(String campaign_code, String campaign_subject, String point, String time)
        {
            Intent intent = new Intent(PointFreeActivity.this, SurveyViewActivity.class);
            intent.putExtra("campaign_subject", campaign_subject);
            intent.putExtra("campaign_code", campaign_code);
            intent.putExtra("survey_point", point);
            intent.putExtra("survey_time", time);
            startActivityForResult(intent, 9);

            Log.d("people_gram", "캠페인코드:" + campaign_code);
        }

    }

}
