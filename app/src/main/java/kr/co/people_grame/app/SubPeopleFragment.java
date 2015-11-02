package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.internal.model.moments.ItemScopeEntity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubPeopleFragment extends Fragment {

    final int peopleView = 1111;

    int all_cnt = 0;
    int p_cnt = 0;
    int f_cnt = 0;
    int l_cnt = 0;
    int c_cnt = 0;
    int s_cnt = 0;
    int n_cnt = 0;

    private ProgressDialog dialog;
    private ListView sf_people_list;

    private ArrayList<SubPeopleListDTO> people_dto_list;
    private ArrayList<SubPeopleListDTO_Temp> people_dto_list_temp;
    private SubPeopleListAdapter people_adapter_list;

    private View mainView;
    private ImageView listview_proplelist_img, btn_question_re;
    private String searchType = "ALL";

    private LinearLayout people_gubun_all, people_gubun_family, people_gubun_friend, people_gubun_lover, people_gubun_job, people_gubun_client, people_gubun_not;
    private TextView people_cnt, et_all_cnt, et_p_cnt, et_f_cnt, et_l_cnt, et_c_cnt, et_s_cnt, et_n_cnt;


    private final int ACTIVITY_CODE = 000001;
    private int pos = 0;

    private static boolean reFresh = false;


    private TextView my_profile_i_now_cnt, my_profile_i_up_cnt, my_profile_d_now_cnt, my_profile_d_up_cnt, my_profile_e_now_cnt, my_profile_e_up_cnt, my_profile_a_now_cnt, my_profile_a_up_cnt;
    private ImageView my_profile_i_up_icon, my_profile_d_up_icon, my_profile_e_up_icon, my_profile_a_up_icon;

    private SearchView serach_view;

    public SubPeopleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_people, container, false);
        View header = inflater.inflate(R.layout.sub_people_header, null, false);


        mainView = rootView;

        people_dto_list_temp = new ArrayList<SubPeopleListDTO_Temp>();

        /*
        private TextView my_profile_i_now_cnt, my_profile_i_up_cnt, my_profile_d_now_cnt, my_profile_d_up_cnt, my_profile_e_now_cnt, my_profile_e_up_cnt, my_profile_a_now_cnt, my_profile_a_up_cnt;
        private ImageView my_profile_i_up_icon, my_profile_d_up_icon, my_profile_e_up_icon, my_profile_a_up_icon;
        */



        sf_people_list = (ListView)rootView.findViewById(R.id.sf_people_list);
        sf_people_list.addHeaderView(header);

        people_cnt = (TextView) rootView.findViewById(R.id.people_cnt);
        btn_question_re = (ImageView) header.findViewById(R.id.btn_question_re);
        btn_question_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MyQuestionRe_Activity.class);
                getActivity().startActivityForResult(intent, peopleView);
                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);

            }
        });
        //btn_question_re = (ImageView) rootView.findViewById(R.id.btn_question_re);

        listview_proplelist_img = (ImageView) rootView.findViewById(R.id.listview_proplelist_img);
        TextView listview_my_people_list_username = (TextView) rootView.findViewById(R.id.listview_my_people_list_username);
        TextView listview_my_people_list_email = (TextView) rootView.findViewById(R.id.listview_my_people_list_email);

        Switch listview_mytype_switch = (Switch) rootView.findViewById(R.id.listview_mytype_switch);
        listview_mytype_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == false) {
                    switch (SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype")) {
                        case "A":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                            break;

                        case "I":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                            break;

                        case "D":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                            break;

                        case "E":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                            break;

                        default:
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                            break;
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid"));
                    HttpClient.post("/user/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {

                            switch (response) {
                                case "A":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                                    break;

                                case "I":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                                    break;

                                case "D":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                                    break;

                                case "E":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                                    break;

                                default:
                                    Toast.makeText(getActivity(), "피플이 귀하를 진단하지 않았습니다.\n피플들에게 요청하세요.", Toast.LENGTH_SHORT).show();
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                                    break;
                            }
                        }
                    });
                }
            }
        });




        listview_my_people_list_username.setText(SharedPreferenceUtil.getSharedPreference(getActivity(), "username"));
        listview_my_people_list_email.setText(SharedPreferenceUtil.getSharedPreference(getActivity(), "email"));

        switch (SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype"))
        {
            case "A":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                break;

            case "I":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                break;

            case "D":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                break;

            case "E":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                break;

            default:

                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                break;
        }






        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {


                } else {
                    SubPeopleListDTO dto = (SubPeopleListDTO) sf_people_list.getItemAtPosition(position);
                    Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleListPopup_Activity.class);

                    pos = sf_people_list.getFirstVisiblePosition();

                    Log.d("people_gram", "현재위치="+pos);

                    intent.putExtra("people_uid", dto.get_profile_uid());
                    intent.putExtra("people_email", dto.get_profile_email());
                    intent.putExtra("people_username", dto.get_profile_username());
                    intent.putExtra("people_mood", dto.get_profile_mood());
                    intent.putExtra("people_type", dto.get_profile_type());
                    intent.putExtra("people_gubun1", dto.get_profile_gubun1());
                    intent.putExtra("people_gubun2", dto.get_profile_gubun2());
                    intent.putExtra("people_speed", dto.get_profile_speed());
                    intent.putExtra("people_control", dto.get_profile_control());
                    intent.putExtra("people_result_count", dto.get_profile_cnt());
                    intent.putExtra("people_friend_count", dto.get_profile_friend_cnt());

                    startActivityForResult(intent, ACTIVITY_CODE);
                    getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                }
            }
        });


        people_gubun_all = (LinearLayout) rootView.findViewById(R.id.people_gubun_all);
        people_gubun_family = (LinearLayout) rootView.findViewById(R.id.people_gubun_family);
        people_gubun_friend = (LinearLayout) rootView.findViewById(R.id.people_gubun_friend);
        people_gubun_lover = (LinearLayout) rootView.findViewById(R.id.people_gubun_lover);
        people_gubun_job = (LinearLayout) rootView.findViewById(R.id.people_gubun_job);
        people_gubun_client = (LinearLayout) rootView.findViewById(R.id.people_gubun_client);
        people_gubun_not = (LinearLayout) rootView.findViewById(R.id.people_gubun_not);

        people_gubun_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "ALL";
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_on);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_off);
                pos = 0;
                //peopleList();

                people_dto_list = new ArrayList<SubPeopleListDTO>();
                people_dto_list.clear();

                for (int i = 0; i < people_dto_list_temp.size(); i++) {
                    SubPeopleListDTO_Temp dto = people_dto_list_temp.get(i);
                    people_dto_list.add(new SubPeopleListDTO(
                            dto.get_profile_uid()
                            , ""
                            , dto.get_profile_username()
                            , dto.get_profile_email()
                            , dto.get_profile_type()
                            , ""
                            , dto.get_profile_gubun1()
                            , dto.get_profile_gubun2()
                            , dto.get_profile_speed()
                            , dto.get_profile_control()
                            , dto.get_profile_cnt()
                            , dto.get_profile_friend_cnt()
                            , dto.get_profile_new_cnt()
                    ));

                }

                people_adapter_list = new SubPeopleListAdapter(getActivity().getBaseContext(), R.layout.sub_people_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);

                people_cnt.setText("피플 (" + String.valueOf(all_cnt) + "명)");

            }
        });
        people_gubun_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "P";
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_on);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_off);
                pos = 0;


                people_dto_list = new ArrayList<SubPeopleListDTO>();
                people_dto_list.clear();

                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    SubPeopleListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_gubun1().equals("P"))
                    people_dto_list.add(new SubPeopleListDTO(
                            dto.get_profile_uid()
                            , ""
                            , dto.get_profile_username()
                            , dto.get_profile_email()
                            , dto.get_profile_type()
                            , ""
                            , dto.get_profile_gubun1()
                            , dto.get_profile_gubun2()
                            , dto.get_profile_speed()
                            , dto.get_profile_control()
                            , dto.get_profile_cnt()
                            , dto.get_profile_friend_cnt()
                            , dto.get_profile_new_cnt()
                    ));

                }

                people_adapter_list = new SubPeopleListAdapter(getActivity().getBaseContext(), R.layout.sub_people_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);
                //peopleList();

                people_cnt.setText("피플 (" + String.valueOf(p_cnt) + "명)");
            }
        });
        people_gubun_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "F";
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_on);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_off);
                pos = 0;

                repeopleList();

                people_cnt.setText("피플 (" + String.valueOf(f_cnt) + "명)");
                //peopleList();


            }
        });
        people_gubun_lover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "L";
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_on);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_off);
                pos = 0;

                repeopleList();

                people_cnt.setText("피플 (" + String.valueOf(l_cnt) + "명)");
                //peopleList();
            }
        });

        people_gubun_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "C";
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_on);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_off);
                pos = 0;

                repeopleList();

                people_cnt.setText("피플 (" + String.valueOf(c_cnt) + "명)");
                //peopleList();
            }
        });

        people_gubun_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "S";
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_on);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_off);
                pos = 0;

                repeopleList();

                people_cnt.setText("피플 (" + String.valueOf(s_cnt) + "명)");
                //peopleList();
            }
        });

        people_gubun_not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "";
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_on);
                pos = 0;
                repeopleList();

                people_cnt.setText("피플 (" + String.valueOf(n_cnt) + "명)");
                //peopleList();
            }
        });

        TextView mainTitle = (TextView) getActivity().findViewById(R.id.mainTitle);
        mainTitle.setText("피플그램");


        et_all_cnt = (TextView) rootView.findViewById(R.id.all_cnt);
        et_p_cnt = (TextView) rootView.findViewById(R.id.p_cnt);
        et_f_cnt = (TextView) rootView.findViewById(R.id.f_cnt);
        et_l_cnt = (TextView) rootView.findViewById(R.id.l_cnt);
        et_c_cnt = (TextView) rootView.findViewById(R.id.c_cnt);
        et_s_cnt = (TextView) rootView.findViewById(R.id.s_cnt);
        et_n_cnt = (TextView) rootView.findViewById(R.id.n_cnt);


        my_profile_i_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_i_now_cnt);
        my_profile_d_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_d_now_cnt);
        my_profile_e_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_e_now_cnt);
        my_profile_a_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_a_now_cnt);

        my_profile_i_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_i_up_cnt);
        my_profile_d_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_d_up_cnt);
        my_profile_e_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_e_up_cnt);
        my_profile_a_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_a_up_cnt);

        my_profile_i_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_i_up_icon);
        my_profile_d_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_d_up_icon);
        my_profile_e_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_e_up_icon);
        my_profile_a_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_a_up_icon);





        peopleList();




        return rootView;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        peopleList();
        switch (SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype")) {
            case "A":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                break;

            case "I":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                break;

            case "D":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                break;

            case "E":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                break;

            default:
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void repeopleList()
    {
        people_dto_list = new ArrayList<SubPeopleListDTO>();
        people_dto_list.clear();

        for(int i = 0; i<people_dto_list_temp.size(); i++) {
            SubPeopleListDTO_Temp dto = people_dto_list_temp.get(i);
            if(dto.get_profile_gubun1().equals(searchType))
                people_dto_list.add(new SubPeopleListDTO(
                        dto.get_profile_uid()
                        , ""
                        , dto.get_profile_username()
                        , dto.get_profile_email()
                        , dto.get_profile_type()
                        , ""
                        , dto.get_profile_gubun1()
                        , dto.get_profile_gubun2()
                        , dto.get_profile_speed()
                        , dto.get_profile_control()
                        , dto.get_profile_cnt()
                        , dto.get_profile_friend_cnt()
                        , dto.get_profile_new_cnt()
                ));

        }

        people_adapter_list = new SubPeopleListAdapter(getActivity().getBaseContext(), R.layout.sub_people_row_list, people_dto_list);
        sf_people_list.setAdapter(people_adapter_list);
    }

    private void peopleList()
    {
        people_dto_list = new ArrayList<SubPeopleListDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid"));
        params.put("searchType", searchType);
        HttpClient.post("/user/member_people", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFailure()
            {
            }

            public void onFinish()  {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                try {

                    JSONObject data = new JSONObject(response);
                    JSONObject my_profile = data.getJSONObject("my");
                    JSONArray people_list = data.getJSONArray("people");

                    //JSONArray people_list = new JSONArray(response);

                    String i_total = my_profile.getString("MY_I_TOTAL");
                    String d_total = my_profile.getString("MY_D_TOTAL");
                    String e_total = my_profile.getString("MY_E_TOTAL");
                    String a_total = my_profile.getString("MY_A_TOTAL");

                    String my_i_now_total = my_profile.getString("MY_I_NOW_MAX");
                    String my_d_now_total = my_profile.getString("MY_D_NOW_MAX");
                    String my_e_now_total = my_profile.getString("MY_E_NOW_MAX");
                    String my_a_now_total = my_profile.getString("MY_A_NOW_MAX");

                    Log.d("people_gram", i_total + ":::" + d_total + ":::" + e_total + ":::" + a_total);

                    my_profile_i_now_cnt.setText(i_total);
                    my_profile_d_now_cnt.setText(d_total);
                    my_profile_e_now_cnt.setText(e_total);
                    my_profile_a_now_cnt.setText(a_total);

                    if(my_i_now_total.equals("0")) {
                        my_profile_i_up_icon.setVisibility(View.GONE);
                        my_profile_i_up_cnt.setVisibility(View.GONE);
                    } else {
                        my_profile_i_up_cnt.setText(my_i_now_total);
                    }

                    if(my_d_now_total.equals("0")) {
                        my_profile_d_up_icon.setVisibility(View.GONE);
                        my_profile_d_up_cnt.setVisibility(View.GONE);
                    } else {
                        my_profile_d_up_cnt.setText(my_d_now_total);
                    }

                    if(my_e_now_total.equals("0")) {
                        my_profile_e_up_icon.setVisibility(View.GONE);
                        my_profile_e_up_cnt.setVisibility(View.GONE);
                    } else {
                        my_profile_e_up_cnt.setText(my_e_now_total);
                    }

                    if(my_a_now_total.equals("0")) {
                        my_profile_a_up_icon.setVisibility(View.GONE);
                        my_profile_a_up_cnt.setVisibility(View.GONE);
                    } else {
                        my_profile_a_up_cnt.setText(my_a_now_total);
                    }


                    /*
                    my_profile_i_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_i_up_icon);
                    my_profile_d_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_d_up_icon);
                    my_profile_e_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_e_up_icon);
                    my_profile_a_up_icon = (ImageView) rootView.findViewById(R.id.my_profile_a_up_icon);
                    */


                    /*
                    my_profile_i_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_i_now_cnt);

                    my_profile_d_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_d_now_cnt);
                    my_profile_e_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_e_now_cnt);
                    my_profile_a_now_cnt = (TextView) rootView.findViewById(R.id.my_profile_a_now_cnt);

                    my_profile_i_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_i_up_cnt);
                    my_profile_d_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_d_up_cnt);
                    my_profile_e_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_e_up_cnt);
                    my_profile_a_up_cnt = (TextView) rootView.findViewById(R.id.my_profile_a_up_cnt);
                    */


                    people_cnt.setText("피플 (" + people_list.length() + "명)");
                    for (int i = 0; i < people_list.length(); i++) {
                        JSONObject jobj = people_list.getJSONObject(i);

                        String email = "";
                        String type = "";
                        String gubun1 = "";
                        String gubun2 = "";
                        int speed = 0;
                        int control = 0;

                        if (jobj.getString("JOIN_EMAIL") != "null") {
                            email = jobj.getString("JOIN_EMAIL");
                        } else {
                            email = "미가입";
                        }
                        if (jobj.getString("YOU_TYPE") != "null") {
                            type = jobj.getString("YOU_TYPE");
                        }
                        if (jobj.getString("GUBUN1") != "null") {
                            gubun1 = jobj.getString("GUBUN1");
                        }
                        if (jobj.getString("GUBUN2") != "null") {
                            gubun2 = jobj.getString("GUBUN2");
                        }

                        if (jobj.getString("SPEED") != "null") {
                            speed = Integer.parseInt(jobj.getString("SPEED"));
                        }
                        if (jobj.getString("CONTROL") != "null") {
                            control = Integer.parseInt(jobj.getString("CONTROL"));
                        }

                        all_cnt++;

                        if(gubun1.equals("P")) {
                            p_cnt++;
                        } else if(gubun1.equals("F")) {
                            f_cnt++;
                        } else if(gubun1.equals("L")) {
                            l_cnt++;
                        } else if(gubun1.equals("C")) {
                            c_cnt++;
                        } else if(gubun1.equals("S")) {
                            s_cnt++;
                        } else {
                            n_cnt++;
                        }

                        people_dto_list.add(new SubPeopleListDTO(
                                jobj.getString("PEOPLE_UID")
                                , ""
                                , jobj.getString("PEOPLE_USERNAME")
                                , email
                                , type
                                , ""
                                , gubun1
                                , gubun2
                                , speed
                                , control
                                , jobj.getInt("TOTAL_COUNT")
                                , jobj.getInt("FRIEND_COUNT")
                                , jobj.getInt("NOW_MAX")
                        ));

                        people_dto_list_temp.add(new SubPeopleListDTO_Temp(
                                jobj.getString("PEOPLE_UID")
                                , ""
                                , jobj.getString("PEOPLE_USERNAME")
                                , email
                                , type
                                , ""
                                , gubun1
                                , gubun2
                                , speed
                                , control
                                , jobj.getInt("TOTAL_COUNT")
                                , jobj.getInt("FRIEND_COUNT")
                                , jobj.getInt("NOW_MAX")
                        ));

                        //Log.d("people_gram", jobj.getInt("NOW_CHECK") + ":::" + jobj.getInt("NOW_MAX"));
                    }

                    people_adapter_list = new SubPeopleListAdapter(getActivity().getBaseContext(), R.layout.sub_people_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);

                    sf_people_list.setSelection(pos);



                    et_all_cnt.setText(String.valueOf(all_cnt));
                    et_p_cnt.setText(String.valueOf(p_cnt));
                    et_f_cnt.setText(String.valueOf(f_cnt));
                    et_l_cnt.setText(String.valueOf(l_cnt));
                    et_c_cnt.setText(String.valueOf(c_cnt));
                    et_s_cnt.setText(String.valueOf(s_cnt));
                    et_n_cnt.setText(String.valueOf(n_cnt));


                    //Log.d("people_gram", all_cnt + "::" + p_cnt + "::" + f_cnt + "::" + c_cnt + "::" + c_cnt + ":::" + s_cnt + "::" + n_cnt);


                    //dialog.dismiss();
                    //listview_noticeList.setAdapter(notice_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onPause()
    {
        super.onPause();
    }



}
