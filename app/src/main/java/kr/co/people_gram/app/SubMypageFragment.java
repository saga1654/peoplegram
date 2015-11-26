package kr.co.people_gram.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
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

    private LinearLayout mypage_people_btn, mypage_gram_store_btn,people_macthing_order_btn;
    private LinearLayout mypage_all_btn, mypage_family_btn, mypage_friend_btn, mypage_lover_btn, mypage_job_btn, mypage_client_btn, mypage_panel_btn;
    private LinearLayout people_all_btn, people_family_btn, people_friend_btn, people_lover_btn, people_job_btn, people_client_btn;


    private TextView et_all, et_family, et_friend, et_lover, et_job, et_client;
    private TextView people_et_all, people_et_family, people_et_friend, people_et_lover, people_et_job, people_et_client;
    private WebView chart_webview, chart_webview2;

    private String my_type = "";
    private String people_type = "";

    //private TextView mypage_point;
    private ProgressDialog dialog;

    private WebViewInterface mWebViewInterface;
    private WebViewInterface mWebViewInterface2;

    public SubMypageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_mypage, container, false);

        //mypage_people_btn = (LinearLayout) rootView.findViewById(R.id.mypage_people_btn);
        //mypage_gram_store_btn = (LinearLayout) rootView.findViewById(R.id.mypage_gram_store_btn);
        //mypage_point = (TextView) rootView.findViewById(R.id.mypage_point);
        //mypage_type = (TextView) rootView.findViewById(R.id.mypage_type);



        mypage_all_btn = (LinearLayout) rootView.findViewById(R.id.mypage_all_btn);
        mypage_family_btn = (LinearLayout) rootView.findViewById(R.id.mypage_family_btn);
        mypage_friend_btn = (LinearLayout) rootView.findViewById(R.id.mypage_friend_btn);
        mypage_lover_btn = (LinearLayout) rootView.findViewById(R.id.mypage_lover_btn);
        mypage_job_btn = (LinearLayout) rootView.findViewById(R.id.mypage_job_btn);
        mypage_client_btn = (LinearLayout) rootView.findViewById(R.id.mypage_client_btn);


        people_all_btn = (LinearLayout) rootView.findViewById(R.id.people_all_btn);
        people_family_btn = (LinearLayout) rootView.findViewById(R.id.people_family_btn);
        people_friend_btn = (LinearLayout) rootView.findViewById(R.id.people_friend_btn);
        people_lover_btn = (LinearLayout) rootView.findViewById(R.id.people_lover_btn);
        people_job_btn = (LinearLayout) rootView.findViewById(R.id.people_job_btn);
        people_client_btn = (LinearLayout) rootView.findViewById(R.id.people_client_btn);
        people_macthing_order_btn = (LinearLayout) rootView.findViewById(R.id.people_macthing_order_btn);
        //mypage_panel_btn = (LinearLayout) rootView.findViewById(R.id.mypage_panel_btn);


        et_all = (TextView) rootView.findViewById(R.id.et_all);
        et_family = (TextView) rootView.findViewById(R.id.et_family);
        et_friend = (TextView) rootView.findViewById(R.id.et_friend);
        et_lover = (TextView) rootView.findViewById(R.id.et_lover);
        et_job = (TextView) rootView.findViewById(R.id.et_job);
        et_client = (TextView) rootView.findViewById(R.id.et_client);

        people_et_all = (TextView) rootView.findViewById(R.id.people_et_all);
        people_et_family = (TextView) rootView.findViewById(R.id.people_et_family);
        people_et_friend = (TextView) rootView.findViewById(R.id.people_et_friend);
        people_et_lover = (TextView) rootView.findViewById(R.id.people_et_lover);
        people_et_job = (TextView) rootView.findViewById(R.id.people_et_job);
        people_et_client = (TextView) rootView.findViewById(R.id.people_et_client);

        countData();


        chart_webview = (WebView) rootView.findViewById(R.id.chart_webview);
        chart_webview2 = (WebView) rootView.findViewById(R.id.chart_webview2);
        WebSettings webSettings = chart_webview.getSettings();
        WebSettings webSettings2 = chart_webview2.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings2.setJavaScriptEnabled(true);
        chart_webview.loadUrl("http://app.peoplegram.co.kr/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=all");
        chart_webview2.loadUrl("http://app.peoplegram.co.kr/mypage/chart2?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=all");

        mWebViewInterface = new WebViewInterface(getActivity(), chart_webview); //JavascriptInterface 객체화
        mWebViewInterface2 = new WebViewInterface(getActivity(), chart_webview2); //JavascriptInterface 객체화
        chart_webview.addJavascriptInterface(mWebViewInterface, "Android");
        chart_webview2.addJavascriptInterface(mWebViewInterface2, "Android");
        people_macthing_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PeopleMatchTop10_Activity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_up_info,R.anim.slide_down_info);
            }
        });




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
                chart_webview.loadUrl("http://app.peoplegram.co.kr/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=all");
                my_type = "";
            }
        });

        people_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_all_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                people_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);

                chart_webview2.loadUrl("http://app.peoplegram.co.kr/mypage/chart2?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=all");
                people_type = "";
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
                chart_webview.loadUrl("http://app.peoplegram.co.kr/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=family");

                my_type = "P";
            }
        });

        people_family_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_family_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                people_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);

                chart_webview2.loadUrl("http://app.peoplegram.co.kr/mypage/chart2?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=family");

                people_type = "P";


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
                chart_webview.loadUrl("http://app.peoplegram.co.kr/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=friend");

                my_type = "F";
            }
        });

        people_friend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_friend_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                people_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);

                chart_webview2.loadUrl("http://app.peoplegram.co.kr/mypage/chart2?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=friend");
                people_type = "F";
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
                chart_webview.loadUrl("http://app.peoplegram.co.kr/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=lover");

                my_type = "L";
            }
        });

        people_lover_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_lover_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                people_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                chart_webview2.loadUrl("http://app.peoplegram.co.kr/mypage/chart2?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=lover");
                people_type = "L";
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
                chart_webview.loadUrl("http://app.peoplegram.co.kr/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=job");

                my_type = "C";
            }
        });

        people_job_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_job_btn.setBackgroundResource(R.drawable.mypage_btn_on);
                people_client_btn.setBackgroundResource(R.drawable.mypage_btn_off);

                chart_webview2.loadUrl("http://app.peoplegram.co.kr/mypage/chart2?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=job");
                people_type = "C";
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
                chart_webview.loadUrl("http://app.peoplegram.co.kr/mypage/chart?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=client");

                my_type = "S";
            }
        });

        people_client_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_all_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_family_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_friend_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_lover_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_job_btn.setBackgroundResource(R.drawable.mypage_btn_off);
                people_client_btn.setBackgroundResource(R.drawable.mypage_btn_on);

                chart_webview2.loadUrl("http://app.peoplegram.co.kr/mypage/chart2?uid=" + SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid") + "&type=client");
                people_type = "S";
            }
        });




        //String Point = SharedPreferenceUtil.getSharedPreference(getActivity(), "point");
        //mypage_point.setText(Point + "g");

        /*
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
        */




        /*
        mypage_gram_store_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubGramPoint.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });
        */

        /*
        mypage_panel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PanelActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });
        */

        return rootView;
    }

    @Override
    public void onStart()
    {
        //countData();
        super.onStart();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    private void countData()
    {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
        HttpClient.post("/user/peopleDataCount", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFinish() {
                //dialog.dismiss();
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

                    people_et_all.setText(jobj.getString("P_TOTAL"));
                    people_et_family.setText(jobj.getString("P_FAMILY"));
                    people_et_friend.setText(jobj.getString("P_FRIEND"));
                    people_et_lover.setText(jobj.getString("P_LOVER"));
                    people_et_job.setText(jobj.getString("P_JOB"));
                    people_et_client.setText(jobj.getString("P_CLIENT"));



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public class WebViewInterface {

        private WebView mAppView;
        private Activity mContext;

        /**
         * 생성자.
         * @param activity : context
         * @param view : 적용될 웹뷰
         */
        public WebViewInterface(Activity activity, WebView view) {
            mAppView = view;
            mContext = activity;
        }

        @JavascriptInterface
        public void myList(String type)
        {
            Intent intent = new Intent(getActivity(), SubMypage_PeopleList_Activity.class);
            intent.putExtra("searchType", type);
            intent.putExtra("list", "my");
            intent.putExtra("my_type", my_type);
            intent.putExtra("people_type", "");
            startActivity(intent);

            //Log.d("people_gram", "타입="+type);
        }

        @JavascriptInterface
        public void peopleList(String type)
        {
            Intent intent = new Intent(getActivity(), SubMypage_PeopleList_Activity.class);
            intent.putExtra("searchType", type);
            intent.putExtra("list", "people");
            intent.putExtra("my_type", "");
            intent.putExtra("people_type", people_type);
            startActivity(intent);
            //Log.d("people_gram", "타입="+type);
        }
    }


}
