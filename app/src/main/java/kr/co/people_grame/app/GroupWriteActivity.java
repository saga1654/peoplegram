package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
    private SubGroupPeopleListAdapter people_adapter_list;
    private ListView sf_people_list;
    private ProgressDialog dialog;
    private TextView now_cnt;

    private LinearLayout groupBtn;
    private EditText group_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_write);

        sf_people_list = (ListView) findViewById(R.id.sf_people_list);

        now_cnt = (TextView) findViewById(R.id.now_cnt);
        groupBtn = (LinearLayout) findViewById(R.id.groupBtn);
        group_name = (EditText) findViewById(R.id.group_name);
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
    }


    private void peopleList()
    {
        Log.d("people_gram", "성공");
        people_dto_list = new ArrayList<SubGroupPeopleListDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(GroupWriteActivity.this, "uid"));
        //params.put("searchType", searchType);
        HttpClient.post("/user/member_sub_people", params, new AsyncHttpResponseHandler() {
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

        if(group_name.getText().length() == 0) {
            Toast.makeText(GroupWriteActivity.this, "그룹이름을 입력해주세요.", Toast.LENGTH_LONG).show();
        } else {

            if (check != 0) {
                RequestParams params = new RequestParams();
                params.put("uid", SharedPreferenceUtil.getSharedPreference(GroupWriteActivity.this, "uid"));
                params.put("group_name", group_name.getText().toString());
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
                            finish();
                        }
                        //Log.d("people_gram", response);
                    }
                });
            } else {
                Toast.makeText(GroupWriteActivity.this, "그룹에 등록할 피플을 선택해주세요.", Toast.LENGTH_LONG).show();
            }
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
