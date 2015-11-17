package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubPeopleSelect_MainFragment extends Fragment {

    private PeopleData pd;
    private String mytype, people_type, gubun1, gubun2, youname;
    private TextView tip1_text, tip2_text, tip3_text, tip4_text, tip5_text, tip6_text, tip7_text;
    private LinearLayout tip1_btn, tip2_btn, tip3_btn, tip4_btn, tip5_btn, tip6_btn, tip7_btn;


    public SubPeopleSelect_MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subpeopleselect_main_fragment, container, false);


        pd = new PeopleData();

        mytype = SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "mytype");
        people_type = pd.get_people_type();
        gubun1 = pd.get_people_gubun1();
        gubun2 = pd.get_people_gubun2();
        youname = pd.get_people_username();



        tip1_text = (TextView) rootView.findViewById(R.id.tip1_text);
        tip2_text = (TextView) rootView.findViewById(R.id.tip2_text);
        tip3_text = (TextView) rootView.findViewById(R.id.tip3_text);
        tip4_text = (TextView) rootView.findViewById(R.id.tip4_text);
        tip5_text = (TextView) rootView.findViewById(R.id.tip5_text);
        tip6_text = (TextView) rootView.findViewById(R.id.tip6_text);
        tip7_text = (TextView) rootView.findViewById(R.id.tip7_text);

        tip1_btn = (LinearLayout) rootView.findViewById(R.id.tip1_btn);
        tip1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleSelect_TipActivity.class);
                intent.putExtra("viewType", "DEFAULT");
                intent.putExtra("tipType", "");
                startActivity(intent);
            }
        });

        tip2_btn = (LinearLayout) rootView.findViewById(R.id.tip2_btn);
        tip2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleSelect_TipActivity.class);
                intent.putExtra("viewType", "ME");
                intent.putExtra("tipType", "2");
                startActivity(intent);
            }
        });


        tip3_btn = (LinearLayout) rootView.findViewById(R.id.tip3_btn);
        tip3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleSelect_TipActivity.class);
                intent.putExtra("viewType", "YOU");
                intent.putExtra("tipType", "3");
                startActivity(intent);
            }
        });

        tip4_btn = (LinearLayout) rootView.findViewById(R.id.tip4_btn);
        tip4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleSelect_TipActivity.class);
                intent.putExtra("viewType", "YOU");
                intent.putExtra("tipType", "4");
                startActivity(intent);
            }
        });

        tip5_btn = (LinearLayout) rootView.findViewById(R.id.tip5_btn);
        tip5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleSelect_TipActivity.class);
                intent.putExtra("viewType", "YOU");
                intent.putExtra("tipType", "5");
                startActivity(intent);
            }
        });


        tip6_btn = (LinearLayout) rootView.findViewById(R.id.tip6_btn);
        tip6_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleSelect_TipActivity.class);
                intent.putExtra("viewType", "YOU");
                intent.putExtra("tipType", "6");
                startActivity(intent);
            }
        });
        tip7_btn = (LinearLayout) rootView.findViewById(R.id.tip7_btn);
        tip7_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleSelect_TipActivity.class);
                intent.putExtra("viewType", "YOU");
                intent.putExtra("tipType", "7");
                startActivity(intent);
            }
        });

        tip();

        return rootView;
    }

    private void tip()
    {
        String packName = getActivity().getPackageName(); // 패키지명
        String resName = "";
        for(int i = 1; i<=7; i++) {
            if(gubun1.equals("F")) {
                resName = "@string/tip" + i + "_" + gubun1;
            } else if(gubun1.equals("L")) {
                resName = "@string/tip" + i + "_" + gubun1;
            } else if(gubun1.equals("S")) {
                resName = "@string/tip" + i + "_" + gubun1;
            } else {
                resName = "@string/tip" + i + "_" + gubun1 + gubun2;
            }

            int resID = getResources().getIdentifier(resName, "values", packName);
            String title = getString(resID);
            title = title.replace("[gubun1]", "<font color='#ff6d2a'>"+gubun1_return(gubun1)+"</font>");
            title = title.replace("[gubun2]", "<font color='#ff6d2a'>"+gubun2_return(gubun1, gubun2)+"</font>");
            title = title.replace("[you_name]", "<font color='#ff6d2a'>"+youname+"</font>");



            switch (i){
                case 1:
                    tip1_text.setText(Html.fromHtml(title));
                    break;
                case 2:
                    tip2_text.setText(Html.fromHtml(title));
                    break;
                case 3:
                    tip3_text.setText(Html.fromHtml(title));
                    break;
                case 4:
                    tip4_text.setText(Html.fromHtml(title));
                    break;
                case 5:
                    tip5_text.setText(Html.fromHtml(title));
                    break;
                case 6:
                    tip6_text.setText(Html.fromHtml(title));
                    break;
                case 7:
                    tip7_text.setText(Html.fromHtml(title));
                    break;
            }
            //Log.d("people_gram", getString(resID));
        }
    }

    private String gubun1_return(String gubun)
    {
        String returnString = "";
        switch (gubun)
        {
            case "P":
                returnString = "가족";
                break;
            case "F":
                returnString = "친구";
                break;
            case "L":
                returnString = "연인";
                break;
            case "C":
                returnString = "직장";
                break;
            case "S":
                returnString = "고객";
                break;


        }
        return returnString;
    }

    private String gubun2_return(String _gubun1, String _gubun2)
    {
        String returnString = "";
        switch (_gubun1)
        {
            case "P":
                switch (_gubun2) {
                    case "A":
                        returnString = "부모";
                        break;
                    case "B":
                        returnString = "형제";
                        break;
                    case "C":
                        returnString = "부부";
                        break;
                    case "D":
                        returnString = "자녀(18세)";
                        break;
                    case "E":
                        returnString = "자녀(19세 이상)";
                        break;
                }

                break;
            case "F":
                returnString = "친구";
                break;
            case "L":
                returnString = "연인";
                break;
            case "C":
                switch (_gubun2) {
                    case "A":
                        returnString = "상사";
                        break;
                    case "B":
                        returnString = "동료";
                        break;
                    case "C":
                        returnString = "후임";
                        break;
                }
                break;
            case "S":
                returnString = "고객";
                break;


        }
        return  returnString;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
