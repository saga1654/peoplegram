package kr.co.people_gram.app;

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
import android.webkit.WebView;

public class SurveyStartActivity extends AppCompatActivity {

    private WebView surveyView;
    private String campaign_code = "";

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

}
