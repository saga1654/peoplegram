package kr.co.people_grame.app;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyQuestion_Activity extends AppCompatActivity {


    private LinearLayout ll_my_question_activity_layout;
    private TextView tv_my_question_activity_title, tv_my_question_activity_page;
    private SeekBar sb_my_question_activity_data;

    private static int questionNum = 1;
    private static int dataArray[] = new int[10];


    private static String uid = "";
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question_);


        ll_my_question_activity_layout = (LinearLayout) findViewById(R.id.ll_my_question_activity_layout);
        tv_my_question_activity_page = (TextView) findViewById(R.id.tv_my_question_activity_page);
        tv_my_question_activity_title = (TextView) findViewById(R.id.tv_my_question_activity_title);
        sb_my_question_activity_data = (SeekBar) findViewById(R.id.sb_my_question_activity_data);

        //title_str = getString(R.string.step+questionNum);

        uid = SharedPreferenceUtil.getSharedPreference(this, "uid");
    }

    public void btn_prev(View v) {
        if(questionNum == 1) {
            Toast.makeText(this, "첫페이지", Toast.LENGTH_SHORT).show();
        } else {
            getQuestionNum_minus();
            tv_my_question_activity_page.setText(String.valueOf(questionNum) + "/" + "10");
        }
        getQuestionTitle();
    }

    public void btn_next(View v)
    {
        if(questionNum == 10) {
            //Toast.makeText(this, "마지막페이지", Toast.LENGTH_SHORT).show();


            RequestParams params = new RequestParams();
            params.put("uid", uid);
            int dataNum = 0;
            for(int i = 0; i<dataArray.length; i++) {
                dataNum = i + 1;
                params.put("data"+dataNum, String.valueOf(dataArray[i]));
            }

            HttpClient.post("/user/my_question", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    Log.d("people_gram", "시작");
                }

                public void onFinish() {
                    Log.d("people_gram", "완료");
                }

                @Override
                public void onSuccess(String response)
                {
                    intent = new Intent(MyQuestion_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d("people_gram", response);
                }

            });

        } else {
            questionNum_plus();
            tv_my_question_activity_page.setText(String.valueOf(questionNum) + "/" + "10");
        }
        getQuestionTitle();
        dataSet();
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
    private void dataSet()
    {
        /* 배열로 셋팅 */
        dataArray[questionNum-1] = sb_my_question_activity_data.getProgress();
        /*
        for(int i = 0; i<dataArray.length; i++) {
            Log.d("people_gram", i+":::"+String.valueOf(dataArray[i]));
        }
        */


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


    private void move() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_my_question_translate_close);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                ll_my_question_activity_layout.setVisibility(View.GONE);
            }
        });

        ll_my_question_activity_layout.startAnimation(animation);
    }
}
