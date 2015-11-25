package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class GuideActivityStep1 extends AppCompatActivity {

    private ImageView guide;
    private String step ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_activity_step1);

        guide = (ImageView) findViewById(R.id.guide_img);

        Intent intent = getIntent();
        if(intent != null) {
            step = intent.getStringExtra("step");
            switch (step){
                case "1":
                    guide.setImageResource(R.drawable.guide_content_1);
                    break;

                case "2":
                    guide.setImageResource(R.drawable.guide_content_2);
                    break;
                case "3":
                    guide.setImageResource(R.drawable.guide_content_3);
                    break;
                case "4":
                    guide.setImageResource(R.drawable.guide_content_4);
                    break;
            }
        }


    }


    public void step1_close_btn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
