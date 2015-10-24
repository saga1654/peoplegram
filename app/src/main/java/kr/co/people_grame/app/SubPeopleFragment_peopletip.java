package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * Created by 광희 on 2015-09-15.
 */

public class SubPeopleFragment_peopletip extends Fragment {
    private LinearLayout subpeople_menu1, subpeople_menu2, subpeople_menu3, subpeople_menu4, subpeople_menu5;
    private PeopleData pd;

    private String people_uid, people_name, people_type, people_gubun1;
    private ProgressDialog dialog;
    private String gubun1 = "";

    public SubPeopleFragment_peopletip() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subpeopletype_fragment, container, false);


        pd = new PeopleData();


        subpeople_menu1 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu1);
        subpeople_menu2 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu2);
        subpeople_menu3 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu3);
        subpeople_menu4 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu4);
        subpeople_menu5 = (LinearLayout) rootView.findViewById(R.id.subpeople_menu5);


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


        people_uid = pd.get_people_uid();
        people_name = pd.get_people_username();
        people_type = pd.get_people_type();
        people_gubun1 = pd.get_people_gubun1();

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
            String point_str = SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "point");
            int point = Integer.parseInt(point_str);

            point = 0;


            /*
            intent = new Intent(getActivity().getBaseContext(), SubPeopleContentsType_Activity.class);



            intent.putExtra("people_uid", people_uid);
            intent.putExtra("people_name", people_name);
            intent.putExtra("people_type", people_type);
            */




            switch (v.getId()) {
                case R.id.subpeople_menu1:
                    gubun1 = "P";
                    break;

                case R.id.subpeople_menu2:
                    gubun1 = "F";
                    break;

                case R.id.subpeople_menu3:
                    gubun1 = "L";
                    break;

                case R.id.subpeople_menu4:
                    gubun1 = "C";
                    break;

                case R.id.subpeople_menu5:
                    gubun1 = "S";
                    break;


            }


            RequestParams params = new RequestParams();
            params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
            params.put("people_uid", people_uid);
            params.put("people_name", people_name);
            params.put("pepple_type", people_type);
            params.put("gubun1", gubun1);

            HttpClient.post("/my_type/youTypeSelect", params, new AsyncHttpResponseHandler() {
                public void onStart() {
                    dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                }

                public void onFinish() {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(String response) {
                    Log.d("people_gram", response);
                    if (response.equals("998")) {
                        Toast.makeText(getActivity(), "진단한 내용이 존재하지 않습니다.\n추후 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                    } else if (response.equals("999")) {
                        Toast.makeText(getActivity(), "진단한 내용이 존재하지 않습니다.\n추후 다시 시도해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getActivity(), SubPeopleContentsType_Activity.class);
                        intent.putExtra("gubun", gubun1);
                        intent.putExtra("youtype", people_type);
                        intent.putExtra("people_name", people_name);
                        intent.putExtra("people_youtype", response);
                        getActivity().startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                }
            });

            /*
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            */
        }
    };



}
