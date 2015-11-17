package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class SubPeopleTypeContents_Activity extends AppCompatActivity {

    private TextView popup_contents_title;
    private WebView people_content_webview;

    private String gubun1 = "";
    private String mytype = "";
    private String peopletype = "";
    private String my_speed = "";
    private String my_control = "";
    private String people_speed = "";
    private String people_control = "";

    private String chartSize = "0";

    private String my_data1 = "";
    private String my_data2 = "";
    private String my_data3 = "";
    private String my_data4 = "";
    private String my_data5 = "";
    private String my_data6 = "";
    private String my_data7 = "";
    private String my_data8 = "";
    private String my_data9 = "";
    private String my_data10 = "";


    private String people_total = "";
    private String people_data1 = "";
    private String people_data2 = "";
    private String people_data3 = "";
    private String people_data4 = "";
    private String people_data5 = "";
    private String people_data6 = "";
    private String people_data7 = "";
    private String people_data8 = "";
    private String people_data9 = "";
    private String people_data10 = "";

    private String viewType = "my";
    private String gubun_type = "";
    private String people_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_type_contents_);

        popup_contents_title = (TextView) findViewById(R.id.popup_contents_title);

        Intent intent = getIntent();
        if(intent != null) {


            mytype = intent.getStringExtra("mytype");
            peopletype = intent.getStringExtra("people_type");
            gubun1 = intent.getStringExtra("gubun1");
            my_speed = intent.getStringExtra("my_speed");
            my_control = intent.getStringExtra("my_control");
            people_name = intent.getStringExtra("people_name");
            people_speed = intent.getStringExtra("people_speed");
            people_control = intent.getStringExtra("people_control");
            viewType = intent.getStringExtra("viewType");

            switch (gubun1)
            {

                case "P":
                    if(viewType.equals("my")) {
                        popup_contents_title.setText("가족들이 생각하는 나의 타입");
                    }else {
                        popup_contents_title.setText("피플의 가족들이 생각하는 피플타입");
                    }
                    gubun_type = "가족";
                    break;
                case "F":
                    if(viewType.equals("my")) {
                        popup_contents_title.setText("친구들이 생각하는 나의 타입");
                    }else{
                        popup_contents_title.setText("피플의 친구들이 생각하는 피플타입");
                    }
                    gubun_type = "친구";
                    break;
                case "L":
                    if(viewType.equals("my")) {
                        popup_contents_title.setText("연인이 생각하는 나의 타입");
                    }else{
                        popup_contents_title.setText("피플의 연인이 생각하는 피플타입");
                    }
                    gubun_type = "연인";
                    break;
                case "C":
                    if(viewType.equals("my")) {
                        popup_contents_title.setText("직장에서 생각하는 나의 타입");
                    }else{
                        popup_contents_title.setText("피플의 직장에서 생각하는 피플타입");
                    }
                    gubun_type = "직장";
                    break;
                case "S":
                    if(viewType.equals("my")) {
                        popup_contents_title.setText("고객들이 생각하는 나의 타입");
                    }else{
                        popup_contents_title.setText("피플의 고객들이 생각하는 피플타입");
                    }
                    gubun_type = "고객";
                    break;
            }


            my_data1 = intent.getStringExtra("my_data1");
            my_data2 = intent.getStringExtra("my_data2");
            my_data3 = intent.getStringExtra("my_data3");
            my_data4 = intent.getStringExtra("my_data4");
            my_data5 = intent.getStringExtra("my_data5");
            my_data6 = intent.getStringExtra("my_data6");
            my_data7 = intent.getStringExtra("my_data7");
            my_data8 = intent.getStringExtra("my_data8");
            my_data9 = intent.getStringExtra("my_data9");
            my_data10 = intent.getStringExtra("my_data10");

            people_data1 = intent.getStringExtra("people_data1");
            people_data2 = intent.getStringExtra("people_data2");
            people_data3 = intent.getStringExtra("people_data3");
            people_data4 = intent.getStringExtra("people_data4");
            people_data5 = intent.getStringExtra("people_data5");
            people_data6 = intent.getStringExtra("people_data6");
            people_data7 = intent.getStringExtra("people_data7");
            people_data8 = intent.getStringExtra("people_data8");
            people_data9 = intent.getStringExtra("people_data9");
            people_data10 = intent.getStringExtra("people_data10");

            people_total = intent.getStringExtra("people_total");
        }

        Log.d("people_gram", "데이터전달=" + my_speed + ":::" + my_control + ":::" + people_speed + "::" + people_control + ":::" + people_total);
        Log.d("people_gram", "데이터전달2="+people_data1);
        Log.d("people_gram", "데이터전달2="+people_data2);
        Log.d("people_gram", "데이터전달2="+people_data3);
        Log.d("people_gram", "데이터전달2="+people_data4);
        Log.d("people_gram", "데이터전달2="+people_data5);
        Log.d("people_gram", "데이터전달2="+people_data6);
        Log.d("people_gram", "데이터전달2="+people_data7);
        Log.d("people_gram", "데이터전달2="+people_data8);
        Log.d("people_gram", "데이터전달2="+people_data9);
        Log.d("people_gram", "데이터전달2="+people_data10);

        people_content_webview = (WebView) findViewById(R.id.people_content_webview);
        WebSettings webSettings = people_content_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        people_content_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                people_content_webview.loadUrl("javascript:type_chart(" + my_speed + ", "+my_control+","+people_speed+","+people_control+", '"+gubun_type+"', '"+viewType+"')");
                people_content_webview.loadUrl("javascript:nameView('" + SharedPreferenceUtil.getSharedPreference(SubPeopleTypeContents_Activity.this, "username") + "','"+people_name+"')");

                people_content_webview.loadUrl("javascript:tip_match(" + my_speed + ", "+my_control+","+people_speed+","+people_control+")");
                people_content_webview.loadUrl("javascript:tip_graph('1', '1', "+people_total+","+people_data1+")");
                people_content_webview.loadUrl("javascript:tip_graph('2', '1', "+people_total+","+people_data2+")");
                people_content_webview.loadUrl("javascript:tip_graph('3', '1', "+people_total+","+people_data3+")");
                people_content_webview.loadUrl("javascript:tip_graph('4', '1', "+people_total+","+people_data4+")");
                people_content_webview.loadUrl("javascript:tip_graph('5', '1', "+people_total+","+people_data5+")");
                people_content_webview.loadUrl("javascript:tip_graph('6', '1', "+people_total+","+people_data6+")");
                people_content_webview.loadUrl("javascript:tip_graph('7', '1', "+people_total+","+people_data7+")");
                people_content_webview.loadUrl("javascript:tip_graph('8', '1', "+people_total+","+people_data8+")");
                people_content_webview.loadUrl("javascript:tip_graph('9', '1', "+people_total+","+people_data9+")");
                people_content_webview.loadUrl("javascript:tip_graph('10', '1', "+people_total+","+people_data10+")");

                //tip_match
                super.onPageFinished(view, url);
            }
        });


        //og.d("people_gram", "file:///android_asset/peopletype_my/" + gubun1 + "/" + gubun1 + "_MY_" + mytype + peopletype + ".html");



        if(viewType.equals("my")) {
            people_content_webview.loadUrl("file:///android_asset/peopletype_my/" + gubun1 + "/" + gubun1 + "_MY_" + mytype + peopletype + ".html");
            //people_content_webview.loadUrl("file:///android_asset/peopletype_my/" + gubun1 + "/" + gubun1 + "_MY_AA.html");
        } else {
            people_content_webview.loadUrl("file:///android_asset/peopletype_you/" + gubun1 + "/" + gubun1 + "_PEOPLE_" + mytype + peopletype + ".html");
        }
        //people_content_webview.loadUrl("file:///android_asset/peopletype_my/" + gubun1 + "/" + gubun1 + "_MY_AA.html");
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

    public void closeBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
