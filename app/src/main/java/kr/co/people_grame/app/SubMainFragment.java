package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubMainFragment extends Fragment {


    private ProgressDialog dialog;
    private ListView sf_people_list;


    public SubMainFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_main, container, false);





        return rootView;
    }

        /*
    public void onActivityCreated()
    {
        RequestParams params = new RequestParams();
        params.put("UID", "");


        HttpClient.post("/user/member_people", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFinish() {
                //Log.d("people_gram", "완료");
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response)
            {
                Log.d("people_gram", response);
            }

        });
    }
    */

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
