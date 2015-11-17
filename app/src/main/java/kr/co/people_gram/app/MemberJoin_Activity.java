package kr.co.people_gram.app;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    private ImageView memberjoin_activity_check1,memberjoin_activity_check2,memberjoin_activity_check3;
    private BitmapDrawable memberjoin_check_on,memberjoin_check_off;
    private Context ActivityContext;

    /* 닉네임, 이메일, 패스워드 체크 */
    private Boolean nick_check = false;
    private Boolean email_check = false;
    private Boolean pw_check = false;

    private RelativeLayout memberjoin_li_main;

    private boolean index1 = true;
    private boolean index2 = true;
    private boolean index3 = true;

    private String PhoneNumber;
    private String telecom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join);
        AsyncHttpClient client = HttpClient.getInstance();

        ActivityContext = this;


        TelephonyManager systemService = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        try {

            PhoneNumber = systemService.getLine1Number();    //폰번호를 가져오는 겁니다..
            telecom = systemService.getNetworkOperatorName();
            Log.d("people_gram", PhoneNumber);
        } catch (Exception e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoin_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
            alert.setMessage("현재 핸드폰은 사용불가능한 핸드폰입니다.\n활성화된 핸드폰으로 가입 부탁드립니다.");
            alert.show();
        }



        //Toast.makeText(MemberJoin_Activity.this,PhoneNumber, Toast.LENGTH_SHORT).show();

        et_usernickname = (EditText) findViewById(R.id.et_activity_memberjoin_nickname);
        //et_usernickname.setOnFocusChangeListener(EditTextFocus);
        et_useremail = (EditText) findViewById(R.id.et_activity_memberjoin_email);
        //et_useremail.setOnFocusChangeListener(EditTextFocus);
        et_userpw = (EditText) findViewById(R.id.et_activity_memberjoin_pw);

        memberjoin_activity_check1 = (ImageView) findViewById(R.id.memberjoin_activity_check1);
        memberjoin_activity_check2 = (ImageView) findViewById(R.id.memberjoin_activity_check2);
        memberjoin_activity_check3 = (ImageView) findViewById(R.id.memberjoin_activity_check3);
        memberjoin_check_on = (BitmapDrawable)getResources().getDrawable(R.drawable.memberjoin_check_on);
        memberjoin_check_off = (BitmapDrawable)getResources().getDrawable(R.drawable.memberjoin_check_off);

        memberjoin_li_main = (RelativeLayout) findViewById(R.id.memberjoin_li_main);

        /*
        memberjoin_activity_check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index1){
                    memberjoin_activity_check1.setImageDrawable(memberjoin_check_off);
                    memberjoin_activity_check2.setImageDrawable(memberjoin_check_off);
                    memberjoin_activity_check3.setImageDrawable(memberjoin_check_off);
                        index1 = false;
                }else{
                        memberjoin_activity_check1.setImageDrawable(memberjoin_check_on);
                        memberjoin_activity_check2.setImageDrawable(memberjoin_check_on);
                        memberjoin_activity_check3.setImageDrawable(memberjoin_check_on);
                         index1 = true;
                }
                Log.d("people_gram",String.valueOf(index1));
            }
        });

        memberjoin_activity_check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index2){
                   memberjoin_activity_check2.setImageDrawable(memberjoin_check_off);
                   index2 = false;
                }else{
                    memberjoin_activity_check2.setImageDrawable(memberjoin_check_on);
                    index2 = true;
                }
                Log.d("people_gram",String.valueOf(index2));
            }

        });

        memberjoin_activity_check3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index3){
                    memberjoin_activity_check3.setImageDrawable(memberjoin_check_off);
                    index3 = false;
                }else{
                    memberjoin_activity_check3.setImageDrawable(memberjoin_check_on);
                    index3 = true;
                }
                Log.d("people_gram",String.valueOf(index3));
            }
        });
        */
    }


    /*
    private View.OnFocusChangeListener EditTextFocus =  new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean gainFocus) {

            Log.d("people_gram", "테스트="+gainFocus);

            switch (view.getId()) {
                case R.id.et_activity_memberjoin_email:
                    if (gainFocus) {

                        RequestParams params = new RequestParams();

                        params.put("userNickName", et_usernickname.getText().toString());
                        params.put("userID", et_useremail.getText().toString());
                        params.put("userPW", et_userpw.getText().toString());


                        HttpClient.post("/user/emailCheck", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(String response) {

                                Log.d("people_gram", "이메일 받기");
                                JSONArray json;
                                try {
                                    json = new JSONArray(response);
                                    JSONObject jobj = json.getJSONObject(0);
                                    String code = jobj.getString("code");

                                    switch (code.toString()) {
                                        case "000":
                                            JSONArray array = (JSONArray) jobj.get("user_data");
                                            JSONObject user_data = (JSONObject) array.get(0);

                                            String email = user_data.get("EMAIL").toString();
                                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "email", email);

                                            ImageView img = (ImageView) findViewById(R.id.memberjoin_filed_check2);
                                            img.setImageResource(R.drawable.memberjoin_filed_check_on);
                                            email_check = true;
                                            break;
                                        case "999":
                                            Log.d("people_gram", "동일한 이메일이 존재합니다.");
                                            Toast.makeText(MemberJoin_Activity.this, "동일한 이메일이 존재합니다.", Toast.LENGTH_LONG).show();
                                            et_useremail.requestFocus();
                                            email_check = false;
                                            break;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                        Log.d("people_gram", String.valueOf(et_userpw.length()));

                    }
                    else {
                        //((EditText) view).setText("");

                    }
                    break;
            }


        };
    };
    */

    private Boolean emailCheck(CharSequence  email) {
        String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(mail);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public void btn_agree(View v) {
        if(index1){
            memberjoin_activity_check1.setImageDrawable(memberjoin_check_off);
            memberjoin_activity_check2.setImageDrawable(memberjoin_check_off);
            memberjoin_activity_check3.setImageDrawable(memberjoin_check_off);
            index1 = false;
        }else{
            memberjoin_activity_check1.setImageDrawable(memberjoin_check_on);
            memberjoin_activity_check2.setImageDrawable(memberjoin_check_on);
            memberjoin_activity_check3.setImageDrawable(memberjoin_check_on);
            index1 = true;
        }
        Log.d("people_gram",String.valueOf(index1));
    }

    public void btn_agreeterm(View v) {
        if(index2){
            memberjoin_activity_check2.setImageDrawable(memberjoin_check_off);
            index2 = false;
        }else{
            memberjoin_activity_check2.setImageDrawable(memberjoin_check_on);
            index2 = true;
        }
        Log.d("people_gram",String.valueOf(index2));

    }

    public void btn_agreePerson(View v) {
        if(index3){
            memberjoin_activity_check3.setImageDrawable(memberjoin_check_off);
            index3 = false;
        }else{
            memberjoin_activity_check3.setImageDrawable(memberjoin_check_on);
            index3 = true;
        }
        Log.d("people_gram",String.valueOf(index3));

    }

    /* 다음 버튼 */
    public void btn_start(View v) {
        //Log.d("people_gram", "버튼클릭");

        Boolean email_bool = this.emailCheck(et_useremail.getText().toString());

        /*
        if(nick_check == false) {
            Toast.makeText(MemberJoin_Activity.this, "닉네임을 확인해주세요.", Toast.LENGTH_LONG).show();
            et_usernickname.requestFocus();
            return;
        }
        */

        /*
        if(email_check == false) {
            Toast.makeText(MemberJoin_Activity.this, "이메일을 확인해주세요.", Toast.LENGTH_LONG).show();
            et_useremail.requestFocus();
            return;
        }
        */


        if(et_userpw.length() < 6 && et_userpw.length() > 0) {
            Toast.makeText(MemberJoin_Activity.this, "패스워드는 최소 6자리 이상 이어야 합니다.패스워드를 확인해주세요.", Toast.LENGTH_LONG).show();
            et_userpw.requestFocus();
            return;
        }

        if(email_bool == false) {
            Toast.makeText(MemberJoin_Activity.this, "이메일 형식이 올바르지 않습니다.다시 확인하시기 바랍니다.", Toast.LENGTH_LONG).show();
            et_useremail.requestFocus();
            return;
        }

        if(index1 == false) {
            Toast.makeText(MemberJoin_Activity.this, "약관 및 정보수집에 동의하시기 바랍니다.", Toast.LENGTH_LONG).show();
            return;
        }

        if(index2 == false) {
            Toast.makeText(MemberJoin_Activity.this, "서비스 이용 약관에 동의하시기 바랍니다.", Toast.LENGTH_LONG).show();
            return;
        }

        if(index3 == false) {
            Toast.makeText(MemberJoin_Activity.this, "정보수집 및 이용에 동의하시기 바랍니다.", Toast.LENGTH_LONG).show();
            return;
        }



        RequestParams params = new RequestParams();

        params.put("userNickName", et_usernickname.getText().toString());
        params.put("userID", et_useremail.getText().toString());
        params.put("userPW", et_userpw.getText().toString());
        params.put("phone", PhoneNumber);
        params.put("telecom", telecom);
        //params.put("phone", PhoneNumber);



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

                            //Log.d("people_gram", uid + ":::" + userNickName + ":::" + email);

                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "uid", uid);
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "username", userNickName);
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "email", email);

                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "point", "");
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "mytype", "");
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "my_speed", "");
                            SharedPreferenceUtil.putSharedPreference(ActivityContext, "my_control", "");

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

                        case "999":
                            Toast.makeText(MemberJoin_Activity.this, "중복된 이메일이 존재합니다.\n다시 확인해주세요.", Toast.LENGTH_LONG).show();
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
