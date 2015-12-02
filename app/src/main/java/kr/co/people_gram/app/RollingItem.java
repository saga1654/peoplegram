package kr.co.people_gram.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

public class RollingItem extends FrameLayout {
    private LayoutInflater inflater;

    private Animation appear, disappear;
    private ViewGroup container;

    public RollingItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public RollingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RollingItem(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.rolling_item, this);
        this.inflater = LayoutInflater.from(context);
        appear = AnimationUtils.loadAnimation(context, R.anim.up_appear);
        disappear = AnimationUtils.loadAnimation(context, R.anim.up_disappear);

        container = (ViewGroup) findViewById(R.id.RollingItemContainer);

        appear.setFillAfter(true);
        disappear.setFillAfter(true);
        appear.setAnimationListener(onAnimationAppear);

        initViewPool(2);
    }

    private View createView(int num) {
        TextView tv = (TextView) inflater.inflate(R.layout.rolling_item_number, this, false);
        tv.setText(String.valueOf(num));
        return tv;
    }

    private View[] viewPool;
    private int currView, nextView;
    private int countNext = 1;
    private int repeatTime = 0;

    private void initViewPool(int count) {
        viewPool = new View[count];

        for(int i = 0; i < count; i++) {
            viewPool[i] = createView(i);

            container.addView(viewPool[i]);
        }

        currView = 0;
        nextView = 1;
    }

    public void roll(int count) {
        repeatTime = count + 1;
        countNext = 0;

        doNextMove();
    }

    private void resetDuration() {
        if(10 < repeatTime) {
            appear.setDuration(50);
            disappear.setDuration(50);
        } else if(4 < repeatTime) {
            appear.setDuration(70);
            disappear.setDuration(70);
        } else if(2 < repeatTime) {
            appear.setDuration(120);
            disappear.setDuration(120);
        }  else if(0 < repeatTime) {
            appear.setDuration(150);
            disappear.setDuration(150);
        }
    }

    private Animation.AnimationListener onAnimationAppear = new AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            postDelayed(new Runnable() {

                @Override
                public void run() {
                    doNextMove();
                }
            }, 1);
        }
    };

    private void doNextMove() {
        countNext++;
        if(10 <= countNext) {
            countNext = 0;
        }

        repeatTime--;
        if(0 >= repeatTime) {
            repeatTime = 0;
            return;
        }

        resetDuration();

        TextView tvCurrent = (TextView) getCurrentView();
        tvCurrent.startAnimation(disappear);

        TextView tv = (TextView) getNextView();
        tv.setText(String.valueOf(countNext));
        tv.startAnimation(appear);
        tv.setVisibility(View.VISIBLE);

        Log.d("Roll", "repatTime: " + repeatTime);
        Log.d("Roll", "count next : " + countNext);
        Log.d("Roll", "current View : " + currView + ", " + tvCurrent.getText().toString());
        Log.d("Roll", "next View : " + nextView + ", " + tv.getText().toString());

        next();
    }

    private View getCurrentView() {
        View v = viewPool[currView];

        return v;
    }

    private View getNextView() {
        View v = viewPool[nextView];

        return v;
    }

    private void next() {
        int length = viewPool.length;
        currView = nextView;

        nextView++;
        if(length <= nextView) {
            nextView = 0;
        }
    }

}

