package kr.co.people_gram.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class SurveyStartActivity extends AppCompatActivity {

    private WebView surveyView;
    private String campaign_code = "";

    private WebViewInterface mWebViewInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_start);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

        Intent intent = getIntent();
        if(intent != null) {
            campaign_code = intent.getStringExtra("campaign_code");
        }

        surveyView = (WebView) findViewById(R.id.surveyView);
        surveyView.loadUrl(HttpClient.BASE_URL+"/survey/sView/"+campaign_code);

        WebSettings webSettings = surveyView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final Context myApp = this;

        mWebViewInterface = new WebViewInterface(SurveyStartActivity.this, surveyView); //JavascriptInterface 객체화
        surveyView.addJavascriptInterface(mWebViewInterface, "Android");
        surveyView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)
            {
                new AlertDialog.Builder(myApp)
                        .setTitle("AlertDialog")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();

                return true;
            };

        });



    }

    public void survey_close(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(SurveyStartActivity.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.setMessage("설문을 중단하시겠습니까?");
        alert.setNegativeButton("취소", null);
        alert.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder alert = new AlertDialog.Builder(SurveyStartActivity.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.setMessage("설문을 중단하시겠습니까?");
                alert.setNegativeButton("취소", null);
                alert.show();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
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
        public void campaignComplate(String campaign_code)
        {
            Log.d("people_gram", "성공");
            Intent intent = new Intent(SurveyStartActivity.this, SurveyComplateActivity.class);
            intent.putExtra("campaign_code", campaign_code);
            startActivity(intent);
            finish();

        }

    }

}