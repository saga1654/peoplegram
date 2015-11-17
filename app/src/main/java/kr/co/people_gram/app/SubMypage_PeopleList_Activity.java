package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SubMypage_PeopleList_Activity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ListView sf_people_list;
    private ArrayList<SubPeopleListDTO> people_dto_list;
    private SubPeopleListAdapter people_adapter_list;
    public String searchType = "";
    public String my_type = "";
    public String people_type = "";
    private String list = "";

    private TextView title, titleCnt;
    private ImageView mytype_activity_typeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_mypage__people_list_);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);


        title = (TextView) findViewById(R.id.title);
        titleCnt = (TextView) findViewById(R.id.titleCnt);
        mytype_activity_typeImg = (ImageView) findViewById(R.id.mytype_activity_typeImg);

        Intent intent = getIntent();
        if(intent != null) {
            searchType = intent.getStringExtra("searchType");
            my_type = intent.getStringExtra("my_type");
            people_type = intent.getStringExtra("people_type");
            list = intent.getStringExtra("list");

            Log.d("people_gram", searchType + ":::" + my_type + ":::" + people_type);


            if(list.equals("my")) {
                peopleList();
                title.setText("내가 진단한 피플현황");


                switch (searchType) {
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

            if(list.equals("people")) {
                peopleList2();
                switch (searchType) {
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
                title.setText("나를 진단한 피플현황");
            }
        }


        sf_people_list = (ListView) findViewById(R.id.sf_people_list);
        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final SubPeopleListDTO dto = (SubPeopleListDTO) sf_people_list.getItemAtPosition(position);


                RequestParams params = new RequestParams();
                params.put("uid", SharedPreferenceUtil.getSharedPreference(SubMypage_PeopleList_Activity.this, "uid"));
                params.put("people_uid", dto.get_profile_uid());
                HttpClient.post("/people/peoplePopupView", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        dialog = ProgressDialog.show(SubMypage_PeopleList_Activity.this, "", "데이터 수신중");
                    }

                    public void onFailure() {
                    }

                    public void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(String response) {
                        //Log.d("people_gram", response);
                        Intent intent = new Intent(SubMypage_PeopleList_Activity.this, SubPeopleListPopup_Activity.class);
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

                        startActivityForResult(intent, 000001);
                        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                });
            }
        });
    }


    private void peopleList()
    {
        Log.d("people_gram", "성공");
        people_dto_list = new ArrayList<SubPeopleListDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubMypage_PeopleList_Activity.this, "uid"));
        params.put("searchType", searchType);
        params.put("gubun", my_type);
        HttpClient.post("/user/member_mypage_people", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SubMypage_PeopleList_Activity.this, "", "데이터 수신중");
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


                    switch (my_type)
                    {
                        case "P":
                            titleCnt.setText("가족 ("+people_list.length()+"명)");
                            break;
                        case "F":
                            titleCnt.setText("친구 ("+people_list.length()+"명)");
                            break;
                        case "L":
                            titleCnt.setText("연인 ("+people_list.length()+"명)");
                            break;
                        case "C":
                            titleCnt.setText("직장 ("+people_list.length()+"명)");
                            break;
                        case "S":
                            titleCnt.setText("고객 ("+people_list.length()+"명)");
                            break;
                        default:
                            titleCnt.setText("전체 ("+people_list.length()+"명)");
                            break;
                    }


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
                            , jobj.getInt("TOTAL_COUNT")
                            , jobj.getInt("FRIEND_COUNT")
                            , jobj.getInt("NOW_MAX")
                        ));

                    }

                    people_adapter_list = new SubPeopleListAdapter(SubMypage_PeopleList_Activity.this, R.layout.sub_people_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    private void peopleList2()
    {
        Log.d("people_gram", "성공2");
        people_dto_list = new ArrayList<SubPeopleListDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubMypage_PeopleList_Activity.this, "uid"));
        params.put("searchType", searchType);
        params.put("gubun", people_type);
        HttpClient.post("/user/member_mypage_me_people", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SubMypage_PeopleList_Activity.this, "", "데이터 수신중");
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


                    switch (people_type)
                    {
                        case "P":
                            titleCnt.setText("가족 ("+people_list.length()+"명)");
                            break;
                        case "F":
                            titleCnt.setText("친구 ("+people_list.length()+"명)");
                            break;
                        case "L":
                            titleCnt.setText("연인 ("+people_list.length()+"명)");
                            break;
                        case "C":
                            titleCnt.setText("직장 ("+people_list.length()+"명)");
                            break;
                        case "S":
                            titleCnt.setText("고객 ("+people_list.length()+"명)");
                            break;
                        default:
                            titleCnt.setText("전체 ("+people_list.length()+"명)");
                            break;
                    }


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
                                , jobj.getInt("TOTAL_COUNT")
                                , jobj.getInt("FRIEND_COUNT")
                                , jobj.getInt("NOW_MAX")
                        ));

                    }

                    people_adapter_list = new SubPeopleListAdapter(SubMypage_PeopleList_Activity.this, R.layout.sub_people_row_list, people_dto_list);
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

    public void mypage_people_close(View v)
    {
        finish();
    }

}
