package kr.co.people_grame.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubSettingFragment extends Fragment implements View.OnClickListener {
    private LinearLayout sf_setting_btn_profile, sf_setting_btn_notice, sf_setting_btn_agree, sf_setting_btn_info, sf_setting_btn_question, sf_setting_btn_logout;

    public SubSettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_setting, container, false);


        //sf_setting_btn_profile = (LinearLayout) rootView.findViewById(R.id.sf_setting_btn_profile);
        sf_setting_btn_notice = (LinearLayout) rootView.findViewById(R.id.sf_setting_btn_notice);
        //sf_setting_btn_agree = (LinearLayout) rootView.findViewById(R.id.sf_setting_btn_agree);
        //sf_setting_btn_info = (LinearLayout) rootView.findViewById(R.id.sf_setting_btn_info);
        sf_setting_btn_question = (LinearLayout) rootView.findViewById(R.id.sf_setting_btn_question);
        //sf_setting_btn_logout = (LinearLayout) rootView.findViewById(R.id.sf_setting_btn_logout);

        //sf_setting_btn_profile.setOnClickListener(this);
        sf_setting_btn_notice.setOnClickListener(this);
        //sf_setting_btn_agree.setOnClickListener(this);
        //sf_setting_btn_info.setOnClickListener(this);
        sf_setting_btn_question.setOnClickListener(this);
        //sf_setting_btn_logout.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent;


        switch (v.getId()) {
            case R.id.sf_setting_btn_notice:

                intent = new Intent(getActivity(), NoticeActivity.class);
                getActivity().startActivityForResult(intent, 0001);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);


                break;

            case R.id.sf_setting_btn_question:
                intent = new Intent(getActivity(), SubQnaActivity.class);
                getActivity().startActivityForResult(intent, 0001);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

                break;

        }

        //Log.d("people_gram", String.valueOf(v.getId()));
    }



}
