package kr.co.people_grame.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyType_Complate_Activity extends AppCompatActivity {

    private TextView mytype_tv;

    private String username = "";
    private String mytype = "";
    private ImageView mytype_activity_typeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type__complate_);

        Log.d("people_gram", SharedPreferenceUtil.getSharedPreference(MyType_Complate_Activity.this, "uid"));
        mytype = SharedPreferenceUtil.getSharedPreference(MyType_Complate_Activity.this, "mytype");
        username = SharedPreferenceUtil.getSharedPreference(MyType_Complate_Activity.this, "username");

        mytype_tv = (TextView) findViewById(R.id.mytype_tv);
        mytype_activity_typeImg = (ImageView) findViewById(R.id.mytype_activity_typeImg);
        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+username+"</font>님의 자기진단 결과<br><b>표출형 특징을 가진 주도형</b><br>으로 진단되었습니다."));


        switch (mytype)
        {
            case "I":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_i);
                break;

            case "D":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_d);
                break;

            case "E":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_e);
                break;

            case "A":
                mytype_activity_typeImg.setImageResource(R.drawable.mytype_a);
                break;
        }





    }

}
