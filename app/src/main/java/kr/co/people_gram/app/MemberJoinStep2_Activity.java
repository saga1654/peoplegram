package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class MemberJoinStep2_Activity extends AppCompatActivity {

    private LinearLayout nextLL;
    private EditText et_email;
    private ProgressDialog dialog;
    private TextView memberjoin_step2_tv;
    private String memberjoin_step2_string;

    private MemberData md;
    private Boolean enterCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step2_);
        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        //nextLL.setVisibility(View.INVISIBLE);

        md = new MemberData();


        memberjoin_step2_string = getString(R.string.memberjoin_step2_text1);

        et_email = (EditText) findViewById(R.id.et_email);
        memberjoin_step2_tv = (TextView) findViewById(R.id.memberjoin_step2_tv);
        memberjoin_step2_tv.setText(Html.fromHtml(memberjoin_step2_string));

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (emailCheck(et_email.getText()) == false) {
                    Toast.makeText(MemberJoinStep2_Activity.this,"이메일 또는 아이디를 확인해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    emailData();
                }
            }
        });

        et_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    if(enterCheck == false) {
                        enterCheck = true;
                        if (emailCheck(et_email.getText()) == false) {
                            Toast.makeText(MemberJoinStep2_Activity.this,"이메일 또는 아이디를 확인해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            emailData();
                        }
                    } else {
                        enterCheck = false;
                    }

                    return true;
                }

                return false;
            }
        });
    }

    private void emailData()
    {
        RequestParams params = new RequestParams();
        params.put("userID", et_email.getText().toString());
        HttpClient.post("/user/emailCheck", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(MemberJoinStep2_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    String code = jobj.getString("code");
                    switch (code) {
                        case "000":
                            md.set_userid(et_email.getText().toString());
                            Intent intent = new Intent(MemberJoinStep2_Activity.this, MemberJoinStep3_Activity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                            next_finish();
                            break;
                        case "101":
                            memberjoin_step2_string = getString(R.string.memberjoin_step2_text1);
                            memberjoin_step2_tv.setText(Html.fromHtml(memberjoin_step2_string));
                            enterCheck = false;
                            break;
                        case "999":
                            memberjoin_step2_string = getString(R.string.memberjoin_step2_text2);
                            memberjoin_step2_tv.setText(Html.fromHtml(memberjoin_step2_string));
                            enterCheck = false;
                            break;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Log.d("people_gram", response);
            }


        });
    }

    private Boolean emailCheck(CharSequence  email) {
        //String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

        //Pattern p = Pattern.compile(mail);
        //Matcher m = p.matcher(email);
        //return m.matches();
        if(email.length() > 0) {
            if (email.length() < 6) {
                Toast.makeText(MemberJoinStep2_Activity.this, "6자리 이상 입력해주세요.", Toast.LENGTH_LONG).show();
            } else {

                return true;
            }
        }
        return false;
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

    public void closeMember(View v)
    {
        finish();
    }

}
