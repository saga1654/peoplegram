package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * Created by 광희 on 2015-09-15.
 */
public class SubFeellingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ProgressDialog dialog;
    private ArrayList<SubFeelingListDTO> dto;
    private SubFeelingListAdapter adapter;

    private ListView contentList;
    private SwipeRefreshLayout swipeLayout;

    private ImageButton feeling_write_btn;

    public SubFeellingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sub_fragment_feelling, container, false);
        View header = inflater.inflate(R.layout.sub_fragment_feeling_header, null, false);


        feeling_write_btn = (ImageButton) rootView.findViewById(R.id.feeling_write_btn);
        feeling_write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), MyFilling.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });

        contentList = (ListView) rootView.findViewById(R.id.mainContent);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeLayout.setOnRefreshListener(this);
        contentList.addHeaderView(header);

        dto = new ArrayList<SubFeelingListDTO>();
        RequestParams params = new RequestParams();

        HttpClient.post("/feeling/feeling_list", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
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
                        dto.add(new SubFeelingListDTO(
                                jobj.getString("PEOPLE_UID")
                                ,jobj.getString("PEOPLE_IMG")
                                ,jobj.getString("PEOPLE_USERNAME")
                                ,jobj.getString("JOIN_EMAIL")
                                ,jobj.getString("FEELING_TYPE")
                                ,jobj.getString("FEELING_MSG")
                                ,jobj.getString("FEELING_SELECT1")
                                ,jobj.getString("FEELING_SELECT2")
                                ,jobj.getString("FEELING_SELECT3")
                                ,jobj.getString("FEELING_SELECT4")
                                ,jobj.getString("FEELING_SELECT5")
                        ));
                        adapter = new SubFeelingListAdapter(getActivity().getBaseContext(), R.layout.sub_fragment_feeling_rowlist, dto);
                        contentList.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //feelling_btn.setOnClickListener(this);

        return rootView;
    }


    public void feeling_write_btn(View v) {
        Intent intent = new Intent(getActivity().getBaseContext(), MyFilling.class);
        startActivity(intent);
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
