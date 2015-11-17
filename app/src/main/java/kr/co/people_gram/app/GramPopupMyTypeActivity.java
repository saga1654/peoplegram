package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class GramPopupMyTypeActivity extends AppCompatActivity {


    private TextView gram_point;
    private ProgressDialog dialog;
    private Intent intent;
    private String point = "0";
    private String gubun1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gram_popup_my_type);

        gram_point = (TextView) findViewById(R.id.gram_point);

        intent = getIntent();
        if(intent != null) {
            point = intent.getStringExtra("point");
            gubun1 = intent.getStringExtra("gubun1");
            gram_point.setText(point + "g");
        }

    }

    public void paymentBtn(View v) {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(GramPopupMyTypeActivity.this, "uid"));
        params.put("gubun1", gubun1);
        HttpClient.post("/my_type/profile_people_type", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(GramPopupMyTypeActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {

                JSONObject jobj = null;
                try {
                    jobj = new JSONObject(response);

                    if(jobj.getString("code").equals("999")) {
                        intent.putExtra("code", "999");
                        Toast.makeText(GramPopupMyTypeActivity.this, "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        String my_type = SharedPreferenceUtil.getSharedPreference(GramPopupMyTypeActivity.this, "mytype");
                        String people_type = jobj.getString("people_type");
                        String my_speed = jobj.getString("my_speed");
                        String my_control = jobj.getString("my_control");
                        String people_speed = jobj.getString("people_speed");
                        String people_control = jobj.getString("people_control");
                        String gubun1 = jobj.getString("gubun1");

                        intent.putExtra("code", "000");
                        intent.putExtra("mytype", "A");
                        intent.putExtra("people_type", "A");
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", my_speed);
                        intent.putExtra("my_control", my_control);
                        intent.putExtra("people_speed", people_speed);
                        intent.putExtra("people_control", people_control);
                        startActivity(intent);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                /*
                if(response.equals("999") == false) {
                    intent.putExtra("code", "000");
                    intent.putExtra("people_type", response);
                    intent.putExtra("gubun1", gubun1);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    intent.putExtra("code", "999");
                    Toast.makeText(GramPopupMyTypeActivity.this, "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    finish();
                }
                */
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
