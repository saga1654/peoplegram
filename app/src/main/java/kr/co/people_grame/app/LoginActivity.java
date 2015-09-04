package kr.co.people_grame.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.loopj.android.http.*;


public class LoginActivity extends AppCompatActivity {

    private Intent intent;
    private EditText et_userid, et_userpw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AsyncHttpClient client = HttpClient.getInstance();
        //PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        //client.setCookieStore(myCookieStore);
    }

    public void btn_login(View v) {
        EditText et_userid = (EditText) findViewById(R.id.et_activity_login_userid);
        EditText et_userpw = (EditText) findViewById(R.id.et_activity_login_userpw);

        if(et_userid.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("아이디를 입력해주세요.");
            alert.show();
            return;
        }

        if(et_userpw.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("패스워드를 입력해주세요.");
            alert.show();
            return;
        }


        RequestParams params = new RequestParams();

        params.put("userID", et_userid.getText().toString());
        params.put("userPW", et_userpw.getText().toString());

        Log.d("people_gram", "데이터 전송시작");


        HttpClient.post("/user/loginCheck", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                Log.d("people_gram", "시작");
            }

            public void onFinish() {
                Log.d("people_gram", "완료");
            }

            @Override
            public void onSuccess(String response)
            {
                Log.d("people_gram", response);
            }

        });


    }

}
