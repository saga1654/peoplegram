package kr.co.people_gram.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PointFreeActivity extends AppCompatActivity {

    private WebView free;
    private WebViewInterface mWebViewInterface;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //super.getWindow().requestFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_point_free);

        free = (WebView) findViewById(R.id.free);

        final Context myApp = this;


        free.clearCache(true);
        free.clearHistory();


        WebSettings webSettings = free.getSettings();
        webSettings.setJavaScriptEnabled(true);


        free.loadUrl(HttpClient.BASE_URL + "/survey/sList/" + SharedPreferenceUtil.getSharedPreference(PointFreeActivity.this, "uid"));

        mWebViewInterface = new WebViewInterface(PointFreeActivity.this, free); //JavascriptInterface 객체화
        free.addJavascriptInterface(mWebViewInterface, "Android");
        free.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
            }
        });

        free.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
                new AlertDialog.Builder(myApp)
                        .setTitle("AlertDialog")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();

                return true;
            };

            /*
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 0) {
                    dialog = ProgressDialog.show(PointFreeActivity.this, "", "데이터 수신중");
                }
                if (newProgress >= 100) {
                    dialog.dismiss();
                }
            }
            */

        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        free.loadUrl(HttpClient.BASE_URL + "/survey/sList/"+SharedPreferenceUtil.getSharedPreference(PointFreeActivity.this, "uid"));
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
