package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubMyDrop_Activity extends AppCompatActivity {

    private EditText drop_contents;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_my_drop_);

        drop_contents = (EditText) findViewById(R.id.drop_contents);

    }

    public void dropSave(View v) {
        Log.d("people_gram", "테스트");
        if(drop_contents.getText().toString().equals("")) {

            AlertDialog.Builder alert = new AlertDialog.Builder(SubMyDrop_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("탈퇴내용을 간략히 적어주세요.");
            alert.setNegativeButton("취소", null);
            alert.show();

        } else {

            AlertDialog.Builder alert = new AlertDialog.Builder(SubMyDrop_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dropSend();
                }
            });
            alert.setMessage("피플그램 회원탈퇴하시겠습니까?\n포인트 및 모든 정보는 삭제 됩니다.");
            alert.setNegativeButton("취소", null);
            alert.show();

        }
    }

    public void dropSend() {
        RequestParams params = new RequestParams();

        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubMyDrop_Activity.this, "uid"));
        params.put("drop_contents", drop_contents.getText().toString());

        HttpClient.post("/user/memberDrop", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SubMyDrop_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            public void onSuccess(String response) {
                Log.d("people_gram", response);
                if (response.equals("000")) {
                    SharedPreferenceUtil.putSharedPreference(SubMyDrop_Activity.this, "uid", "");
                    SharedPreferenceUtil.putSharedPreference(SubMyDrop_Activity.this, "username", "");
                    SharedPreferenceUtil.putSharedPreference(SubMyDrop_Activity.this, "point", "");
                    SharedPreferenceUtil.putSharedPreference(SubMyDrop_Activity.this, "mytype", "");
                    SharedPreferenceUtil.putSharedPreference(SubMyDrop_Activity.this, "my_speed", "");
                    SharedPreferenceUtil.putSharedPreference(SubMyDrop_Activity.this, "my_control", "");
                    SharedPreferenceUtil.putSharedPreference(SubMyDrop_Activity.this, "email", "");

                    Toast.makeText(SubMyDrop_Activity.this, "탈퇴되었습니다. 이용해 주셔서 감사합니다.", Toast.LENGTH_LONG).show();

                    finish();
                    SettingActivity.settingactivity.finish();
                    MainActivity2.mainActivity.finish();

                    Intent intent = new Intent(SubMyDrop_Activity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
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

    public void drop_prevBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
    }

}
