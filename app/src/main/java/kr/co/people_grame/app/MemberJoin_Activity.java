package kr.co.people_grame.app;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberJoin_Activity extends AppCompatActivity {
    private Intent intent;
    private EditText et_usernickname,et_useremail, et_userpw;
    private Context ActivityContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join);
        AsyncHttpClient client = HttpClient.getInstance();

        ActivityContext = this;

    }

    private Boolean emailCheck(CharSequence  email) {
        String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(mail);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public void btn_start(View v) {
        EditText et_usernickname = (EditText) findViewById(R.id.et_activity_memberjoin_nickname);
        EditText et_useremail = (EditText) findViewById(R.id.et_activity_memberjoin_email);
        EditText et_userpw = (EditText) findViewById(R.id.et_activity_memberjoin_pw);

        Boolean email_bool = this.emailCheck(et_useremail.getText().toString());
        if(email_bool == false) {
            Toast.makeText(MemberJoin_Activity.this, "이메일 형식이 올바르지 않습니다.다시 확인하시기 바랍니다.", Toast.LENGTH_LONG).show();
            return;
        }

        if(et_usernickname.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoin_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("닉네임을 입력해주세요.");
            alert.show();
            return;
        }

        if(et_useremail.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoin_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            alert.setMessage("이메일을 입력해주세요.");
            alert.show();
            return;
        }

        if(et_userpw.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoin_Activity.this);
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

        params.put("userNickName", et_usernickname.getText().toString());
        params.put("userID", et_useremail.getText().toString());
        params.put("userPW", et_userpw.getText().toString());

        Log.d("people_gram", "데이터 전송시작");


        HttpClient.post("/user/memberCheck", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                Log.d("people_gram", "시작");
            }

            public void onFinish() {
                Log.d("people_gram", "완료");
            }

            @Override
            public void onSuccess(String response)
            {
                Log.d("people_gram", response.toString());
                JSONArray json;
                try {
                    json = new JSONArray(response);
                    JSONObject jobj = json.getJSONObject(0);
                    String code = jobj.getString("code");

                    //String code = jobj.getString("code");


                    Log.d("people_gram", code);
                    //Log.d("people_gram", user_data.getString("UID"));

                    switch (code.toString()) {
                        case "000":
                            // SharedPreference 넣기
                            //Log.d("people_gram", jobj.get("user_data"));

                            JSONArray array = (JSONArray) jobj.get("user_data");
                            JSONObject user_data = (JSONObject) array.get(0);


                            String uid = user_data.get("UID").toString();
                            String userNickName = user_data.get("USERNICKNAME").toString();
                            String email = user_data.get("EMAIL").toString();

                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "uid", uid);
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "userNickName", userNickName);
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "email", email);

                            intent = new Intent(MemberJoin_Activity.this, MyQuestion_Activity.class);
                            startActivity(intent);
                            finish();

                            Log.d("people_gram", "회원가입 성공");

                            break;

                        case "101":
                            Log.d("people_gram", "Error");
                            break;
                        case "102":
                            Log.d("people_gram", "Error");
                            break;
                        case "998":
                            Log.d("people_gram", "닉네임이 존재합니다.");
                            Toast.makeText(MemberJoin_Activity.this, "닉네임이 존재합니다.", Toast.LENGTH_LONG).show();
                            break;
                        case "999":
                            Log.d("people_gram", "동일한 이메일이 존재합니다.");
                            Toast.makeText(MemberJoin_Activity.this, "동일한 이메일이 존재합니다.", Toast.LENGTH_LONG).show();
                            break;

                    }


                } catch(JSONException e) {
                    e.printStackTrace();
                }

                //Log.d("people_gram", response);
            }


        });

        //intent = new Intent(MemberJoin_Activity.this, MyQuestion_Activity.class);
        //startActivity(intent);

    }


}
