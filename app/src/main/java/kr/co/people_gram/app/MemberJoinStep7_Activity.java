package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MemberJoinStep7_Activity extends AppCompatActivity {

    private Spinner area1, area2, area3;
    ArrayList<String> arraylist;
    ArrayList<String> arraylist2;
    ArrayList<String> arraylist3;

    private ProgressDialog dialog;

    private LinearLayout nextLL;

    private String area1_string, area2_string, area3_string;
    private CheckBox panel_join_chk;
    private String panel_YN = "N";

    private MemberData md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step7_);


        panel_join_chk = (CheckBox) findViewById(R.id.panel_join_chk);


        md = new MemberData();

        area1_string = "시/도";
        area2_string = "시/구/군";
        area3_string = "동/면/리";

        arraylist = new ArrayList<String>();
        arraylist2 = new ArrayList<String>();
        arraylist3 = new ArrayList<String>();

        area1 = (Spinner) findViewById(R.id.area1);
        area2 = (Spinner) findViewById(R.id.area2);
        area3 = (Spinner) findViewById(R.id.area3);

        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setVisibility(View.INVISIBLE);

        arraylist2.clear();
        arraylist2.add("시/구/군");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                android.R.layout.simple_spinner_dropdown_item, arraylist2);
        area2.setPrompt("시/구/군"); // 스피너 제목
        area2.setAdapter(adapter2);


        arraylist3.clear();
        arraylist3.add("동/면/리");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                android.R.layout.simple_spinner_dropdown_item, arraylist3);
        area3.setPrompt("동/읍/면"); // 스피너 제목
        area3.setAdapter(adapter3);

        panel_join_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false) {
                    panel_YN = "N";
                } else {
                    panel_YN = "Y";
                }
            }
        });

        sido();
        area1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area1_string = arraylist.get(position);


                if (arraylist.get(position).equals("시/도")) {
                    gugun_default();
                    dong_default();
                } else {
                    gugun(arraylist.get(position));


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        area2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                area2_string = arraylist2.get(position);


                if(arraylist2.get(position).equals("동/읍/면")) {
                    dong_default();
                } else {
                    dong(arraylist2.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        area3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area3_string = arraylist3.get(position);
                if(area3_string.equals("동/읍/면")) {
                    nextLL.setVisibility(View.INVISIBLE);
                } else {
                    nextLL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(MemberJoinStep7_Activity.this, MemberComplate_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                next_finish();
                */

                md.set_area1(area1_string);
                md.set_area1(area2_string);
                md.set_area1(area3_string);

                RequestParams params = new RequestParams();
                params.put("userID", md.get_userid());
                params.put("userPW", md.get_userpw());
                params.put("userNickName", md.get_nickname());
                params.put("phone", md.get_phone());
                params.put("telecom", md.get_telecom());
                params.put("sex", md.get_sex());
                params.put("birthtype", md.get_birthtype());
                params.put("birthday", md.get_birthday());
                params.put("area1", area1_string);
                params.put("area2", area2_string);
                params.put("area3", area3_string);
                params.put("panel_YN", panel_YN);


                HttpClient.post("/user/memberCheck", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        dialog = ProgressDialog.show(MemberJoinStep7_Activity.this, "", "데이터 수신중");
                    }

                    public void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(String response) {
                        Log.d("people_gram", response);
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONObject jobj_data = new JSONObject(jobj.getString("user_data"));
                            String code = jobj.getString("code");

                            if (code.equals("000")) {
                                String uid = jobj_data.get("UID").toString();
                                String userNickName = jobj_data.get("USERNICKNAME").toString();
                                String email = jobj_data.get("EMAIL").toString();

                                //Log.d("people_gram", uid + ":::" + userNickName + ":::" + email);

                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "uid", uid);
                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "username", userNickName);
                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "email", md.get_userid());

                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "point", jobj_data.getString("POINT"));
                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "mytype", "");
                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "my_speed", "");
                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "my_control", "");
                                SharedPreferenceUtil.putSharedPreference(MemberJoinStep7_Activity.this, "panelYN", panel_YN);

                                Intent intent = new Intent(MemberJoinStep7_Activity.this, MemberComplate_Activity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                                next_finish();


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Log.d("people_gram", response);
                    }


                });



            }
        });
    }

    public void sido()
    {
        RequestParams params = new RequestParams();
        HttpClient.post("/panel/search_sido", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //dialog = ProgressDialog.show(MemberJoinStep7_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                //dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                JSONArray json;
                try {
                    json = new JSONArray(response);
                    arraylist.clear();
                    arraylist.add("시/도");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jobj = json.getJSONObject(i);
                        arraylist.add(jobj.getString("SIDO"));
                        //Log.d("people_gram", jobj.getString("SIDO"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                            android.R.layout.simple_spinner_dropdown_item, arraylist);
                    area1.setPrompt("시/도"); // 스피너 제목
                    area1.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Log.d("people_gram", response);
            }


        });

    }

    private void gugun_default()
    {
        arraylist2.clear();
        arraylist2.add("시/구/군");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                android.R.layout.simple_spinner_dropdown_item, arraylist2);
        area2.setPrompt("시/구/군"); // 스피너 제목
        area2.setAdapter(adapter);



        arraylist3.clear();
        arraylist3.add("동/읍/면");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                android.R.layout.simple_spinner_dropdown_item, arraylist3);
        area3.setPrompt("동/읍/면"); // 스피너 제목
        area3.setAdapter(adapter);

        nextLL.setVisibility(View.INVISIBLE);
    }

    public void gugun(String sido)
    {
        RequestParams params = new RequestParams();
        params.put("sido", sido);
        HttpClient.post("/panel/search_gugun", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //dialog = ProgressDialog.show(MemberJoinStep7_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                //dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
                JSONArray json;
                try {
                    json = new JSONArray(response);
                    arraylist2.clear();
                    arraylist2.add("시/구/군");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jobj = json.getJSONObject(i);
                        arraylist2.add(jobj.getString("GUGUN"));
                        //Log.d("people_gram", jobj.getString("SIDO"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                            android.R.layout.simple_spinner_dropdown_item, arraylist2);
                    area2.setPrompt("구/군"); // 스피너 제목
                    area2.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Log.d("people_gram", response);
            }


        });

    }

    public void dong_default()
    {
        arraylist3.clear();
        arraylist3.add("동/읍/면");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                android.R.layout.simple_spinner_dropdown_item, arraylist3);
        area3.setPrompt("동/읍/면"); // 스피너 제목
        area3.setAdapter(adapter);
        nextLL.setVisibility(View.INVISIBLE);
    }

    public void dong(String gugun)
    {
        RequestParams params = new RequestParams();
        params.put("gugun", gugun);
        HttpClient.post("/panel/search_dong", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //dialog = ProgressDialog.show(MemberJoinStep7_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                //dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
                JSONArray json;
                try {
                    json = new JSONArray(response);
                    arraylist3.clear();
                    arraylist3.add("동/읍/면");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jobj = json.getJSONObject(i);
                        arraylist3.add(jobj.getString("DONG"));
                        //Log.d("people_gram", jobj.getString("SIDO"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MemberJoinStep7_Activity.this,
                            android.R.layout.simple_spinner_dropdown_item, arraylist3);
                    area3.setPrompt("동/읍/면"); // 스피너 제목
                    area3.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Log.d("people_gram", response);
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

    public void next_finish()
    {
        super.finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

    public void closeMember(View v)
    {
        finish();
    }

}
