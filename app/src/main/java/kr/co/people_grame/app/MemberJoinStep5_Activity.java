package kr.co.people_grame.app;

import android.content.Intent;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MemberJoinStep5_Activity extends AppCompatActivity {

    private ImageView sex_man, sex_woman;
    private String sexType = "";
    private LinearLayout nextLL;

    private MemberData md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step5_);

        md = new MemberData();

        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setVisibility(View.INVISIBLE);

        sex_man = (ImageView) findViewById(R.id.sex_man);
        sex_woman = (ImageView) findViewById(R.id.sex_woman);

        sex_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex_man.setImageResource(R.drawable.sexman_on);
                sex_woman.setImageResource(R.drawable.sexwoman_off);
                sexType = "M";
                md.set_sex("M");
                nextLL.setVisibility(View.VISIBLE);
            }
        });

        sex_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex_man.setImageResource(R.drawable.sexman_off);
                sex_woman.setImageResource(R.drawable.sexwoman_on);
                sexType = "F";
                md.set_sex("F");
                nextLL.setVisibility(View.VISIBLE);
            }
        });

        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sexType.equals("")) {
                    Toast.makeText(MemberJoinStep5_Activity.this, "성별을 선택해주세요", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MemberJoinStep5_Activity.this, MemberJoinStep6_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                    next_finish();
                }
            }
        });

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


    public void next_finish()
    {
        super.finish();
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
