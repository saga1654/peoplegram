package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


public class LogoActivity extends AppCompatActivity {


    private Intent intent;
    private String uid, username;

    private LinearLayout login_activity_li_logo, login_activity_li_btn;
    private ImageView logo_imageView;
    private ProgressDialog dialog;
    private AnimationDrawable frameAnimation;
    private Animation animation;

    private String token = "";

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private DataProfileCount dpc;

    /**
     * Instance ID를 이용하여 디바이스 토큰을 가져오는 RegistrationIntentService를 실행한다.
     */
    public void getInstanceIdToken() {
        if (checkPlayServices()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    /**
     * Google Play Service를 사용할 수 있는 환경이지를 체크한다.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("mainActivity", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * 앱이 실행되어 화면에 나타날때 LocalBoardcastManager에 액션을 정의하여 등록한다.
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_READY));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_GENERATING));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));

    }

    /**
     * 앱이 화면에서 사라지면 등록된 LocalBoardcast를 모두 삭제한다.
     */
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }



    /**
     * LocalBroadcast 리시버를 정의한다. 토큰을 획득하기 위한 READY, GENERATING, COMPLETE 액션에 따라 UI에 변화를 준다.
     */
    public void registBroadcastReceiver(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();


                if(action.equals(QuickstartPreferences.REGISTRATION_READY)){
                    // 액션이 READY일 경우
                    //mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                    //mInformationTextView.setVisibility(View.GONE);
                    Log.d("people_gram", "READY");
                } else if(action.equals(QuickstartPreferences.REGISTRATION_GENERATING)){
                    // 액션이 GENERATING일 경우
                    //mRegistrationProgressBar.setVisibility(ProgressBar.VISIBLE);
                    //mInformationTextView.setVisibility(View.VISIBLE);
                    //mInformationTextView.setText(getString(R.string.registering_message_generating));

                    Log.d("people_gram", "GENERATING");

                } else if(action.equals(QuickstartPreferences.REGISTRATION_COMPLETE)){
                    // 액션이 COMPLETE일 경우
                    //mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                    //mRegistrationButton.setText(getString(R.string.registering_message_complete));
                    //mRegistrationButton.setEnabled(false);
                    String token = intent.getStringExtra("token");
                    SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "token", token);
                    Log.d("people_gram", "토큰=" + token);
                    //mInformationTextView.setText(token);
                }

            }
        };
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dpc = new DataProfileCount();

        SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "panelYN", "N");


        if(SharedPreferenceUtil.getSharedPreference(LogoActivity.this, "token") == "") {
            registBroadcastReceiver();
            getInstanceIdToken();
        }


        /*
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
        */

        setContentView(R.layout.activity_logo);

        login_activity_li_btn = (LinearLayout) findViewById(R.id.login_activity_li_btn);

        logo_imageView = (ImageView) findViewById(R.id.logo_imageView);
        logo_imageView.setBackgroundResource(R.drawable.logo_animation);
        frameAnimation = (AnimationDrawable) logo_imageView.getBackground();
        frameAnimation.start();

        long totalDuration = 0;
        for(int i = 0; i < frameAnimation.getNumberOfFrames(); i++) {
            totalDuration += frameAnimation.getDuration(i);
        }

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                frameAnimation.stop();

                //if(Utilities.getNetworkType(LogoActivity.this) != 3) {
                    if (SharedPreferenceUtil.getSharedPreference(LogoActivity.this, "uid") == "") {
                        if (SharedPreferenceUtil.getSharedPreference(LogoActivity.this, "intro") == "Y") {
                            Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                            finish();
                        } else {
                            Intent intent = new Intent(LogoActivity.this, Guide_Activity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                            finish();
                        }

                        //login_activity_li_btn.setVisibility(View.VISIBLE);
                    } else {
                        RequestParams params = new RequestParams();
                        params.put("uid", SharedPreferenceUtil.getSharedPreference(LogoActivity.this, "uid"));
                        params.put("token", SharedPreferenceUtil.getSharedPreference(LogoActivity.this, "token"));
                        HttpClient.post("/user/pointSelect", params, new AsyncHttpResponseHandler() {
                            public void onStart() {
                            }

                            public void onFinish() {
                            }

                            @Override
                            public void onSuccess(String response) {
                                Log.d("people_gram", response);
                                try {
                                    JSONObject jobj = new JSONObject(response);


                                    SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "point", jobj.getString("POINT"));
                                    SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "mytype", jobj.getString("MYTYPE"));
                                    SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "my_speed", jobj.getString("MY_SPEED"));
                                    SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "my_control", jobj.getString("MY_CONTROL"));
                                    SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "email", jobj.getString("EMAIL"));
                                    SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "panelYN", jobj.getString("PANEL"));

                                    dpc.set_user_count(jobj.getInt("NEW_COUNT"));


                                    if (jobj.getString("MYTYPE").equals("")) {
                                        Intent intent = new Intent(LogoActivity.this, MemberComplate_Activity.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                                        finish();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    }
                /*
                } else {
                    new AlertDialog.Builder(LogoActivity.this)
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
                */
            }
        };
        timer.schedule(timerTask, totalDuration);



        //Log.d("people_gram", "토큰저장="+SharedPreferenceUtil.getSharedPreference(LogoActivity.this, "token"));

        //getInstanceIdToken();




        //login_activity_li_logo = (LinearLayout) findViewById(R.id.login_activity_li_logo);
        //login_activity_li_btn = (LinearLayout) findViewById(R.id.login_activity_li_btn);


        /*
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
                                SharedPreferenceUtil.putSharedPreference(LogoActivity.this, "email", jobj.getString("EMAIL"));


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
        */

    }

    public void btn_memberJoin(View v)
    {
        intent = new Intent(LogoActivity.this, MemberJoinStep1_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
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
