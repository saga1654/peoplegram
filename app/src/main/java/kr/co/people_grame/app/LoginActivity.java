package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;


public class LoginActivity extends AppCompatActivity {

    private Intent intent;
    private EditText et_userid, et_userpw;
    private Context ActivityContext;


    private ProgressDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AsyncHttpClient client = HttpClient.getInstance();
        ActivityContext = this;



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
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(LoginActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                //Log.d("people_gram", "완료");
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response)
            {

                try {
                    JSONArray json = new JSONArray(response);
                    JSONObject jobj = json.getJSONObject(0);
                    String code = jobj.getString("code");



                    //Log.d("people_gram", user_data.getString("UID"));

                    switch (code.toString()) {
                        case "000":
                            // SharedPreference 넣기
                            JSONArray array = (JSONArray) jobj.get("user_data");
                            JSONObject user_data = (JSONObject) array.get(0);


                            String uid = user_data.get("UID").toString();
                            String username = user_data.get("USERNICKNAME").toString();
                            String mytype = user_data.get("MYTYPE").toString();


                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "uid", uid);
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "username", username);



                            if(mytype.equals("N")) {
                                SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", "");
                                intent = new Intent(LoginActivity.this, MyQuestion_Activity.class);
                            } else {
                                SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", mytype);
                                intent = new Intent(LoginActivity.this, MainActivity.class);
                            }

                            startActivity(intent);
                            finish();




                            break;


                        case "101":
                            Log.d("people_gram", "Error");
                            break;
                        case "102":
                            Log.d("people_gram", "Error");
                            break;
                        case "999":
                            Log.d("people_gram", "아이디가 존재하지 않습니다.");
                            break;
                        case "998":
                            Log.d("people_gram", "패스워드가 일치하지 않습니다.");
                            break;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*
                JSONArray json;
                try {
                    JSONArray json = new JSONArray(response);
                    JSONObject jobj = json.getJSONObject(0);
                    String code = jobj.getString("code");



                    //Log.d("people_gram", user_data.getString("UID"));

                    switch (code.toString()) {
                        case "000":
                            // SharedPreference 넣기
                            JSONArray array = (JSONArray) jobj.get("user_data");
                            JSONObject user_data = (JSONObject) array.get(0);


                            String uid = user_data.get("UID").toString();
                            String username = user_data.get("USERNICKNAME").toString();
                            String mytype = user_data.get("MYTYPE").toString();

                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "uid", uid);
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "username", username);
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", mytype);


                            if(mytype.equals("N")) {
                                intent = new Intent(LoginActivity.this, MyQuestion_Activity.class);
                            } else {
                                intent = new Intent(LoginActivity.this, MainActivity.class);
                            }

                            startActivity(intent);
                            finish();



                            //Log.d("people_gram", "로그인 성공");

                            break;


                        case "101":
                            Log.d("people_gram", "Error");
                            break;
                        case "102":
                            Log.d("people_gram", "Error");
                            break;
                        case "999":
                            Log.d("people_gram", "아이디가 존재하지 않습니다.");
                            break;
                        case "998":
                            Log.d("people_gram", "패스워드가 일치하지 않습니다.");
                            break;
                    }

                } catch(JSONException e) {
                    e.printStackTrace();
                }


                */
                //Log.d("people_gram", response);
            }


        });


    }

}
