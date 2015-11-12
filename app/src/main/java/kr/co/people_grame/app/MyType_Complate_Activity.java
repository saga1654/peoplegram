package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyType_Complate_Activity extends AppCompatActivity {

    private TextView mytype_tv;

    private String username = "";
    private String mytype = "";
    private ImageView mytype_activity_typeImg;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type__complate_);

        Log.d("people_gram", SharedPreferenceUtil.getSharedPreference(MyType_Complate_Activity.this, "uid"));
        mytype = SharedPreferenceUtil.getSharedPreference(MyType_Complate_Activity.this, "mytype");
        username = SharedPreferenceUtil.getSharedPreference(MyType_Complate_Activity.this, "username");

        mytype_tv = (TextView) findViewById(R.id.mytype_tv);
        mytype_activity_typeImg = (ImageView) findViewById(R.id.mytype_activity_typeImg);
        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+username+"</font>님의 자기진단 결과<br><b>표출형 특징을 가진 주도형</b><br>으로 진단되었습니다."));


        userData();

        switch (mytype)
        {
            case "I":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_i);
                break;

            case "D":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_d);
                break;

            case "E":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_e);
                break;

            case "A":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_a);
                break;
        }
    }

    private void userData()
    {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(MyType_Complate_Activity.this, "uid"));
        HttpClient.post("/user/member_type", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(MyType_Complate_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);
                    String DATA1 = jobj.getString("DATA1");
                    String DATA2 = jobj.getString("DATA2");
                    String DATA3 = jobj.getString("DATA3");
                    String DATA4 = jobj.getString("DATA4");
                    String DATA5 = jobj.getString("DATA5");
                    String DATA6 = jobj.getString("DATA6");
                    String DATA7 = jobj.getString("DATA7");
                    String DATA8 = jobj.getString("DATA8");
                    String DATA9 = jobj.getString("DATA9");
                    String DATA10 = jobj.getString("DATA10");
                    int SPEED = Integer.parseInt(jobj.getString("SPEED"));
                    int CONTROL = Integer.parseInt(jobj.getString("CONTROL"));

                    if (SPEED > 0 && CONTROL > 0) {


                        if (SPEED <= 5 && CONTROL <= 1) {
                            if (SPEED <= 1 && CONTROL <= 1) {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>분석형,표출형 특징을 가진 <b color='#ff8a55'>주도형</b></b><br>으로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>표출형 특징을 가진 <b color='#ff8a55'>주도형</b></b><br>으로 진단되었습니다."));
                            }
                        } else if (SPEED <= 1 && CONTROL <= 5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>분석형 특징을 가진 <b color='#ff8a55'>주도형</b></b><br>으로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b color='#ff8a55'>주도형</b>으로 진단되었습니다."));
                        }
                    }
                    if (SPEED > 0 && CONTROL < 0) {

                        if (SPEED <= 5 && CONTROL >= -1) {
                            if (SPEED <= 1 && CONTROL >= -1) {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>주도형,우호형 특징을 가진 <b color='#aa64f8'>표출형</b></b><br>으로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>주도형 특징을 가진 <b color='#aa64f8'>표출형</b></b><br>으로 진단되었습니다."));
                            }
                        } else if (SPEED <= 1 && CONTROL >= -5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>우호형 특징을 가진 <b color='#aa64f8'>표출형</b></b><br>으로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b color='#aa64f8'>표출형</b>으로 진단되었습니다."));
                        }
                    }
                    if (SPEED < 0 && CONTROL > 0) {

                        if (SPEED >= -5 && CONTROL <= 1) {
                            if (SPEED >= -1 && CONTROL <= 1) {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>주도형,우호형 특징을 가진 <b color='#37afec'>분석형</b></b><br>으로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>우호형 특징을 가진 <b color='#37afec'>분석형</b></b><br>으로 진단되었습니다."));
                            }
                        } else if (SPEED >= -1 && CONTROL <= 5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>주도형 특징을 가진 <b color='#37afec'>분석형</b></b><br>으로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b color='#37afec'>분석형</b>으로 진단되었습니다."));
                        }
                    }
                    if (SPEED < 0 && CONTROL < 0) {

                        if (SPEED >= -5 && CONTROL >= -1) {
                            if (SPEED >= -1 && CONTROL >= -1) {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>분석형,표출형 특징을 가진 <b color='#52d935'>우호형</b></b><br>으로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>분석형 특징을 가진 <b color='#52d935'>우호형</b></b><br>으로 진단되었습니다."));
                            }
                        } else if (SPEED >= -1 && CONTROL >= -5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>표출형 특징을 가진 <b color='#52d935'>우호형</b></b><br>으로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b color='#52d935'>우호형</b>으로 진단되었습니다."));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

     public void mytype_re_btn(View v) {

         Intent intent = new Intent(MyType_Complate_Activity.this, MyQuestion_Activity.class);
         startActivity(intent);
         overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

         finish();
     }

    public void mytype_view_btn(View v) {
        Intent intent = new Intent(MyType_Complate_Activity.this, MyType_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
    }

    public void start_btn(View v) {
        //Intent intent = new Intent(MyType_Complate_Activity.this, My)

        Intent intent = new Intent(MyType_Complate_Activity.this, PeopleSync_Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }
    public void closeBtn(View v) {
        finish();
    }
    public void finish()
    {
        super.finish();
    }

}
