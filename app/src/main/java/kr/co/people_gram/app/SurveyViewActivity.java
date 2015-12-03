package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SurveyViewActivity extends AppCompatActivity {

    String campaign_subject = "";
    String campaign_code = "";
    String survey_point = "";
    String survey_time = "";

    TextView tv_campaign_subject, tv_survey_point, tv_survey_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_view);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);

        Intent intent = getIntent();
        if(intent != null) {
            campaign_subject = intent.getStringExtra("campaign_subject");
            campaign_code = intent.getStringExtra("campaign_code");
            survey_point = intent.getStringExtra("survey_point");
            survey_time = intent.getStringExtra("survey_time");
        }

        tv_campaign_subject = (TextView) findViewById(R.id.tv_campaign_subject);
        tv_survey_point = (TextView) findViewById(R.id.tv_survey_point);
        tv_survey_time = (TextView) findViewById(R.id.tv_survey_time);

        tv_campaign_subject.setText(campaign_subject);
        tv_survey_point.setText(survey_point + "P");
        tv_survey_time.setText(survey_time);

    }

    public void surveyStart(View v) {
        Intent intent = new Intent(SurveyViewActivity.this, SurveyStartActivity.class);
        intent.putExtra("campaign_code", campaign_code);
        startActivity(intent);

        finish();
    }

    public void closeBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
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
