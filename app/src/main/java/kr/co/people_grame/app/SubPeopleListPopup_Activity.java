package kr.co.people_grame.app;

import android.content.Intent;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SubPeopleListPopup_Activity extends AppCompatActivity {

    private String people_uid = "";
    private String people_username = "";
    private String people_mood = "";
    private String people_type = "";
    private String people_gubun1 = "";
    private String people_gubun2 = "";
    private int people_speed = 0;
    private int people_control = 0;

    private TextView popup_username, popup_mood;
    private ImageView popup_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_list_popup_);



        popup_username = (TextView) findViewById(R.id.popup_username);
        popup_type = (ImageView) findViewById(R.id.popup_type);

        Intent intent = getIntent();
        if(intent != null) {
            people_uid = intent.getStringExtra("people_uid");
            people_username = intent.getStringExtra("people_username");
            people_type = intent.getStringExtra("people_type");
            people_gubun1 = intent.getStringExtra("people_gubun1");
            people_gubun2 = intent.getStringExtra("people_gubun2");
            people_speed = intent.getIntExtra("people_speed", 0);
            people_control = intent.getIntExtra("people_control", 0);


            PeopleData pd = new PeopleData();
            pd.set_people_uid(people_uid);
            pd.set_people_username(people_username);
            pd.set_people_type(people_type);
            pd.set_people_gubun1(people_gubun1);
            pd.set_people_gubun2(people_gubun2);
            pd.set_people_speed(people_speed);
            pd.set_people_control(people_control);


            popup_username.setText(people_username);


            switch (people_type) {
                case "A":
                    popup_type.setImageResource(R.mipmap.people_type_a);
                    break;
                case "E":
                    popup_type.setImageResource(R.mipmap.people_type_e);
                    break;
                case "D":
                    popup_type.setImageResource(R.mipmap.people_type_d);
                    break;
                case "I":
                    popup_type.setImageResource(R.mipmap.people_type_i);
                    break;
                default:
                    popup_type.setImageResource(R.mipmap.people_type_default);
                    break;
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

        /*
        intent.putExtra("people_uid", people_uid);
        intent.putExtra("people_username", people_username);
        intent.putExtra("people_mood", people_mood);
        intent.putExtra("people_type", people_type);
        */
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
