package kr.co.people_grame.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubMypageFragment extends Fragment {

    private LinearLayout mypage_people_btn, mypage_gram_store_btn;

    private TextView mypage_point, mypage_type;

    public SubMypageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_mypage, container, false);

        mypage_people_btn = (LinearLayout) rootView.findViewById(R.id.mypage_people_btn);
        mypage_gram_store_btn = (LinearLayout) rootView.findViewById(R.id.mypage_gram_store_btn);
        mypage_point = (TextView) rootView.findViewById(R.id.mypage_point);
        mypage_type = (TextView) rootView.findViewById(R.id.mypage_type);

        String Point = SharedPreferenceUtil.getSharedPreference(getActivity(), "point");
        String Type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
        mypage_point.setText(Point + "g");

        switch (Type) {
            case "I":
                mypage_type.setText("자기진단 : 우호형");
                break;
            case "D":
                mypage_type.setText("자기진단 : 주도형");
                break;
            case "E":
                mypage_type.setText("자기진단 : 표출형");
                break;
            case "A":
                mypage_type.setText("자기진단 : 분석형");
                break;
        }



        mypage_people_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PeopleSync_Activity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });

        mypage_gram_store_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubGramPoint.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
            }
        });

        return rootView;
    }

}
