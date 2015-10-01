package kr.co.people_grame.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubMyNowFragment extends Fragment implements View.OnClickListener {

    private String btn_string;
    private ImageButton nowme_btn1, nowme_btn2, nowme_btn3, nowme_btn4, nowme_btn5, nowme_btn6, nowme_btn7, nowme_btn8, nowme_btn9, nowme_btn10, nowme_btn11, nowme_btn12;

    public SubMyNowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_mynow, container, false);

        nowme_btn1 = (ImageButton) rootView.findViewById(R.id.nowme_btn1);
        nowme_btn2 = (ImageButton) rootView.findViewById(R.id.nowme_btn2);
        nowme_btn3 = (ImageButton) rootView.findViewById(R.id.nowme_btn3);
        nowme_btn4 = (ImageButton) rootView.findViewById(R.id.nowme_btn4);
        nowme_btn5 = (ImageButton) rootView.findViewById(R.id.nowme_btn5);
        nowme_btn6 = (ImageButton) rootView.findViewById(R.id.nowme_btn6);
        nowme_btn7 = (ImageButton) rootView.findViewById(R.id.nowme_btn7);
        nowme_btn8 = (ImageButton) rootView.findViewById(R.id.nowme_btn8);
        nowme_btn9 = (ImageButton) rootView.findViewById(R.id.nowme_btn9);
        nowme_btn10 = (ImageButton) rootView.findViewById(R.id.nowme_btn10);
        nowme_btn11 = (ImageButton) rootView.findViewById(R.id.nowme_btn11);
        nowme_btn12 = (ImageButton) rootView.findViewById(R.id.nowme_btn12);


        nowme_btn1.setOnClickListener(this);
        nowme_btn2.setOnClickListener(this);
        nowme_btn3.setOnClickListener(this);
        nowme_btn4.setOnClickListener(this);
        nowme_btn5.setOnClickListener(this);
        nowme_btn6.setOnClickListener(this);
        nowme_btn7.setOnClickListener(this);
        nowme_btn8.setOnClickListener(this);
        nowme_btn9.setOnClickListener(this);
        nowme_btn10.setOnClickListener(this);
        nowme_btn11.setOnClickListener(this);
        nowme_btn12.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nowme_btn1:
                btn_string = "1";
                break;
            case R.id.nowme_btn2:
                btn_string = "2";
                break;
            case R.id.nowme_btn3:
                btn_string = "3";
                break;
            case R.id.nowme_btn4:
                btn_string = "4";
                break;
            case R.id.nowme_btn5:
                btn_string = "5";
                break;
            case R.id.nowme_btn6:
                btn_string = "6";
                break;
            case R.id.nowme_btn7:
                btn_string = "7";
                break;
            case R.id.nowme_btn8:
                btn_string = "8";
                break;
            case R.id.nowme_btn9:
                btn_string = "9";
                break;
            case R.id.nowme_btn10:
                btn_string = "10";
                break;
            case R.id.nowme_btn11:
                btn_string = "11";
                break;
            case R.id.nowme_btn12:
                btn_string = "12";
                break;
        }

        Intent intent = new Intent(getActivity().getBaseContext(), MyNowAcitivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }
}
