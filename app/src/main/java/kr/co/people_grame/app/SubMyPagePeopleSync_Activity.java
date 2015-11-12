package kr.co.people_grame.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class SubMyPagePeopleSync_Activity extends AppCompatActivity {

    private LinearLayout friend_sync, friend_hide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_my_page_people_sync_);

        friend_sync = (LinearLayout) findViewById(R.id.friend_sync);
        friend_hide = (LinearLayout) findViewById(R.id.friend_hide);

        //friend_sync.setOnTouchListener(onBtnTouchListener);
        //friend_hide.setOnTouchListener(onBtnTouchListener);

    }

    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId())
            {
                case R.id.friend_sync:
                    if(event.getAction() == 2) {
                        friend_sync.setBackgroundColor(Color.rgb(255, 255, 255));
                    } else {
                        friend_sync.setBackgroundColor(Color.rgb(241, 241, 241));

                    }
                    break;
                case R.id.friend_hide:
                    if(event.getAction() == 2) {
                        friend_hide.setBackgroundColor(Color.rgb(255, 255, 255));
                    } else {
                        friend_hide.setBackgroundColor(Color.rgb(241, 241, 241));
                    }
                    break;
            }
            return false;
        }
    };



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

    public void closeView(View v) {
        finish();
    }

    public void friend_sync_btn(View v) {
        Intent intent =  new Intent(SubMyPagePeopleSync_Activity.this, PeopleSync_Activity.class);
        intent.putExtra("mypage","ok");
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void friend_hide_btn(View v) {
        Intent intent = new Intent(SubMyPagePeopleSync_Activity.this, SubMyPagePeopleHide_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

}
