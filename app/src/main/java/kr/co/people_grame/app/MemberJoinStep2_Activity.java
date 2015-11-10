package kr.co.people_grame.app;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

public class MemberJoinStep2_Activity extends AppCompatActivity {

    private LinearLayout nextLL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step2_);
        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setVisibility(View.GONE);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
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

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

    public void closeMember(View v)
    {
        finish();
    }

}
