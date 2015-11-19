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


public class ProfileActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private TextView mytype_tv;

    private String people_type_return = "";
    private ImageView mytype_me, mytype_people;
    private TextView mytype_per, profile_sex, profile_age, profile_area;
    private TextView profile_username, profile_email, profile_point;
    private TextView point_add;

    private int ACTIVITY_CODE = 00002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        mytype_me = (ImageView) findViewById(R.id.mytype_me);
        mytype_people = (ImageView) findViewById(R.id.mytype_people);

        mytype_per = (TextView) findViewById(R.id.mytype_per);
        profile_sex = (TextView) findViewById(R.id.profile_sex);
        profile_age = (TextView) findViewById(R.id.profile_age);
        profile_area = (TextView) findViewById(R.id.profile_area);
        profile_username = (TextView) findViewById(R.id.profile_username);
        profile_email = (TextView) findViewById(R.id.profile_email);
        profile_point = (TextView) findViewById(R.id.profile_point);

        point_add = (TextView) findViewById(R.id.point_add);

        profile_username.setText(SharedPreferenceUtil.getSharedPreference(this, "username"));
        profile_email.setText(SharedPreferenceUtil.getSharedPreference(this, "email"));
        profile_point.setText(SharedPreferenceUtil.getSharedPreference(this, "point"));

        /*추가부분*/
        mytype_tv = (TextView) findViewById(R.id.mytype_tv);
        mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>표출형 특징을 가진 주도형</b><br>으로 진단되었습니다."));

        mytype_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubMyType_Activity.class);
                intent.putExtra("mytype", SharedPreferenceUtil.getSharedPreference(ProfileActivity.this, "mytype"));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

        mytype_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubMyType_Activity.class);
                intent.putExtra("mytype", people_type_return);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

        point_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubGramPoint.class);
                startActivity(intent);
                overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });

        dataResult();



        //mytype

        /*
        profile_username = (TextView) findViewById(R.id.profile_username);
        profileView_username = (TextView) findViewById(R.id.profileView_username);
        profileView_email = (TextView) findViewById(R.id.profileView_email);
        profileView_type = (TextView) findViewById(R.id.profileView_type);
        profileView_point = (TextView) findViewById(R.id.profileView_point);

        profileView_typeImg = (ImageView) findViewById(R.id.profileView_typeImg);


        profile_sex = (TextView) findViewById(R.id.profile_sex);
        profile_age = (TextView) findViewById(R.id.profile_age);
        profile_area = (TextView) findViewById(R.id.profile_area);

        profile_username.setText(SharedPreferenceUtil.getSharedPreference(this, "username"));
        profileView_username.setText(SharedPreferenceUtil.getSharedPreference(this, "username"));
        profileView_email.setText(SharedPreferenceUtil.getSharedPreference(this, "email"));
        profileView_point.setText(SharedPreferenceUtil.getSharedPreference(this, "point") + "그램");

        switch (SharedPreferenceUtil.getSharedPreference(this, "mytype")) {
            case "I":
                profileView_type.setText("우호형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_i);
                break;
            case "D":
                profileView_type.setText("주도형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_d);
                break;
            case "E":
                profileView_type.setText("표현형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_e);
                break;
            case "A":
                profileView_type.setText("분석형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_a);
                break;
            default:
                profileView_type.setText("자기진단안함");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_default);
                break;
        }
        (/







        /*
        profile_img = (CircularImageView) findViewById(R.id.profile_img);
        String filename = SharedPreferenceUtil.getSharedPreference(ProfileActivity.this, "profile_image");
        profile_img_view(filename);
        */
    }


    private void dataResult()
    {
        switch (SharedPreferenceUtil.getSharedPreference(this, "mytype")) {
            case "I":
                mytype_me.setImageResource(R.mipmap.people_type_i);
                break;
            case "D":
                mytype_me.setImageResource(R.mipmap.people_type_d);
                break;
            case "E":
                mytype_me.setImageResource(R.mipmap.people_type_e);
                break;
            case "A":
                mytype_me.setImageResource(R.mipmap.people_type_a);
                break;
            default:
                mytype_me.setImageResource(R.mipmap.people_type_default);
                break;
        }

        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(this, "uid"));
        HttpClient.post("/user/profile_user_select", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(ProfileActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONObject profile = new JSONObject(jobj.getString("profile"));
                    JSONObject my_data = new JSONObject(jobj.getString("my_data"));
                    JSONObject you_data = new JSONObject(jobj.getString("people_data"));

                    if (profile.getString("SEX").equals("M")) {
                        profile_sex.setText("남성");
                    } else {
                        profile_sex.setText("여성");
                    }

                    profile_age.setText(Utilities.age_return(profile.getString("BIRTHDAY")));
                    profile_area.setText(profile.getString("SIDO") + " " + profile.getString("GUGUN") + " " + profile.getString("DONG"));

                    mytype_people.setImageResource(R.mipmap.people_type_default);

                    float my_speed = Float.parseFloat(my_data.getString("SPEED"));
                    float my_control = Float.parseFloat(my_data.getString("CONTROL"));

                    float people_speed = Float.parseFloat(you_data.getString("sumdata_speed"));
                    float people_control = Float.parseFloat(you_data.getString("sumdata_control"));

                    people_type_return = you_data.getString("peopleType");

                    switch (you_data.getString("peopleType")) {
                        case "I":
                            mytype_people.setImageResource(R.mipmap.people_type_i);
                            break;
                        case "D":
                            mytype_people.setImageResource(R.mipmap.people_type_d);
                            break;
                        case "E":
                            mytype_people.setImageResource(R.mipmap.people_type_e);
                            break;
                        case "A":
                            mytype_people.setImageResource(R.mipmap.people_type_a);
                            break;
                        default:
                            mytype_people.setImageResource(R.mipmap.people_type_default);
                            break;
                    }

                    String per = String.valueOf(Math.ceil(Utilities.people_match_int(my_speed, people_speed, my_control, people_control)));
                    if (you_data.getString("peopleType").equals("")) {
                        mytype_per.setText("?");
                    } else {
                        mytype_per.setText(per + "%");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /*추가부분*/
    private void userData()
    {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(ProfileActivity.this, "uid"));
        HttpClient.post("/user/member_type", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(ProfileActivity.this, "", "데이터 수신중");
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
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>꼼꼼하기도 하면서 때론 완벽을 추구하는 <b color='#ff8a55'>강한 추진력과 모험심 있는 스타일</b></b><br>로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>사람과의 관계와 소통을 중요시하며 <b color='#ff8a55'>때론 추진력과 도전을 좋아하는 스타일</b></b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED <= 1 && CONTROL <= 5) {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>꼼꼼하면서 <b color='#ff8a55'>밀어부치기도 도전을 좋아하기도 하는 스타일</b></b><br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b color='#ff8a55'>새로운 도전과 모험, 강한 추진력을 가진 스타일</b>로 진단되었습니다."));
                        }
                    }
                    if (SPEED > 0 && CONTROL < 0) {

                        if (SPEED <= 5 && CONTROL >= -1) {
                            if (SPEED <= 1 && CONTROL >= -1) {
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>가끔 밀어부치기도 하고 그러나 상대방을 배려하는 이해심도 가진<b color='#aa64f8'>소통의 달인</b></b><br>으로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>사람을 끄는 힘을 가진<b color='#aa64f8'>관계 형성이 좋은 스타일</b></b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED <= 1 && CONTROL >= -5) {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>상대에 대한 이해심이 높고 믿을 수 있는 <b color='#aa64f8'>소통하고 싶은 스타일</b></b><br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b color='#aa64f8'>인간관계가 중요하고 큰 문제가 없으며 상대의 마음을 진심으로 대하는 스타일</b>로 진단되었습니다."));
                        }
                    }
                    if (SPEED < 0 && CONTROL > 0) {

                        if (SPEED >= -5 && CONTROL <= 1) {
                            if (SPEED >= -1 && CONTROL <= 1) {
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>도전정신을 가진 이해심 깊은<b color='#37afec'>완벽주의자</b></b><br>로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>속 깊이 잘 챙기는<b color='#37afec'>꼼꼼주의자</b></b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED >= -1 && CONTROL <= 5) {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>새로운 것을 마다하지 않고 끝까지 완력하게 꼼꼼히 이루는 스타일<br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b color='#37afec'>꼼꼼한 완벽주의자</b>로 진단되었습니다."));
                        }
                    }
                    if (SPEED < 0 && CONTROL < 0) {

                        if (SPEED >= -5 && CONTROL >= -1) {
                            if (SPEED >= -1 && CONTROL >= -1) {
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>사람과의 관계에서 이해심이 높지만 그래도 나름 계산하여 마음을 보이는 스타일</b><br>로 진단되었습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>꼼꼼히 상대를 알아보고 끝까지 믿어주는 스타일</b><br>로 진단되었습니다."));
                            }
                        } else if (SPEED >= -1 && CONTROL >= -5) {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b>상대를 이해하며 좋은 인간관계를 끝까지 유지하는 스타일</b><br>로 진단되었습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("자기진단 결과<br><b color='#52d935'>상대방에 대한 배려심 좋고 신뢰성 있는 스타일</b>로 진단되었습니다."));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void mytype_re_btn(View v)
    {
        Intent intent = new Intent(ProfileActivity.this, MyQuestionRe_Activity.class);
        startActivityForResult(intent, ACTIVITY_CODE);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("people_gram", "성공");
        dataResult();
    }


    public void btn_back(View v) {
        finish();
    }
    public void closeBtn(View v) {
        finish();
    }
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
