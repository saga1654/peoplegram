package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private String viewType = "";

    private LinearLayout panel_join_ll, point_plus_ll;

    private String  mytype, my_data1, my_data2, my_data3, my_data4, my_data5, my_data6, my_data7, my_data8, my_data9, my_data10;
    private String people_type, my_speed, my_control, people_speed, people_control;
    private String people_data1, people_data2, people_data3, people_data4, people_data5, people_data6, people_data7, people_data8, people_data9, people_data10;
    private String people_total;

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
            viewType = intent.getStringExtra("viewType");

            if(viewType.equals("my")) {
                mytype = intent.getStringExtra("mytype");
                my_data1 = intent.getStringExtra("my_data1");
                my_data2 = intent.getStringExtra("my_data2");
                my_data3 = intent.getStringExtra("my_data3");
                my_data4 = intent.getStringExtra("my_data4");
                my_data5 = intent.getStringExtra("my_data5");
                my_data6 = intent.getStringExtra("my_data6");
                my_data7 = intent.getStringExtra("my_data7");
                my_data8 = intent.getStringExtra("my_data8");
                my_data9 = intent.getStringExtra("my_data9");
                my_data10 = intent.getStringExtra("my_data10");

                people_type = intent.getStringExtra("people_type");
                my_speed = intent.getStringExtra("my_speed");
                my_control = intent.getStringExtra("my_control");
                people_speed = intent.getStringExtra("people_speed");
                people_control = intent.getStringExtra("people_control");

                people_data1 = intent.getStringExtra("people_data1");
                people_data2 = intent.getStringExtra("people_data2");
                people_data3 = intent.getStringExtra("people_data3");
                people_data4 = intent.getStringExtra("people_data4");
                people_data5 = intent.getStringExtra("people_data5");
                people_data6 = intent.getStringExtra("people_data6");
                people_data7 = intent.getStringExtra("people_data7");
                people_data8 = intent.getStringExtra("people_data8");
                people_data9 = intent.getStringExtra("people_data9");
                people_data10 = intent.getStringExtra("people_data10");

                people_total = intent.getStringExtra("people_total");
            }

        }

        my_point.setText("현재 포인트 " + Utilities.comma(Integer.parseInt(SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "point"))) + "p");

        if(SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "panelYN").equals("Y")) {
            //Toast.makeText(GramPopupActivity.this, "패널가입확인", Toast.LENGTH_LONG).show();
            panel_check = true;

            panel_join_ll.setVisibility(View.GONE);
            //
        } else {
            //Toast.makeText(GramPopupActivity.this, "미가입", Toast.LENGTH_LONG).show();
            panel_check = false;

            panel_join_ll.setVisibility(View.VISIBLE);
            //point_plus_ll.setVisibility(View.GONE);
        }
        point_plus_ll.setVisibility(View.VISIBLE);
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
                params.put("viewType", viewType);
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
                                        //intent.putExtra("mytype", mytype);


                                        if (viewType.equals("my")) {

                                            intent.putExtra("mytype", mytype);

                                            intent.putExtra("my_data1", my_data1);
                                            intent.putExtra("my_data2", my_data2);
                                            intent.putExtra("my_data3", my_data3);
                                            intent.putExtra("my_data4", my_data4);
                                            intent.putExtra("my_data5", my_data5);
                                            intent.putExtra("my_data6", my_data6);
                                            intent.putExtra("my_data7", my_data7);
                                            intent.putExtra("my_data8", my_data8);
                                            intent.putExtra("my_data9", my_data9);
                                            intent.putExtra("my_data10", my_data10);

                                            intent.putExtra("people_type", people_type);
                                            intent.putExtra("my_speed", my_speed);
                                            intent.putExtra("my_control", my_control);
                                            intent.putExtra("people_speed", people_speed);
                                            intent.putExtra("people_control", people_control);
                                            intent.putExtra("people_total", people_total);
                                            intent.putExtra("people_data1", people_data1);
                                            intent.putExtra("people_data2", people_data2);
                                            intent.putExtra("people_data3", people_data3);
                                            intent.putExtra("people_data4", people_data4);
                                            intent.putExtra("people_data5", people_data5);
                                            intent.putExtra("people_data6", people_data6);
                                            intent.putExtra("people_data7", people_data7);
                                            intent.putExtra("people_data8", people_data8);
                                            intent.putExtra("people_data9", people_data9);
                                            intent.putExtra("people_data10", people_data10);

                                            intent.putExtra("viewType", "my");



                                            Toast.makeText(GramPopupActivity.this, "결제가 완료되었습니다.", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(GramPopupActivity.this, "결제가 완료되었습니다. 관계팁과 피플타입을 보실수 있습니다.", Toast.LENGTH_LONG).show();
                                        }
                                        setResult(RESULT_OK, intent);
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
                                    SharedPreferenceUtil.putSharedPreference(GramPopupActivity.this, "panelYN", "Y");
                                    panel_join_ll.setVisibility(View.GONE);
                                    panel_check = true;
                                    my_point.setText("현재 포인트 " + Utilities.comma(Integer.parseInt(SharedPreferenceUtil.getSharedPreference(GramPopupActivity.this, "point"))) + "p");
                                    Toast.makeText(GramPopupActivity.this, "2000포인트 적립되었습니다.", Toast.LENGTH_LONG).show();

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
