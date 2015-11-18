package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SettingActivity extends AppCompatActivity {

    private TextView top_title;
    private Intent intent;
    private Switch push_switch;
    private ProgressDialog dialog;

    private LinearLayout notice_btn, agree_btn, userinfo_btn, logout_btn, question_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        top_title = (TextView) findViewById(R.id.top_title);
        top_title.setText("설정");


        push_switch = (Switch) findViewById(R.id.push_switch);

        notice_btn = (LinearLayout) findViewById(R.id.notice_btn);
        agree_btn = (LinearLayout) findViewById(R.id.agree_btn);
        userinfo_btn = (LinearLayout) findViewById(R.id.userinfo_btn);
        question_btn = (LinearLayout) findViewById(R.id.question_btn);
        logout_btn = (LinearLayout) findViewById(R.id.logout_btn);

        notice_btn.setOnTouchListener(onBtnTouchListener);
        notice_btn.setOnClickListener(onBtnClickListener);

        agree_btn.setOnTouchListener(onBtnTouchListener);
        agree_btn.setOnClickListener(onBtnClickListener);

        userinfo_btn.setOnTouchListener(onBtnTouchListener);
        userinfo_btn.setOnClickListener(onBtnClickListener);

        logout_btn.setOnTouchListener(onBtnTouchListener);
        logout_btn.setOnClickListener(onBtnClickListener);

        question_btn.setOnTouchListener(onBtnTouchListener);
        question_btn.setOnClickListener(onBtnClickListener);

        pushdata();




        push_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String push_yn = "N";
                if(isChecked == true) {
                    push_yn = "Y";
                } else {
                    push_yn = "N";
                }

                RequestParams params = new RequestParams();
                params.put("uid", SharedPreferenceUtil.getSharedPreference(SettingActivity.this, "uid"));
                params.put("push_yn", push_yn);
                HttpClient.post("/user/pushSetting", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        dialog = ProgressDialog.show(SettingActivity.this, "", "데이터 수신중");
                    }

                    public void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(String response) {
                        if(response.equals("000") == false) {
                            Toast.makeText(SettingActivity.this, "다시 시도해주세요.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void pushdata() {
        RequestParams params = new RequestParams();

        params.put("uid", SharedPreferenceUtil.getSharedPreference(SettingActivity.this, "uid"));

        HttpClient.post("/user/pushSelect", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SettingActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            public void onSuccess(String response) {
                if(response.equals("Y")) {
                    push_switch.setChecked(true);
                } else {
                    push_switch.setChecked(false);
                }
            }
        });
    }

    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.notice_btn:
                    if(event.getAction() == 2) {
                        notice_btn.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        notice_btn.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.agree_btn:
                    if(event.getAction() == 2) {
                        agree_btn.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        agree_btn.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.userinfo_btn:
                    if(event.getAction() == 2) {
                        userinfo_btn.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        userinfo_btn.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.logout_btn:
                    if(event.getAction() == 2) {
                        logout_btn.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        logout_btn.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.question_btn:
                    if(event.getAction() == 2) {
                        question_btn.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        question_btn.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

            }
            return false;
        }
    };

    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.notice_btn:
                    intent = new Intent(SettingActivity.this, NoticeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    break;
                case R.id.agree_btn:
                    intent = new Intent(SettingActivity.this, Setting_UserAgreeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    break;
                case R.id.userinfo_btn:
                    intent = new Intent(SettingActivity.this, Setting_UserInfoActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    break;
                case R.id.logout_btn:
                    AlertDialog.Builder alert = new AlertDialog.Builder(SettingActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferenceUtil.putSharedPreference(SettingActivity.this, "uid", "");
                            SharedPreferenceUtil.putSharedPreference(SettingActivity.this, "username", "");
                            SharedPreferenceUtil.putSharedPreference(SettingActivity.this, "point", "");
                            SharedPreferenceUtil.putSharedPreference(SettingActivity.this, "mytype", "");
                            SharedPreferenceUtil.putSharedPreference(SettingActivity.this, "my_speed", "");
                            SharedPreferenceUtil.putSharedPreference(SettingActivity.this, "my_control", "");
                            SharedPreferenceUtil.putSharedPreference(SettingActivity.this, "email", "");

                            finish();
                            MainActivity.mainActivity.finish();

                            Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    alert.setMessage("로그아웃 하시겠습니까?");
                    alert.setNegativeButton("취소", null);
                    alert.show();
                    break;
                case R.id.question_btn:
                    intent = new Intent(SettingActivity.this, SubQnaActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    break;

            }
        }
    };

    public void settingPrev(View v) {
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