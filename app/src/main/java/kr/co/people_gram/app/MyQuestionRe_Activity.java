package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class MyQuestionRe_Activity extends FragmentActivity {


    /* 변수 private */
    private int questionNum = 1;
    private static String uid = "";

    //private Boolean nextCheck = false;
    //private Boolean seekCheck = false;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question_re);

        intent = getIntent();

        ActivityContext = this;

        //질문순서 셋팅
        questionNum = 1;

        //uid = "201509161111AA";
        uid = SharedPreferenceUtil.getSharedPreference(this, "uid");
        Log.d("people_gram", uid);

        //Activity mcontext에 담기
        mcontext = this;

        /* SeekBar title 가져오기 */
        //mTxvSeekBarValue = (TextView) findViewById(R.id.txvSeekBarValue);

        /* SeekBar value 가져오기 */
        //sb_my_question_activity_data = (SeekBar) findViewById(R.id.sb_my_question_activity_data);
        tv_my_question_activity_title = (TextView) findViewById(R.id.tv_my_question_activity_title);


        myQuestion_left = (LinearLayout) findViewById(R.id.myQuestion_left);
        myQuestion_right = (LinearLayout) findViewById(R.id.myQuestion_right);

        myQuestion_left_textview = (TextView) findViewById(R.id.myQuestion_left_textview);
        myQuestion_right_textview = (TextView) findViewById(R.id.myQuestion_right_textview);

        getQuestionTitle();


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
        /* ##### navigation 설정 끝 #### */
    }

    public void myQuestion_default()
    {
        left_onoff = false;
        right_onoff = false;
        myQuestion_left.setBackgroundColor(Color.rgb(241, 241, 241));
        myQuestion_right.setBackgroundColor(Color.rgb(241, 241, 241));
    }

    public void myQuestion_left_click(View v) {
        right_onoff = false;
        if(left_onoff == false) {
            left_onoff = true;
            myQuestion_left.setBackgroundColor(Color.rgb(250,229,4));
            myQuestion_right.setBackgroundColor(Color.rgb(241,241,241));
            Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(40);
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


            dialog = ProgressDialog.show(MyQuestionRe_Activity.this, "", "데이터 수신중");
            readContacts();

            RequestParams params = new RequestParams();
            params.put("uid", uid);
            int dataNum = 0;
            for(int i = 0; i<dataArray.length; i++) {
                dataNum = i + 1;
                params.put("data"+dataNum, String.valueOf(dataArray[i]));
            }

            Log.d("people_gram", userData);
            params.put("userData", userdataArray);

            HttpClient.post("/user/my_question", params, new AsyncHttpResponseHandler() {
                public void onStart() {

                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response)
                {
                    dialog.dismiss();
                    /*
                    intent = new Intent(MyQuestion_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    */
                    //Log.d("people_gram", response);

                    SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", response);
                    finish();

                }

            });



        } else {
            questionNum_plus();
            pager.setCurrentItem(questionNum);
        }
        getQuestionTitle();
        myQuestion_default();
    }

    public void myQuestion_right_click(View v) {
        left_onoff = false;
        if(left_onoff == false) {
            right_onoff = true;
            myQuestion_left.setBackgroundColor(Color.rgb(241,241,241));
            myQuestion_right.setBackgroundColor(Color.rgb(250,229,4));
        } else {
            right_onoff = false;
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


            dialog = ProgressDialog.show(MyQuestionRe_Activity.this, "", "데이터 수신중");
            readContacts();

            RequestParams params = new RequestParams();
            params.put("uid", uid);
            int dataNum = 0;
            for(int i = 0; i<dataArray.length; i++) {
                dataNum = i + 1;
                params.put("data"+dataNum, String.valueOf(dataArray[i]));
            }

            Log.d("people_gram", userData);
            params.put("userData", userdataArray);

            HttpClient.post("/user/my_question", params, new AsyncHttpResponseHandler() {
                public void onStart() {

                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response)
                {
                    dialog.dismiss();
                    /*
                    intent = new Intent(MyQuestion_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    */
                    //Log.d("people_gram", response);

                    SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", response);
                    overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
                    finish();

                }

            });



        } else {
            questionNum_plus();
            pager.setCurrentItem(questionNum);
        }
        getQuestionTitle();
        myQuestion_default();
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /* 이미지 동적 가져오기 */
            ImageView image = new ImageView(MyQuestionRe_Activity.this);
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

    private void readContacts() {
        /*
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String phone = null;
        Log.d("people_gram", "count="+String.valueOf(cur.getCount()));
        userdataArray = new String[cur.getCount()];
        if (cur.getCount() > 0) {
            int i = 0;
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while(pCur.moveToNext()) {
                        name = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        userdataArray[i] = name+":::"+phone;
                    }
                    pCur.close();
                }
                i++;
            }
            cur.close();
        }
        */

        //Log.d("people_gram", userData);
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
            int dataNum = 0;
            for(int i = 0; i<dataArray.length; i++) {
                dataNum = i + 1;
                params.put("data"+dataNum, String.valueOf(dataArray[i]));
            }

            Log.d("people_gram", userData);
            params.put("userData", userdataArray);

            HttpClient.post("/user/my_question", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    dialog = ProgressDialog.show(MyQuestionRe_Activity.this, "", "데이터 수신중");
                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response)
                {
                    dialog.dismiss();
                    /*
                    intent = new Intent(MyQuestion_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    */
                    //Log.d("people_gram", response);

                    SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", response);
                    finish();

                }

            });



        } else {
            questionNum_plus();
            pager.setCurrentItem(questionNum);
        }
        getQuestionTitle();
        myQuestion_default();


    }

    /* 질문지 순서 + */
    private void questionNum_plus() {
        questionNum = questionNum + 1;
    }


    /* 질문지 순서 - */
    private void getQuestionNum_minus()
    {
        questionNum = questionNum - 1;
    }


    /* 데이터 셋팅 */
    private Boolean dataSet()
    {

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
        setResult(RESULT_OK, intent);
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);

    }

}
