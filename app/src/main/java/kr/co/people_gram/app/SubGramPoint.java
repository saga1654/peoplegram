package kr.co.people_gram.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class SubGramPoint extends AppCompatActivity {

    private LinearLayout pointhistory_btn, pointcoupon_btn, coupon_close_btn;
    private Intent intent;
    private PopupWindow mPopupWindow;
    private TextView mypage_grampoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_gram_point);

        pointhistory_btn = (LinearLayout) findViewById(R.id.pointhistory_btn);
        mypage_grampoint = (TextView) findViewById(R.id.mypage_grampoint);
        pointhistory_btn.setOnTouchListener(onBtnTouchListener);
        pointhistory_btn.setOnClickListener(onBtnClickListener);

        String Point = SharedPreferenceUtil.getSharedPreference(SubGramPoint.this, "point");

        mypage_grampoint.setText(Point + "p");

        //pointcoupon_btn = (LinearLayout) findViewById(R.id.pointcoupon_btn);
        //pointcoupon_btn.setOnTouchListener(onBtnTouchListener);
        //pointcoupon_btn.setOnClickListener(onBtnClickListener);

        /*
        coupon_close_btn = (LinearLayout) findViewById(R.id.coupon_close_btn);
        coupon_close_btn.setOnClickListener(onBtnClickListener);
        */
    }


    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.pointhistory_btn:
                    if(event.getAction() == 2) {
                        pointhistory_btn.setBackgroundColor(Color.rgb(241,241,241));
                    } else {
                        pointhistory_btn.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.pointcoupon_btn:
                    if(event.getAction() == 2) {
                        pointcoupon_btn.setBackgroundColor(Color.rgb(241,241,241));
                    } else {
                        pointcoupon_btn.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;

            }
            return false;
        }
    };

    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pointhistory_btn:
                    intent = new Intent(SubGramPoint.this, SubGramHistory.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                    break;

                case R.id.pointcoupon_btn:
                    View popupView = getLayoutInflater().inflate(R.layout.popup_coupon, null);

                    mPopupWindow = new PopupWindow(popupView,
                            RelativeLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                    mPopupWindow.setAnimationStyle(-1); // 애니메이션 설정(-1:설정, 0:설정안함)

                    mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

                    LinearLayout coupon_close_btn = (LinearLayout) popupView.findViewById(R.id.coupon_close_btn);
                    coupon_close_btn.setOnClickListener(onBtnClickListener);
                    break;

                case R.id.coupon_close_btn:
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }

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

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void btn_back(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

}
