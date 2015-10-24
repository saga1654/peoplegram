package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubPeopleTypeFragment extends Fragment implements View.OnClickListener {

    final int SubPeopleTypeFragmentCode = 2;


    private CircularImageView profile_img;
    private AQuery aq;
    private TextView profile_username;
    private ImageView profile_type;
    private String mytype;

    private LinearLayout peopletype_menu1, peopletype_menu2, peopletype_menu3, peopletype_menu4, peopletype_menu5;
    private LinearLayout people_menu1, people_menu2, people_menu3, people_menu4, people_menu5;
    private ProgressDialog dialog;

    public static Boolean P_check = false;
    public static Boolean F_check = false;
    public static Boolean L_check = false;
    public static Boolean C_check = false;
    public static Boolean S_check = false;

    private int P_cnt = 0;
    private int F_cnt = 0;
    private int L_cnt = 0;
    private int C_cnt = 0;
    private int S_cnt = 0;

    private int P_point = 0;
    private int F_point = 0;
    private int L_point = 0;
    private int C_point = 0;
    private int S_point = 0;

    public void dataResult()
    {
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
        HttpClient.post("/my_type/myTypeSelect", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONObject jobj_p = new JSONObject(jobj.getString("P_cnt"));
                    JSONObject jobj_f = new JSONObject(jobj.getString("F_cnt"));
                    JSONObject jobj_l = new JSONObject(jobj.getString("L_cnt"));
                    JSONObject jobj_c = new JSONObject(jobj.getString("C_cnt"));
                    JSONObject jobj_s = new JSONObject(jobj.getString("S_cnt"));


                    if(jobj_p.getString("p_code").equals("000")) {
                        P_check = true;
                        P_cnt = Integer.parseInt(jobj_p.getString("p_count"));
                        P_point = Integer.parseInt(jobj_p.getString("point"));
                    } else {
                        P_check = false;
                        P_cnt = Integer.parseInt(jobj_p.getString("p_count"));
                        P_point = Integer.parseInt(jobj_p.getString("point"));
                    }

                    if(jobj_f.getString("f_code").equals("000")) {
                        F_check = true;
                        F_cnt = Integer.parseInt(jobj_f.getString("f_count"));
                        F_point = Integer.parseInt(jobj_f.getString("point"));
                    } else {
                        F_check = false;
                        F_cnt = Integer.parseInt(jobj_f.getString("f_count"));
                        F_point = Integer.parseInt(jobj_f.getString("point"));
                    }

                    if(jobj_l.getString("l_code").equals("000")) {
                        L_check = true;
                        L_cnt = Integer.parseInt(jobj_l.getString("l_count"));
                        L_point = Integer.parseInt(jobj_l.getString("point"));
                    } else {
                        L_check = false;
                        L_cnt = Integer.parseInt(jobj_l.getString("l_count"));
                        L_point = Integer.parseInt(jobj_l.getString("point"));
                    }

                    if(jobj_c.getString("c_code").equals("000")) {
                        C_check = true;
                        C_cnt = Integer.parseInt(jobj_c.getString("c_count"));
                        C_point = Integer.parseInt(jobj_c.getString("point"));
                    } else {
                        C_check = false;
                        C_cnt = Integer.parseInt(jobj_c.getString("c_count"));
                        C_point = Integer.parseInt(jobj_c.getString("point"));
                    }

                    if(jobj_s.getString("s_code").equals("000")) {
                        S_check = true;
                        S_cnt = Integer.parseInt(jobj_s.getString("s_count"));
                        S_point = Integer.parseInt(jobj_s.getString("point"));
                    } else {
                        S_check = false;
                        S_cnt = Integer.parseInt(jobj_s.getString("s_count"));
                        S_point = Integer.parseInt(jobj_s.getString("point"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public SubPeopleTypeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_peopletype, container, false);

        peopletype_menu1 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu1);
        peopletype_menu2 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu2);
        peopletype_menu3 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu3);
        peopletype_menu4 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu4);
        peopletype_menu5 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu5);

        people_menu1 = (LinearLayout) rootView.findViewById(R.id.people_menu1);
        people_menu2 = (LinearLayout) rootView.findViewById(R.id.people_menu2);
        people_menu3 = (LinearLayout) rootView.findViewById(R.id.people_menu3);
        people_menu4 = (LinearLayout) rootView.findViewById(R.id.people_menu4);
        people_menu5 = (LinearLayout) rootView.findViewById(R.id.people_menu5);

        mytype = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");


        dataResult();



        peopletype_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(P_check == false) {

                    if(P_cnt < 2) {
                        Toast.makeText(getActivity(), "본인 포함 최소 2명 이상 진단된 경우에 볼 수 있습니다.\n진단 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("people_gram", String.valueOf(P_point));
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(P_point));
                        intent.putExtra("gubun1", "P");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
                    params.put("gubun1", "P");
                    HttpClient.post("/my_type/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {

                            String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                            String people_type = response;
                            String gubun1 = "P";
                            //Log.d("people_gram", my_type + ":::" + people_type + ":::" + gubun1);

                            if(people_type.equals("999")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else if(people_type.equals("998")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                                intent.putExtra("mytype", my_type);
                                intent.putExtra("people_type", people_type);
                                intent.putExtra("gubun1", gubun1);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                            }

                            /*
                            intent.putExtra("people_type", response);
                            intent.putExtra("gubun1", gubun1);
                            setResult(RESULT_OK, intent);
                            finish();
                            */

                        }
                    });
                }
            }
        });

        peopletype_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(F_check == false) {
                    if(F_cnt < 2) {
                        Toast.makeText(getActivity(), "본인 포함 최소 2명 이상 진단된 경우에 볼 수 있습니다.\n진단 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("people_gram", String.valueOf(P_point));
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(P_point));
                        intent.putExtra("gubun1", "F");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
                    params.put("gubun1", "F");
                    HttpClient.post("/my_type/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {


                            String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                            String people_type = response;
                            String gubun1 = "F";
                            //Log.d("people_gram", my_type + ":::" + people_type + ":::" + gubun1);

                            if(people_type.equals("999")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else if(people_type.equals("998")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                                intent.putExtra("mytype", my_type);
                                intent.putExtra("people_type", people_type);
                                intent.putExtra("gubun1", gubun1);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                            }
                        }
                    });
                }
            }
        });

        peopletype_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(L_check == false) {

                    if(L_cnt < 1) {
                        Toast.makeText(getActivity(), "연인으로 진단된 내역이 존재하지 않습니다.\n진단 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("people_gram", String.valueOf(P_point));
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(P_point));
                        intent.putExtra("gubun1", "L");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
                    params.put("gubun1", "L");
                    HttpClient.post("/my_type/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {


                            String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                            String people_type = response;
                            String gubun1 = "L";
                            //Log.d("people_gram", my_type + ":::" + people_type + ":::" + gubun1);

                            if(people_type.equals("999")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else if(people_type.equals("998")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                                intent.putExtra("mytype", my_type);
                                intent.putExtra("people_type", people_type);
                                intent.putExtra("gubun1", gubun1);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                            }
                        }
                    });
                }
            }
        });

        peopletype_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(C_check == false) {


                    if(C_cnt < 3) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {

                        Log.d("people_gram", String.valueOf(P_point));
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(P_point));
                        intent.putExtra("gubun1", "C");
                        String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                        intent.putExtra("mytype", my_type);
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
                    params.put("gubun1", "C");
                    HttpClient.post("/my_type/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {
                            String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                            String people_type = response;
                            String gubun1 = "C";


                            if(people_type.equals("999")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else if(people_type.equals("998")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                                intent.putExtra("mytype", my_type);
                                intent.putExtra("people_type", people_type);
                                intent.putExtra("gubun1", gubun1);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                            }
                        }
                    });
                }
            }
        });

        peopletype_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(S_check == false) {

                    if(C_cnt < 1) {
                        Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("people_gram", String.valueOf(P_point));
                        Intent intent = new Intent(getActivity(), GramPopupMyTypeActivity.class);
                        intent.putExtra("point", String.valueOf(P_point));
                        intent.putExtra("gubun1", "S");
                        getActivity().startActivityForResult(intent, SubPeopleTypeFragmentCode);
                        getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity(), "uid"));
                    params.put("gubun1", "S");
                    HttpClient.post("/my_type/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {


                            String my_type = SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype");
                            String people_type = response;
                            String gubun1 = "S";
                            //Log.d("people_gram", my_type + ":::" + people_type + ":::" + gubun1);

                            if(people_type.equals("999")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else if(people_type.equals("998")) {
                                Toast.makeText(getActivity(), "평가한 인원수가 부족합니다\n피플들에게 요청해주세요.", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(getActivity(), SubPeopleTypeContents_Activity.class);
                                intent.putExtra("mytype", my_type);
                                intent.putExtra("people_type", people_type);
                                intent.putExtra("gubun1", gubun1);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                            }
                        }
                    });
                }
            }
        });




        //profile_img = (CircularImageView) rootView.findViewById(R.id.profile_img);
        //profile_username = (TextView) rootView.findViewById(R.id.profile_username);
        //profile_type = (ImageView) rootView.findViewById(R.id.profile_type);
       // aq = new AQuery(getActivity());

        //String filename = SharedPreferenceUtil.getSharedPreference(getActivity(), "profile_image");
        //profile_img_view(filename);

        //profile_username.setText(SharedPreferenceUtil.getSharedPreference(getActivity(), "username"));
/*
        switch (SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype")) {
            case "I":
                profile_type.setImageResource(R.mipmap.peoplelist_type_i);
                break;
            case "D":
                profile_type.setImageResource(R.mipmap.peoplelist_type_d);
                break;
            case "E":
                profile_type.setImageResource(R.mipmap.peoplelist_type_e);
                break;
            case "A":
                profile_type.setImageResource(R.mipmap.peoplelist_type_a);
                break;
            default:
                profile_type.setImageResource(R.mipmap.peoplelist_type_default);
                break;
        }
*/
        return rootView;
    }






    private void profile_img_view(String filename)
    {
        ImageOptions options = new ImageOptions();
        options.ratio = 1;

        options.memCache = true;
        options.fileCache = true;


        String imageUrl = "http://121.162.209.41:81/"+filename;
        aq.id(profile_img).image(imageUrl, options);

    }

    @Override
    public void onClick(View v) {

    }

}
