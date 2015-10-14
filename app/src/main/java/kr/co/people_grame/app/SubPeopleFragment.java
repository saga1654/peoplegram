package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubPeopleFragment extends Fragment {

    private ProgressDialog dialog;
    private ListView sf_people_list;

    private ArrayList<SubPeopleListDTO> people_dto_list;
    private SubPeopleListAdapter people_adapter_list;

    private View mainView;
    private ImageView listview_proplelist_img;

    public SubPeopleFragment() {
        Log.d("people_gram", "Fragment_실행");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_people, container, false);
        View header = inflater.inflate(R.layout.sub_people_header, null, false);

        mainView = rootView;

        sf_people_list = (ListView)rootView.findViewById(R.id.sf_people_list);
        sf_people_list.addHeaderView(header);

        listview_proplelist_img = (ImageView) rootView.findViewById(R.id.listview_proplelist_img);
        TextView listview_my_people_list_username = (TextView) rootView.findViewById(R.id.listview_my_people_list_username);
        TextView listview_my_people_list_email = (TextView) rootView.findViewById(R.id.listview_my_people_list_email);

        Switch listview_mytype_switch = (Switch) rootView.findViewById(R.id.listview_mytype_switch);
        listview_mytype_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == false) {
                    switch (SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype"))
                    {
                        case "A":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                            break;

                        case "I":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                            break;

                        case "D":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                            break;

                        case "E":
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                            break;

                        default:
                            listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                            break;
                    }
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid"));
                    HttpClient.post("/user/profile_people_type", params, new AsyncHttpResponseHandler() {
                        public void onStart() {
                            //Log.d("people_gram", "시작");
                            dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
                        }

                        public void onFinish() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onSuccess(String response) {

                            switch (response)
                            {
                                case "A":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                                    break;

                                case "I":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                                    break;

                                case "D":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                                    break;

                                case "E":
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                                    break;

                                default:
                                    Toast.makeText(getActivity(), "피플이 귀하를 진단하지 않았습니다.\n피플들에게 요청하세요.", Toast.LENGTH_SHORT).show();
                                    listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                                    break;
                            }
                        }
                    });
                }
            }
        });




        listview_my_people_list_username.setText(SharedPreferenceUtil.getSharedPreference(getActivity(), "username"));
        listview_my_people_list_email.setText(SharedPreferenceUtil.getSharedPreference(getActivity(), "email"));

        switch (SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype"))
        {
            case "A":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                break;

            case "I":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                break;

            case "D":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                break;

            case "E":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                break;

            default:

                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                break;
        }


        //peopleList();


        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {


                } else {
                    SubPeopleListDTO dto = (SubPeopleListDTO) sf_people_list.getItemAtPosition(position);
                    Intent intent = new Intent(getActivity().getBaseContext(), SubPeopleListPopup_Activity.class);

                    intent.putExtra("people_uid", dto.get_profile_uid());
                    intent.putExtra("people_username", dto.get_profile_username());
                    intent.putExtra("people_mood", dto.get_profile_mood());
                    intent.putExtra("people_type", dto.get_profile_type());
                    intent.putExtra("people_gubun1", dto.get_profile_gubun1());
                    intent.putExtra("people_gubun2", dto.get_profile_gubun2());
                    intent.putExtra("people_speed", dto.get_profile_speed());
                    intent.putExtra("people_control", dto.get_profile_control());

                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                }
            }
        });



        return rootView;
    }

    private void peopleList()
    {
        people_dto_list = new ArrayList<SubPeopleListDTO>();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid"));
        HttpClient.post("/user/member_people", params, new AsyncHttpResponseHandler() {
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
                    JSONArray people_list = new JSONArray(response);
                    for (int i = 0; i < people_list.length(); i++) {
                        JSONObject jobj = people_list.getJSONObject(i);

                        String email = "";
                        String type = "";
                        String gubun1 = "";
                        String gubun2 = "";
                        int speed = 0;
                        int control = 0;

                        if (jobj.getString("JOIN_EMAIL") != "null") {
                            email = jobj.getString("JOIN_EMAIL");
                        }
                        if (jobj.getString("YOU_TYPE") != "null") {
                            type = jobj.getString("YOU_TYPE");
                        }
                        if(jobj.getString("GUBUN1") != "null") {
                            gubun1 = jobj.getString("GUBUN1");
                        }
                        if(jobj.getString("GUBUN2") != "null") {
                            gubun2 = jobj.getString("GUBUN2");
                        }

                        if(jobj.getString("SPEED") != "null") {
                            speed = Integer.parseInt(jobj.getString("SPEED"));
                        }
                        if(jobj.getString("CONTROL") != "null") {
                            control = Integer.parseInt(jobj.getString("CONTROL"));
                        }

                        people_dto_list.add(new SubPeopleListDTO(
                                jobj.getString("PEOPLE_UID")
                                , ""
                                , jobj.getString("PEOPLE_USERNAME")
                                , email
                                , type
                                , ""
                                , gubun1
                                , gubun2
                                , speed
                                , control
                        ));
                    }

                    people_adapter_list = new SubPeopleListAdapter(getActivity().getBaseContext(), R.layout.sub_people_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);

                    //listview_noticeList.setAdapter(notice_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onStart()
    {
        peopleList();
        super.onStart();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }
}
