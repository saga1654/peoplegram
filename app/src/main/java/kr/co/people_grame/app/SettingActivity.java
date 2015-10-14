package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    private TextView top_title;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        top_title = (TextView) findViewById(R.id.top_title);
        top_title.setText("설정");
    }

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

    public void noticeBtn(View v) {
        intent = new Intent(SettingActivity.this, NoticeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void agreeBtn(View v) {
        intent = new Intent(SettingActivity.this, Setting_UserAgreeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void userinfoBtn(View v) {
        intent = new Intent(SettingActivity.this, Setting_UserInfoActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void questionBtn(View v) {
        intent = new Intent(SettingActivity.this, SubQnaActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }
}
