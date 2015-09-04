package kr.co.people_grame.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyQuestion_Activity extends AppCompatActivity {
    private TextView tv_my_question_activity_title, tv_my_question_activity_page;
    private static int questionNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question_);

        tv_my_question_activity_title = (TextView) findViewById(R.id.tv_my_question_activity_title);
    }

    public void btn_prev(View v) {
        if(questionNum == 1) {
            Toast.makeText(this, "첫페이지", Toast.LENGTH_SHORT).show();
        } else {
            getQuestionNum_minus();
            Log.d("people_gram", String.valueOf(questionNum));
            //tv_my_question_activity_page.setText(String.valueOf(questionNum) + "/" + "10");
        }
    }

    public void btn_next(View v)
    {
        if(questionNum == 10) {
            Toast.makeText(this, "마지막페이지", Toast.LENGTH_SHORT).show();
        } else {
            questionNum_plus();
            Log.d("people_gram", String.valueOf(questionNum));
            //tv_my_question_activity_page.setText(String.valueOf(questionNum) + "/" + "10");
        }
    }

    private void questionNum_plus() {
        questionNum = questionNum + 1;
    }

    private void getQuestionNum_minus()
    {
        questionNum = questionNum - 1;
    }
}
