package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class SubPeopleListPopup_Activity extends AppCompatActivity {

    private String people_uid = "";
    private String people_username = "";
    private String people_mood = "";
    private String people_type = "";
    private String people_gubun1 = "";
    private String people_gubun2 = "";
    private int people_speed = 0;
    private int people_control = 0;
    private int people_result_count = 0;
    private int people_friend_count = 0;
    private String people_email = "";


    private TextView popup_username, popup_mood, et_people_friend_count, et_people_result_count;
    private ImageView popup_type, people_popup_btn1;

    private String request = "";
    private ProgressDialog dialog;

    private LinearLayout backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_list_popup_);

        Random generator = new Random();
        int random;



        popup_username = (TextView) findViewById(R.id.popup_username);
        popup_type = (ImageView) findViewById(R.id.popup_type);
        et_people_friend_count = (TextView) findViewById(R.id.et_people_friend_count);
        et_people_result_count = (TextView) findViewById(R.id.et_people_result_count);

        backImg = (LinearLayout) findViewById(R.id.backImg);
        people_popup_btn1 = (ImageView) findViewById(R.id.people_popup_btn1);

        Intent intent = getIntent();
        if(intent != null) {
            people_uid = intent.getStringExtra("people_uid");
            people_username = intent.getStringExtra("people_username");
            people_type = intent.getStringExtra("people_type");
            people_gubun1 = intent.getStringExtra("people_gubun1");
            people_gubun2 = intent.getStringExtra("people_gubun2");
            people_speed = intent.getIntExtra("people_speed", 0);
            people_control = intent.getIntExtra("people_control", 0);
            people_email = intent.getStringExtra("people_email");
            people_result_count = intent.getIntExtra("people_result_count", 0);
            people_friend_count = intent.getIntExtra("people_friend_count", 0);

            et_people_friend_count.setText("등록 피플수 : "+people_friend_count+"명");
            et_people_result_count.setText("진단 피플수 : "+people_result_count+"명");


            if(people_email.toString().equals("")) {
                request = "N";
                people_popup_btn1.setImageResource(R.drawable.people_popup_btn1_style);
            } else {
                if(people_speed == 0 && people_control == 0) {
                    request = "F";
                    people_popup_btn1.setImageResource(R.drawable.people_popup_btn1_retype_style);
                } else {
                    request = "R";
                    people_popup_btn1.setImageResource(R.drawable.people_popup_btn1_reretype_style);
                }
            }


            PeopleData pd = new PeopleData();
            pd.set_people_uid(people_uid);
            pd.set_people_username(people_username);
            pd.set_people_type(people_type);
            pd.set_people_gubun1(people_gubun1);
            pd.set_people_gubun2(people_gubun2);
            pd.set_people_speed(people_speed);
            pd.set_people_control(people_control);


            popup_username.setText(people_username);


            switch (people_type) {
                case "A":
                    popup_type.setImageResource(R.mipmap.people_type_a);
                    break;
                case "E":
                    popup_type.setImageResource(R.mipmap.people_type_e);
                    break;
                case "D":
                    popup_type.setImageResource(R.mipmap.people_type_d);
                    break;
                case "I":
                    popup_type.setImageResource(R.mipmap.people_type_i);
                    break;
                default:
                    popup_type.setImageResource(R.mipmap.people_type_default);
                    break;
            }

        }


    }

    public void peopleReType_btn(View v) {


        RequestParams params = new RequestParams();
        switch (request) {
            case "N":

                break;
            case "F":
                params.put("uid", SharedPreferenceUtil.getSharedPreference(SubPeopleListPopup_Activity.this, "uid"));
                params.put("people_uid", people_uid);
                params.put("people_username", SharedPreferenceUtil.getSharedPreference(SubPeopleListPopup_Activity.this, "username"));
                HttpClient.post("/user/peoplePushSend", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        //Log.d("people_gram", "시작");
                        dialog = ProgressDialog.show(SubPeopleListPopup_Activity.this, "", "데이터 수신중");
                    }

                    public void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(String response) {
                        Log.d("people_gram", response);
                    }
                });
                break;
            case "R":


                params.put("uid", SharedPreferenceUtil.getSharedPreference(SubPeopleListPopup_Activity.this, "uid"));
                params.put("people_uid", people_uid);
                params.put("people_username", SharedPreferenceUtil.getSharedPreference(SubPeopleListPopup_Activity.this, "username"));
                HttpClient.post("/user/peoplePushSend", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        //Log.d("people_gram", "시작");
                        dialog = ProgressDialog.show(SubPeopleListPopup_Activity.this, "", "데이터 수신중");
                    }

                    public void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(String response) {
                        Log.d("people_gram", response);
                    }
                });


                break;
        }
    }

    public void peopleType_btn(View v) {
        Intent intent = new Intent(SubPeopleListPopup_Activity.this, YouType_Actvity_step1.class);
        finish();

        intent.putExtra("people_uid", people_uid);
        intent.putExtra("people_username", people_username);


        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public void peopleView_btn(View v) {
        Log.d("people_gram", "타입=" + people_type.toString());
        if(people_type.toString().equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(SubPeopleListPopup_Activity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(SubPeopleListPopup_Activity.this, YouType_Actvity_step1.class);
                    finish();

                    intent.putExtra("people_uid", people_uid);
                    intent.putExtra("people_username", people_username);

                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                }
            });
            alert.setMessage("타인진단을 진행해야\n매칭을 볼 수 있습니다.\n타인진단을 진행하시겠습니까?");
            alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.show();
            return;

            //Toast.makeText(SubPeopleListPopup_Activity.this, "타인진단을 해주세요.", Toast.LENGTH_LONG).show();


        } else {

            Intent intent = new Intent(SubPeopleListPopup_Activity.this, SubPeopleListSelect_Activity.class);

            /*
            intent.putExtra("people_uid", people_uid);
            intent.putExtra("people_username", people_username);
            intent.putExtra("people_mood", people_mood);
            intent.putExtra("people_type", people_type);
            */
            finish();
            startActivity(intent);
            overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

        }
    }

    public void peopleDeleteBtn(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(SubPeopleListPopup_Activity.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RequestParams params = new RequestParams();
                params.put("uid", SharedPreferenceUtil.getSharedPreference(SubPeopleListPopup_Activity.this, "uid"));
                params.put("people_uid", people_uid);
                HttpClient.post("/user/peopleDelete", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                    }

                    public void onFinish() {
                    }

                    @Override
                    public void onSuccess(String response) {
                        if(response.equals("000") == false) {
                            Toast.makeText(SubPeopleListPopup_Activity.this, "잠시후 다시 실행해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            finish();
                        }
                    }
                });
            }
        });
        alert.setMessage("현재 피플리스트에서 삭제 진행을 하려고 합니다.\n맞으면 확인버튼을 눌러주세요.");
        alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
        return;
    }

    public void closeBtn(View v) {
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

}
