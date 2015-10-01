package kr.co.people_grame.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class SubPeopleContents_Activity extends AppCompatActivity {

    private WebView people_content_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_contents_);

        people_content_webview = (WebView) findViewById(R.id.people_content_webview);

        WebSettings webSettings = people_content_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        people_content_webview.loadUrl("file:///android_asset/people_result/C_IA_1.html");
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

    public void finish()
    {
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
        super.finish();
    }

}
