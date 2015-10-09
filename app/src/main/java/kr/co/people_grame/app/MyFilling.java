package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MyFilling extends AppCompatActivity {

    Singleton m_Inst = Singleton.getInstance();

    private LinearLayout filling_no_select, filling_select_btn_view;
    private ImageButton filling_select_1_btn, filling_select_2_btn, filling_select_3_btn, filling_select_4_btn, filling_select_5_btn;

    private String feelingType = "";
    private String[] dataArray = new String[5];

    private Context mContext;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_filling);

        mContext = this;
        m_Inst.InitGUIFrame(this);

        MyFilling_data.filling_comment = "";


        //filling

        //RelativeLayout panel = new RelativeLayout(this);
        //setContentView(panel);

        RelativeLayout panel = (RelativeLayout) findViewById(R.id.feelling);

        TextView tv = new TextView(this);
        //tv.setText("Rotary knob control\nRadu Motisan 2013\nwww.pocketmagic.net");
        //tv.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        panel.addView(tv, lp);
        panel.setBackgroundColor(Color.rgb(71, 72, 90));


        filling_no_select = (LinearLayout) findViewById(R.id.filling_no_select);
        filling_select_btn_view = (LinearLayout) findViewById(R.id.filling_select_btn_view);


        filling_select_1_btn = (ImageButton) findViewById(R.id.filling_select_1_btn);
        filling_select_2_btn = (ImageButton) findViewById(R.id.filling_select_2_btn);
        filling_select_3_btn = (ImageButton) findViewById(R.id.filling_select_3_btn);
        filling_select_4_btn = (ImageButton) findViewById(R.id.filling_select_4_btn);
        filling_select_5_btn = (ImageButton) findViewById(R.id.filling_select_5_btn);


        /*
        final TextView tv2 = new TextView(this); tv2.setText("");
        lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        panel.addView(tv2, lp);
        */

        RoundKnobButton rv = new RoundKnobButton(this, R.drawable.myfilling_line_bg, R.drawable.myfilling_line, R.drawable.myfilling_line,
                m_Inst.Scale(400), m_Inst.Scale(400));
        lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv, lp);

        rv.setRotorPercentage(0);
        rv.SetListener(new RoundKnobButton.RoundKnobButtonListener() {
            public void onStateChange(boolean newstate) {
                Toast.makeText(MyFilling.this, "New state:" + newstate, Toast.LENGTH_SHORT).show();
            }

            public void onRotate(final int percentage) {
                feeling_btn_detault();

                if(percentage > 0 && percentage <= 17) {


                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu1_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu1_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu1_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu1_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu1_5_off);

                    feelingType = "1";

                } else if(percentage > 17 && percentage <= 34) {
                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu2_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu2_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu2_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu2_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu2_5_off);

                    feelingType = "2";


                } else if(percentage > 34 && percentage <= 51) {
                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu3_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu3_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu3_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu3_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu3_5_off);

                    feelingType = "3";


                } else if(percentage > 51 && percentage <= 59) {
                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu4_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu4_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu4_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu4_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu4_5_off);

                    feelingType = "4";


                } else if(percentage < 0 && percentage >= -17) {
                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu7_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu7_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu7_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu7_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu7_5_off);

                    feelingType = "7";


                } else if(percentage < -17 && percentage >= -34) {
                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu6_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu6_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu6_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu6_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu6_5_off);

                    feelingType = "6";


                } else if(percentage < -34 && percentage >= -51) {
                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu5_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu5_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu5_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu5_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu5_5_off);

                    feelingType = "5";


                } else if(percentage < -51 && percentage >= -59) {
                    filling_no_select.setVisibility(View.GONE);
                    filling_select_btn_view.setVisibility(View.VISIBLE);

                    filling_select_1_btn.setImageResource(R.drawable.myfilling_menu4_1_off);
                    filling_select_2_btn.setImageResource(R.drawable.myfilling_menu4_2_off);
                    filling_select_3_btn.setImageResource(R.drawable.myfilling_menu4_3_off);
                    filling_select_4_btn.setImageResource(R.drawable.myfilling_menu4_4_off);
                    filling_select_5_btn.setImageResource(R.drawable.myfilling_menu4_5_off);


                    feelingType = "4";


                } else {
                    filling_no_select.setVisibility(View.VISIBLE);
                    filling_select_btn_view.setVisibility(View.GONE);

                    feelingType = "";
                }
                /*
                tv2.post(new Runnable() {
                    public void run() {
                        /*
                        if(percentage == 0) {
                            Drawable drawable = getResources().getDrawable(R.drawable.myfilling_line_bg);
                            RelativeLayout panel = (RelativeLayout) findViewById(R.id.feelling);
                            panel.setBackground(drawable);
                        } else if(percentage >= 0) {
                            Drawable drawable = getResources().getDrawable(R.drawable.myfilling_line_bg1);
                            RelativeLayout panel = (RelativeLayout) findViewById(R.id.feelling);
                            panel.setBackground(drawable);

                        }

                        //tv2.setText("\n" + percentage + "%\n");
                    }
                });
                */
            }
        });
    }

    private void feeling_btn_detault()
    {
        for(int i = 0; i < dataArray.length; i++) {
            dataArray[i] = "";
        }
    }


    public void btn_comment(View v) {
        Intent intent = new Intent(MyFilling.this, MyFilling_Comment_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void btn_back(View v) {
        finish();
    }

    public void filling_select_btn(View v) {

        switch (v.getId()) {
            case R.id.filling_select_1_btn:



                if(dataArray[0] != "") {
                    dataArray[0] = "";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_1_off", "drawable", mContext.getPackageName());
                    filling_select_1_btn.setImageResource(resID);
                } else {
                    dataArray[0] = "1";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_1_on", "drawable", mContext.getPackageName());
                    filling_select_1_btn.setImageResource(resID);
                }
                break;

            case R.id.filling_select_2_btn:

                if(dataArray[1] != "") {
                    dataArray[1] = "";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_2_off", "drawable", mContext.getPackageName());
                    filling_select_2_btn.setImageResource(resID);
                } else {
                    dataArray[1] = "2";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_2_on", "drawable", mContext.getPackageName());
                    filling_select_2_btn.setImageResource(resID);
                }

                break;

            case R.id.filling_select_3_btn:

                if(dataArray[2] != "") {
                    dataArray[2] = "";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_3_off", "drawable", mContext.getPackageName());
                    filling_select_3_btn.setImageResource(resID);
                } else {
                    dataArray[2] = "3";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_3_on", "drawable", mContext.getPackageName());
                    filling_select_3_btn.setImageResource(resID);
                }

                break;
            case R.id.filling_select_4_btn:
                if(dataArray[3] != "") {
                    dataArray[3] = "";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_4_off", "drawable", mContext.getPackageName());
                    filling_select_4_btn.setImageResource(resID);
                } else {
                    dataArray[3] = "4";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_4_on", "drawable", mContext.getPackageName());
                    filling_select_4_btn.setImageResource(resID);
                }
                break;
            case R.id.filling_select_5_btn:

                if(dataArray[4] != "") {
                    dataArray[4] = "";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_5_off", "drawable", mContext.getPackageName());
                    filling_select_5_btn.setImageResource(resID);
                } else {
                    dataArray[4] = "5";
                    int resID = mContext.getResources().getIdentifier("myfilling_menu"+feelingType+"_5_on", "drawable", mContext.getPackageName());
                    filling_select_5_btn.setImageResource(resID);
                }

                break;
        }
    }

    public void myfillng_save(View v) {
        //Log.d("people_gram", "테스트="+MyFilling_data.filling_comment);
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(this, "uid"));
        params.put("feeling_type", feelingType);
        params.put("feeling_msg", MyFilling_data.filling_comment);
        for(int i = 0; i<5; i++) {
            int checkName = i + 1;
            params.put("feeling_select"+String.valueOf(checkName), dataArray[i]);
        }


        HttpClient.post("/feeling/feeling_save", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(MyFilling.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                finish();
            }


        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_my_filling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
