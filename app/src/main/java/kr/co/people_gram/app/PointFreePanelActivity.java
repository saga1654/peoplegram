package kr.co.people_gram.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class PointFreePanelActivity extends AppCompatActivity {

    private Button panel_join_btn;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_free_panel);


        panel_join_btn = (Button) findViewById(R.id.panel_join_btn);
        panel_join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("uid", SharedPreferenceUtil.getSharedPreference(PointFreePanelActivity.this, "uid"));

                HttpClient.post("/user/panelJoin", params, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        dialog = ProgressDialog.show(PointFreePanelActivity.this, "", "데이터 수신중");
                    }

                    public void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onSuccess(String response) {
                        SharedPreferenceUtil.putSharedPreference(PointFreePanelActivity.this, "panelYN", "Y");

                        Toast.makeText(PointFreePanelActivity.this, "패널가입이 되셨습니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PointFreePanelActivity.this, PointFreeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                        finish();

                    }
                });
            }
        });
    }
    public void free_prevBtn(View v) {
        finish();
    }

}
