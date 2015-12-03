package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SurveyComplateActivity extends AppCompatActivity {

    private String campaign_code = "";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_complate);

        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

        Intent intent = getIntent();
        if(intent != null) {
            campaign_code = intent.getStringExtra("campaign_code");
        }


        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(SurveyComplateActivity.this, "uid"));
        params.put("campaign_code", campaign_code);
        //params.put("searchType", searchType);
        HttpClient.post("/survey/sComplate", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SurveyComplateActivity.this, "", "데이터 수신중");
            }

            public void onFailure() {
                Log.d("people_gram", "타임아웃");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                //Log.d("people_gram", response);
                try {

                    JSONObject data = new JSONObject(response);
                    JSONObject campaign_data = new JSONObject(data.getString("campaign_data"));
                    String point = data.getString("point");

                    SharedPreferenceUtil.putSharedPreference(SurveyComplateActivity.this, "point", point);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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

    public void finish()
    {
        Intent intent = getIntent();
        setResult(RESULT_OK, intent);

        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
