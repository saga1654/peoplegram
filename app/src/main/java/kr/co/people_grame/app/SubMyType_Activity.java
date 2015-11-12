package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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


public class SubMyType_Activity extends AppCompatActivity {

    private ProgressDialog dialog;
    private String myType;

    private ImageView mytype_activity_typeImg, myresult_close;
    private LinearLayout mytype_li_bg, mytype_menu01, mytype_menu02, mytype_menu03, mytype_menu04, mytype_menu05, mytype_menu06, mytype_menu07, mytype_menu08;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);

        setContentView(R.layout.activity_sub_mytype);


        Intent intent = getIntent();

        if(intent != null) {
            myType = intent.getStringExtra("mytype");
        }

        //myType = "I";

        //myType = SharedPreferenceUtil.getSharedPreference(this, "mytype");
        //myType = "I";
        mytype_activity_typeImg = (ImageView) findViewById(R.id.mytype_activity_typeImg);
        //myresult_close = (ImageView) findViewById(R.id.myresult_close);
        mytype_li_bg = (LinearLayout) findViewById(R.id.mytype_li_bg);


        mytype_menu01 = (LinearLayout) findViewById(R.id.mytype_menu01);
        mytype_menu02 = (LinearLayout) findViewById(R.id.mytype_menu02);
        mytype_menu03 = (LinearLayout) findViewById(R.id.mytype_menu03);
        mytype_menu04 = (LinearLayout) findViewById(R.id.mytype_menu04);
        mytype_menu05 = (LinearLayout) findViewById(R.id.mytype_menu05);
        mytype_menu06 = (LinearLayout) findViewById(R.id.mytype_menu06);
        mytype_menu07 = (LinearLayout) findViewById(R.id.mytype_menu07);
        mytype_menu08 = (LinearLayout) findViewById(R.id.mytype_menu08);


        mytype_menu01.setOnTouchListener(onBtnTouchListener);
        mytype_menu01.setOnClickListener(onBtnClickListener);
        mytype_menu02.setOnTouchListener(onBtnTouchListener);
        mytype_menu02.setOnClickListener(onBtnClickListener);
        mytype_menu03.setOnTouchListener(onBtnTouchListener);
        mytype_menu03.setOnClickListener(onBtnClickListener);
        mytype_menu04.setOnTouchListener(onBtnTouchListener);
        mytype_menu04.setOnClickListener(onBtnClickListener);
        mytype_menu05.setOnTouchListener(onBtnTouchListener);
        mytype_menu05.setOnClickListener(onBtnClickListener);
        mytype_menu06.setOnTouchListener(onBtnTouchListener);
        mytype_menu06.setOnClickListener(onBtnClickListener);
        mytype_menu07.setOnTouchListener(onBtnTouchListener);
        mytype_menu07.setOnClickListener(onBtnClickListener);
        mytype_menu08.setOnTouchListener(onBtnTouchListener);
        mytype_menu08.setOnClickListener(onBtnClickListener);
        /*
        mytype_menu01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mytype_menu01.setBackgroundColor(Color.rgb(234,234,237));

                        break;
                    case MotionEvent.ACTION_UP:
                        mytype_menu01.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                }
                return true;
            }
        });
        */
        /*
        mytype_menu01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyType_Activity.this, MyType_Result_Activity.class);

                Log.d("people_gram", myType);

                intent.putExtra("mytype",myType);
                intent.putExtra("contents_num", "1");
                intent.putExtra("title","일반적특징");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });


        mytype_menu02.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mytype_menu02.setBackgroundColor(Color.rgb(234,234,237));
                        break;
                    case MotionEvent.ACTION_UP:
                        mytype_menu02.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                }
                return true;
            }
        });

        mytype_menu03.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mytype_menu03.setBackgroundColor(Color.rgb(234,234,237));
                        break;
                    case MotionEvent.ACTION_UP:
                        mytype_menu03.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                }
                return true;
            }
        });

        mytype_menu04.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mytype_menu04.setBackgroundColor(Color.rgb(234,234,237));
                        break;
                    case MotionEvent.ACTION_UP:
                        mytype_menu04.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                }
                return true;
            }
        });

        mytype_menu05.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mytype_menu05.setBackgroundColor(Color.rgb(234,234,237));
                        break;
                    case MotionEvent.ACTION_UP:
                        mytype_menu05.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                }
                return true;
            }
        });

        mytype_menu06.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mytype_menu06.setBackgroundColor(Color.rgb(234,234,237));
                        break;
                    case MotionEvent.ACTION_UP:
                        mytype_menu06.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                }
                return true;
            }
        });

        mytype_menu07.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mytype_menu07.setBackgroundColor(Color.rgb(234,234,237));
                        break;
                    case MotionEvent.ACTION_UP:
                        mytype_menu07.setBackgroundColor(Color.rgb(255,255,255));
                        break;
                }
                return true;
            }
        });
        */

        switch (myType)
        {
            case "A":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_a);
                mytype_li_bg.setBackgroundColor(Color.rgb(7, 154, 232));
                break;
            case "D":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_d);
                mytype_li_bg.setBackgroundColor(Color.rgb(255, 109, 42));
                break;
            case "E":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_e);
                mytype_li_bg.setBackgroundColor(Color.rgb(147, 63, 242));
                break;
            case "I":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_i);
                mytype_li_bg.setBackgroundColor(Color.rgb(41, 207, 4));
                break;
        }

    }

    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(v.getId()){
                case R.id.mytype_menu01:
                    if(event.getAction() == 2) {
                        mytype_menu01.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu01.setBackgroundColor(Color.rgb(255,255,255));
                    }

                    break;

                case R.id.mytype_menu02:
                    if(event.getAction() == 2) {
                        mytype_menu02.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu02.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.mytype_menu03:
                    if(event.getAction() == 2) {
                        mytype_menu03.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu03.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.mytype_menu04:
                    if(event.getAction() == 2) {
                        mytype_menu04.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu04.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.mytype_menu05:
                    if(event.getAction() == 2) {
                        mytype_menu05.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu05.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.mytype_menu06:
                    if(event.getAction() == 2) {
                        mytype_menu06.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu06.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

                case R.id.mytype_menu07:
                    if(event.getAction() == 2) {
                        mytype_menu07.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu07.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;


                case R.id.mytype_menu08:
                    if(event.getAction() == 2) {
                        mytype_menu08.setBackgroundColor(Color.rgb(234,234,237));
                    } else {
                        mytype_menu08.setBackgroundColor(Color.rgb(255,255,255));
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
            switch (v.getId()){
                case R.id.mytype_menu01:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "1");
                    intent.putExtra("title","일반적특징");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;
                case R.id.mytype_menu02:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "2");
                    intent.putExtra("title","긍정적인 면");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;
                case R.id.mytype_menu03:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "3");
                    intent.putExtra("title","부정적인 면");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.mytype_menu04:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "4");
                    intent.putExtra("title","직업분포");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.mytype_menu05:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "5");
                    intent.putExtra("title","싫어하는 것");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.mytype_menu06:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "6");
                    intent.putExtra("title","스트레스를 받으면");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.mytype_menu07:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "7");
                    intent.putExtra("title","의사결정 스타일");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

                case R.id.mytype_menu08:
                    intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
                    intent.putExtra("mytype",myType);
                    intent.putExtra("contents_num", "8");
                    intent.putExtra("title","추구하는 점");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    break;

            }
        }
    };


    public void btn_main(View v)
    {
        Intent intent = new Intent(SubMyType_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void btn_action01(View v)
    {
        Log.d("people_gram", "클릭");

    }

    public void btn_action02(View v)
    {

    }

    public void btn_action03(View v)
    {
        Intent intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
        intent.putExtra("mytype",myType);
        intent.putExtra("contents_num", "3");
        intent.putExtra("title","부정적인 면");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void btn_action04(View v)
    {
        Intent intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
        intent.putExtra("mytype",myType);
        intent.putExtra("contents_num", "4");
        intent.putExtra("title","직업분표");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void btn_action05(View v)
    {
        Intent intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
        intent.putExtra("mytype",myType);
        intent.putExtra("contents_num", "5");
        intent.putExtra("title","싫어하는 것");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void btn_action06(View v)
    {
        Intent intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
        intent.putExtra("mytype",myType);
        intent.putExtra("contents_num", "6");
        intent.putExtra("title","스트레스를 받으면");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void btn_action07(View v)
    {
        Intent intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
        intent.putExtra("mytype",myType);
        intent.putExtra("contents_num", "7");
        intent.putExtra("title","의사결정 스타일");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void btn_action08(View v)
    {
        Intent intent = new Intent(SubMyType_Activity.this, MyType_Result_Activity.class);
        intent.putExtra("mytype",myType);
        intent.putExtra("contents_num", "8");
        intent.putExtra("title","추구하는 점");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
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

    public void subtype_prevBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }
}
