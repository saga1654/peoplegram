package kr.co.people_grame.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class SubPeopleListPopup_Activity extends AppCompatActivity {

    private String people_uid = "";
    private String people_username = "";
    private String people_mood = "";

    private TextView popup_username, popup_mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_list_popup_);


        popup_username = (TextView) findViewById(R.id.popup_username);
        popup_mood = (TextView) findViewById(R.id.popup_mood);

        Intent intent = getIntent();
        if(intent != null) {
            people_uid = intent.getStringExtra("people_uid");
            people_username = intent.getStringExtra("people_username");
            people_mood = intent.getStringExtra("people_mood");

            popup_username.setText(people_username);

            if(people_mood.equals("")) {
                popup_mood.setText("미설정");
            } else {
                popup_mood.setText(people_mood);
            }

        }

    }

    public void peopleType_btn(View v) {
        Intent intent = new Intent(SubPeopleListPopup_Activity.this, YouType_Actvity_step1.class);
        finish();

        intent.putExtra("people_uid", people_uid);
        intent.putExtra("people_username", people_username);

        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void peopleView_btn(View v)
    {
        Intent intent = new Intent(SubPeopleListPopup_Activity.this, SubPeopleListSelect_Activity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

}
