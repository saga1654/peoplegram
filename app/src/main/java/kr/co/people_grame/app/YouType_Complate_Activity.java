package kr.co.people_grame.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class YouType_Complate_Activity extends AppCompatActivity {
    private String people_uid, people_username, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;
    private String speed, control;
    private String youtype;


    private ImageView mytype_activity_typeImg;
    private TextView mytype_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_type__complate_);

        mytype_activity_typeImg = (ImageView) findViewById(R.id.mytype_activity_typeImg);
        mytype_tv = (TextView) findViewById(R.id.mytype_tv);

        Intent intent = getIntent();
        if(intent != null) {
            people_uid = intent.getStringExtra("people_uid");
            people_username = intent.getStringExtra("people_username");
            data1 = intent.getStringExtra("data1");
            data2 = intent.getStringExtra("data2");
            data3 = intent.getStringExtra("data3");
            data4 = intent.getStringExtra("data4");
            data5 = intent.getStringExtra("data5");
            data6 = intent.getStringExtra("data6");
            data7 = intent.getStringExtra("data7");
            data8 = intent.getStringExtra("data8");
            data9 = intent.getStringExtra("data9");
            data10 = intent.getStringExtra("data10");
            speed = intent.getStringExtra("speed");
            control = intent.getStringExtra("control");
            youtype = intent.getStringExtra("youtype");

            Log.d("people_gram", people_username);

            switch (youtype)
            {
                case "I":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_i);
                    break;
                case "D":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_d);
                    break;
                case "E":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_e);
                    break;
                case "A":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_a);
                    break;
            }
        }

    }

    public void mytype_re_btn(View v)
    {
        Intent intent = new Intent(YouType_Complate_Activity.this, YouType_Actvity_step1.class);
        intent.putExtra("people_uid", people_uid);
        intent.putExtra("people_username", people_username);
        startActivity(intent);
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
        closeView();
    }

    public void mytype_view_btn(View v)
    {
        //mytype_view_btn
        Intent intent = new Intent(YouType_Complate_Activity.this, YouType_Activity.class);
        intent.putExtra("youtype", youtype);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void start_btn(View v) {
        finish();
    }

    public void closeView()
    {
        super.finish();
    }

    public void prevBtn(View v) {
        finish();
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

}
