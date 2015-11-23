package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>꼼꼼하기도 하면서 때론 완벽을 추구하는 <b color='#ff8a55'>강한 추진력과 모험심 있는 스타일</b></b><br>로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>사람과의 관계와 소통을 중요시하며 <b color='#ff8a55'>때론 추진력과 도전을 좋아하는 스타일</b></b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED <= 1 && CONTROL <= 5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>꼼꼼하면서 <b color='#ff8a55'>밀어부치기도 도전을 좋아하기도 하는 스타일</b></b><br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b color='#ff8a55'>새로운 도전과 모험, 강한 추진력을 가진 스타일</b>로 진단되었습니다."));
                        }
                    }
                    if (SPEED > 0 && CONTROL < 0) {

                        if (SPEED <= 5 && CONTROL >= -1) {
                            if (SPEED <= 1 && CONTROL >= -1) {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>가끔 밀어부치기도 하고 그러나 상대방을 배려하는 이해심도 가진 <b color='#aa64f8'>소통의 달인</b></b><br>으로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>사람을 끄는 힘을 가진 <b color='#aa64f8'>관계 형성이 좋은 스타일</b></b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED <= 1 && CONTROL >= -5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br><b>상대에 대한 이해심이 높고 믿을 수 있는 <b color='#aa64f8'>소통하고 싶은 스타일</b></b><br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b color='#aa64f8'>인간관계가 중요하고 큰 문제가 없으며 상대의 마음을 진심으로 대하는 스타일</b>로 진단되었습니다."));
                        }
                    }
                    if (SPEED < 0 && CONTROL > 0) {

                        if (SPEED >= -5 && CONTROL <= 1) {
                            if (SPEED >= -1 && CONTROL <= 1) {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b>도전정신을 가진 이해심 깊은<b color='#37afec'>완벽주의자</b></b><br>로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b>속 깊이 잘 챙기는<b color='#37afec'>꼼꼼주의자</b></b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED >= -1 && CONTROL <= 5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b>새로운 것을 마다하지 않고 끝까지 완벽하게 꼼꼼히 이루는 스타일<br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b color='#37afec'>꼼꼼한 완벽주의자</b>로 진단되었습니다."));
                        }
                    }
                    if (SPEED < 0 && CONTROL < 0) {

                        if (SPEED >= -5 && CONTROL >= -1) {
                            if (SPEED >= -1 && CONTROL >= -1) {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b>사람과의 관계에서 이해심이 높지만 그래도 나름 계산하여 마음을 보이는 스타일</b><br>로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b>꼼꼼히 상대를 알아보고 끝까지 믿어주는 스타일</b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED >= -1 && CONTROL >= -5) {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b>상대를 이해하며 좋은 인간관계를 끝까지 유지하는 스타일</b><br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + username + "</font>님의 자기진단 결과<br> <b color='#52d935'>상대방에 대한 배려심 좋고 신뢰성 있는 스타일</b>로 진단되었습니다."));
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
