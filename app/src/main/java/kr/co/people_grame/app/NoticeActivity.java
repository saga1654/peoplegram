package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        //prepareListData();

        RequestParams params = new RequestParams();

        mContext = this;





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

                    listDataHeader = new ArrayList<String>();
                    listDataChild = new HashMap<String, List<String>>();


                    for(int i = 0; i < noticeList.length(); i++) {
                        JSONObject jobj = noticeList.getJSONObject(i);
                        /*
                        noticeDTOList.add(new NoticeDTO(
                                jobj.getString("CODE")
                                , jobj.getString("SUBJECT")
                                , jobj.getString("CREATE_DATE")
                        ));

                        notice_adapter = new NoticeAdapter(getBaseContext(), R.layout.activity_notice_rowlist, noticeDTOList);
                        listview_noticeList.setAdapter(notice_adapter);
                        */



                        listDataHeader.add(jobj.getString("SUBJECT") + "///" + jobj.getString("CREATE_DATE"));
                        List<String> nowShowing = new ArrayList<String>();
                        nowShowing.add(jobj.getString("CONTENTS"));
                        listDataChild.put(listDataHeader.get(i), nowShowing);
                        listAdapter = new NoticeAdapter(NoticeActivity.this, listDataHeader, listDataChild);

                    }



                    expListView.setAdapter(listAdapter);



                } catch (JSONException e) {
                    Log.d("people_gram", "실패");
                    e.printStackTrace();
                }
            }


        });
    }

    private void prepareListData() {


        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250);
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}
