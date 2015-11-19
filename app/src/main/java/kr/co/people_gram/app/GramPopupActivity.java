package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class GramPopupActivity extends AppCompatActivity {

    String Point = "0";

    private TextView gram_point;
    private ProgressDialog dialog;
    private String uid, people_uid, people_username, gubun1, gubun2;
    private LinearLayout panel_linear;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gram_popup);

        gram_point = (TextView) findViewById(R.id.gram_point);
        panel_linear = (LinearLayout) findViewById(R.id.panel_linear);

        intent = getIntent();
        if(intent != null) {
            Point = intent.getStringExtra("use_point");
            people_uid = intent.getStringExtra("people_uid");
            people_username = intent.getStringExtra("people_username");
            gubun1 = intent.getStringExtra("gubun1");
            gubun2 = intent.getStringExtra("gubun2");
            gram_point.setText(Point + "포인트");
        }

        if(SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "panelYN").equals("Y")) {
            panel_linear.setVisibility(View.GONE);
        }
    }

    public void paymentBtn(View v) {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "uid"));
        params.put("people_uid", people_uid);
        params.put("people_username", people_username);
        params.put("gubun1", gubun1);
        params.put("gubun2", gubun2);
        params.put("point", Point);
        HttpClient.post("/people/peoplePointPayment", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(GramPopupActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    if(jobj.getString("code").equals("000") == true) {
                        SharedPreferenceUtil.putSharedPreference(GramPopupActivity.this, "point", jobj.getString("data_point"));
                        intent.putExtra("data_OK", "OK");
                        setResult(RESULT_OK, intent);
                        finish();

                        //Intent intent = new Intent(GramPopupActivity.this, )


                    } else {
                        Toast.makeText(GramPopupActivity.this, "잔여 포인트가 부족합니다("+jobj.getString("data_point")+"포인트)", Toast.LENGTH_LONG).show();
                        intent.putExtra("data_OK", "NO");
                        setResult(RESULT_OK,intent);
                        finish();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void backBtn(View v) {
        finish();
    }

    public void finish()
    {

        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);

    }


}
