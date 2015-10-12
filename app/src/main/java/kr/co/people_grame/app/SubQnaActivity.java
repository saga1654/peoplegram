package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SubQnaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_qna);
    }

    public void qnaWrite(View v) {
        Intent intent = new Intent(SubQnaActivity.this, SubQnaWriteActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
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

    public void qna_prevBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
