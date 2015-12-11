package kr.co.people_gram.app;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class SubPeopleListSelect_Activity extends AppCompatActivity {

    public static AppCompatActivity subpeoplelistselect_Activity;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    private static final int VIEW_CODE = 100000;

    private ImageButton people_click_btn, people_tip_btn, people_now_btn;

    private String clickType = "NOW";
    private PeopleData pd;


    private String uid, mood, myname;
    public static String mytype;
    private String my_code = "000";
    private int my_speed = 0;
    private float my_sub_speed = 0;
    public static String my_sub_type = "";
    private int my_control = 0;
    private float my_sub_control = 0;


    public static Boolean my_type_check = false;
    public static Boolean people_type_check = false;


    private String people_name, people_mood, people_uid, people_gubun1, people_gubun2;
    public static String people_type;
    private float people_speed = 0;
    private float people_control = 0;

    public static String people_sub_type = "";

    private String people_code = "000";
    private float people_sub_speed = 0;
    private float people_sub_control = 0;

    private TextView detail_myname, detail_youname, tv_tip1, tv_tip2, tv_tip3, gubun1, peopleTextView;

    private LinearLayout popup_mytype, popup_youtype, li_tip1, li_tip2, li_tip3;
    private LinearLayout tip1_line, tip2_line, tip3_line;

    private Switch listview_mytype_switch, listview_youtype_switch;
    private ProgressDialog dialog;
    private boolean payment_result = false;

    private CircularProgressBar c2;

    private LinearLayout tip_view;
    private TextView MY_std, YOU_std;

    private ImageView guide_content;
    private PopupWindow mPopupWindow;

    private void newPopup()
    {
        if(SharedPreferenceUtil.getSharedPreference(this, "people_match").equals("C") == false) {
            final View popupView = getLayoutInflater().inflate(R.layout.activity_guide_activity_step1, null);
            mPopupWindow = new PopupWindow(popupView,
                    RelativeLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            popupView.post(new Runnable() {
                @Override
                public void run() {
                    mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                }
            });


            guide_content = (ImageView) popupView.findViewById(R.id.guide_img);
            guide_content.setImageResource(R.drawable.sub_guide_peoplematch);
            SharedPreferenceUtil.putSharedPreference(this, "people_match", "C");
            LinearLayout step1_close_btn = (LinearLayout) popupView.findViewById(R.id.step1_close_btn);
            step1_close_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_list_select_);

        subpeoplelistselect_Activity = this;

        newPopup();

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

        tip_view = (LinearLayout) findViewById(R.id.tip_view);
        MY_std = (TextView) findViewById(R.id.MY_std);
        YOU_std = (TextView) findViewById(R.id.YOU_std);

        Log.d("people_gram", uid + ":::" + people_uid);

        paymentResult();

        //Log.d("people_gram", "구분타입"+people_gubun1);

        gubun1 = (TextView) findViewById(R.id.gubun1);

        switch (people_gubun1) {
            case "P":
                gubun1.setText("가족 관계");
                break;

            case "F":
                gubun1.setText("친구(지인) 관계");
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


        c2 = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
        peopleTextView = (TextView) findViewById(R.id.peopleTextView);
        double total = Utilities.people_match_int(my_speed, people_speed, my_control, people_control);
        graph(total);


        peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));




        detail_myname = (TextView) findViewById(R.id.detail_myname);
        detail_youname = (TextView) findViewById(R.id.detail_youname);

        popup_mytype = (LinearLayout) findViewById(R.id.popup_mytype);
        popup_youtype = (LinearLayout) findViewById(R.id.popup_youtype);

        li_tip1 = (LinearLayout) findViewById(R.id.li_tip1);
        li_tip2 = (LinearLayout) findViewById(R.id.li_tip2);
        li_tip3 = (LinearLayout) findViewById(R.id.li_tip3);

        tv_tip1 = (TextView) findViewById(R.id.tv_tip1);
        tv_tip2 = (TextView) findViewById(R.id.tv_tip2);
        tv_tip3 = (TextView) findViewById(R.id.tv_tip3);

        li_tip1.setOnClickListener(onBtnClickListener);
        li_tip2.setOnClickListener(onBtnClickListener);
        li_tip3.setOnClickListener(onBtnClickListener);

        tip1_line = (LinearLayout) findViewById(R.id.tip1_line);
        tip2_line = (LinearLayout) findViewById(R.id.tip2_line);
        tip3_line = (LinearLayout) findViewById(R.id.tip3_line);


        listview_mytype_switch = (Switch) findViewById(R.id.listview_mytype_switch);
        listview_youtype_switch = (Switch) findViewById(R.id.listview_youtype_switch);

        listview_mytype_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false) {
                    my_type_check = false;
                    if(listview_youtype_switch.isChecked() == false) {
                        double total = Utilities.people_match_int(my_speed, people_speed, my_control, people_control);
                        graph(total);

                        peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                    } else {

                        double total = Utilities.people_match_int(my_speed, people_sub_speed, my_control, people_sub_control);
                        graph(total);

                        peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));

                    }
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
                    MY_std.setText("나 : 내진단 기준");
                } else {

                    switch (my_sub_type) {
                        case "A":
                            MY_std.setText("나 : 타인 진단 기준");
                            my_type_check = true;
                            if (listview_youtype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_sub_speed, people_speed, my_sub_control, people_control);
                                graph(total);
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                            }
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_a);
                            break;
                        case "I":
                            MY_std.setText("나 : 타인 진단 기준");
                            my_type_check = true;
                            if (listview_youtype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_sub_speed, people_speed, my_sub_control, people_control);
                                graph(total);
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                            }
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_i);
                            break;
                        case "E":
                            MY_std.setText("나 : 타인 진단 기준");
                            my_type_check = true;
                            if (listview_youtype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_sub_speed, people_speed, my_sub_control, people_control);
                                graph(total);
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                            }
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_e);
                            break;
                        case "D":
                            MY_std.setText("나 : 타인 진단 기준");
                            my_type_check = true;
                            if (listview_youtype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_sub_speed, people_speed, my_sub_control, people_control);
                                graph(total);
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                            }
                            popup_mytype.setBackgroundResource(R.mipmap.people_type_d);
                            break;
                        default:
                            MY_std.setText("나 : 내 진단 기준");
                            my_type_check = false;
                            listview_mytype_switch.setChecked(false);

                            switch (people_gubun1)
                            {
                                case "P":
                                    Toast.makeText(SubPeopleListSelect_Activity.this, "가족들에게 나의 진단을 요청해주세요.", Toast.LENGTH_LONG).show();
                                    break;

                                case "F":
                                    Toast.makeText(SubPeopleListSelect_Activity.this, "친구들에게 나의 진단을 요청해주세요.", Toast.LENGTH_LONG).show();
                                    break;
                                case "L":
                                    Toast.makeText(SubPeopleListSelect_Activity.this, "연인에게 나의 진단을 요청해주세요.", Toast.LENGTH_LONG).show();
                                    break;
                                case "C":
                                    Toast.makeText(SubPeopleListSelect_Activity.this, "직장피플에게 나의 진단을 요청해주세요.", Toast.LENGTH_LONG).show();
                                    break;
                                case "S":
                                    Toast.makeText(SubPeopleListSelect_Activity.this, "고객피플들에게 나의 진단을 요청해주세요.", Toast.LENGTH_LONG).show();
                                    break;
                            }



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

                            /*
                            if(my_sub_type.equals("") != false) {

                            }
                            */

                    }


                }
            }
        });

        listview_youtype_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false) {
                    YOU_std.setText("상대방 : 내 진단 기준");
                    people_type_check = false;
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

                    if(listview_mytype_switch.isChecked() == true) {
                        double total = Utilities.people_match_int(my_sub_speed, people_speed, my_sub_control, people_control);
                        graph(total);

                        peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                    } else {
                        double total = Utilities.people_match_int(my_speed, people_speed, my_control, people_control);
                        graph(total);

                        peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                    }

                } else {

                    Log.d("people_gram", my_speed + ":" + people_sub_speed + ":" + my_control + ":" + people_sub_control);
                    Log.d("people_gram", my_sub_type);
                    switch (people_sub_type) {
                        case "A":
                            YOU_std.setText("상대방 : 타인 진단 기준");
                            people_type_check = true;
                            if (listview_mytype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_speed, people_sub_speed, my_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            }


                            popup_youtype.setBackgroundResource(R.mipmap.people_type_a);
                            break;
                        case "I":
                            YOU_std.setText("상대방 : 타인 진단 기준");
                            people_type_check = true;
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_i);
                            if (listview_mytype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_speed, people_sub_speed, my_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            }
                            break;
                        case "E":
                            YOU_std.setText("상대방 : 타인 진단 기준");
                            people_type_check = true;
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_e);
                            if (listview_mytype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_speed, people_sub_speed, my_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            }
                            break;
                        case "D":
                            YOU_std.setText("상대방 : 타인 진단 기준");
                            people_type_check = true;
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_d);
                            if (listview_mytype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_speed, people_sub_speed, my_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            }

                            //SubPeopleFragment_tip fragment_tip = (SubPeopleFragment_tip) getFragmentManager().findFragmentById(R.id.MY_std);


                            //SubPeopleFragment_tip fragB = (SubPeopleFragment_tip) getFragmentManager().findFragmentByTag("FRAGMENT_TAG");

                            break;
                        default:
                            YOU_std.setText("상대방 : 내 진단 기준");
                            people_type_check = false;
                            /*
                            popup_youtype.setBackgroundResource(R.mipmap.people_type_default);
                            if (listview_mytype_switch.isChecked() == false) {
                                double total = Utilities.people_match_int(my_speed, people_sub_speed, my_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            } else {
                                double total = Utilities.people_match_int(my_sub_speed, people_sub_speed, my_sub_control, people_sub_control);
                                graph(total);
                                peopleTextView.setText(Utilities.peopleContectView(SubPeopleListSelect_Activity.this, people_gubun1, total));
                            }
                            */
                            listview_youtype_switch.setChecked(false);
                            Toast.makeText(SubPeopleListSelect_Activity.this, "피플들의 진단이 부족합니다.", Toast.LENGTH_LONG).show();
                            break;
                    }
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

                    tip_view.setVisibility(View.GONE);

                    tv_tip1.setTextColor(Color.rgb(254, 23, 5));
                    tv_tip2.setTextColor(Color.rgb(0, 0, 0));
                    tv_tip3.setTextColor(Color.rgb(0, 0, 0));
                    tip1_line.setBackgroundColor(Color.rgb(254, 23, 5));
                    tip2_line.setBackgroundColor(Color.rgb(255, 255, 255));
                    tip3_line.setBackgroundColor(Color.rgb(255,255,255));

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
                        /*
                        li_tip1.setBackgroundColor(Color.rgb(241, 241, 241));
                        tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                        li_tip2.setBackgroundColor(Color.rgb(77, 77, 77));
                        tv_tip2.setTextColor(Color.rgb(255, 255, 255));
                        li_tip3.setBackgroundColor(Color.rgb(241, 241, 241));
                        tv_tip3.setTextColor(Color.rgb(0, 0, 0));
                        */

                        tv_tip1.setTextColor(Color.rgb(0,0,0));
                        tv_tip2.setTextColor(Color.rgb(254, 23, 5));
                        tv_tip3.setTextColor(Color.rgb(0, 0, 0));
                        tip1_line.setBackgroundColor(Color.rgb(255, 255, 255));
                        tip2_line.setBackgroundColor(Color.rgb(254, 23, 5));
                        tip3_line.setBackgroundColor(Color.rgb(255,255,255));


                        tip_view.setVisibility(View.VISIBLE);

                        fragmentManager = getSupportFragmentManager();
                        ft = fragmentManager.beginTransaction();

                        SubPeopleFragment_tip sub_tip_fragment = new SubPeopleFragment_tip();
                        ft.replace(R.id.fragment_sub_people, sub_tip_fragment);
                        ft.commit();

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


                                        tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                                        tv_tip2.setTextColor(Color.rgb(254, 23, 5));
                                        tv_tip3.setTextColor(Color.rgb(0, 0, 0));
                                        tip1_line.setBackgroundColor(Color.rgb(255, 255, 255));
                                        tip2_line.setBackgroundColor(Color.rgb(254, 23, 5));
                                        tip3_line.setBackgroundColor(Color.rgb(255, 255, 255));


                                        tip_view.setVisibility(View.VISIBLE);


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
                                        intent.putExtra("viewType", "you");
                                        startActivityForResult(intent, 1);
                                        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }

                    break;
                case R.id.li_tip3:
                    tip_view.setVisibility(View.GONE);

                    if(payment_result == true) {
                        /*
                        li_tip1.setBackgroundColor(Color.rgb(241, 241, 241));
                        tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                        li_tip2.setBackgroundColor(Color.rgb(241, 241, 241));
                        tv_tip2.setTextColor(Color.rgb(0, 0, 0));
                        li_tip3.setBackgroundColor(Color.rgb(77, 77, 77));
                        tv_tip3.setTextColor(Color.rgb(255, 255, 255));
                        */

                        tv_tip1.setTextColor(Color.rgb(0,0,0));
                        tv_tip2.setTextColor(Color.rgb(0, 0, 0));
                        tv_tip3.setTextColor(Color.rgb(254, 23, 5));
                        tip1_line.setBackgroundColor(Color.rgb(255, 255, 255));
                        tip2_line.setBackgroundColor(Color.rgb(255,255,255));
                        tip3_line.setBackgroundColor(Color.rgb(254, 23, 5));




                        fragmentManager = getSupportFragmentManager();
                        ft = fragmentManager.beginTransaction();

                        SubPeopleFragment_peopletip sub_peopletip_fragment = new SubPeopleFragment_peopletip();
                        ft.replace(R.id.fragment_sub_people, sub_peopletip_fragment);
                        ft.commit();

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
                                        tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                                        tv_tip2.setTextColor(Color.rgb(0, 0, 0));
                                        tv_tip3.setTextColor(Color.rgb(254, 23, 5));
                                        tip1_line.setBackgroundColor(Color.rgb(255, 255, 255));
                                        tip2_line.setBackgroundColor(Color.rgb(255,255,255));
                                        tip3_line.setBackgroundColor(Color.rgb(254, 23, 5));


                                        fragmentManager = getSupportFragmentManager();
                                        ft = fragmentManager.beginTransaction();

                                        SubPeopleFragment_peopletip sub_peopletip_fragment = new SubPeopleFragment_peopletip();
                                        ft.replace(R.id.fragment_sub_people, sub_peopletip_fragment);
                                        ft.commit();
                                    } else {
                                        Intent intent = new Intent(SubPeopleListSelect_Activity.this, GramPopupActivity.class);
                                        intent.putExtra("use_point", jobj.getString("point"));
                                        intent.putExtra("people_uid", people_uid);
                                        intent.putExtra("people_username", people_name);
                                        intent.putExtra("gubun1", people_gubun1);
                                        intent.putExtra("gubun2", people_gubun2);
                                        intent.putExtra("viewType", "you");
                                        startActivityForResult(intent, 1);
                                        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }

                    break;
            }
        }
    };

    private void graph(double total)
    {
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
    }



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
                //dialog = ProgressDialog.show(SubPeopleListSelect_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                //dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONObject my_obj = new JSONObject(jobj.getString("my_data"));
                    JSONObject people_obj = new JSONObject(jobj.getString("you_data"));
                    if(my_obj.getString("code").equals("000")) {
                        my_sub_control = Float.valueOf(my_obj.getString("sumdata_control"));
                        my_sub_speed = Float.valueOf(my_obj.getString("sumdata_speed"));
                        my_sub_type = my_obj.getString("peopleType");

                    } else {
                        my_sub_speed = 0;
                        my_sub_control = 0;
                        my_sub_type = "";
                    }

                    if(people_obj.getString("code").equals("000")) {
                        people_sub_control = Float.valueOf(people_obj.getString("sumdata_control"));
                        people_sub_speed = Float.valueOf(people_obj.getString("sumdata_speed"));
                        people_sub_type = people_obj.getString("peopleType");

                    } else {
                        people_sub_control = 0;
                        people_sub_speed = 0;
                        people_sub_type = "";
                    }

                    Log.d("people_gram", my_sub_control + ":::" + my_sub_speed + ":::" + people_sub_speed + ":::" + people_sub_control + ":::"+ my_sub_type + ":::" + people_sub_type);

                    if(jobj.getString("view_cnt").equals("0")) {
                        payment_result = false;
                    } else {
                        payment_result = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*
                if(response.equals("0")) {
                    Log.d("people_gram", response+"=실패");
                    payment_result = false;
                } else {
                    Log.d("people_gram", response+"=성공");
                    payment_result = true;
                }
                */
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

                    tv_tip1.setTextColor(Color.rgb(0, 0, 0));
                    tv_tip2.setTextColor(Color.rgb(254, 23, 5));
                    tv_tip3.setTextColor(Color.rgb(0, 0, 0));
                    tip1_line.setBackgroundColor(Color.rgb(255, 255, 255));
                    tip2_line.setBackgroundColor(Color.rgb(254, 23, 5));
                    tip3_line.setBackgroundColor(Color.rgb(255, 255, 255));

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
