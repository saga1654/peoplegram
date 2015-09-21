package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ListView listview_noticeList;
    private ArrayList<NoticeDTO> noticeDTOList;
    private NoticeAdapter notice_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        RequestParams params = new RequestParams();

        noticeDTOList = new ArrayList<NoticeDTO>();
        listview_noticeList = (ListView) findViewById(R.id.listview_noticeList);

        HttpClient.post("/setting/noticeList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(NoticeActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                //Log.d("people_gram", "완료");
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response)
            {
                try {
                    JSONArray noticeList = new JSONArray(response);


                    for(int i = 0; i < noticeList.length(); i++) {
                        JSONObject jobj = noticeList.getJSONObject(i);

                        noticeDTOList.add(new NoticeDTO(
                                jobj.getString("CODE")
                                , jobj.getString("SUBJECT")
                                , jobj.getString("CREATE_DATE")
                        ));

                        notice_adapter = new NoticeAdapter(getBaseContext(), R.layout.activity_notice_rowlist, noticeDTOList);
                        listview_noticeList.setAdapter(notice_adapter);
                        //Log.d("people_gram", jobj.getString("CODE") + ":::" + jobj.getString("SUBJECT") + ":::" + jobj.getString("CONTENTS"));
                    }



                } catch (JSONException e) {
                    Log.d("people_gram", "실패");
                    e.printStackTrace();
                }
            }


        });




    }
}
