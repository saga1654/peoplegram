package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.kakao.kakaolink.KakaoLink;

public class KakaoMsgActivity extends AppCompatActivity {

    private EditText et_contents;
    private String msg = "이 테스트 해봤어요? \n나는 누구일까요? \n다른 사람은 나를 어떻게 생각할까요? \n좋은 관계를 만들려면 어떻게 해야 할까요? \n\n나와 다른 사람의 행동 유형이 궁금하다면 지금 진단해 보세요! \n(아이폰유저는 1월말 출시예정)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_msg);


        et_contents = (EditText) findViewById(R.id.et_contents);
        et_contents.setText(msg);
    }

    public void kakaoSave(View v) {
        if(et_contents.getText().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(KakaoMsgActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("카카오톡으로 보낼 메세지를 입력해주세요.");
            alert.show();
            return;
        } else {
            Intent intent = new Intent(KakaoMsgActivity.this, KakaoLinkMainActivity.class);
            intent.putExtra("kakao_msg", et_contents.getText().toString());
            startActivity(intent);
            overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            finish();
        }
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

    public void closeBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

}
