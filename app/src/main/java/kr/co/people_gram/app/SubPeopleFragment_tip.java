package kr.co.people_gram.app;

import android.content.Intent;
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

public class SubPeopleFragment_tip extends Fragment {
    private LinearLayout subpeople_menu1, subpeople_menu2, subpeople_menu3, subpeople_menu4, subpeople_menu5, subpeople_menu6;
    private PeopleData pd;

    //private TextView MY_std, YOU_std;

    public SubPeopleFragment_tip() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subpeople_fragment, container, false);


        /*
        MY_std = (TextView) rootView.findViewById(R.id.MY_std);
        YOU_std = (TextView) rootView.findViewById(R.id.YOU_std);


        if(SubPeopleListSelect_Activity.my_type_check == false) {
            MY_std.setText("나 : 내 진단 기준");
        } else {
            MY_std.setText("나 : 타인 진단 기준");
        }

        if(SubPeopleListSelect_Activity.people_type_check == false) {
            YOU_std.setText("상대방 : 내 진단 기준");
        } else {
            YOU_std.setText("상대방 : 타인 진단 기준");
        }
        */


        subpeople_menu1 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu1);
        subpeople_menu2 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu2);
        subpeople_menu3 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu3);
        subpeople_menu4 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu4);
        subpeople_menu5 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu5);
        subpeople_menu6 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu6);


        subpeople_menu1.setOnTouchListener(onBtnTouchListener);
        subpeople_menu1.setOnClickListener(onBtnClickListener);

        subpeople_menu2.setOnTouchListener(onBtnTouchListener);
        subpeople_menu2.setOnClickListener(onBtnClickListener);

        subpeople_menu3.setOnTouchListener(onBtnTouchListener);
        subpeople_menu3.setOnClickListener(onBtnClickListener);

        subpeople_menu4.setOnTouchListener(onBtnTouchListener);
        subpeople_menu4.setOnClickListener(onBtnClickListener);

        subpeople_menu5.setOnTouchListener(onBtnTouchListener);
        subpeople_menu5.setOnClickListener(onBtnClickListener);

        subpeople_menu6.setOnTouchListener(onBtnTouchListener);
        subpeople_menu6.setOnClickListener(onBtnClickListener);


        return rootView;
    }

    private View.OnTouchListener onBtnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };


    private View.OnClickListener onBtnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //String point_str = SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "point");
            //int point = Integer.parseInt(point_str);
            Intent intent;

            //point = 0;

            /*
            if(point == 0) {
                intent = new Intent(getActivity().getBaseContext(), GramPopupNotActivity.class);
            } else {
                intent = new Intent(getActivity().getBaseContext(), GramPopupActivity.class);
            }
            */

            intent = new Intent(getActivity().getBaseContext(), SubPeopleContents_Activity.class);


            switch (v.getId()) {
                case R.id.subpeople_menu1:
                    intent.putExtra("matchNum","1");
                    break;

                case R.id.subpeople_menu2:
                    intent.putExtra("matchNum","2");
                    break;

                case R.id.subpeople_menu3:
                    intent.putExtra("matchNum","3");
                    break;

                case R.id.subpeople_menu4:
                    intent.putExtra("matchNum","4");
                    break;

                case R.id.subpeople_menu5:
                    intent.putExtra("matchNum","5");
                    break;

                case R.id.subpeople_menu6:
                    intent.putExtra("matchNum", "6");
                    break;
            }

            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
        }
    };



}
