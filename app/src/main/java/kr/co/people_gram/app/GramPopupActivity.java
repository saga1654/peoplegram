package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
    private TextView my_point;
    private ProgressDialog progressdialog;
    private String uid, people_uid, people_username, gubun1, gubun2;
    private LinearLayout panel_linear;

    private LinearLayout panel_join_ll, point_plus_ll;

    private Boolean panel_check = false;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gram_popup);

        panel_join_ll = (LinearLayout) findViewById(R.id.panel_join_ll);
        point_plus_ll = (LinearLayout) findViewById(R.id.point_plus_ll);


        gram_point = (TextView) findViewById(R.id.gram_point);
        my_point = (TextView) findViewById(R.id.my_point);

        intent = getIntent();
        if(intent != null) {
            Point = intent.getStringExtra("use_point");
            people_uid = intent.getStringExtra("people_uid");
            people_username = intent.getStringExtra("people_username");
            gubun1 = intent.getStringExtra("gubun1");
            gubun2 = intent.getStringExtra("gubun2");
            gram_point.setText(Point);
        }

        my_point.setText("현재 포인트 " + Utilities.comma(Integer.parseInt(SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "point"))) + "p");

        if(SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "panelYN").equals("Y")) {
            //Toast.makeText(GramPopupActivity.this, "패널가입확인", Toast.LENGTH_LONG).show();
            panel_check = true;

            panel_join_ll.setVisibility(View.GONE);
            point_plus_ll.setVisibility(View.VISIBLE);
        } else {
            //Toast.makeText(GramPopupActivity.this, "미가입", Toast.LENGTH_LONG).show();
            panel_check = false;

            panel_join_ll.setVisibility(View.VISIBLE);
            point_plus_ll.setVisibility(View.GONE);
        }

    }


    public void paymentBtn(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(GramPopupActivity.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RequestParams params = new RequestParams();
                params.put("uid",SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this,"uid"));
                params.put("people_uid",people_uid);
                params.put("people_username",people_username);
                params.put("gubun1",gubun1);
                params.put("gubun2",gubun2);
                params.put("point",Point);
                HttpClient.post("/people/peoplePointPayment",params,new

                        AsyncHttpResponseHandler() {
                            public void onStart () {
                                progressdialog = ProgressDialog.show(GramPopupActivity.this, "", "데이터 수신중");
                            }

                            public void onFinish() {
                                progressdialog.dismiss();
                            }

                            @Override
                            public void onSuccess(String response) {

                                try {
                                    JSONObject jobj = new JSONObject(response);

                                    if (jobj.getString("code").equals("000") == true) {
                                        SharedPreferenceUtil.putSharedPreference(GramPopupActivity.this, "point", jobj.getString("data_point"));
                                        intent.putExtra("data_OK", "OK");
                                        setResult(RESULT_OK, intent);
                                        Toast.makeText(GramPopupActivity.this, "결제가 완료되었습니다. 관계팁과 피플타입을 보실수 있습니다.", Toast.LENGTH_LONG).show();
                                        finish();


                                        //Intent intent = new Intent(GramPopupActivity.this, )


                                    } else {
                                        Toast.makeText(GramPopupActivity.this, "잔여 포인트가 부족합니다(" + jobj.getString("data_point") + "포인트) 포인트 충전 후 이용부탁드립니다.", Toast.LENGTH_LONG).show();
                                        intent.putExtra("data_OK", "NO");
                                        setResult(RESULT_OK, intent);
                                        finish();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
            }
        });
        alert.setMessage("포인트 결제를 진행하려고 합니다.\n결제를 진행하시려면 확인 버튼을 클릭해주세요.");
        alert.setNegativeButton("취소", null);
        alert.show();
    }

    public void pointBtn(View v)
    {
        if(panel_check == true) {
            Intent intent = new Intent(GramPopupActivity.this, SubGramPoint.class);
            startActivity(intent);
            overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(GramPopupActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RequestParams params = new RequestParams();
                    params.put("uid",SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this,"uid"));
                    HttpClient.post("/user/panelJoin",params,new AsyncHttpResponseHandler() {
                        public void onStart () {
                            progressdialog = ProgressDialog.show(GramPopupActivity.this, "", "데이터 수신중");
                        }

                        public void onFinish() {
                            progressdialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {

                            try {
                                JSONObject jobj = new JSONObject(response);
                                if(jobj.getString("code").equals("000")) {
                                    SharedPreferenceUtil.putSharedPreference(GramPopupActivity.this, "point", jobj.getString("point"));
                                    panel_check = true;
                                    my_point.setText("현재 포인트 " + Utilities.comma(Integer.parseInt(SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "point"))) + "p");

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }
            });
            alert.setMessage("설문조사 회원가입을 하시겠습니까?");
            alert.setNegativeButton("취소", null);
            alert.show();
        }
    }

    public void point_add_Btn(View v) {

        Intent intent = new Intent(GramPopupActivity.this, SubGramPoint.class);
        startActivity(intent);
        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
        super.finish();
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
