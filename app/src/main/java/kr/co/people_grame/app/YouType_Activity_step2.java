package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Vector;

public class YouType_Activity_step2 extends FragmentActivity {


    /* 변수 private */
    private int questionNum = 1;
    private static String uid = "";
    private static String people_uid = "";
    private Boolean nextCheck = false;
    private Boolean seekCheck = false;
    private static int dataArray[] = new int[10];

    private String userdataArray[];

    /* comportment private */
    private TextView mTxvSeekBarValue, tv_my_question_activity_title;
    private SeekBar sb_my_question_activity_data;
    private Context mcontext;
    private ProgressDialog dialog;
    private Intent intent;

    /* ViewPager private */
    private PagerContainer mContainer;
    private ViewPager pager;

    private String userData = "";
    private Context ActivityContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_type__activity_step2);

        ActivityContext = this;
        questionNum = 1;

        Intent intent = getIntent();
        people_uid = intent.getExtras().getString("people_uid").toString();
        uid = SharedPreferenceUtil.getSharedPreference(this, "uid");

        //Activity mcontext에 담기
        mcontext = this;

        /* SeekBar title 가져오기 */
        mTxvSeekBarValue = (TextView) findViewById(R.id.txvSeekBarValue);

        /* SeekBar value 가져오기 */
        sb_my_question_activity_data = (SeekBar) findViewById(R.id.sb_my_question_activity_data);
        tv_my_question_activity_title = (TextView) findViewById(R.id.tv_my_question_activity_title);


        /* SeekBar Tooltip 좌표 설정 */
        int dp = 250;
        int thumbPos = Utilities.getScreenWidth(mcontext) / 2;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = thumbPos - Utilities.getPxToDp(mcontext, dp);
        mTxvSeekBarValue.setLayoutParams(params);
        /* ########################## */



        /* seekBar 드래그 좌표 재 설정 */
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

                    intent = new Intent(YouType_Activity_step2.this, YouType_Activity.class);
                    intent.putExtra("people_uid", people_uid);
                    intent.putExtra("youtype", response);
                    //SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", response);
                    startActivity(intent);
                    finish();

                }

            });



        } else {
            questionNum_plus();
            pager.setCurrentItem(questionNum);
            nextCheck = true;
            sb_my_question_activity_data.setMax(100);
            sb_my_question_activity_data.setProgress(50);

            seekCheck = false;
            //sb_my_question_activity_data.setMax(100);
            //sb_my_question_activity_data.setProgress(50);
        }
        getQuestionTitle();


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
        if(sb_my_question_activity_data.getProgress() == 50) {
            return false;
        } else {
            dataArray[questionNum-1] = sb_my_question_activity_data.getProgress();
            for(int i = 0; i<dataArray.length; i++) {
                //Log.d("people_gram", i+":::"+String.valueOf(dataArray[i]));
            }
            return true;
        }
    }

    private void getQuestionTitle() {

        switch (questionNum) {
            case 1:
                tv_my_question_activity_title.setText(getString(R.string.step1));
                break;
            case 2:
                tv_my_question_activity_title.setText(getString(R.string.step2));
                break;
            case 3:
                tv_my_question_activity_title.setText(getString(R.string.step3));
                break;
            case 4:
                tv_my_question_activity_title.setText(getString(R.string.step4));
                break;
            case 5:
                tv_my_question_activity_title.setText(getString(R.string.step5));
                break;
            case 6:
                tv_my_question_activity_title.setText(getString(R.string.step6));
                break;
            case 7:
                tv_my_question_activity_title.setText(getString(R.string.step7));
                break;
            case 8:
                tv_my_question_activity_title.setText(getString(R.string.step8));
                break;
            case 9:
                tv_my_question_activity_title.setText(getString(R.string.step9));
                break;
            case 10:
                tv_my_question_activity_title.setText(getString(R.string.step10));
                break;
        }

    }

}
