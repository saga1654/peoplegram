package kr.co.people_grame.app;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import com.loopj.android.http.*;

public class MemberJoin_Activity extends AppCompatActivity {
    private Intent intent;
    private EditText et_usernickname,et_useremail, et_userpw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join);
        AsyncHttpClient client = HttpClient.getInstance();

    }

    public void btn_start(View v) {
        EditText et_usernickname = (EditText) findViewById(R.id.et_activity_memberjoin_nickname);
        EditText et_useremail = (EditText) findViewById(R.id.et_activity_memberjoin_email);
        EditText et_userpw = (EditText) findViewById(R.id.et_activity_memberjoin_pw);

        if(et_usernickname.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoin_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("닉네임을 입력해주세요.");
            alert.show();
            return;
        }

        if(et_useremail.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoin_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("이메일을 입력해주세요.");
            alert.show();
            return;
        }

        if(et_userpw.getText().toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MemberJoin_Activity.this);
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

        params.put("userNickName", et_usernickname.getText().toString());
        params.put("userID", et_useremail.getText().toString());
        params.put("userPW", et_userpw.getText().toString());

        Log.d("people_gram", "데이터 전송시작");


        HttpClient.post("/user2/memberCheck", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                Log.d("people_gram", "시작");
            }

            public void onFinish() {
                Log.d("people_gram", "완료");
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
            }


        });

        intent = new Intent(MemberJoin_Activity.this, MyQuestion_Activity.class);
        startActivity(intent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_member_join, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
