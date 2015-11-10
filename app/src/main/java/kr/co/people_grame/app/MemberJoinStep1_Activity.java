package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class MemberJoinStep1_Activity extends AppCompatActivity {
    private String PhoneNumber, telecom;
    private MemberData md;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step1_);

        md = new MemberData();

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

    public void closeMember(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
