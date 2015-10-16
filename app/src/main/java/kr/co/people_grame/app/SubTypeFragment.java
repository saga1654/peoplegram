package kr.co.people_grame.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubTypeFragment extends Fragment {
    private LinearLayout type_i, type_e, type_d, type_a;


    public SubTypeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_type, container, false);

        type_i = (LinearLayout) rootView.findViewById(R.id.type_i);
        type_d = (LinearLayout) rootView.findViewById(R.id.type_d);
        type_e = (LinearLayout) rootView.findViewById(R.id.type_e);
        type_a = (LinearLayout) rootView.findViewById(R.id.type_a);

        type_i.setOnTouchListener(onBtnTouchListener);
        type_i.setOnClickListener(onBtnClickListener);

        type_d.setOnTouchListener(onBtnTouchListener);
        type_d.setOnClickListener(onBtnClickListener);

        type_e.setOnTouchListener(onBtnTouchListener);
        type_e.setOnClickListener(onBtnClickListener);

        type_a.setOnTouchListener(onBtnTouchListener);
        type_a.setOnClickListener(onBtnClickListener);



        return rootView;
    }

    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.type_i:
                    if(event.getAction() == 2) {
                        type_i.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        type_i.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.type_d:
                    if(event.getAction() == 2) {
                        type_d.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        type_d.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.type_e:
                    if(event.getAction() == 2) {
                        type_e.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        type_e.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
                case R.id.type_a:
                    if(event.getAction() == 2) {
                        type_a.setBackgroundColor(Color.rgb(241, 241, 241));
                    } else {
                        type_a.setBackgroundColor(Color.rgb(255,255,255));
                    }
                    break;
            }
            return false;
        }
    };


    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {



            Intent intent = new Intent(getActivity().getBaseContext(), SubMyType_Activity.class);
            switch (v.getId()) {
                case R.id.type_i:
                    intent.putExtra("mytype", "I");
                    break;
                case R.id.type_d:
                    intent.putExtra("mytype", "D");
                    break;
                case R.id.type_e:
                    intent.putExtra("mytype", "E");
                    break;
                case R.id.type_a:
                    intent.putExtra("mytype", "A");
                    break;

            }

            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
        }
    };

}
