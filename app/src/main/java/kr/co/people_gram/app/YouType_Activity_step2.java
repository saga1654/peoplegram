package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class YouType_Activity_step2 extends FragmentActivity {


    /* 변수 private */
    private int questionNum = 1;
    private static String uid = "";
    private static String people_email = "";
    private static String people_uid = "";
    private static String people_username = "";
    private String gubun1 = "";
    private String gubun2 = "";
    /*
    private Boolean nextCheck = false;
    private Boolean seekCheck = false;
    */
    private static int dataArray[] = new int[10];

    private String userdataArray[];

    /* comportment private */
    private TextView tv_my_question_activity_title;
    //private SeekBar sb_my_question_activity_data;
    private Context mcontext;
    private ProgressDialog dialog;
    private Intent intent;

    /* ViewPager private */
    private PagerContainer mContainer;
    private ViewPager pager;

    private String userData = "";
    private Context ActivityContext;

    private LinearLayout myQuestion_left, myQuestion_right;
    private Boolean left_onoff = false;
    private Boolean right_onoff = false;

    private TextView myQuestion_left_textview, myQuestion_right_textview;
    private PeopleData pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_type__activity_step2);

        pd = new PeopleData();

        ActivityContext = this;
        questionNum = 1;

        Intent intent = getIntent();
        people_uid = intent.getExtras().getString("people_uid").toString();
        uid = SharedPreferenceUtil.getSharedPreference(this, "uid");
        people_username = intent.getExtras().getString("people_username").toString();
        people_email = intent.getExtras().getString("people_email").toString();
        gubun1 = intent.getExtras().getString("gubun1");
        gubun2 = intent.getExtras().getString("gubun2");



        //Log.d("people_gram", "구분=" + gubun1 + ":::" + gubun2);

        //Log.d("people_gram", people_username);


        //Activity mcontext에 담기
        mcontext = this;

        /* SeekBar title 가져오기 */
        /*mTxvSeekBarValue = (TextView) findViewById(R.id.txvSeekBarValue);*/

        /* SeekBar value 가져오기 */
       // sb_my_question_activity_data = (SeekBar) findViewById(R.id.sb_my_question_activity_data);
        tv_my_question_activity_title = (TextView) findViewById(R.id.tv_my_question_activity_title);

        myQuestion_left = (LinearLayout) findViewById(R.id.myQuestion_left);
        myQuestion_right = (LinearLayout) findViewById(R.id.myQuestion_right);

        myQuestion_left_textview = (TextView) findViewById(R.id.myQuestion_left_textview);
        myQuestion_right_textview = (TextView) findViewById(R.id.myQuestion_right_textview);

        getQuestionTitle();
        /* SeekBar Tooltip 좌표 설정 */
        /*
        int dp = 250;
        int thumbPos = Utilities.getScreenWidth(mcontext) / 2;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = thumbPos - Utilities.getPxToDp(mcontext, dp);
        mTxvSeekBarValue.setLayoutParams(params);

        sb_my_question_activity_data.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int width = seekBar.getWidth()
                        - seekBar.getPaddingLeft()
                        - seekBar.getPaddingRight();
                int thumbPos = seekBar.getPaddingLeft()
                        + width
                        * seekBar.getProgress()
                        / seekBar.getMax();

                int dp = 75;

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = thumbPos - Utilities.getPxToDp(mcontext, dp);
                mTxvSeekBarValue.setLayoutParams(params);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                //Log.d("prople_gram", "터치시작");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() <= 30) {
                    seekBar.setProgress(0);
                } else if(seekBar.getProgress() >= 31 && seekBar.getProgress() <= 49) {
                    seekBar.setProgress(30);
                } else if(seekBar.getProgress() >= 51 && seekBar.getProgress() <= 75) {
                    seekBar.setProgress(70);
                } else {
                    seekBar.setProgress(100);
                }
                //Log.d("prople_gram", "터치종료="+seekBar.getProgress());
            }
        });
        */
        /* ####### seekBar 드래그 좌표 #####  */


        /* navigation 설정 */
        mContainer = (PagerContainer) findViewById(R.id.pager_container);
        pager = mContainer.getViewPager();
        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setPageMargin(15);

        pager.setClipChildren(false);
        pager.post(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(1);
            }
        });
        final GestureDetector gd = new GestureDetector(new GestureDetector.SimpleOnGestureListener());



        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        gd.onTouchEvent(event);
                        break;
                }
                return true;
            }
        });
        /* ##### navigation 설정 끝 #### */
    }

    public void myQuestion_default()
    {
        left_onoff = false;
        right_onoff = false;
        myQuestion_left.setBackgroundColor(Color.rgb(241,241,241));
        myQuestion_right.setBackgroundColor(Color.rgb(241,241,241));
    }

    public void myQuestion_left_click(View v) {
        right_onoff = false;

        if(left_onoff == false) {
            left_onoff = true;
            myQuestion_left.setBackgroundColor(Color.rgb(250,229,4));
            myQuestion_right.setBackgroundColor(Color.rgb(241,241,241));
        } else {
            left_onoff = false;
            myQuestion_left.setBackgroundColor(Color.rgb(241,241,241));
            myQuestion_right.setBackgroundColor(Color.rgb(241,241,241));
        }

        if(dataSet() == false) {
            Toast.makeText(this, "선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(questionNum == 10) {
            Log.d("people_gram", "마지막페이지");
            //Toast.makeText(this, "마지막페이지", Toast.LENGTH_SHORT).show();


            RequestParams params = new RequestParams();
            params.put("uid", uid);
            params.put("people_uid", people_uid);
            params.put("username", SharedPreferenceUtil.getSharedPreference(YouType_Activity_step2.this,"username"));
            params.put("people_username", people_username);
            params.put("gubun1", gubun1);
            params.put("gubun2", gubun2);
            int dataNum = 0;
            for(int i = 0; i<dataArray.length; i++) {
                dataNum = i + 1;
                params.put("data"+dataNum, String.valueOf(dataArray[i]));
            }

            HttpClient.post("/user/you_question", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    dialog = ProgressDialog.show(YouType_Activity_step2.this, "", "데이터 수신중");
                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response)
                {
                    try {
                        JSONObject jobj = new JSONObject(response);

                        String data1 = jobj.getString("data1");
                        String data2 = jobj.getString("data2");
                        String data3 = jobj.getString("data3");
                        String data4 = jobj.getString("data4");
                        String data5 = jobj.getString("data5");
                        String data6 = jobj.getString("data6");
                        String data7 = jobj.getString("data7");
                        String data8 = jobj.getString("data8");
                        String data9 = jobj.getString("data9");
                        String data10 = jobj.getString("data10");

                        String speed = jobj.getString("speed");
                        String control = jobj.getString("control");

                        String people_type = jobj.getString("people_type");

                        intent = new Intent(YouType_Activity_step2.this, YouType_Complate_Activity.class);
                        intent.putExtra("people_uid", people_uid);
                        intent.putExtra("people_email", people_email);
                        intent.putExtra("people_username", people_username);
                        intent.putExtra("data1", data1);
                        intent.putExtra("data2", data2);
                        intent.putExtra("data3", data3);
                        intent.putExtra("data4", data4);
                        intent.putExtra("data5", data5);
                        intent.putExtra("data6", data6);
                        intent.putExtra("data7", data7);
                        intent.putExtra("data8", data8);
                        intent.putExtra("data9", data9);
                        intent.putExtra("data10", data10);
                        intent.putExtra("speed", speed);
                        intent.putExtra("control", control);
                        intent.putExtra("youtype", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("gubun2", gubun2);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    /*
                    dialog.dismiss();

                    intent = new Intent(YouType_Activity_step2.this, YouType_Activity.class);
                    intent.putExtra("people_uid", people_uid);
                    intent.putExtra("youtype", response);
                    //SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", response);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    finish();
                    */

                }

            });



        } else {
            questionNum_plus();
            pager.setCurrentItem(questionNum);
           /* nextCheck = true;
            sb_my_question_activity_data.setMax(100);
            sb_my_question_activity_data.setProgress(50);

            seekCheck = false;
            //sb_my_question_activity_data.setMax(100);
            //sb_my_question_activity_data.setProgress(50);
            */
        }
        getQuestionTitle();
        myQuestion_default();


        /*

        */
    }

    public void myQuestion_right_click(View v) {

        left_onoff = false;
        if(left_onoff == false) {
            right_onoff = true;
            myQuestion_left.setBackgroundColor(Color.rgb(241,241,241));
            myQuestion_right.setBackgroundColor(Color.rgb(250,229,4));
        } else {
            right_onoff = false;
            myQuestion_left.setBackgroundColor(Color.rgb(241, 241, 241));
            myQuestion_right.setBackgroundColor(Color.rgb(241,241,241));
        }

        if(dataSet() == false) {
            Toast.makeText(this, "선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(questionNum == 10) {
            Log.d("people_gram", "마지막페이지");
            //Toast.makeText(this, "마지막페이지", Toast.LENGTH_SHORT).show();


            RequestParams params = new RequestParams();
            params.put("uid", uid);
            params.put("username", SharedPreferenceUtil.getSharedPreference(YouType_Activity_step2.this,"username"));
            params.put("people_username", people_username);
            params.put("people_uid", people_uid);
            params.put("gubun1", gubun1);
            params.put("gubun2", gubun2);
            int dataNum = 0;
            for(int i = 0; i<dataArray.length; i++) {
                dataNum = i + 1;
                params.put("data"+dataNum, String.valueOf(dataArray[i]));
            }

            HttpClient.post("/user/you_question", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    dialog = ProgressDialog.show(YouType_Activity_step2.this, "", "데이터 수신중");
                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response)
                {
                    try {
                        JSONObject jobj = new JSONObject(response);

                        String data1 = jobj.getString("data1");
                        String data2 = jobj.getString("data2");
                        String data3 = jobj.getString("data3");
                        String data4 = jobj.getString("data4");
                        String data5 = jobj.getString("data5");
                        String data6 = jobj.getString("data6");
                        String data7 = jobj.getString("data7");
                        String data8 = jobj.getString("data8");
                        String data9 = jobj.getString("data9");
                        String data10 = jobj.getString("data10");

                        String speed = jobj.getString("speed");
                        String control = jobj.getString("control");

                        String people_type = jobj.getString("people_type");






                        intent = new Intent(YouType_Activity_step2.this, YouType_Complate_Activity.class);
                        intent.putExtra("people_uid", people_uid);
                        intent.putExtra("people_email", people_email);
                        intent.putExtra("people_username", people_username);
                        intent.putExtra("data1", data1);
                        intent.putExtra("data2", data2);
                        intent.putExtra("data3", data3);
                        intent.putExtra("data4", data4);
                        intent.putExtra("data5", data5);
                        intent.putExtra("data6", data6);
                        intent.putExtra("data7", data7);
                        intent.putExtra("data8", data8);
                        intent.putExtra("data9", data9);
                        intent.putExtra("data10", data10);
                        intent.putExtra("speed", speed);
                        intent.putExtra("control", control);
                        intent.putExtra("youtype", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("gubun2", gubun2);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    /*
                    dialog.dismiss();

                    intent = new Intent(YouType_Activity_step2.this, YouType_Activity.class);
                    intent.putExtra("people_uid", people_uid);
                    intent.putExtra("youtype", response);
                    //SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", response);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    finish();
                    */



                }

            });



        } else {
            questionNum_plus();
            pager.setCurrentItem(questionNum);
           /* nextCheck = true;
            sb_my_question_activity_data.setMax(100);
            sb_my_question_activity_data.setProgress(50);

            seekCheck = false;
            //sb_my_question_activity_data.setMax(100);
            //sb_my_question_activity_data.setProgress(50);
            */
        }
        getQuestionTitle();
        myQuestion_default();


        /*

        */
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /* 이미지 동적 가져오기 */
            ImageView image = new ImageView(YouType_Activity_step2.this);
            image.setId(position);
            int resID = mcontext.getResources().getIdentifier("myquestion_title"+position+"_off", "drawable", mcontext.getPackageName());
            image.setImageResource(resID);
            container.addView(image);
            return image;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
        @Override
        public int getCount() {
            return 11;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }




    public void btn_prev(View v) {

        if(questionNum == 1) {
            Toast.makeText(this, "첫페이지 입니다", Toast.LENGTH_SHORT).show();
        } else {
            getQuestionNum_minus();
            pager.setCurrentItem(questionNum);
            //tv_my_question_activity_page.setText(String.valueOf(questionNum) + "/" + "10");
        }
        getQuestionTitle();
        myQuestion_default();
    }

    public void btn_next(View v)
    {

        if(dataSet() == false) {
            Toast.makeText(this, "선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(questionNum == 10) {
            Log.d("people_gram", "마지막페이지");
            //Toast.makeText(this, "마지막페이지", Toast.LENGTH_SHORT).show();


            RequestParams params = new RequestParams();
            params.put("uid", uid);
            params.put("people_uid", people_uid);
            params.put("gubun1", gubun1);
            params.put("gubun2", gubun2);
            int dataNum = 0;
            for(int i = 0; i<dataArray.length; i++) {
                dataNum = i + 1;
                params.put("data"+dataNum, String.valueOf(dataArray[i]));
            }

            HttpClient.post("/user/you_question", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    dialog = ProgressDialog.show(YouType_Activity_step2.this, "", "데이터 수신중");
                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response)
                {
                    dialog.dismiss();

                    intent = new Intent(YouType_Activity_step2.this, YouType_Complate_Activity.class);
                    intent.putExtra("people_uid", people_uid);
                    intent.putExtra("people_email", people_email);
                    intent.putExtra("youtype", response);
                    //SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", response);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    finish();

                }

            });



        } else {
            questionNum_plus();
            pager.setCurrentItem(questionNum);
           /* nextCheck = true;
            sb_my_question_activity_data.setMax(100);
            sb_my_question_activity_data.setProgress(50);

            seekCheck = false;
            //sb_my_question_activity_data.setMax(100);
            //sb_my_question_activity_data.setProgress(50);
            */
        }
        getQuestionTitle();
        myQuestion_default();

    }

    /* 질문지 순서 + */
    private void questionNum_plus() {
        questionNum = questionNum + 1;
        getQuestionTitle();
    }


    /* 질문지 순서 - */
    private void getQuestionNum_minus()
    {
        questionNum = questionNum - 1;
        getQuestionTitle();
    }


    /* 데이터 셋팅 */
    private Boolean dataSet()
    {
        /*
        if(sb_my_question_activity_data.getProgress() == 50) {
            return false;
        } else {
            dataArray[questionNum-1] = sb_my_question_activity_data.getProgress();
            for(int i = 0; i<dataArray.length; i++) {
                //Log.d("people_gram", i+":::"+String.valueOf(dataArray[i]));
            }
            return true;
        }
        */
        if(left_onoff == false && right_onoff == false) {
            return false;
        } else {
            if(left_onoff == true) {
                dataArray[questionNum-1] = -1;
            }

            if(right_onoff == true) {
                dataArray[questionNum-1] = 1;
            }
            return true;
        }
    }

    private void getQuestionTitle() {

        switch (questionNum) {
            case 1:
                tv_my_question_activity_title.setText(getString(R.string.step1));
                myQuestion_left_textview.setText(getString(R.string.step1_left));
                myQuestion_right_textview.setText(getString(R.string.step1_right));

                break;
            case 2:
                tv_my_question_activity_title.setText(getString(R.string.step2));

                myQuestion_left_textview.setText(getString(R.string.step2_left));
                myQuestion_right_textview.setText(getString(R.string.step2_right));
                break;
            case 3:
                tv_my_question_activity_title.setText(getString(R.string.step3));

                myQuestion_left_textview.setText(getString(R.string.step3_left));
                myQuestion_right_textview.setText(getString(R.string.step3_right));
                break;
            case 4:
                tv_my_question_activity_title.setText(getString(R.string.step4));

                myQuestion_left_textview.setText(getString(R.string.step4_left));
                myQuestion_right_textview.setText(getString(R.string.step4_right));
                break;
            case 5:
                tv_my_question_activity_title.setText(getString(R.string.step5));

                myQuestion_left_textview.setText(getString(R.string.step5_left));
                myQuestion_right_textview.setText(getString(R.string.step5_right));
                break;
            case 6:
                tv_my_question_activity_title.setText(getString(R.string.step6));

                myQuestion_left_textview.setText(getString(R.string.step6_left));
                myQuestion_right_textview.setText(getString(R.string.step6_right));
                break;
            case 7:
                tv_my_question_activity_title.setText(getString(R.string.step7));

                myQuestion_left_textview.setText(getString(R.string.step7_left));
                myQuestion_right_textview.setText(getString(R.string.step7_right));
                break;
            case 8:
                tv_my_question_activity_title.setText(getString(R.string.step8));

                myQuestion_left_textview.setText(getString(R.string.step8_left));
                myQuestion_right_textview.setText(getString(R.string.step8_right));
                break;
            case 9:
                tv_my_question_activity_title.setText(getString(R.string.step9));

                myQuestion_left_textview.setText(getString(R.string.step9_left));
                myQuestion_right_textview.setText(getString(R.string.step9_right));
                break;
            case 10:
                tv_my_question_activity_title.setText(getString(R.string.step10));

                myQuestion_left_textview.setText(getString(R.string.step10_left));
                myQuestion_right_textview.setText(getString(R.string.step10_right));
                break;
        }

    }



}
