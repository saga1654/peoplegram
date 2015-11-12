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
import android.widget.LinearLayout;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubMyPagePeopleHide_Activity extends AppCompatActivity {



    private ArrayList<SubPeopleListDTO> people_dto_list;
    private SubPeopleListHideAdapter people_adapter_list;
    private ProgressDialog dialog;

    private ListView people_hide_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_my_page_people_hide_);

        people_hide_list = (ListView) findViewById(R.id.people_hide_list);

        peopleList();

    }


    private void peopleList()
    {
        Log.d("people_gram", "성공");
        people_dto_list = new ArrayList<SubPeopleListDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubMyPagePeopleHide_Activity.this, "uid"));
        //params.put("searchType", searchType);
        HttpClient.post("/user/member_people_hide", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SubMyPagePeopleHide_Activity.this, "", "데이터 수신중");
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



                        //Log.d("people_gram", jobj.getInt("NOW_CHECK") + ":::" + jobj.getInt("NOW_MAX"));
                    }

                    people_adapter_list = new SubPeopleListHideAdapter(SubMyPagePeopleHide_Activity.this, R.layout.sub_people_hide_row_list, people_dto_list);
                    people_hide_list.setAdapter(people_adapter_list);

                    //Log.d("people_gram", all_cnt + "::" + p_cnt + "::" + f_cnt + "::" + c_cnt + "::" + c_cnt + ":::" + s_cnt + "::" + n_cnt);


                    //dialog.dismiss();
                    //listview_noticeList.setAdapter(notice_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void peopleClose(View v) {
        finish();
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
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

}
