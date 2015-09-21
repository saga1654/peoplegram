package kr.co.people_grame.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;

import java.util.concurrent.ExecutorService;

public class MyType_Result_Activity extends AppCompatActivity {

    private WebView mytype_result_activity_webview;
    private TextView mytype_result_activity_title;
    private ImageView myresulttype_activity_typeImg;
    private ImageView myresult_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type__result);

        mytype_result_activity_title = (TextView) findViewById(R.id.mytype_result_activity_title);
        myresult_close = (ImageView) findViewById(R.id.myresult_close);


        Intent intent = getIntent();
        String mytype = intent.getExtras().getString("mytype").toString();
        String title = intent.getExtras().getString("title").toString();
        String contents_num = intent.getExtras().getString("contents_num").toString();
        mytype_result_activity_title.setText(title);

        myresulttype_activity_typeImg = (ImageView) findViewById(R.id.myresulttype_activity_typeImg);
        mytype_result_activity_webview = (WebView) findViewById(R.id.mytype_result_activity_webview);
        WebSettings webSettings = mytype_result_activity_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mytype_result_activity_webview.loadUrl("file:///android_asset/myresult/"+mytype+contents_num+".html");


        switch (mytype)
        {
            case "A":
                myresulttype_activity_typeImg.setImageResource(R.drawable.mytype_result_a_title);
                myresult_close.setImageResource(R.drawable.myresult_close_a_btn);
                break;
            case "D":
                myresulttype_activity_typeImg.setImageResource(R.drawable.mytype_result_d_title);
                myresult_close.setImageResource(R.drawable.myresult_close_d_btn);
                break;
            case "E":
                myresulttype_activity_typeImg.setImageResource(R.drawable.mytype_result_e_title);
                myresult_close.setImageResource(R.drawable.myresult_close_e_btn);
                break;
            case "I":
                myresulttype_activity_typeImg.setImageResource(R.drawable.mytype_result_i_title);
                myresult_close.setImageResource(R.drawable.myresult_close_i_btn);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_type__result_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
