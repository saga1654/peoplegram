package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubGramHistory extends AppCompatActivity {

    private ListView pointhistory;
    private ArrayList<PointHistoryDTO> point_dto;
    private ProgressDialog dialog;

    private PointHistoryAdapter point_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_gram_history);

        pointhistory = (ListView) findViewById(R.id.pointhistory);
        pointList();
    }


    private void pointList()
    {
        point_dto = new ArrayList<PointHistoryDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(SubGramHistory.this, "uid"));
        HttpClient.post("/user/pointList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(SubGramHistory.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray point_list = new JSONArray(response);
                    for (int i = 0; i < point_list.length(); i++) {
                        JSONObject jobj = point_list.getJSONObject(i);

                        String datetime = jobj.getString("CREATE_DATETIME");
                        datetime = datetime.substring(0, 10);
                        String point_msg = jobj.getString("POINT_MSG");
                        String type = jobj.getString("TYPE");
                        String point = jobj.getString("ADD_POINT");

                        point_dto.add(
                                new PointHistoryDTO(
                                        datetime
                                        ,point_msg
                                        ,point
                                )
                        );

                    }

                    point_adapter = new PointHistoryAdapter(SubGramHistory.this, R.layout.sub_pointhistory_row, point_dto);
                    pointhistory.setAdapter(point_adapter);

                    //listview_noticeList.setAdapter(notice_adapter);
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

    public void btn_back(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
