package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class MemberJoinStep4_Activity extends AppCompatActivity {

    private EditText et_nickname;
    private Boolean enterCheck = false;
    private LinearLayout nextLL;

    private ProgressDialog dialog;

    private MemberData md;

    private String nickname_string = "";
    private int nickname_string_cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step4_);

        md = new MemberData();

        et_nickname = (EditText) findViewById(R.id.et_nickname);
        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setVisibility(View.INVISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        et_nickname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    if (enterCheck == false) {
                        enterCheck = true;
                        md.set_nickname(et_nickname.getText().toString());

                        Intent intent = new Intent(MemberJoinStep4_Activity.this, MemberJoinStep5_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_end);
                        next_finish();
                    }

                } else {
                    enterCheck = false;
                }
                return false;
            }
        });

        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nickname_string = String.valueOf(et_nickname.getText());
                nickname_string_cnt = nickname_string.length();

                if (nickname_string_cnt < 2) {
                    nextLL.setVisibility(View.INVISIBLE);
                } else {
                    nextLL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        et_nickname.addTextChangedListener(watcher);

        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterCheck == false) {
                    enterCheck = true;
                    if (nickname_string_cnt < 2) {
                        Toast.makeText(MemberJoinStep4_Activity.this, "이름을 2자 이상 입력해주세요.", Toast.LENGTH_LONG).show();
                    } else {

                        hideSoftKeyboard(v);
                        md.set_nickname(nickname_string);
                        RequestParams params = new RequestParams();
                        params.put("userID", md.get_userid());
                        params.put("userPW", md.get_userpw());
                        params.put("userNickName", md.get_nickname());
                        params.put("phone", md.get_phone());
                        params.put("telecom", md.get_telecom());

                        //Intent intent = new Intent(MemberJoinStep4_Activity.this, MemberJoinStep5_Activity.class);
                        //startActivity(intent);
                        //overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_end);
                        //next_finish();

                        HttpClient.post("/user/memberCheck", params, new AsyncHttpResponseHandler() {
                            public void onStart() {
                                dialog = ProgressDialog.show(MemberJoinStep4_Activity.this, "", "데이터 수신중");
                            }

                            public void onFinish() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onSuccess(String response) {
                                Log.d("people_gram", response);
                                try {
                                    JSONObject jobj = new JSONObject(response);
                                    JSONObject jobj_data = new JSONObject(jobj.getString("user_data"));
                                    String code = jobj.getString("code");

                                    if (code.equals("000")) {
                                        String uid = jobj_data.get("UID").toString();
                                        String userNickName = jobj_data.get("USERNICKNAME").toString();
                                        String email = jobj_data.get("EMAIL").toString();


                                        SharedPreferenceUtil.putSharedPreference(MemberJoinStep4_Activity.this, "uid", uid);
                                        SharedPreferenceUtil.putSharedPreference(MemberJoinStep4_Activity.this, "username", userNickName);
                                        SharedPreferenceUtil.putSharedPreference(MemberJoinStep4_Activity.this, "email", md.get_userid());

                                        SharedPreferenceUtil.putSharedPreference(MemberJoinStep4_Activity.this, "point", jobj_data.getString("POINT"));
                                        SharedPreferenceUtil.putSharedPreference(MemberJoinStep4_Activity.this, "mytype", "");
                                        SharedPreferenceUtil.putSharedPreference(MemberJoinStep4_Activity.this, "my_speed", "");
                                        SharedPreferenceUtil.putSharedPreference(MemberJoinStep4_Activity.this, "my_control", "");

                                        LoginActivity.loginActivity.finish();

                                        Intent intent = new Intent(MemberJoinStep4_Activity.this, MemberComplate_Activity.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                                        next_finish();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //Log.d("people_gram", response);
                            }


                        });


                    }
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




    public void next_finish()
    {
        super.finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }



    public void closeMember(View v) {
        finish();
    }

    protected void hideSoftKeyboard(View view) {
        InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




}
