package kr.co.people_gram.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MemberJoinStep3_Activity extends AppCompatActivity {

    private static final String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$";

    private EditText et_password;
    private Boolean enterCheck = false;
    private LinearLayout nextLL;

    private int password_string_cnt = 0;
    private String password_string = "";

    private MemberData md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step3_);

        et_password = (EditText) findViewById(R.id.et_password);
        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setVisibility(View.INVISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        md = new MemberData();


        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password_string = String.valueOf(et_password.getText());
                password_string_cnt = password_string.length();

                if(password_string_cnt < 6) {
                    nextLL.setVisibility(View.INVISIBLE);
                } else {
                    nextLL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        et_password.addTextChangedListener(watcher);


        et_password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (enterCheck == false) {
                        enterCheck = true;
                        if (password_string_cnt < 6) {
                            Toast.makeText(MemberJoinStep3_Activity.this, "6자리 이상 입력해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            md.set_userpw(password_string);
                            Intent intent = new Intent(MemberJoinStep3_Activity.this, MemberJoinStep4_Activity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                            next_finish();
                        }

                    }
                } else {

                    enterCheck = false;
                }

                return false;
            }
        });

        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (password_string_cnt < 6) {
                        Toast.makeText(MemberJoinStep3_Activity.this, "6자리 이상 입력해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        md.set_userpw(password_string);
                        Intent intent = new Intent(MemberJoinStep3_Activity.this, MemberJoinStep4_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                        next_finish();
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



    public void closeMember(View v)
    {
        finish();
    }

}
