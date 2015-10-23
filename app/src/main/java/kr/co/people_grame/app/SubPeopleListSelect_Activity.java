package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class SubPeopleListSelect_Activity extends AppCompatActivity {

    public static AppCompatActivity subpeoplelistselect_Activity;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    private static final int VIEW_CODE = 100000;

    private ImageButton people_click_btn, people_tip_btn, people_now_btn;

    private String clickType = "NOW";
    private PeopleData pd;


    private String uid, mood, myname, mytype;
    private int my_speed = 0;
    private int my_control = 0;


    private String people_name, people_type, people_mood, people_uid, people_gubun1, people_gubun2;
    private int people_speed = 0;
    private int people_control = 0;

    private TextView detail_myname, detail_youname, tv_tip1, tv_tip2, gubun1;

    private LinearLayout popup_mytype, popup_youtype, li_tip1, li_tip2;

    private Switch listview_mytype_switch, listview_youtype_switch;
    private ProgressDialog dialog;
    private boolean payment_result = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_list_select_);

        subpeoplelistselect_Activity = this;





        pd = new PeopleData();
        uid = SharedPreferenceUtil.getSharedPreference(this, "uid");
        myname = SharedPreferenceUtil.getSharedPreference(this, "username");
        mytype = SharedPreferenceUtil.getSharedPreference(this, "mytype");
        my_control = Integer.parseInt(SharedPreferenceUtil.getSharedPreference(this, "my_control"));
        my_speed = Integer.parseInt(SharedPreferenceUtil.getSharedPreference(this, "my_speed"));


        people_uid = pd.get_people_uid();
        people_name = pd.get_people_username();
        people_type = pd.get_people_type();
        people_mood = pd.get_people_mood();
        people_gubun1 = pd.get_people_gubun1();
        people_gubun2 = pd.get_people_gubun2();
        people_speed = pd.get_people_speed();
        people_control = pd.get_people_control();


        paymentResult();




        gubun1 = (TextView) findViewById(R.id.gubun1);

        switch (people_gubun1) {
            case "P":
                gubun1.setText("가족 관계");
                break;

            case "F":
                gubun1.setText("친구 관계");
                break;

            case "L":
                gubun1.setText("연인 관계");
                break;

            case "C":
                gubun1.setText("직장 관계");
                break;

            case "S":
                gubun1.setText("고객 관계");
                break;

        }



        double total = Utilities.people_match_int(my_speed, people_speed, my_control, people_control);

        //Log.d("people_gram", "나의점수=" + my_speed + ":::" + my_control + "///상대방=" + people_speed + ":::" + people_control + "///전체=" + total);

        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
        c2.setSubTitle("%");
        c2.animateProgressTo(0, (int) total, new CircularProgressBar.ProgressAnimationListener() {

            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationProgress(int progress) {
                c2.setTitle(progress + "");
            }

            @Override
            public void onAnimationFinish() {
                //c2.setSubTitle("done");
            }
        });



        detail_myname = (TextView) findViewById(R.id.detail_myname);
        detail_youname = (TextView) findViewById(R.id.detail_youname);

        popup_mytype = (LinearLayout) findViewById(R.id.popup_mytype);
        popup_youtype = (LinearLayout) findViewById(R.id.popup_youtype);

        li_tip1 = (LinearLayout) findViewById(R.id.li_tip1);
        li_tip2 = (LinearLayout) findViewById(R.id.li_tip2);

        tv_tip1 = (TextView) findViewById(R.id.tv_tip1);
        tv_tip2 = (TextView) findViewById(R.id.tv_tip2);

        li_tip1.setOnClickListener(onBtnClickListener);
        li_tip2.setOnClickListener(onBtnClickListener);

        listview_mytype_switch = (Switch) findViewById(R.id.listview_mytype_switch);
        listview_youtype_switch = (Switch) findViewById(R.id.listview_youtype_switch);

        listview_mytype_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false) {
                    switch (mytype) {
                        case "A":
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_a);
                            break;
                        case "I":
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_i);
                            break;
                        case "E":
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_e);
                            break;
                        case "D":
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_d);
                            break;
                        default:
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_default);
                            break;
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(SubPeopleListSelect_Activity.this, "uid"));
                    HttpClient.post("/user/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(SubPeopleListSelect_Activity.this, "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {

                            switch (response) {
                                case "A":
                                    popup_mytype.setBackgroundResource(R.mipmap.people_type_a);
                                    break;

                                case "I":
                                    popup_mytype.setBackgroundResource(R.mipmap.people_type_i);
                                    break;

                                case "D":
                                    popup_mytype.setBackgroundResource(R.mipmap.people_type_d);
                                    break;

                                case "E":
                                    popup_mytype.setBackgroundResource(R.mipmap.people_type_e);
                                    break;

                                default:
                                    Toast.makeText(SubPeopleListSelect_Activity.this, "피플이 귀하를 진단하지 않았습니다.\n피플들에게 요청하세요.", Toast.LENGTH_SHORT).show();
                                    popup_mytype.setBackgroundResource(R.mipmap.people_type_default);
                                    break;
                            }
                        }
                    });
                }
            }
        });

        listview_youtype_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false) {
                    switch (people_type) {
                        case "A":
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_a);
                            break;
                        case "I":
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_i);
                            break;
                        case "E":
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_e);
                            break;
                        case "D":
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_d);
                            break;
                        default:
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_default);
                            break;
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(SubPeopleListSelect_Activity.this, "uid"));
                    params.put("people_uid", people_uid);
                    params.put("gubun1", people_gubun1);
                    HttpClient.post("/user/profile_you_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(SubPeopleListSelect_Activity.this, "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {
                            if(response.equals("999")) {
                                switch (people_gubun1) {
                                    case "P":
                                        Toast.makeText(SubPeopleListSelect_Activity.this, "본인 포함 최소 2명 이상 진단된 경우에 볼 수 있습니다.", Toast.LENGTH_LONG).show();
                                        listview_youtype_switch.setChecked(false);
                                        break;
                                    case "F":
                                        Toast.makeText(SubPeopleListSelect_Activity.this, "본인 포함 최소 3명 이상 진단된 경우에 볼 수 있습니다.", Toast.LENGTH_LONG).show();
                                        listview_youtype_switch.setChecked(false);
                                        break;
                                    case "L":
                                        break;
                                    case "C":
                                        Toast.makeText(SubPeopleListSelect_Activity.this, "본인 제외 최소 3명 이상 진단된 경우에 볼 수 있습니다.", Toast.LENGTH_LONG).show();
                                        listview_youtype_switch.setChecked(false);
                                        break;
                                    case "S":
                                        break;
                                }
                            } else {
                                switch (response) {
                                    case "A":
                                        popup_youtype.setBackgroundResource(R.mipmap.people_type_a);
                                        break;
                                    case "I":
                                        popup_youtype.setBackgroundResource(R.mipmap.people_type_i);
                                        break;
                                    case "E":
                                        popup_youtype.setBackgroundResource(R.mipmap.people_type_e);
                                        break;
                                    case "D":
                                        popup_youtype.setBackgroundResource(R.mipmap.people_type_d);
                                        break;
                                    default:
                                        popup_youtype.setBackgroundResource(R.mipmap.people_type_default);
                                        break;
                                }
                            }

                            //Log.d("people_gram", "데이터="+response);
                        }
                    });
                }
            }
        });

        switch (mytype) {
            case "A":
                popup_mytype.setBackgroundResource(R.mipmap.people_type_a);
                break;
            case "I":
                popup_mytype.setBackgroundResource(R.mipmap.people_type_i);
                break;
            case "E":
                popup_mytype.setBackgroundResource(R.mipmap.people_type_e);
                break;
            case "D":
                popup_mytype.setBackgroundResource(R.mipmap.people_type_d);
                break;
            default:
                popup_mytype.setBackgroundResource(R.mipmap.people_type_default);
                break;
        }

        switch (people_type) {
            case "A":
                popup_youtype.setBackgroundResource(R.mipmap.people_type_a);
                break;
            case "I":
                popup_youtype.setBackgroundResource(R.mipmap.people_type_i);
                break;
            case "E":
                popup_youtype.setBackgroundResource(R.mipmap.people_type_e);
                break;
            case "D":
                popup_youtype.setBackgroundResource(R.mipmap.people_type_d);
                break;
            default:
                popup_youtype.setBackgroundResource(R.mipmap.people_type_default);
                break;
        }

        detail_myname.setText(myname);
        detail_youname.setText(people_name);



        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        SubPeopleSelect_MainFragment sub_m_fragment = new SubPeopleSelect_MainFragment();
        ft.replace(R.id.fragment_sub_people, sub_m_fragment);
        ft.commit();


    }

    private View.OnClickListener onBtnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.li_tip1:
                    li_tip1.setBackgroundColor(Color.rgb(50,53,77));
                    tv_tip1.setTextColor(Color.rgb(255, 255, 255));
                    li_tip2.setBackgroundColor(Color.rgb(241, 241, 241));
                    tv_tip2.setTextColor(Color.rgb(0, 0, 0));

                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();

                    SubPeopleSelect_MainFragment sub_m_fragment = new SubPeopleSelect_MainFragment();
                    ft.replace(R.id.fragment_sub_people, sub_m_fragment);
                    ft.commit();


                    break;
                case R.id.li_tip2:
                    /*
                    li_tip1.setBackgroundColor(Color.rgb(241, 241, 241));
                    tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                    li_tip2.setBackgroundColor(Color.rgb(50, 53, 77));
                    tv_tip2.setTextColor(Color.rgb(255, 255, 255));


                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();

                    SubPeopleFragment_tip sub_tip_fragment = new SubPeopleFragment_tip();
                    ft.replace(R.id.fragment_sub_people, sub_tip_fragment);
                    ft.commit();
                    break;
                    */

                    if(payment_result == true) {
                        li_tip1.setBackgroundColor(Color.rgb(241, 241, 241));
                        tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                        li_tip2.setBackgroundColor(Color.rgb(50, 53, 77));
                        tv_tip2.setTextColor(Color.rgb(255, 255, 255));


                        fragmentManager = getSupportFragmentManager();
                        ft = fragmentManager.beginTransaction();

                        SubPeopleFragment_tip sub_tip_fragment = new SubPeopleFragment_tip();
                        ft.replace(R.id.fragment_sub_people, sub_tip_fragment);
                        ft.commit();
                        break;
                    } else {
                        RequestParams params = new RequestParams();
                        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubPeopleListSelect_Activity.this, "uid"));
                        params.put("people_uid", people_uid);
                        params.put("gubun1", people_gubun1);
                        params.put("gubun2", people_gubun2);
                        HttpClient.post("/people/peopleMatchView", params, new AsyncHttpResponseHandler() {
                            public void onStart() {
                                dialog = ProgressDialog.show(SubPeopleListSelect_Activity.this, "", "데이터 수신중");
                            }

                            public void onFinish() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onSuccess(String response) {

                                try {
                                    JSONObject jobj = new JSONObject(response);
                                    Log.d("people_gram", jobj.getString("code"));
                                    if (jobj.getString("code").equals("999")) {
                                        li_tip1.setBackgroundColor(Color.rgb(241, 241, 241));
                                        tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                                        li_tip2.setBackgroundColor(Color.rgb(50, 53, 77));
                                        tv_tip2.setTextColor(Color.rgb(255, 255, 255));


                                        fragmentManager = getSupportFragmentManager();
                                        ft = fragmentManager.beginTransaction();

                                        SubPeopleFragment_tip sub_tip_fragment = new SubPeopleFragment_tip();
                                        ft.replace(R.id.fragment_sub_people, sub_tip_fragment);
                                        ft.commit();
                                    } else {
                                        Intent intent = new Intent(SubPeopleListSelect_Activity.this, GramPopupActivity.class);
                                        intent.putExtra("use_point", jobj.getString("point"));
                                        intent.putExtra("people_uid", people_uid);
                                        intent.putExtra("people_username", people_name);
                                        intent.putExtra("gubun1", people_gubun1);
                                        intent.putExtra("gubun2", people_gubun2);
                                        startActivityForResult(intent, 1);
                                        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
            }
        }
    };





    /*
    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentManager = getSupportFragmentManager();
            ft = fragmentManager.beginTransaction();


            switch (v.getId()) {

                case R.id.people_tip_btn:
                    people_click_btn.setImageResource(R.drawable.people_click_btn_off);
                    people_tip_btn.setImageResource(R.drawable.people_tip_btn_on);
                    people_now_btn.setImageResource(R.drawable.people_now_btn_off);


                    SubPeopleFragment_tip sub_m_fragment = new SubPeopleFragment_tip();
                    ft.replace(R.id.fragment_sub_people, sub_m_fragment);
                    ft.commit();

                    break;
            }
        }
    };
    */


    private void paymentResult()
    {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubPeopleListSelect_Activity.this, "uid"));
        params.put("people_uid", people_uid);
        params.put("gubun1", people_gubun1);
        params.put("gubun2", people_gubun2);

        HttpClient.post("/people/peopleMatchCheck", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(SubPeopleListSelect_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                if(response.equals("0")) {
                    Log.d("people_gram", response+"=실패");
                    payment_result = false;
                } else {
                    Log.d("people_gram", response+"=성공");
                    payment_result = true;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                String yn = data.getStringExtra("data_OK");
                if(yn.equals("OK") == true) {
                    li_tip1.setBackgroundColor(Color.rgb(241, 241, 241));
                    tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                    li_tip2.setBackgroundColor(Color.rgb(50, 53, 77));
                    tv_tip2.setTextColor(Color.rgb(255, 255, 255));

                    payment_result = true;


                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();

                    SubPeopleFragment_tip sub_tip_fragment = new SubPeopleFragment_tip();
                    ft.replace(R.id.fragment_sub_people, sub_tip_fragment);
                    ft.commit();
                } else {
                    payment_result = false;
                }
            }

        }

    }

    public void prev_btn(View v) {
        finish();
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

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

}
