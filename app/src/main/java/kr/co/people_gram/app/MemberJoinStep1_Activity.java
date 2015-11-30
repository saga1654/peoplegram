package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;


public class MemberJoinStep1_Activity extends AppCompatActivity {
    private String PhoneNumber, telecom;
    private MemberData md;
    private LinearLayout agree_btn, userinfo_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step1_);

        md = new MemberData();

        agree_btn = (LinearLayout) findViewById(R.id.agree_btn);
        userinfo_btn = (LinearLayout) findViewById(R.id.userinfo_btn);
        agree_btn.setOnClickListener(onBtnClickListener);
        userinfo_btn.setOnClickListener(onBtnClickListener);

        TelephonyManager systemService = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        try {

            PhoneNumber = systemService.getLine1Number();    //폰번호를 가져오는 겁니다..
            telecom = systemService.getNetworkOperatorName();

            md.set_phone(PhoneNumber);
            md.set_telecom(telecom);
            Log.d("people_gram", PhoneNumber);
        } catch (Exception e) {

            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoinStep1_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            alert.setMessage("현재 핸드폰은 사용불가능한 핸드폰입니다.\n활성화된 핸드폰으로 가입 부탁드립니다.");
            alert.show();
        }

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

    public void NextBtn(View v) {

        Intent intent = new Intent(MemberJoinStep1_Activity.this, MemberJoinStep2_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
        super.finish();
    }
    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();


            switch (v.getId()) {

                case R.id.agree_btn:
                    intent = new Intent(MemberJoinStep1_Activity.this, Setting_UserAgreeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    break;
                case R.id.userinfo_btn:
                    intent = new Intent(MemberJoinStep1_Activity.this, Setting_UserInfoActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    break;


            }
        }
    };
    public void closeMember(View v) {
        finish();
    }

    public void finish()
    {

        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
