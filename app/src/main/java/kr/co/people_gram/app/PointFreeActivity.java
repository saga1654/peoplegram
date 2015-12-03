package kr.co.people_gram.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PointFreeActivity extends AppCompatActivity {

    private WebView free;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_free);

        free = (WebView) findViewById(R.id.free);

        WebSettings webSettings = free.getSettings();
        webSettings.setJavaScriptEnabled(true);

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

}
