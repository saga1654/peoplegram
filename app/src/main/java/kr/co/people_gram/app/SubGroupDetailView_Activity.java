package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SubGroupDetailView_Activity extends AppCompatActivity {

    private String group_code = "";

    private ArrayList<SubPeopleListDTO> people_dto_list;
    private SubPeopleListAdapter people_adapter_list;
    private ProgressDialog dialog;
    private ListView sf_people_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_group_detail_view_);

        sf_people_list = (ListView) findViewById(R.id.sf_people_list);
        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                    final SubPeopleListDTO dto = (SubPeopleListDTO) sf_people_list.getItemAtPosition(position);




                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(SubGroupDetailView_Activity.this, "uid"));
                    params.put("people_uid", dto.get_profile_uid());
                    HttpClient.post("/people/peoplePopupView", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            dialog = ProgressDialog.show(SubGroupDetailView_Activity.this, "", "데이터 수신중");
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
                            Intent intent = new Intent(SubGroupDetailView_Activity.this, SubPeopleListPopup_Activity.class);
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
                            intent.putExtra("people_coaching", response);

                            startActivityForResult(intent, 42);
                            overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                        }
                    });
            }
        });

        Intent intent = getIntent();
        if(intent != null) {
            group_code = intent.getStringExtra("group_code");
            Log.d("people_gram", group_code);
        }

        peopleList();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("people_gram", String.valueOf(requestCode) + ":::" + resultCode);

        if(resultCode == 42) {
            peopleList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void peopleList()
    {
        Log.d("people_gram", "성공");
        people_dto_list = new ArrayList<SubPeopleListDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubGroupDetailView_Activity.this, "uid"));
        params.put("group_code", group_code);

        Log.d("people_gram", SharedPreferenceUtil.getSharedPreference(SubGroupDetailView_Activity.this, "uid") + ":::" + group_code);
        HttpClient.post("/group/groupDetailList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SubGroupDetailView_Activity.this, "", "데이터 수신중");
            }

            public void onFailure() {
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
                try {

                    JSONObject data = new JSONObject(response);
                    JSONArray people_list = data.getJSONArray("people");



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
                                    , 0
                                    , 0
                                    , 0
                            ));




                        //Log.d("people_gram", jobj.getInt("NOW_CHECK") + ":::" + jobj.getInt("NOW_MAX"));
                    }

                    people_adapter_list = new SubPeopleListAdapter(SubGroupDetailView_Activity.this, R.layout.sub_people_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


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

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

    public void detailClose(View v)
    {
        finish();
    }
}
