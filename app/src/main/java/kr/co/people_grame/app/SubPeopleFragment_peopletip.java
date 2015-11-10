package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 광희 on 2015-09-15.
 */

public class SubPeopleFragment_peopletip extends Fragment {
    private LinearLayout subpeople_menu1, subpeople_menu2, subpeople_menu3, subpeople_menu4, subpeople_menu5;
    private PeopleData pd;

    private String people_uid, people_name, people_type, people_gubun1;
    private ProgressDialog dialog;
    private String gubun1 = "";
    private int P_cnt = 0;
    private int F_cnt = 0;
    private int L_cnt = 0;
    private int C_cnt = 0;
    private int S_cnt = 0;

    private String P_code = "999";
    private String F_code = "999";
    private String L_code = "999";
    private String C_code = "999";
    private String S_code = "999";

    private String P_my_speed = "";
    private String P_my_control = "";
    private String P_people_type = "";
    private String P_people_control = "";
    private String P_people_speed = "";


    private String F_my_speed = "";
    private String F_my_control = "";
    private String F_people_type = "";
    private String F_people_control = "";
    private String F_people_speed = "";

    private String L_my_speed = "";
    private String L_my_control = "";
    private String L_people_type = "";
    private String L_people_control = "";
    private String L_people_speed = "";

    private String C_my_speed = "";
    private String C_my_control = "";
    private String C_people_type = "";
    private String C_people_control = "";
    private String C_people_speed = "";

    private String S_my_speed = "";
    private String S_my_control = "";
    private String S_people_type = "";
    private String S_people_control = "";
    private String S_people_speed = "";

    private int P_point = 0;
    private int F_point = 0;
    private int L_point = 0;
    private int C_point = 0;
    private int S_point = 0;

    private String MY_DATA1 = "0";
    private String MY_DATA2 = "0";
    private String MY_DATA3 = "0";
    private String MY_DATA4 = "0";
    private String MY_DATA5 = "0";
    private String MY_DATA6 = "0";
    private String MY_DATA7 = "0";
    private String MY_DATA8 = "0";
    private String MY_DATA9 = "0";
    private String MY_DATA10 = "0";

    private String MY_SPEED = "0";
    private String MY_CONTROL = "0";

    private String P_DATA_TOTAL = "0";
    private String P_DATA1 = "0";
    private String P_DATA2 = "0";
    private String P_DATA3 = "0";
    private String P_DATA4 = "0";
    private String P_DATA5 = "0";
    private String P_DATA6 = "0";
    private String P_DATA7 = "0";
    private String P_DATA8 = "0";
    private String P_DATA9 = "0";
    private String P_DATA10 = "0";

    private String F_DATA_TOTAL = "0";
    private String F_DATA1 = "0";
    private String F_DATA2 = "0";
    private String F_DATA3 = "0";
    private String F_DATA4 = "0";
    private String F_DATA5 = "0";
    private String F_DATA6 = "0";
    private String F_DATA7 = "0";
    private String F_DATA8 = "0";
    private String F_DATA9 = "0";
    private String F_DATA10 = "0";

    private String C_DATA_TOTAL = "0";
    private String C_DATA1 = "0";
    private String C_DATA2 = "0";
    private String C_DATA3 = "0";
    private String C_DATA4 = "0";
    private String C_DATA5 = "0";
    private String C_DATA6 = "0";
    private String C_DATA7 = "0";
    private String C_DATA8 = "0";
    private String C_DATA9 = "0";
    private String C_DATA10 = "0";

    private String L_DATA_TOTAL = "0";
    private String L_DATA1 = "0";
    private String L_DATA2 = "0";
    private String L_DATA3 = "0";
    private String L_DATA4 = "0";
    private String L_DATA5 = "0";
    private String L_DATA6 = "0";
    private String L_DATA7 = "0";
    private String L_DATA8 = "0";
    private String L_DATA9 = "0";
    private String L_DATA10 = "0";


