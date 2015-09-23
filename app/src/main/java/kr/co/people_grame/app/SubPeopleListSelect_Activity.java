package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubPeopleListSelect_Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ProgressDialog dialog;
    private ListView contentList;

    private ArrayList<SubMainListDTO> dto;
    private SubMainListAdapter adapter;

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_list_select_);

        contentList = (ListView) findViewById(R.id.mainContent);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeLayout.setOnRefreshListener(this);


        RequestParams params = new RequestParams();
        params.put("UID", "");

        dto = new ArrayList<SubMainListDTO>();

        HttpClient.post("/people/peopleContentList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(SubPeopleListSelect_Activity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray contest_list = new JSONArray(response);
                    for (int i = 0; i < contest_list.length(); i++) {
                        JSONObject jobj = contest_list.getJSONObject(i);
                        Log.d("people_gram", String.valueOf(jobj));
                        //String my_profile_img, String my_profile_nickname, String my_profile_type, String you_profile_img, String you_profile_nickname, String you_profile_type, String Contents, String insert_datetime, String gonggam_cnt, String comment_cnt
                        //Log.d("people_gram", jobj.getString("MY_PROFILE_NICKNAME"));


                        dto.add(new SubMainListDTO(
                                ""
                                ,jobj.getString("MY_PROFILE_NICKNAME")
                                ,jobj.getString("MY_PROFILE_TYPE")
                                ,""
                                ,jobj.getString("YOU_PROFILE_NICKNAME")
                                ,jobj.getString("YOU_PROFILE_TYPE")
                                ,jobj.getString("CONTENTS")
                                ,jobj.getString("INSERT_DATETIME")
                                ,jobj.getString("GONGGAM_CNT")
                                ,jobj.getString("COMMENT_CNT")
                        ));


                        adapter = new SubMainListAdapter(SubPeopleListSelect_Activity.this, R.layout.sub_fragment_main_row_list, dto);
                        contentList.setAdapter(adapter);

                        //contentList = new SubMainListAdapter(getActivity().getBaseContext(), R.layout.sub_fragment_main_row_list, dto);
                        //sf_people_list.setAdapter(people_adapter_list);
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

        }
        return super.onKeyDown(keyCode, event);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_people_list_select_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {

    }
}
