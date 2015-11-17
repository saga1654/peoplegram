package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;


public class MemberComplate_Activity extends AppCompatActivity {

    private LinearLayout nextLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_complate_);

        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberComplate_Activity.this, MyQuestion_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                next_finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder alert = new AlertDialog.Builder(MemberComplate_Activity.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.setMessage("종료하시겠습니까?");
                alert.setNegativeButton("취소", null);
                alert.show();
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
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
    }



    public void closeMember(View v)
    {
        finish();
    }

}
