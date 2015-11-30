package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class SubQnaActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private ArrayList<QnaDTO> qna_dto;
    private QnaAdapter qna_adapter;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_qna);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QnaDTO dto = (QnaDTO) listView.getItemAtPosition(position);
                Intent intent = new Intent(SubQnaActivity.this, SubQnaViewActivity.class);
                Log.d("people_gram", dto.get_QnaCode());
                intent.putExtra("CODE", dto.get_QnaCode());
                startActivity(intent);
                overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });

        //qnaData();
    }

    public void onStart()
    {
        super.onStart();
        qnaData();
    }

    public void qnaData()
    {

        qna_dto = new ArrayList<QnaDTO>();
        RequestParams params = new RequestParams();

        HttpClient.post("/setting/qnaList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(SubQnaActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response)
            {
                try {
                    JSONArray qnaList = new JSONArray(response);


                    for(int i = 0; i < qnaList.length(); i++) {
                        JSONObject jobj = qnaList.getJSONObject(i);
                        Log.d("people_gram", jobj.getString("CODE") + ":::" + jobj.getString("SUBJECT_Q") + ":::" + jobj.getString("CREATE_DATE") + ":::" + jobj.getString("SUBJECT_A"));

                        qna_dto.add(new QnaDTO(
                                jobj.getString("CODE")
                                , jobj.getString("SUBJECT_Q")
                                , jobj.getString("CREATE_DATE")
                                , jobj.getString("SUBJECT_A")
                        ));
                        qna_adapter = new QnaAdapter(getApplication(), R.layout.activity_qna_rowlist, qna_dto);
                        listView.setAdapter(qna_adapter);
                    }




                } catch (JSONException e) {
                    Log.d("people_gram", "실패");
                    e.printStackTrace();
                }
            }


        });
    }

    public void qnaWrite(View v) {
        Intent intent = new Intent(SubQnaActivity.this, SubQnaWriteActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
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

    public void qna_prevBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
