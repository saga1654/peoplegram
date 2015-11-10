package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

public class MemberJoinStep1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step1_);

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
