package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.plus.People;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PeopleMatchTop10_Activity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ListView sf_people_list;

    private ArrayList<SubPeopleMatchListDTO> people_dto_list;
    private ArrayList<SubPeopleMatchListDTO_Temp> people_dto_list_temp;
    private SubPeopleMatchListAdapter people_adapter_list;

    private TextView people_cnt, et_all_cnt, et_p_cnt, et_f_cnt, et_l_cnt, et_c_cnt, et_s_cnt, et_n_cnt;


    private LinearLayout people_gubun_all, people_gubun_family, people_gubun_friend, people_gubun_lover, people_gubun_job, people_gubun_client, people_gubun_not;
    private LinearLayout people_gubun_all_line, people_gubun_family_line, people_gubun_friend_line, people_gubun_lover_line, people_gubun_job_line, people_gubun_client_line, people_gubun_not_line;


    private TextView et_all_title, et_p_title, et_f_title, et_l_title, et_c_title, et_s_title, et_n_title;
    private String searchType = "";

    int all_cnt = 0;
    int p_cnt = 0;
    int f_cnt = 0;
    int l_cnt = 0;
    int c_cnt = 0;
    int s_cnt = 0;
    int n_cnt = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_match_top10_);

        LayoutInflater infalter = getLayoutInflater();
        ViewGroup header = (ViewGroup) infalter.inflate(R.layout.sub_people_match_header, null, false);


        Log.d("people_gram", "피플매칭 순위");

        sf_people_list = (ListView) findViewById(R.id.sf_people_list);

        sf_people_list.addHeaderView(header);

        et_all_cnt = (TextView) findViewById(R.id.all_cnt);
        et_p_cnt = (TextView) findViewById(R.id.p_cnt);
        et_f_cnt = (TextView) findViewById(R.id.f_cnt);
        et_l_cnt = (TextView) findViewById(R.id.l_cnt);
        et_c_cnt = (TextView) findViewById(R.id.c_cnt);
        et_s_cnt = (TextView) findViewById(R.id.s_cnt);
        et_n_cnt = (TextView) findViewById(R.id.n_cnt);

        et_all_title = (TextView) findViewById(R.id.all_title);
        et_p_title = (TextView) findViewById(R.id.p_title);
        et_f_title = (TextView) findViewById(R.id.f_title);
        et_l_title = (TextView) findViewById(R.id.l_title);
        et_c_title = (TextView) findViewById(R.id.c_title);
        et_s_title = (TextView) findViewById(R.id.s_title);
        et_n_title = (TextView) findViewById(R.id.n_title);

        people_gubun_all = (LinearLayout) findViewById(R.id.people_gubun_all);
        people_gubun_family = (LinearLayout) findViewById(R.id.people_gubun_family);
        people_gubun_friend = (LinearLayout) findViewById(R.id.people_gubun_friend);
        people_gubun_lover = (LinearLayout) findViewById(R.id.people_gubun_lover);
        people_gubun_job = (LinearLayout) findViewById(R.id.people_gubun_job);
        people_gubun_client = (LinearLayout) findViewById(R.id.people_gubun_client);
        people_gubun_not = (LinearLayout) findViewById(R.id.people_gubun_not);

        people_gubun_all_line = (LinearLayout) findViewById(R.id.people_gubun_all_line);
        people_gubun_family_line = (LinearLayout) findViewById(R.id.people_gubun_family_line);
        people_gubun_friend_line = (LinearLayout) findViewById(R.id.people_gubun_friend_line);
        people_gubun_lover_line = (LinearLayout) findViewById(R.id.people_gubun_lover_line);
        people_gubun_job_line = (LinearLayout) findViewById(R.id.people_gubun_job_line);
        people_gubun_client_line = (LinearLayout) findViewById(R.id.people_gubun_client_line);
        people_gubun_not_line = (LinearLayout) findViewById(R.id.people_gubun_not_line);


        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                if(position == 0) {


                } else {

                    final SubPeopleMatchListDTO dto = (SubPeopleMatchListDTO) sf_people_list.getItemAtPosition(position);

                    //Log.d("people_gram", "현재위치="+pos);




                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(PeopleMatchTop10_Activity.this, "uid"));
                    params.put("people_uid", dto.get_profile_uid());
                    HttpClient.post("/people/peoplePopupView", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            dialog = ProgressDialog.show(PeopleMatchTop10_Activity.this, "", "데이터 수신중");
                        }

                        public void onFailure()
                        {
                        }

                        public void onFinish()  {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {
                            //Log.d("people_gram", response);
                            Intent intent = new Intent(PeopleMatchTop10_Activity.this, SubPeopleListPopup_Activity.class);
                            intent.putExtra("people_uid", dto.get_profile_uid());
                            intent.putExtra("people_email", dto.get_profile_email());
                            intent.putExtra("people_username", dto.get_profile_username());
                            intent.putExtra("people_mood", "");
                            intent.putExtra("people_type", dto.get_profile_type());
                            intent.putExtra("people_gubun1", dto.get_profile_gubun1());
                            intent.putExtra("people_gubun2", dto.get_profile_gubun2());
                            intent.putExtra("people_speed", dto.get_profile_speed());
                            intent.putExtra("people_control", dto.get_profile_control());
                            intent.putExtra("people_result_count", 0);
                            intent.putExtra("people_friend_count", 0);
                            intent.putExtra("people_coaching", response);

                            startActivityForResult(intent, 000001);
                            overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                        }
                    });
                }
            }
        });

        people_gubun_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "ALL";

                people_gubun_all_line.setBackgroundColor(Color.rgb(254, 23, 5));
                people_gubun_family_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_friend_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_lover_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_job_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_client_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_not_line.setBackgroundColor(Color.rgb(241, 241, 241));

                et_all_title.setTextColor(Color.rgb(254, 23, 5));
                et_p_title.setTextColor(Color.rgb(29, 29, 38));
                et_f_title.setTextColor(Color.rgb(29, 29, 38));
                et_l_title.setTextColor(Color.rgb(29, 29, 38));
                et_c_title.setTextColor(Color.rgb(29, 29, 38));
                et_s_title.setTextColor(Color.rgb(29, 29, 38));
                et_n_title.setTextColor(Color.rgb(29, 29, 38));

                et_all_cnt.setTextColor(Color.rgb(254, 23, 5));
                et_p_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_f_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_l_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_c_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_s_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_n_cnt.setTextColor(Color.rgb(29, 29, 38));


                //peopleList();

                people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
                people_dto_list.clear();

                for (int i = 0; i < people_dto_list_temp.size(); i++) {
                    SubPeopleMatchListDTO_Temp dto = people_dto_list_temp.get(i);
                    people_dto_list.add(new SubPeopleMatchListDTO(
                            dto.get_profile_uid()
                            , dto.get_profile_username()
                            , dto.get_profile_email()
                            , dto.get_profile_type()
                            , dto.get_profile_gubun1()
                            , dto.get_profile_gubun2()
                            , dto.get_profile_speed()
                            , dto.get_profile_control()
                            , dto.get_profile_total_value()
                            , dto.get_profile_score()
                            , dto.get_profile_match_value()
                    ));

                }

                people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);


            }
        });

        people_gubun_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "P";
                /*
                people_gubun_all.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_family.setBackgroundResource(R.drawable.people_gubun_on);
                people_gubun_friend.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_lover.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_job.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_client.setBackgroundResource(R.drawable.people_gubun_off);
                people_gubun_not.setBackgroundResource(R.drawable.people_gubun_off);
                */


                people_gubun_all_line.setBackgroundColor(Color.rgb(241,241,241));
                people_gubun_family_line.setBackgroundColor(Color.rgb(254,23,5));
                people_gubun_friend_line.setBackgroundColor(Color.rgb(241,241,241));
                people_gubun_lover_line.setBackgroundColor(Color.rgb(241,241,241));
                people_gubun_job_line.setBackgroundColor(Color.rgb(241,241,241));
                people_gubun_client_line.setBackgroundColor(Color.rgb(241,241,241));
                people_gubun_not_line.setBackgroundColor(Color.rgb(241, 241, 241));

                et_all_title.setTextColor(Color.rgb(29, 29, 38));
                et_p_title.setTextColor(Color.rgb(254, 23, 5));
                et_f_title.setTextColor(Color.rgb(29, 29, 38));
                et_l_title.setTextColor(Color.rgb(29, 29, 38));
                et_c_title.setTextColor(Color.rgb(29, 29, 38));
                et_s_title.setTextColor(Color.rgb(29, 29, 38));
                et_n_title.setTextColor(Color.rgb(29, 29, 38));

                et_all_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_p_cnt.setTextColor(Color.rgb(254, 23, 5));
                et_f_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_l_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_c_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_s_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_n_cnt.setTextColor(Color.rgb(29, 29, 38));



                people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
                people_dto_list.clear();

                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    SubPeopleMatchListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_gubun1().equals("P"))
                        people_dto_list.add(new SubPeopleMatchListDTO(
                            dto.get_profile_uid()
                            , dto.get_profile_username()
                            , dto.get_profile_email()
                            , dto.get_profile_type()
                            , dto.get_profile_gubun1()
                            , dto.get_profile_gubun2()
                            , dto.get_profile_speed()
                            , dto.get_profile_control()
                            , dto.get_profile_total_value()
                            , dto.get_profile_score()
                            , dto.get_profile_match_value()
                        ));

                }

                people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);

            }
        });
        people_gubun_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "F";
                people_gubun_all_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_family_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_friend_line.setBackgroundColor(Color.rgb(254, 23, 5));
                people_gubun_lover_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_job_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_client_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_not_line.setBackgroundColor(Color.rgb(241, 241, 241));

                et_all_title.setTextColor(Color.rgb(29, 29, 38));
                et_p_title.setTextColor(Color.rgb(29, 29, 38));
                et_f_title.setTextColor(Color.rgb(254, 23, 5));
                et_l_title.setTextColor(Color.rgb(29, 29, 38));
                et_c_title.setTextColor(Color.rgb(29, 29, 38));
                et_s_title.setTextColor(Color.rgb(29, 29, 38));
                et_n_title.setTextColor(Color.rgb(29, 29, 38));

                et_all_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_p_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_f_cnt.setTextColor(Color.rgb(254, 23, 5));
                et_l_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_c_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_s_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_n_cnt.setTextColor(Color.rgb(29, 29, 38));



                people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
                people_dto_list.clear();

                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    SubPeopleMatchListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_gubun1().equals("F"))
                        people_dto_list.add(new SubPeopleMatchListDTO(
                                dto.get_profile_uid()
                                , dto.get_profile_username()
                                , dto.get_profile_email()
                                , dto.get_profile_type()
                                , dto.get_profile_gubun1()
                                , dto.get_profile_gubun2()
                                , dto.get_profile_speed()
                                , dto.get_profile_control()
                                , dto.get_profile_total_value()
                                , dto.get_profile_score()
                                , dto.get_profile_match_value()
                        ));

                }

                people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);

            }
        });
        people_gubun_lover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "L";
                people_gubun_all_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_family_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_friend_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_lover_line.setBackgroundColor(Color.rgb(254, 23, 5));
                people_gubun_job_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_client_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_not_line.setBackgroundColor(Color.rgb(241, 241, 241));

                et_all_title.setTextColor(Color.rgb(29, 29, 38));
                et_p_title.setTextColor(Color.rgb(29, 29, 38));
                et_f_title.setTextColor(Color.rgb(29, 29, 38));
                et_l_title.setTextColor(Color.rgb(254, 23, 5));
                et_c_title.setTextColor(Color.rgb(29, 29, 38));
                et_s_title.setTextColor(Color.rgb(29, 29, 38));
                et_n_title.setTextColor(Color.rgb(29, 29, 38));

                et_all_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_p_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_f_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_l_cnt.setTextColor(Color.rgb(254, 23, 5));
                et_c_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_s_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_n_cnt.setTextColor(Color.rgb(29, 29, 38));


                people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
                people_dto_list.clear();

                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    SubPeopleMatchListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_gubun1().equals("L"))
                        people_dto_list.add(new SubPeopleMatchListDTO(
                                dto.get_profile_uid()
                                , dto.get_profile_username()
                                , dto.get_profile_email()
                                , dto.get_profile_type()
                                , dto.get_profile_gubun1()
                                , dto.get_profile_gubun2()
                                , dto.get_profile_speed()
                                , dto.get_profile_control()
                                , dto.get_profile_total_value()
                                , dto.get_profile_score()
                                , dto.get_profile_match_value()
                        ));

                }

                people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);
                //peopleList();
            }
        });

        people_gubun_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "C";
                people_gubun_all_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_family_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_friend_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_lover_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_job_line.setBackgroundColor(Color.rgb(254, 23, 5));
                people_gubun_client_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_not_line.setBackgroundColor(Color.rgb(241, 241, 241));

                et_all_title.setTextColor(Color.rgb(29, 29, 38));
                et_p_title.setTextColor(Color.rgb(29, 29, 38));
                et_f_title.setTextColor(Color.rgb(29, 29, 38));
                et_l_title.setTextColor(Color.rgb(29, 29, 38));
                et_c_title.setTextColor(Color.rgb(254, 23, 5));
                et_s_title.setTextColor(Color.rgb(29, 29, 38));
                et_n_title.setTextColor(Color.rgb(29, 29, 38));

                et_all_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_p_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_f_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_l_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_c_cnt.setTextColor(Color.rgb(254, 23, 5));
                et_s_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_n_cnt.setTextColor(Color.rgb(29, 29, 38));

                people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
                people_dto_list.clear();

                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    SubPeopleMatchListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_gubun1().equals("C"))
                        people_dto_list.add(new SubPeopleMatchListDTO(
                                dto.get_profile_uid()
                                , dto.get_profile_username()
                                , dto.get_profile_email()
                                , dto.get_profile_type()
                                , dto.get_profile_gubun1()
                                , dto.get_profile_gubun2()
                                , dto.get_profile_speed()
                                , dto.get_profile_control()
                                , dto.get_profile_total_value()
                                , dto.get_profile_score()
                                , dto.get_profile_match_value()
                        ));

                }

                people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);


                //peopleList();
            }
        });

        people_gubun_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "S";
                people_gubun_all_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_family_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_friend_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_lover_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_job_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_client_line.setBackgroundColor(Color.rgb(254, 23, 5));
                people_gubun_not_line.setBackgroundColor(Color.rgb(241, 241, 241));

                et_all_title.setTextColor(Color.rgb(29, 29, 38));
                et_p_title.setTextColor(Color.rgb(29, 29, 38));
                et_f_title.setTextColor(Color.rgb(29, 29, 38));
                et_l_title.setTextColor(Color.rgb(29, 29, 38));
                et_c_title.setTextColor(Color.rgb(29, 29, 38));
                et_s_title.setTextColor(Color.rgb(254, 23, 5));
                et_n_title.setTextColor(Color.rgb(29, 29, 38));

                et_all_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_p_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_f_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_l_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_c_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_s_cnt.setTextColor(Color.rgb(254, 23, 5));
                et_n_cnt.setTextColor(Color.rgb(29, 29, 38));

                people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
                people_dto_list.clear();

                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    SubPeopleMatchListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_gubun1().equals("S"))
                        people_dto_list.add(new SubPeopleMatchListDTO(
                                dto.get_profile_uid()
                                , dto.get_profile_username()
                                , dto.get_profile_email()
                                , dto.get_profile_type()
                                , dto.get_profile_gubun1()
                                , dto.get_profile_gubun2()
                                , dto.get_profile_speed()
                                , dto.get_profile_control()
                                , dto.get_profile_total_value()
                                , dto.get_profile_score()
                                , dto.get_profile_match_value()
                        ));

                }

                people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);


                //peopleList();
            }
        });

        people_gubun_not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchType = "";
                people_gubun_all_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_family_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_friend_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_lover_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_job_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_client_line.setBackgroundColor(Color.rgb(241, 241, 241));
                people_gubun_not_line.setBackgroundColor(Color.rgb(254, 23, 5));

                et_all_title.setTextColor(Color.rgb(29, 29, 38));
                et_p_title.setTextColor(Color.rgb(29, 29, 38));
                et_f_title.setTextColor(Color.rgb(29, 29, 38));
                et_l_title.setTextColor(Color.rgb(29, 29, 38));
                et_c_title.setTextColor(Color.rgb(29, 29, 38));
                et_s_title.setTextColor(Color.rgb(29, 29, 38));
                et_n_title.setTextColor(Color.rgb(254, 23, 5));

                et_all_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_p_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_f_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_l_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_c_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_s_cnt.setTextColor(Color.rgb(29, 29, 38));
                et_n_cnt.setTextColor(Color.rgb(254, 23, 5));

                people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
                people_dto_list.clear();

                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    SubPeopleMatchListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_gubun1().equals(""))
                        people_dto_list.add(new SubPeopleMatchListDTO(
                                dto.get_profile_uid()
                                , dto.get_profile_username()
                                , dto.get_profile_email()
                                , dto.get_profile_type()
                                , dto.get_profile_gubun1()
                                , dto.get_profile_gubun2()
                                , dto.get_profile_speed()
                                , dto.get_profile_control()
                                , dto.get_profile_total_value()
                                , dto.get_profile_score()
                                , dto.get_profile_match_value()
                        ));

                }

                people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);


                //peopleList();
            }
        });

        peopleList();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void close_match(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);

    }


    private void peopleList()
    {
        people_dto_list = new ArrayList<SubPeopleMatchListDTO>();
        people_dto_list_temp = new ArrayList<SubPeopleMatchListDTO_Temp>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(PeopleMatchTop10_Activity.this, "uid"));
        //params.put("searchType", searchType);
        HttpClient.post("/user/user_matchList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(PeopleMatchTop10_Activity.this, "", "데이터 수신중");
            }

            public void onFailure() {
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject data = new JSONObject(response);
                    JSONArray people_list = data.getJSONArray("people_list");

                    for (int i = 0; i < people_list.length(); i++) {
                        JSONObject jobj = people_list.getJSONObject(i);


                        String gubun1 = jobj.getString("GUBUN1");

                        all_cnt++;

                        if (gubun1.equals("P")) {
                            p_cnt++;
                        } else if (gubun1.equals("F")) {
                            f_cnt++;
                        } else if (gubun1.equals("L")) {
                            l_cnt++;
                        } else if (gubun1.equals("C")) {
                            c_cnt++;
                        } else if (gubun1.equals("S")) {
                            s_cnt++;
                        } else {
                            n_cnt++;
                        }

                        //public SubPeopleMatchListDTO(String profile_uid, String profile_username, String profile_email, String profile_type, String profile_gubun1, String profile_gubun2, int profile_people_speed, int profile_people_control, int profile_total_value, int profile_score_temp, double profile_match_value)

                        people_dto_list.add(new SubPeopleMatchListDTO(
                                jobj.getString("PEOPLE_UID")
                                , jobj.getString("PEOPLE_USERNAME")
                                , jobj.getString("PEOPLE_EMAIL")
                                , jobj.getString("YOU_TYPE")
                                , jobj.getString("GUBUN1")
                                , jobj.getString("GUBUN2")
                                , jobj.getInt("PEOPLE_SPEED")
                                , jobj.getInt("PEOPLE_CONTROL")
                                , jobj.getInt("TOTAL_VALUE")
                                , jobj.getInt("SCORE_TEMP")
                                , jobj.getDouble("MATCH_VALUE")
                        ));


                        people_dto_list_temp.add(new SubPeopleMatchListDTO_Temp(
                                jobj.getString("PEOPLE_UID")
                                , jobj.getString("PEOPLE_USERNAME")
                                , jobj.getString("PEOPLE_EMAIL")
                                , jobj.getString("YOU_TYPE")
                                , jobj.getString("GUBUN1")
                                , jobj.getString("GUBUN2")
                                , jobj.getInt("PEOPLE_SPEED")
                                , jobj.getInt("PEOPLE_CONTROL")
                                , jobj.getInt("TOTAL_VALUE")
                                , jobj.getInt("SCORE_TEMP")
                                , jobj.getDouble("MATCH_VALUE")
                        ));



                    }

                    people_adapter_list = new SubPeopleMatchListAdapter(PeopleMatchTop10_Activity.this, R.layout.sub_people_match_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);

                    et_all_cnt.setText(String.valueOf(all_cnt));
                    et_p_cnt.setText(String.valueOf(p_cnt));
                    et_f_cnt.setText(String.valueOf(f_cnt));
                    et_l_cnt.setText(String.valueOf(l_cnt));
                    et_c_cnt.setText(String.valueOf(c_cnt));
                    et_s_cnt.setText(String.valueOf(s_cnt));
                    et_n_cnt.setText(String.valueOf(n_cnt));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
