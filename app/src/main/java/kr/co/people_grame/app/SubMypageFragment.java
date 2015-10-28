package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubMypageFragment extends Fragment {

    private LinearLayout mypage_people_btn, mypage_gram_store_btn;
    private LinearLayout mypage_all_btn, mypage_family_btn, mypage_friend_btn, mypage_lover_btn, mypage_job_btn, mypage_client_btn;
    private TextView et_all, et_family, et_friend, et_lover, et_job, et_client;
    private WebView chart_webview;

    private TextView mypage_point, mypage_type;
    private ProgressDialog dialog;

    public SubMypageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_mypage, container, false);

        mypage_people_btn = (LinearLayout) rootView.findViewById(R.id.mypage_people_btn);
        mypage_gram_store_btn = (LinearLayout) rootView.findViewById(R.id.mypage_gram_store_btn);
        mypage_point = (TextView) rootView.findViewById(R.id.mypage_point);
        mypage_type = (TextView) rootView.findViewById(R.id.mypage_type);

        mypage_all_btn = (LinearLayout) rootView.findViewById(R.id.mypage_all_btn);
        mypage_family_btn = (LinearLayout) rootView.findViewById(R.id.mypage_family_btn);
        mypage_friend_btn = (LinearLayout) rootView.findViewById(R.id.mypage_friend_btn);
        mypage_lover_btn = (LinearLayout) rootView.findViewById(R.id.mypage_lover_btn);
        mypage_job_btn = (LinearLayout) rootView.findViewById(R.id.mypage_job_btn);
        mypage_client_btn = (LinearLayout) rootView.findViewById(R.id.mypage_client_btn);


        et_all = (TextView) rootView.findViewById(R.id.et_all);
        et_family = (TextView) rootView.findViewById(R.id.et_family);
        et_friend = (TextView) rootView.findViewById(R.id.et_friend);
        et_lover = (TextView) rootView.findViewById(R.id.et_lover);
        et_job = (TextView) rootView.findViewById(R.id.et_job);
        et_client = (TextView) rootView.findViewById(R.id.et_client);


        chart_webview = (WebView) rootView.findViewById(R.id.chart_webview);
        WebSettings webSettings = chart_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        chart_webview.loadUrl("http://121.162.209.41:81/mypage/chart?uid="+SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid")+"&type=all");


        mypage_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypage_all_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                mypage_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);

                WebSettings webSettings = chart_webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                chart_webview.loadUrl("http://121.162.209.41:81/mypage/chart?uid="+SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid")+"&type=all");




            }
        });

        mypage_family_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypage_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_family_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                mypage_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);


                WebSettings webSettings = chart_webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                chart_webview.loadUrl("http://121.162.209.41:81/mypage/chart?uid="+SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid")+"&type=family");


            }
        });

        mypage_friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypage_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_friend_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                mypage_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);


                WebSettings webSettings = chart_webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                chart_webview.loadUrl("http://121.162.209.41:81/mypage/chart?uid="+SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid")+"&type=friend");


            }
        });

        mypage_lover_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypage_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_lover_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                mypage_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);


                WebSettings webSettings = chart_webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                chart_webview.loadUrl("http://121.162.209.41:81/mypage/chart?uid="+SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid")+"&type=lover");
            }
        });

        mypage_job_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypage_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_job_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                mypage_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);


                WebSettings webSettings = chart_webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                chart_webview.loadUrl("http://121.162.209.41:81/mypage/chart?uid="+SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid")+"&type=job");
            }
        });

        mypage_client_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypage_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                mypage_client_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                chart_webview.loadUrl("http://121.162.209.41:81/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=client");
            }
        });




        String Point = SharedPreferenceUtil.getSharedPreference(getActivity(), "point");
        String Type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
        mypage_point.setText(Point + "g");

        switch (Type) {
            case "I":
                mypage_type.setText("자기진단 : 우호형");
                break;
            case "D":
                mypage_type.setText("자기진단 : 주도형");
                break;
            case "E":
                mypage_type.setText("자기진단 : 표출형");
                break;
            case "A":
                mypage_type.setText("자기진단 : 분석형");
                break;
        }


        mypage_people_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PeopleSync_Activity.class);
                intent.putExtra("mypage","ok");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });

        mypage_gram_store_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubGramPoint.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });

        return rootView;
    }

    @Override
    public void onStart()
    {
        countData();
        super.onStart();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    private void countData()
    {
        Log.d("people_gram", "마이페이지");
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
        HttpClient.post("/user/peopleDataCount", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    et_all.setText(jobj.getString("TOTAL"));
                    et_family.setText(jobj.getString("FAMILY"));
                    et_friend.setText(jobj.getString("FRIEND"));
                    et_lover.setText(jobj.getString("LOVER"));
                    et_job.setText(jobj.getString("JOB"));
                    et_client.setText(jobj.getString("CLIENT"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
