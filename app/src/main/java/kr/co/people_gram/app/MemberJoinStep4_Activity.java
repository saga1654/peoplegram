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


public class MemberJoinStep4_Activity extends AppCompatActivity {

    private EditText et_nickname;
    private Boolean enterCheck = false;
    private LinearLayout nextLL;

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

                if(nickname_string_cnt < 3) {
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
                    if (nickname_string_cnt < 3) {
                        Toast.makeText(MemberJoinStep4_Activity.this, "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        md.set_nickname(nickname_string);
                        Intent intent = new Intent(MemberJoinStep4_Activity.this, MemberJoinStep5_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_end);
                        next_finish();
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



    public void closeMember(View v)
    {
        finish();
    }



}
