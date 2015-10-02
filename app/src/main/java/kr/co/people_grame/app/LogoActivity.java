package kr.co.people_grame.app;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogoActivity extends AppCompatActivity {

    private Intent intent;
    private String uid, username;

    private LinearLayout login_activity_li_logo, login_activity_li_btn;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        login_activity_li_logo = (LinearLayout) findViewById(R.id.login_activity_li_logo);
        login_activity_li_btn = (LinearLayout) findViewById(R.id.login_activity_li_btn);

        //Log.d("people_gram", SharedPreferenceUtil.getSharedPreference(this, "uid"));

        if(Utilities.getNetworkType(this) == 3) {
            new AlertDialog.Builder(this)
                    .setTitle("프로그램 종료")
                    .setMessage("네트워크가 정상적으로 연결되지 않았습니다.")
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    })
                    .show();
        }

        if(SharedPreferenceUtil.getSharedPreference(this, "uid") == "") {
            //login_activity_li_btn.setVisibility(View.VISIBLE);
        } else {




            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {


                    /*
                    Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    finish();
                    */

                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(LogoActivity.this, "uid"));
                    HttpClient.post("/user/pointSelect", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(LogoActivity.this, "", "데이터 수신중");
                        }

                        public void onFinish() {
                            //Log.d("people_gram", "완료");
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {
                            try {
                                JSONObject jobj = new JSONObject(response);

                                SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "point", jobj.getString("POINT"));
                                SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "mytype", jobj.getString("MYTYPE"));
                                SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "my_speed", jobj.getString("MY_SPEED"));
                                SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "my_control", jobj.getString("MY_CONTROL"));

                                Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                                finish();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                }
            }, 3000);


        }

        //if(Utilities.getNetworkType(this) == 3) {


        //LinearLayout.LayoutParams logoParams = (LinearLayout.LayoutParams) login_activity_li_logo.getLayoutParams();
        /*
        uid = SharedPreferenceUtil.getSharedPreference(this, "uid");
        username = SharedPreferenceUtil.getSharedPreference(this, "username");
        if(!uid.equals("")) {


        }
        */

        setContentView(R.layout.activity_logo);
    }

    public void btn_memberJoin(View v)
    {
        intent = new Intent(LogoActivity.this, MemberJoin_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void btn_memberLogin(View v) {
        intent = new Intent(LogoActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void btn_memberQuestion(View v) {
        /*
        if(uid.equals("")) {
            Toast.makeText(this, "로그인 후 가능합니다.", Toast.LENGTH_SHORT).show();
        } else {
            intent = new Intent(LogoActivity.this, MyQuestion_Activity.class);
            startActivity(intent);
        }
        */
        intent = new Intent(LogoActivity.this, MyQuestion_Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this)
                        .setTitle("프로그램 종료")
                        .setMessage("프로그램을 종료 하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());

                                //android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();

                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

}
