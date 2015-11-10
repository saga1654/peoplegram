package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberJoinStep2_Activity extends AppCompatActivity {

    private LinearLayout nextLL;
    private EditText et_email;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step2_);
        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setVisibility(View.GONE);


        et_email = (EditText) findViewById(R.id.et_email);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        et_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER) {
                    //Log.d("people_gram", "엔터");
                    if(emailCheck(et_email.getText()) == false) {
                        Log.d("people_gram", "이메일을 확인해주세요.");
                    } else {
                        RequestParams params = new RequestParams();
                        params.put("userID", et_email.getText());
                        HttpClient.post("/user/emailCheck", params, new AsyncHttpResponseHandler() {
                            public void onStart() {
                                dialog = ProgressDialog.show(MemberJoinStep2_Activity.this, "", "데이터 수신중");
                            }

                            public void onFinish() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onSuccess(String response)
                            {
                                Log.d("people_gram", response);
                                try {
                                    JSONObject jobj = new JSONObject(response);


                                } catch(JSONException e) {
                                    e.printStackTrace();
                                }

                                //Log.d("people_gram", response);
                            }


                        });
                    }

                    return true;
                }

                return false;
            }
        });
    }

    private Boolean emailCheck(CharSequence  email) {
        String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(mail);
        Matcher m = p.matcher(email);
        return m.matches();
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
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

    public void closeMember(View v)
    {
        finish();
    }

}
