package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubMainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private ProgressDialog dialog;
    private ListView contentList;
    private ImageButton have_write_btn;

    private ArrayList<SubMainListDTO> dto;
    private SubMainListAdapter adapter;

    private SwipeRefreshLayout swipeLayout;


    public SubMainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_main, container, false);
        View header = inflater.inflate(R.layout.sub_fragment_main_header, null, false);




        have_write_btn = (ImageButton) rootView.findViewById(R.id.have_write_btn);
        have_write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HaveWriteActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

        contentList = (ListView) rootView.findViewById(R.id.mainContent);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeLayout.setOnRefreshListener(this);

        contentList.addHeaderView(header);


        RequestParams params = new RequestParams();
        params.put("UID", "");

        dto = new ArrayList<SubMainListDTO>();


        HttpClient.post("/people/peopleContentList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(getActivity(), "", "체크");
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
                        //Log.d("people_gram", String.valueOf(jobj));
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


                        adapter = new SubMainListAdapter(getActivity().getBaseContext(), R.layout.sub_fragment_main_row_list, dto);
                        contentList.setAdapter(adapter);

                        //contentList = new SubMainListAdapter(getActivity().getBaseContext(), R.layout.sub_fragment_main_row_list, dto);
                        //sf_people_list.setAdapter(people_adapter_list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });



        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 5000);

    }
}
