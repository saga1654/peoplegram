package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubPeopleListSelect_Activity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    private ImageButton people_click_btn, people_tip_btn, people_now_btn;

    private String clickType = "NOW";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_list_select_);


        people_click_btn = (ImageButton) findViewById(R.id.people_click_btn);
        people_tip_btn = (ImageButton) findViewById(R.id.people_tip_btn);
        people_now_btn = (ImageButton) findViewById(R.id.people_now_btn);

        people_click_btn.setOnClickListener(onBtnClickListener);
        people_tip_btn.setOnClickListener(onBtnClickListener);
        people_now_btn.setOnClickListener(onBtnClickListener);

        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        SubMainFragment sub_m_fragment = new SubMainFragment();
        ft.replace(R.id.fragment_sub_people, sub_m_fragment);
        ft.commit();


    }

    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentManager = getSupportFragmentManager();
            ft = fragmentManager.beginTransaction();


            switch (v.getId()) {
                case R.id.people_click_btn:
                    people_click_btn.setImageResource(R.drawable.people_click_btn_on);
                    people_tip_btn.setImageResource(R.drawable.people_tip_btn_off);
                    people_now_btn.setImageResource(R.drawable.people_now_btn_off);

                    break;
                case R.id.people_now_btn:
                    people_click_btn.setImageResource(R.drawable.people_click_btn_off);
                    people_tip_btn.setImageResource(R.drawable.people_tip_btn_off);
                    people_now_btn.setImageResource(R.drawable.people_now_btn_on);
                    break;
                case R.id.people_tip_btn:
                    people_click_btn.setImageResource(R.drawable.people_click_btn_off);
                    people_tip_btn.setImageResource(R.drawable.people_tip_btn_on);
                    people_now_btn.setImageResource(R.drawable.people_now_btn_off);


                    SubPeopleFragment_tip sub_m_fragment = new SubPeopleFragment_tip();
                    ft.replace(R.id.fragment_sub_people, sub_m_fragment);
                    ft.commit();


                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

}
