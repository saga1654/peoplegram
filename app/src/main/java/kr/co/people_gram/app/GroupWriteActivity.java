package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GroupWriteActivity extends AppCompatActivity {

    private ArrayList<SubGroupPeopleListDTO> people_dto_list;
    private ArrayList<SubGroupPeopleListDTO_Temp> people_dto_list_temp;
    private SubGroupPeopleListAdapter people_adapter_list;
    private ListView sf_people_list;
    private ProgressDialog dialog;
    private TextView now_cnt;

    private LinearLayout groupBtn;
    private EditText group_name;
    private EditText search_edit_name;
    private String search_edit_name_string = "";

    private String group_code = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_write);

        sf_people_list = (ListView) findViewById(R.id.sf_people_list);

        Intent intent = getIntent();
        if(intent != null) {
            group_code = intent.getStringExtra("group_code");
            //group_name_txt = intent.getStringExtra("group_name");
        }

        //search_edit_name = (EditText) findViewById(R.id.search_edit_name);

        now_cnt = (TextView) findViewById(R.id.now_cnt);
        groupBtn = (LinearLayout) findViewById(R.id.groupBtn);
        //group_name = (EditText) findViewById(R.id.group_name);
        //group_name.setText(group_name_txt);
        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupInsert();
            }
        });

        peopleList();
        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //people_dto_list.
                //final SubPeopleListDTO dto = (SubPeopleListDTO) people_hide_list.getItemAtPosition(position);

                people_adapter_list.setChecked(position);
                people_adapter_list.notifyDataSetChanged();
            }
        });


        /*
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                people_dto_list.clear();
                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    search_edit_name_string = String.valueOf(search_edit_name.getText());
                    Log.d("people_gram", search_edit_name_string);

                    SubGroupPeopleListDTO_Temp dto = people_dto_list_temp.get(i);
                    //people_adapter_list.setChecked(i);
                    if(SoundSearcher.matchString(dto.get_profile_username().toString(), search_edit_name_string)) {
                        Boolean check = dto.get_checked();
                        people_dto_list.add(new SubGroupPeopleListDTO(
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
                                , dto.get_group_count()
                                , check
                        ));
                    }
                }

                people_adapter_list = new SubGroupPeopleListAdapter(GroupWriteActivity.this, R.layout.sub_group_people_row_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);
                //people_adapter_list = new SubPeopleListAdapter(GroupWriteActivity.this, R.layout.sub_people_rowsearch_list, people_dto_list);


                //sf_people_list.setAdapter(people_adapter_list);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        search_edit_name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(search_edit_name.getWindowToken(), 0);
                        //finish();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });
        search_edit_name.addTextChangedListener(watcher);
        */
    }


    private void peopleList()
    {
        Log.d("people_gram", "성공");
        people_dto_list = new ArrayList<SubGroupPeopleListDTO>();
        people_dto_list_temp = new ArrayList<SubGroupPeopleListDTO_Temp>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(GroupWriteActivity.this, "uid"));
        params.put("group_code", group_code);
        //params.put("searchType", searchType);
        HttpClient.post("/group/groupPeopleList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(GroupWriteActivity.this, "", "데이터 수신중");
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


                        people_dto_list.add(new SubGroupPeopleListDTO(
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
                                , jobj.getInt("GROUP_COUNT")
                                , false
                        ));

                        people_dto_list_temp.add(new SubGroupPeopleListDTO_Temp(
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
                                , jobj.getInt("GROUP_COUNT")
                                , false
                        ));


                    }

                    people_adapter_list = new SubGroupPeopleListAdapter(GroupWriteActivity.this, R.layout.sub_group_people_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void groupInsert() {
        int check = 0;
        for(int i = 0; i<people_adapter_list.isCheckedConfrim.length; i++) {
            if(people_adapter_list.isCheckedConfrim[i] == true) {
                check++;
            }
        }

        Log.d("people_gram", String.valueOf(check));



            if (check != 0) {
                RequestParams params = new RequestParams();
                params.put("uid", SharedPreferenceUtil.getSharedPreference(GroupWriteActivity.this, "uid"));
                params.put("username", SharedPreferenceUtil.getSharedPreference(GroupWriteActivity.this, "username"));
                params.put("group_code", group_code);
                //params.put("group_name", group_name.getText().toString());
                params.put("people_uid", people_adapter_list.uid_check);
                params.put("people_username", people_adapter_list.username_check);
                HttpClient.post("/group/groupWrite", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        dialog = ProgressDialog.show(GroupWriteActivity.this, "", "데이터 수신중");
                    }

                    public void onFailure() {
                    }

                    public void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(String response) {
                        if(response.equals("000")) {
                            Intent intent = new Intent();
                            setResult(41, intent);
                            finish();
                        }
                        //Log.d("people_gram", response);
                    }
                });
            } else {
                Toast.makeText(GroupWriteActivity.this, "그룹에 등록할 피플을 선택해주세요.", Toast.LENGTH_LONG).show();
            }


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

    public void groupwrite_close(View v)
    {
        finish();
    }

}
