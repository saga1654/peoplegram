package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubPeopleTypeFragment extends Fragment implements View.OnClickListener {

    final int SubPeopleTypeFragmentCode = 2;
    static SubPeopleTypeFragment subpeople_type;


    private CircularImageView profile_img;
    private AQuery aq;
    private TextView profile_username;
    private ImageView profile_type;
    private String mytype;

    private LinearLayout peopletype_menu1, peopletype_menu2, peopletype_menu3, peopletype_menu4, peopletype_menu5;
    private LinearLayout people_menu1, people_menu2, people_menu3, people_menu4, people_menu5;
    private ProgressDialog dialog;
    private ImageView peopletype_icon1, peopletype_icon2, peopletype_icon3, peopletype_icon4, peopletype_icon5;

    public static Boolean P_check = true;
    public static Boolean F_check = true;
    public static Boolean L_check = true;
    public static Boolean C_check = true;
    public static Boolean S_check = true;

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


    private TextView people1_cnt, people2_cnt, people3_cnt, people4_cnt, people5_cnt;

    public void dataResult()
    {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
        HttpClient.post("/my_type/myTypeSelect", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFinish() {



                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);
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

                    Log.d("people_gram", String.valueOf(p_data));


                    if(jobj_p.getString("p_code").equals("000")) {
                        P_check = true;
                        P_point = Integer.parseInt(jobj_p.getString("point"));
                    } else {
                        P_check = false;

                        P_point = Integer.parseInt(jobj_p.getString("point"));
                    }

                    P_code = p_data.getString("code");
                    P_cnt = Integer.parseInt(p_data.getString("people_total"));

                    people1_cnt.setText(String.valueOf(P_cnt));

                    P_my_speed = p_data.getString("my_speed");
                    P_my_control = p_data.getString("my_control");
                    P_people_type = p_data.getString("people_type");
                    P_people_control = p_data.getString("people_control");
                    P_people_speed = p_data.getString("people_speed");



                    if(P_code.equals("000") == false) {
                        peopletype_icon1.setImageResource(R.drawable.item_no_key);
                    }

                    if(jobj_f.getString("f_code").equals("000")) {
                        F_check = true;
                        F_point = Integer.parseInt(jobj_f.getString("point"));
                    } else {
                        F_check = false;
                        F_point = Integer.parseInt(jobj_f.getString("point"));
                    }

                    F_code = f_data.getString("code");
                    F_cnt = Integer.parseInt(f_data.getString("people_total"));

                    people2_cnt.setText(String.valueOf(F_cnt));

                    F_my_speed = f_data.getString("my_speed");
                    F_my_control = f_data.getString("my_control");
                    F_people_type = f_data.getString("people_type");
                    F_people_control = f_data.getString("people_control");
                    F_people_speed = f_data.getString("people_speed");

                    if(F_code.equals("000") == false) {
                        peopletype_icon2.setImageResource(R.drawable.item_no_key);
                    }

                    if(jobj_l.getString("l_code").equals("000")) {
                        L_check = true;
                        L_point = Integer.parseInt(jobj_l.getString("point"));
                    } else {
                        L_check = false;
                        L_point = Integer.parseInt(jobj_l.getString("point"));
                    }


                    L_code = l_data.getString("code");
                    L_cnt = Integer.parseInt(l_data.getString("people_total"));

                    people3_cnt.setText(String.valueOf(L_cnt));

                    L_my_speed = l_data.getString("my_speed");
                    L_my_control = l_data.getString("my_control");
                    L_people_type = l_data.getString("people_type");
                    L_people_control = l_data.getString("people_control");
                    L_people_speed = l_data.getString("people_speed");

                    if(L_code.equals("000") == false) {
                        peopletype_icon3.setImageResource(R.drawable.item_no_key);
                    }

                    if(jobj_c.getString("c_code").equals("000")) {
                        C_check = true;
                        C_cnt = Integer.parseInt(c_data.getString("people_total"));
                        C_point = Integer.parseInt(jobj_c.getString("point"));
                    } else {
                        C_check = false;
                        C_cnt = Integer.parseInt(c_data.getString("people_total"));
                        C_point = Integer.parseInt(jobj_c.getString("point"));
                    }

                    Log.d("people_gram", String.valueOf(c_data));


                    C_code = c_data.getString("code");
                    C_cnt = Integer.parseInt(c_data.getString("people_total"));

                    Log.d("people_gram", String.valueOf(c_data));



                    people4_cnt.setText(String.valueOf(C_cnt));

                    C_my_speed = c_data.getString("my_speed");
                    C_my_control = c_data.getString("my_control");
                    C_people_type = c_data.getString("people_type");
                    C_people_control = c_data.getString("people_control");
                    C_people_speed = c_data.getString("people_speed");

                    if(C_code.equals("000") == false) {
                        peopletype_icon4.setImageResource(R.drawable.item_no_key);
                    }

                    if(jobj_s.getString("s_code").equals("000")) {
                        S_check = true;
                        S_point = Integer.parseInt(jobj_s.getString("point"));
                    } else {
                        S_check = false;
                        S_point = Integer.parseInt(jobj_s.getString("point"));
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


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public SubPeopleTypeFragment() {
    }


    @Override
    public void onStart()
    {
        //dataResult();
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_peopletype, container, false);

        peopletype_menu1 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu1);
        peopletype_menu2 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu2);
        peopletype_menu3 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu3);
        peopletype_menu4 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu4);
        peopletype_menu5 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu5);

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

        mytype = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");

        dataResult();

        peopletype_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(P_check == false) {

                    if(P_cnt < 2) {
                        Toast.makeText(getActivity(), "본인 포함 최소 2명 이상 진단된 경우에 볼 수 있습니다.\n진단 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("people_gram", String.valueOf(P_point));
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(P_point));
                        intent.putExtra("gubun1", "P");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    if(P_code.equals("999")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else if(P_code.equals("998")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                        String people_type = P_people_type;
                        String my_speed = P_my_speed;
                        String my_control = P_my_control;
                        String people_speed = P_people_speed;
                        String people_control = P_people_control;
                        String gubun1 = "P";


                        Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", my_speed);
                        intent.putExtra("my_control", my_control);
                        intent.putExtra("people_speed", people_speed);
                        intent.putExtra("people_control", people_control);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                }
            }

        });

        peopletype_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(F_check == false) {

                    if(F_cnt < 2) {
                        Toast.makeText(getActivity(), "본인 포함 최소 3명 이상 진단된 경우에 볼 수 있습니다.\n진단 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("people_gram", String.valueOf(F_point));
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(F_point));
                        intent.putExtra("gubun1", "F");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    if(F_code.equals("999")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else if(F_code.equals("998")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                        String people_type = F_people_type;
                        String my_speed = F_my_speed;
                        String my_control = F_my_control;
                        String people_speed = F_people_speed;
                        String people_control = F_people_control;
                        String gubun1 = "F";


                        Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", my_speed);
                        intent.putExtra("my_control", my_control);
                        intent.putExtra("people_speed", people_speed);
                        intent.putExtra("people_control", people_control);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                }
            }
        });

        peopletype_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(L_check == false) {

                    if(L_cnt < 1) {
                        Toast.makeText(getActivity(), "연인에게 진단을 요청해 주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(L_point));
                        intent.putExtra("gubun1", "L");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    if(F_code.equals("999")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n연인에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else if(F_code.equals("998")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n연인에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                        String people_type = L_people_type;
                        String my_speed = L_my_speed;
                        String my_control = L_my_control;
                        String people_speed = L_people_speed;
                        String people_control = L_people_control;
                        String gubun1 = "L";


                        Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", my_speed);
                        intent.putExtra("my_control", my_control);
                        intent.putExtra("people_speed", people_speed);
                        intent.putExtra("people_control", people_control);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                }
            }
        });

        peopletype_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(C_check == false) {

                    if(C_cnt < 3) {
                        Toast.makeText(getActivity(), "본인 포함 최소 3명 이상 진단된 경우에 볼 수 있습니다.\n진단 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(C_point));
                        intent.putExtra("gubun1", "C");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    if(C_code.equals("999")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n연인에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else if(C_code.equals("998")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n연인에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                        String people_type = C_people_type;
                        String my_speed = C_my_speed;
                        String my_control = C_my_control;
                        String people_speed = C_people_speed;
                        String people_control = C_people_control;
                        String gubun1 = "C";


                        Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", my_speed);
                        intent.putExtra("my_control", my_control);
                        intent.putExtra("people_speed", people_speed);
                        intent.putExtra("people_control", people_control);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                }
            }
        });

        peopletype_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(S_check == false) {

                    if(S_cnt < 1) {
                        Toast.makeText(getActivity(), "진단이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(C_point));
                        intent.putExtra("gubun1", "S");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    if(S_code.equals("999")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n연인에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else if(S_code.equals("998")) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n연인에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                        String people_type = S_people_type;
                        String my_speed = S_my_speed;
                        String my_control = S_my_control;
                        String people_speed = S_people_speed;
                        String people_control = S_people_control;
                        String gubun1 = "S";


                        Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                        intent.putExtra("mytype", my_type);
                        intent.putExtra("people_type", people_type);
                        intent.putExtra("gubun1", gubun1);
                        intent.putExtra("my_speed", my_speed);
                        intent.putExtra("my_control", my_control);
                        intent.putExtra("people_speed", people_speed);
                        intent.putExtra("people_control", people_control);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                }
            }
        });

        //dataResult();



        return rootView;
    }






    private void profile_img_view(String filename)
    {
        ImageOptions options = new ImageOptions();
        options.ratio = 1;

        options.memCache = true;
        options.fileCache = true;


        String imageUrl = "http://121.162.209.41:81/"+filename;
        aq.id(profile_img).image(imageUrl, options);

    }

    @Override
    public void onClick(View v) {

    }

}
