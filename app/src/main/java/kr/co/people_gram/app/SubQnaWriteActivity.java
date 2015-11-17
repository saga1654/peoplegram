package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SubQnaWriteActivity extends AppCompatActivity {

    private EditText qna_subjectQ;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_qna_write);

        qna_subjectQ = (EditText) findViewById(R.id.qna_subjectQ);
    }

    public void qnaSave(View v) {
        if(qna_subjectQ.getText().toString().length() == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(SubQnaWriteActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("내용을 입력해주세요.");
            alert.show();
        } else {

            RequestParams params = new RequestParams();

            params.put("uid", SharedPreferenceUtil.getSharedPreference(SubQnaWriteActivity.this, "uid"));
            params.put("subjectQ", qna_subjectQ.getText().toString());
            HttpClient.post("/setting/qnaWriteProcess", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    dialog = ProgressDialog.show(SubQnaWriteActivity.this, "", "데이터 수신중");
                }

                public void onFinish() {
                    dialog.dismiss();
                }

                public void onSuccess(String response) {
                    finish();
                }
            });
        }
        Log.d("people_gram", "내용입력="+qna_subjectQ.getText());
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
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
