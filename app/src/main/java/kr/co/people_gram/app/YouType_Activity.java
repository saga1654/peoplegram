package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class YouType_Activity extends AppCompatActivity {

    private ProgressDialog dialog;
    private String youType;

    private ImageView youtype_activity_typeImg, myresult_close;
    private LinearLayout youtype_li_bg, youtype_menu01, youtype_menu02, youtype_menu03, youtype_menu04, youtype_menu05, youtype_menu06, youtype_menu07, youtype_menu08;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_type_);

        Intent intent = getIntent();

        if(intent != null) {
            youType = intent.getStringExtra("youtype");
        }


        youtype_activity_typeImg = (ImageView) findViewById(R.id.youtype_activity_typeImg);
        //myresult_close = (ImageView) findViewById(R.id.myresult_close);
        youtype_li_bg = (LinearLayout) findViewById(R.id.youtype_li_bg);


        youtype_menu01 = (LinearLayout) findViewById(R.id.youtype_menu01);
        youtype_menu02 = (LinearLayout) findViewById(R.id.youtype_menu02);
        youtype_menu03 = (LinearLayout) findViewById(R.id.youtype_menu03);
        youtype_menu04 = (LinearLayout) findViewById(R.id.youtype_menu04);
        youtype_menu05 = (LinearLayout) findViewById(R.id.youtype_menu05);
        youtype_menu06 = (LinearLayout) findViewById(R.id.youtype_menu06);
        youtype_menu07 = (LinearLayout) findViewById(R.id.youtype_menu07);
        youtype_menu08 = (LinearLayout) findViewById(R.id.youtype_menu08);


        youtype_menu01.setOnTouchListener(onBtnTouchListener);
        youtype_menu01.setOnClickListener(onBtnClickListener);

        youtype_menu02.setOnTouchListener(onBtnTouchListener);
        youtype_menu02.setOnClickListener(onBtnClickListener);

        youtype_menu03.setOnTouchListener(onBtnTouchListener);
        youtype_menu03.setOnClickListener(onBtnClickListener);

        youtype_menu04.setOnTouchListener(onBtnTouchListener);
        youtype_menu04.setOnClickListener(onBtnClickListener);

        youtype_menu05.setOnTouchListener(onBtnTouchListener);
        youtype_menu05.setOnClickListener(onBtnClickListener);

        youtype_menu06.setOnTouchListener(onBtnTouchListener);
        youtype_menu06.setOnClickListener(onBtnClickListener);

        youtype_menu07.setOnTouchListener(onBtnTouchListener);
        youtype_menu07.setOnClickListener(onBtnClickListener);

        youtype_menu08.setOnTouchListener(onBtnTouchListener);
        youtype_menu08.setOnClickListener(onBtnClickListener);



        switch (youType)
        {
            case "A":
                youtype_activity_typeImg.setImageResource(R.drawable.mytype_a);
                youtype_li_bg.setBackgroundColor(Color.rgb(7, 154, 232));
                break;
            case "D":
                youtype_activity_typeImg.setImageResource(R.drawable.mytype_d);
                youtype_li_bg.setBackgroundColor(Color.rgb(255, 109, 42));
                break;
            case "E":
                youtype_activity_typeImg.setImageResource(R.drawable.mytype_e);
                youtype_li_bg.setBackgroundColor(Color.rgb(147, 63, 242));
                break;
            case "I":
                youtype_activity_typeImg.setImageResource(R.drawable.mytype_i);
                youtype_li_bg.setBackgroundColor(Color.rgb(41, 207, 4));
                break;
        }

    }


    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(v.getId()){
                case R.id.youtype_menu01:
                    if(event.getAction() == 2) {
                        youtype_menu01.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu01.setBackgroundColor(Color.rgb(255,255,255));
                    }

                    break;

                case R.id.youtype_menu02:
                    if(event.getAction() == 2) {
                        youtype_menu02.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu02.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.youtype_menu03:
                    if(event.getAction() == 2) {
                        youtype_menu03.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu03.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.youtype_menu04:
                    if(event.getAction() == 2) {
                        youtype_menu04.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu04.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.youtype_menu05:
                    if(event.getAction() == 2) {
                        youtype_menu05.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu05.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.youtype_menu06:
                    if(event.getAction() == 2) {
                        youtype_menu06.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu06.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.youtype_menu07:
                    if(event.getAction() == 2) {
                        youtype_menu07.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu07.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;


                case R.id.youtype_menu08:
                    if(event.getAction() == 2) {
                        youtype_menu08.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        youtype_menu08.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;


            }
            return false;
        }
    };

    private View.OnClickListener onBtnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent;

            Log.d("people_gram", "클릭="+String.valueOf(v.getId()));

            switch (v.getId()){
                case R.id.youtype_menu01:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "1");
                    intent.putExtra("title","일반적특징");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;
                case R.id.youtype_menu02:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "2");
                    intent.putExtra("title","긍정적인 면");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;
                case R.id.youtype_menu03:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "3");
                    intent.putExtra("title","부정적인 면");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.youtype_menu04:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "4");
                    intent.putExtra("title","직업분포");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.youtype_menu05:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "5");
                    intent.putExtra("title","싫어하는 것");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.youtype_menu06:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "6");
                    intent.putExtra("title","스트레스를 받으면");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.youtype_menu07:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "7");
                    intent.putExtra("title","의사결정 스타일");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.youtype_menu08:
                    intent = new Intent(YouType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",youType);
                    intent.putExtra("contents_num", "8");
                    intent.putExtra("title","추구하는 점");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

            }
        }
    };

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