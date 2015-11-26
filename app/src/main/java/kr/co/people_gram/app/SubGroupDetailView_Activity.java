package kr.co.people_gram.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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


public class SubGroupDetailView_Activity extends AppCompatActivity {

    public static Activity subgroupdetailview_activity;
    private String group_code = "";
    private String group_name = "";
    private TextView mytype_tv;

    private ArrayList<SubGroupDetailPeopleListDTO> people_dto_list;
    private SubGroupDetailPeopleListAdapter people_adapter_list;
    private ProgressDialog dialog;
    private ListView sf_people_list;
    private ImageView group_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_group_detail_view_);
        mytype_tv = (TextView) findViewById(R.id.mytype_tv);
        mytype_tv.setText(Html.fromHtml("그룹 내 멤버 아무도 진단하지 않았습니다."));
        subgroupdetailview_activity = this;

        sf_people_list = (ListView) findViewById(R.id.sf_people_list);
        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                people_adapter_list.setChecked(position);
                people_adapter_list.notifyDataSetChanged();

                final SubGroupDetailPeopleListDTO dto = (SubGroupDetailPeopleListDTO) sf_people_list.getItemAtPosition(position);




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
            group_name = intent.getStringExtra("group_name");
            Log.d("people_gram", group_code);
        }

        group_create = (ImageView) findViewById(R.id.group_create);
        group_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubGroupDetailView_Activity.this, GroupWriteActivity.class);
                intent.putExtra("group_code", group_code);
                intent.putExtra("group_name", group_name);
                startActivityForResult(intent, 42);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

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
        people_dto_list = new ArrayList<SubGroupDetailPeopleListDTO>();
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
                    JSONObject my_data = new JSONObject(data.getString("my_data"));
                    JSONArray people_list = data.getJSONArray("people");

                    String people_type = data.getString("peopleType");
                    int people_speed = my_data.getInt("PEOPLE_SPEED");
                    int people_control = my_data.getInt("PEOPLE_CONTROL");

                    ImageView popup_type = (ImageView) findViewById(R.id.popup_type);

                    switch (people_type) {
                        case "I":
                            popup_type.setImageResource(R.mipmap.people_type_i);
                            break;
                        case "D":
                            popup_type.setImageResource(R.mipmap.people_type_d);
                            break;
                        case "E":
                            popup_type.setImageResource(R.mipmap.people_type_e);
                            break;
                        case "A":
                            popup_type.setImageResource(R.mipmap.people_type_a);
                            break;
                        default:
                            popup_type.setImageResource(R.mipmap.people_type_default);
                            break;
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


                        people_dto_list.add(new SubGroupDetailPeopleListDTO(
                                group_code
                                , jobj.getString("PEOPLE_UID")
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

                    people_adapter_list = new SubGroupDetailPeopleListAdapter(SubGroupDetailView_Activity.this, R.layout.sub_people_group_detail_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);

                    if (people_speed > 0 && people_control > 0) {


                        if (people_speed <= 5 && people_control <= 1) {
                            if (people_speed <= 1 && people_control <= 1) {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를  <b>꼼꼼하기도 하면서 때론 완벽을 추구하는</b> <b color='#ff8a55'>강한 추진력과 모험심 있는 스타일</b>로 생각하고 있습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>사람과의 관계와 소통을 중요시하며</b> <b color='#ff8a55'>때론 추진력과 도전을 좋아하는 스타일</b>로 생각하고 있습니다."));
                            }
                        } else if (people_speed <= 1 && people_control <= 5) {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>꼼꼼하면서</b> <b color='#ff8a55'>밀어부치기도 도전을 좋아하기도 하는 스타일</b>로 생각하고 있습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b color='#ff8a55'>새로운 도전과 모험, 강한 추진력을 가진 스타일</b>로 생각하고 있습니다."));
                        }
                    }
                    if (people_speed > 0 && people_control < 0) {

                        if (people_speed <= 5 && people_control >= -1) {
                            if (people_speed <= 1 && people_control >= -1) {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>가끔 밀어부치기도 하고 그러나 상대방을 배려하는 이해심도 가진</b><b color='#aa64f8'>소통의 달인</b>으로 생각하고 있습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>사람을 끄는 힘을 가진</b> <b color='#aa64f8'>관계 형성이 좋은 스타일</b>로 생각하고 있습니다."));
                            }
                        } else if (people_speed <= 1 && people_control >= -5) {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>상대에 대한 이해심이 높고 믿을 수 있는</b> <b color='#aa64f8'>소통하고 싶은 스타일</b>로 생각하고 있습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b color='#aa64f8'>인간관계가 중요하고 큰 문제가 없으며 상대의 마음을 진심으로 대하는 스타일</b>로 생각하고 있습니다."));
                        }
                    }
                    if (people_speed < 0 && people_control > 0) {

                        if (people_speed >= -5 && people_control <= 1) {
                            if (people_speed >= -1 && people_control <= 1) {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>도전정신을 가진 이해심 깊은</b><b color='#37afec'>완벽주의자</b>로 생각하고 있습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>속 깊이 잘 챙기는</b><b color='#37afec'>꼼꼼주의자</b>로 생각하고 있습니다."));
                            }
                        } else if (people_speed >= -1 && people_control <= 5) {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>새로운 것을 마다하지 않고 끝까지 완력하게 꼼꼼히 이루는 스타일</b>로 생각하고 있습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b color='#37afec'>꼼꼼한 완벽주의자</b>로 생각하고 있습니다."));
                        }
                    }
                    if (people_speed < 0 && people_control < 0) {

                        if (people_speed >= -5 && people_control >= -1) {
                            if (people_speed >= -1 && people_control >= -1) {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>사람과의 관계에서 이해심이 높지만 그래도 나름 계산하여 마음을 보이는 스타일</b>로 생각하고 있습니다."));
                            } else {
                                mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>꼼꼼히 상대를 알아보고 끝까지 믿어주는 스타일</b>로 생각하고 있습니다."));
                            }
                        } else if (people_speed >= -1 && people_control >= -5) {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b>상대를 이해하며 좋은 인간관계를 끝까지 유지하는 스타일</b>로 생각하고 있습니다."));
                        } else {
                            mytype_tv.setText(Html.fromHtml("그룹 내 멤버들은 귀하를 <b color='#52d935'>상대방에 대한 배려심 좋고 신뢰성 있는 스타일</b>로 생각하고 있습니다."));
                        }
                    }
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