    private String S_DATA_TOTAL = "0";
    private String S_DATA1 = "0";
    private String S_DATA2 = "0";
    private String S_DATA3 = "0";
    private String S_DATA4 = "0";
    private String S_DATA5 = "0";
    private String S_DATA6 = "0";
    private String S_DATA7 = "0";
    private String S_DATA8 = "0";
    private String S_DATA9 = "0";
    private String S_DATA10 = "0";

    private String my_type = "";


    private ImageView peopletype_icon1, peopletype_icon2, peopletype_icon3, peopletype_icon4, peopletype_icon5;
    private TextView people1_cnt, people2_cnt, people3_cnt, people4_cnt, people5_cnt;
    private TextView you_type1,you_type2,you_type3,you_type4,you_type5;

    private String my_speed, my_control;

    public SubPeopleFragment_peopletip() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subpeopletype_fragment, container, false);


        my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
        pd = new PeopleData();
        people_uid = pd.get_people_uid();
        people_type = pd.get_people_type();
        people_gubun1 = pd.get_people_gubun1();
        people_name = pd.get_people_username();


        subpeople_menu1 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu1);
        subpeople_menu2 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu2);
        subpeople_menu3 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu3);
        subpeople_menu4 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu4);
        subpeople_menu5 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu5);

        you_type1 = (TextView) rootView.findViewById(R.id.you_type1);
        you_type2 = (TextView) rootView.findViewById(R.id.you_type2);
        you_type3 = (TextView) rootView.findViewById(R.id.you_type3);
        you_type4 = (TextView) rootView.findViewById(R.id.you_type4);
        you_type5 = (TextView) rootView.findViewById(R.id.you_type5);

        you_type1.setText(Html.fromHtml("가족들이 생각하는 <b color='#32354d;'>"+people_name+"</b>님의타입"));
        you_type2.setText(Html.fromHtml("친구들이 생각하는 <b color='#32354d;'>"+people_name+"</b>님의타입"));
        you_type3.setText(Html.fromHtml("연인이 생각하는 <b color='#32354d;'>"+people_name+"</b>님의타입"));
        you_type4.setText(Html.fromHtml("직장에서 생각하는 <b color='#32354d;'>"+people_name+"</b>님의타입"));
        you_type5.setText(Html.fromHtml("고객들이 생각하는 <b color='#32354d;'>"+people_name+"</b>님의타입"));


        subpeople_menu1.setOnTouchListener(onBtnTouchListener);
        subpeople_menu1.setOnClickListener(onBtnClickListener);

        subpeople_menu2.setOnTouchListener(onBtnTouchListener);
        subpeople_menu2.setOnClickListener(onBtnClickListener);

        subpeople_menu3.setOnTouchListener(onBtnTouchListener);
        subpeople_menu3.setOnClickListener(onBtnClickListener);

        subpeople_menu4.setOnTouchListener(onBtnTouchListener);
        subpeople_menu4.setOnClickListener(onBtnClickListener);

        subpeople_menu5.setOnTouchListener(onBtnTouchListener);
        subpeople_menu5.setOnClickListener(onBtnClickListener);


        peopletype_icon1 = (ImageView) rootView.findViewById(R.id.peopletype_icon1);
        peopletype_icon2 = (ImageView) rootView.findViewById(R.id.peopletype_icon2);
        peopletype_icon3 = (ImageView) rootView.findViewById(R.id.peopletype_icon3);
        peopletype_icon4 = (ImageView) rootView.findViewById(R.id.peopletype_icon4);
        peopletype_icon5 = (ImageView) rootView.findViewById(R.id.peopletype_icon5);

        people1_cnt = (TextView) rootView.findViewById(R.id.people1_cnt);
        people2_cnt = (TextView) rootView.findViewById(R.id.people2_cnt);
        people3_cnt = (TextView) rootView.findViewById(R.id.people3_cnt);
        people4_cnt = (TextView) rootView.findViewById(R.id.people4_cnt);
        people5_cnt = (TextView) rootView.findViewById(R.id.people5_cnt);




        dataCheck();

        return rootView;
    }

    private void dataCheck()
    {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
        params.put("people_uid", people_uid);
        params.put("people_name", people_name);
        params.put("pepple_type", people_type);

        HttpClient.post("/my_type/youTypeSelect", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                try {
                    Log.d("people_gram", response);

                    JSONObject jobj = new JSONObject(response);
                    JSONObject jobj_my = new JSONObject(jobj.getString("my_type"));
                    JSONObject jobj_p = new JSONObject(jobj.getString("P_cnt"));
                    JSONObject jobj_f = new JSONObject(jobj.getString("F_cnt"));
                    JSONObject jobj_l = new JSONObject(jobj.getString("L_cnt"));
                    JSONObject jobj_c = new JSONObject(jobj.getString("C_cnt"));
                    JSONObject jobj_s = new JSONObject(jobj.getString("S_cnt"));


                    JSONObject p_data = new JSONObject(jobj_p.getString("data"));
                    JSONObject f_data = new JSONObject(jobj_f.getString("data"));
                    JSONObject l_data = new JSONObject(jobj_l.getString("data"));
                    JSONObject c_data = new JSONObject(jobj_c.getString("data"));
                    JSONObject s_data = new JSONObject(jobj_s.getString("data"));



                    MY_DATA1 = jobj_my.getString("MY_DATA1");
                    MY_DATA2 = jobj_my.getString("MY_DATA2");
                    MY_DATA3 = jobj_my.getString("MY_DATA3");
                    MY_DATA4 = jobj_my.getString("MY_DATA4");
                    MY_DATA5 = jobj_my.getString("MY_DATA5");
                    MY_DATA6 = jobj_my.getString("MY_DATA6");
                    MY_DATA7 = jobj_my.getString("MY_DATA7");
                    MY_DATA8 = jobj_my.getString("MY_DATA8");
                    MY_DATA9 = jobj_my.getString("MY_DATA9");
                    MY_DATA10 = jobj_my.getString("MY_DATA10");

                    MY_SPEED = jobj_my.getString("SPEED");
                    MY_CONTROL = jobj_my.getString("CONTROL");

                    P_code = p_data.getString("code");
                    P_cnt = Integer.parseInt(p_data.getString("people_total"));

                    people1_cnt.setText(String.valueOf(P_cnt));

                    P_my_speed = p_data.getString("my_speed");
                    P_my_control = p_data.getString("my_control");
                    P_people_type = p_data.getString("people_type");
                    P_people_control = p_data.getString("people_control");
                    P_people_speed = p_data.getString("people_speed");


                    P_DATA1 = p_data.getString("DATA1");
                    P_DATA2 = p_data.getString("DATA2");
                    P_DATA3 = p_data.getString("DATA3");
                    P_DATA4 = p_data.getString("DATA4");
                    P_DATA5 = p_data.getString("DATA5");
                    P_DATA6 = p_data.getString("DATA6");
                    P_DATA7 = p_data.getString("DATA7");
                    P_DATA8 = p_data.getString("DATA8");
                    P_DATA9 = p_data.getString("DATA9");
                    P_DATA10 = p_data.getString("DATA10");
                    P_DATA_TOTAL = p_data.getString("people_total");



                    if(P_code.equals("000") == false) {
                        peopletype_icon1.setImageResource(R.drawable.item_no_key);
                    }


                    F_code = f_data.getString("code");
                    F_cnt = Integer.parseInt(f_data.getString("people_total"));


                    F_DATA1 = f_data.getString("DATA1");
                    F_DATA2 = f_data.getString("DATA2");
                    F_DATA3 = f_data.getString("DATA3");
                    F_DATA4 = f_data.getString("DATA4");
                    F_DATA5 = f_data.getString("DATA5");
                    F_DATA6 = f_data.getString("DATA6");
                    F_DATA7 = f_data.getString("DATA7");
                    F_DATA8 = f_data.getString("DATA8");
                    F_DATA9 = f_data.getString("DATA9");
                    F_DATA10 = f_data.getString("DATA10");
                    F_DATA_TOTAL = f_data.getString("people_total");

                    people2_cnt.setText(String.valueOf(F_cnt));

                    F_my_speed = f_data.getString("my_speed");
                    F_my_control = f_data.getString("my_control");
                    F_people_type = f_data.getString("people_type");
                    F_people_control = f_data.getString("people_control");
                    F_people_speed = f_data.getString("people_speed");

                    if(F_code.equals("000") == false) {
                        peopletype_icon2.setImageResource(R.drawable.item_no_key);
                    }


                    L_code = l_data.getString("code");
                    L_cnt = Integer.parseInt(l_data.getString("people_total"));

                    people3_cnt.setText(String.valueOf(L_cnt));

                    L_my_speed = l_data.getString("my_speed");
                    L_my_control = l_data.getString("my_control");
                    L_people_type = l_data.getString("people_type");
                    L_people_control = l_data.getString("people_control");
                    L_people_speed = l_data.getString("people_speed");


                    L_DATA1 = l_data.getString("DATA1");
                    L_DATA2 = l_data.getString("DATA2");
                    L_DATA3 = l_data.getString("DATA3");
                    L_DATA4 = l_data.getString("DATA4");
                    L_DATA5 = l_data.getString("DATA5");
                    L_DATA6 = l_data.getString("DATA6");
                    L_DATA7 = l_data.getString("DATA7");
                    L_DATA8 = l_data.getString("DATA8");
                    L_DATA9 = l_data.getString("DATA9");
                    L_DATA10 = l_data.getString("DATA10");
                    L_DATA_TOTAL = l_data.getString("people_total");

                    if(L_code.equals("000") == false) {
                        peopletype_icon3.setImageResource(R.drawable.item_no_key);
                    }



                    C_code = c_data.getString("code");
                    C_cnt = Integer.parseInt(c_data.getString("people_total"));





                    people4_cnt.setText(String.valueOf(C_cnt));

                    C_my_speed = c_data.getString("my_speed");
                    C_my_control = c_data.getString("my_control");
                    C_people_type = c_data.getString("people_type");
                    C_people_control = c_data.getString("people_control");
                    C_people_speed = c_data.getString("people_speed");

                    C_DATA1 = c_data.getString("DATA1");
                    C_DATA2 = c_data.getString("DATA2");
                    C_DATA3 = c_data.getString("DATA3");
                    C_DATA4 = c_data.getString("DATA4");
                    C_DATA5 = c_data.getString("DATA5");
                    C_DATA6 = c_data.getString("DATA6");
                    C_DATA7 = c_data.getString("DATA7");
                    C_DATA8 = c_data.getString("DATA8");
                    C_DATA9 = c_data.getString("DATA9");
                    C_DATA10 = c_data.getString("DATA10");
                    C_DATA_TOTAL = c_data.getString("people_total");

                    if(C_code.equals("000") == false) {
                        peopletype_icon4.setImageResource(R.drawable.item_no_key);
                    }


                    if(S_code.equals("000") == false) {
                        peopletype_icon5.setImageResource(R.drawable.item_no_key);
                    }

                    S_code = s_data.getString("code");
                    S_cnt = Integer.parseInt(s_data.getString("people_total"));

                    people5_cnt.setText(String.valueOf(S_cnt));

                    S_my_speed = s_data.getString("my_speed");
                    S_my_control = s_data.getString("my_control");
                    S_people_type = s_data.getString("people_type");
                    S_people_control = s_data.getString("people_control");
                    S_people_speed = s_data.getString("people_speed");


                    S_DATA1 = s_data.getString("DATA1");
                    S_DATA2 = s_data.getString("DATA2");
                    S_DATA3 = s_data.getString("DATA3");
                    S_DATA4 = s_data.getString("DATA4");
                    S_DATA5 = s_data.getString("DATA5");
                    S_DATA6 = s_data.getString("DATA6");
                    S_DATA7 = s_data.getString("DATA7");
                    S_DATA8 = s_data.getString("DATA8");
                    S_DATA9 = s_data.getString("DATA9");
                    S_DATA10 = s_data.getString("DATA10");
                    S_DATA_TOTAL = s_data.getString("people_total");





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };


    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String point_str = SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "point");
            int point = Integer.parseInt(point_str);

            point = 0;


            /*
            intent = new Intent(getActivity().getBaseContext(), SubPeopleContentsType_Activity.class);



            intent.putExtra("people_uid", people_uid);
            intent.putExtra("people_name", people_name);
            intent.putExtra("people_type", people_type);
            */


            Intent intent;


            switch (v.getId()) {
                case R.id.subpeople_menu1:
                    gubun1 = "P";
                    if(P_code.equals("000")) {
                        intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("my_data1", MY_DATA1);
                        intent.putExtra("my_data2", MY_DATA2);
                        intent.putExtra("my_data3", MY_DATA3);
                        intent.putExtra("my_data4", MY_DATA4);
                        intent.putExtra("my_data5", MY_DATA5);
                        intent.putExtra("my_data6", MY_DATA6);
                        intent.putExtra("my_data7", MY_DATA7);
                        intent.putExtra("my_data8", MY_DATA8);
                        intent.putExtra("my_data9", MY_DATA9);
                        intent.putExtra("my_data10", MY_DATA10);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", MY_SPEED);
                        intent.putExtra("my_control", MY_CONTROL);
                        intent.putExtra("people_name", people_name);
                        intent.putExtra("people_speed", P_people_speed);
                        intent.putExtra("people_control", P_people_control);
                        intent.putExtra("people_data1", P_DATA1);
                        intent.putExtra("people_data2", P_DATA2);
                        intent.putExtra("people_data3", P_DATA3);
                        intent.putExtra("people_data4", P_DATA4);
                        intent.putExtra("people_data5", P_DATA5);
                        intent.putExtra("people_data6", P_DATA6);
                        intent.putExtra("people_data7", P_DATA7);
                        intent.putExtra("people_data8", P_DATA8);
                        intent.putExtra("people_data9", P_DATA9);
                        intent.putExtra("people_data10", P_DATA10);
                        intent.putExtra("people_total", P_DATA_TOTAL);
                        intent.putExtra("viewType", "you");


                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "피플들의 진단수가 부족합니다.", Toast.LENGTH_LONG).show();
                    }



                    break;

                case R.id.subpeople_menu2:
                    gubun1 = "F";

                    if(F_code.equals("000")) {
                        intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("my_data1", MY_DATA1);
                        intent.putExtra("my_data2", MY_DATA2);
                        intent.putExtra("my_data3", MY_DATA3);
                        intent.putExtra("my_data4", MY_DATA4);
                        intent.putExtra("my_data5", MY_DATA5);
                        intent.putExtra("my_data6", MY_DATA6);
                        intent.putExtra("my_data7", MY_DATA7);
                        intent.putExtra("my_data8", MY_DATA8);
                        intent.putExtra("my_data9", MY_DATA9);
                        intent.putExtra("my_data10", MY_DATA10);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", MY_SPEED);
                        intent.putExtra("my_control", MY_CONTROL);
                        intent.putExtra("people_name", people_name);
                        intent.putExtra("people_speed", F_people_speed);
                        intent.putExtra("people_control", F_people_control);
                        intent.putExtra("people_data1", F_DATA1);
                        intent.putExtra("people_data2", F_DATA2);
                        intent.putExtra("people_data3", F_DATA3);
                        intent.putExtra("people_data4", F_DATA4);
                        intent.putExtra("people_data5", F_DATA5);
                        intent.putExtra("people_data6", F_DATA6);
                        intent.putExtra("people_data7", F_DATA7);
                        intent.putExtra("people_data8", F_DATA8);
                        intent.putExtra("people_data9", F_DATA9);
                        intent.putExtra("people_data10", F_DATA10);
                        intent.putExtra("people_total", F_DATA_TOTAL);
                        intent.putExtra("viewType", "you");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "피플들의 진단수가 부족합니다.", Toast.LENGTH_LONG).show();
                    }



                    break;

                case R.id.subpeople_menu3:
                    gubun1 = "L";

                    if(L_code.equals("000")) {
                        intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("my_data1", MY_DATA1);
                        intent.putExtra("my_data2", MY_DATA2);
                        intent.putExtra("my_data3", MY_DATA3);
                        intent.putExtra("my_data4", MY_DATA4);
                        intent.putExtra("my_data5", MY_DATA5);
                        intent.putExtra("my_data6", MY_DATA6);
                        intent.putExtra("my_data7", MY_DATA7);
                        intent.putExtra("my_data8", MY_DATA8);
                        intent.putExtra("my_data9", MY_DATA9);
                        intent.putExtra("my_data10", MY_DATA10);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("people_name", people_name);
                        intent.putExtra("my_speed", MY_SPEED);
                        intent.putExtra("my_control", MY_CONTROL);
                        intent.putExtra("people_speed", L_people_speed);
                        intent.putExtra("people_control", L_people_control);
                        intent.putExtra("people_data1", L_DATA1);
                        intent.putExtra("people_data2", L_DATA2);
                        intent.putExtra("people_data3", L_DATA3);
                        intent.putExtra("people_data4", L_DATA4);
                        intent.putExtra("people_data5", L_DATA5);
                        intent.putExtra("people_data6", L_DATA6);
                        intent.putExtra("people_data7", L_DATA7);
                        intent.putExtra("people_data8", L_DATA8);
                        intent.putExtra("people_data9", L_DATA9);
                        intent.putExtra("people_data10", L_DATA10);
                        intent.putExtra("people_total", L_DATA_TOTAL);
                        intent.putExtra("viewType", "you");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "피플들의 진단수가 부족합니다.", Toast.LENGTH_LONG).show();
                    }


                    break;

                case R.id.subpeople_menu4:
                    gubun1 = "C";

                    if(C_code.equals("000")) {
                        intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("my_data1", MY_DATA1);
                        intent.putExtra("my_data2", MY_DATA2);
                        intent.putExtra("my_data3", MY_DATA3);
                        intent.putExtra("my_data4", MY_DATA4);
                        intent.putExtra("my_data5", MY_DATA5);
                        intent.putExtra("my_data6", MY_DATA6);
                        intent.putExtra("my_data7", MY_DATA7);
                        intent.putExtra("my_data8", MY_DATA8);
                        intent.putExtra("my_data9", MY_DATA9);
                        intent.putExtra("my_data10", MY_DATA10);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("people_name", people_name);
                        intent.putExtra("my_speed", MY_SPEED);
                        intent.putExtra("my_control", MY_CONTROL);
                        intent.putExtra("people_speed", C_people_speed);
                        intent.putExtra("people_control", C_people_control);
                        intent.putExtra("people_data1", C_DATA1);
                        intent.putExtra("people_data2", C_DATA2);
                        intent.putExtra("people_data3", C_DATA3);
                        intent.putExtra("people_data4", C_DATA4);
                        intent.putExtra("people_data5", C_DATA5);
                        intent.putExtra("people_data6", C_DATA6);
                        intent.putExtra("people_data7", C_DATA7);
                        intent.putExtra("people_data8", C_DATA8);
                        intent.putExtra("people_data9", C_DATA9);
                        intent.putExtra("people_data10", C_DATA10);
                        intent.putExtra("people_total", C_DATA_TOTAL);
                        intent.putExtra("viewType", "you");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "피플들의 진단수가 부족합니다.", Toast.LENGTH_LONG).show();
                    }


                    break;

                case R.id.subpeople_menu5:
                    gubun1 = "S";


                    if(C_code.equals("000")) {
                        intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("my_data1", MY_DATA1);
                        intent.putExtra("my_data2", MY_DATA2);
                        intent.putExtra("my_data3", MY_DATA3);
                        intent.putExtra("my_data4", MY_DATA4);
                        intent.putExtra("my_data5", MY_DATA5);
                        intent.putExtra("my_data6", MY_DATA6);
                        intent.putExtra("my_data7", MY_DATA7);
                        intent.putExtra("my_data8", MY_DATA8);
                        intent.putExtra("my_data9", MY_DATA9);
                        intent.putExtra("my_data10", MY_DATA10);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("people_name", people_name);
                        intent.putExtra("my_speed", MY_SPEED);
                        intent.putExtra("my_control", MY_CONTROL);
                        intent.putExtra("people_speed", S_people_speed);
                        intent.putExtra("people_control", S_people_control);
                        intent.putExtra("people_data1", S_DATA1);
                        intent.putExtra("people_data2", S_DATA2);
                        intent.putExtra("people_data3", S_DATA3);
                        intent.putExtra("people_data4", S_DATA4);
                        intent.putExtra("people_data5", S_DATA5);
                        intent.putExtra("people_data6", S_DATA6);
                        intent.putExtra("people_data7", S_DATA7);
                        intent.putExtra("people_data8", S_DATA8);
                        intent.putExtra("people_data9", S_DATA9);
                        intent.putExtra("people_data10", S_DATA10);
                        intent.putExtra("people_total", S_DATA_TOTAL);
                        intent.putExtra("viewType", "you");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    } else {
                        Toast.makeText(getActivity().getBaseContext(), "피플들의 진단수가 부족합니다.", Toast.LENGTH_LONG).show();
                    }


                    break;


            }


            /*
            RequestParams params = new RequestParams();
            params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
            params.put("people_uid", people_uid);
            params.put("people_name", people_name);
            params.put("pepple_type", people_type);
            params.put("gubun1", gubun1);

            HttpClient.post("/my_type/youTypeSelect", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jobj = new JSONObject(response);
                        String code = jobj.getString("code");
                        String my_speed = jobj.getString("my_speed");
                        String my_control = jobj.getString("my_control");
                        people_type = jobj.getString("people_type");
                        String people_speed = jobj.getString("people_speed");
                        String people_control = jobj.getString("people_control");

                        if (code.equals("000")) {
                            Intent intent = new Intent(getActivity(), SubPeopleContentsType_Activity.class);
                            intent.putExtra("gubun", gubun1);
                            intent.putExtra("youtype", people_type);
                            intent.putExtra("my_speed", my_speed);
                            intent.putExtra("my_control", my_control);
                            intent.putExtra("people_name", people_name);
                            intent.putExtra("people_youtype", people_type);
                            intent.putExtra("people_speed", people_speed);
                            intent.putExtra("people_control", people_control);

                            getActivity().startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                        } else {
                            Toast.makeText(getActivity(), "진단한 내용이 존재하지 않습니다.\n추후 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("people_gram", response);
                    /*
                    if (response.equals("998")) {
                        Toast.makeText(getActivity(), "진단한 내용이 존재하지 않습니다.\n추후 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                    } else if (response.equals("999")) {
                        Toast.makeText(getActivity(), "진단한 내용이 존재하지 않습니다.\n추후 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getActivity(), SubPeopleContentsType_Activity.class);
                        intent.putExtra("gubun", gubun1);
                        intent.putExtra("youtype", people_type);
                        intent.putExtra("people_name", people_name);
                        intent.putExtra("people_youtype", response);
                        getActivity().startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                }
            });
            */

            /*
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            */
        }
    };

}
