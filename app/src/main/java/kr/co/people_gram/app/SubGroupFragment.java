package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
public class SubGroupFragment extends Fragment {

    private ImageView group_create;

    private ArrayList<SubGroupListDTO> people_dto_list;
    private SubGroupListAdapter people_adapter_list;
    private ProgressDialog dialog;
    private ListView sf_people_list;

    final int SubGramFragmentView = 41;

    public SubGroupFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_group, container, false);


        sf_people_list = (ListView) rootView.findViewById(R.id.people_list);
        group_create = (ImageView) rootView.findViewById(R.id.group_create);
        //group_delete = (ImageView) rootView.findViewById(R.id.group_delete);




        group_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GroupWriteActivity.class);
                startActivityForResult(intent, 41);
                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });


        peopleList();

        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                people_adapter_list.setChecked(position);
                people_adapter_list.notifyDataSetChanged();


                SubGroupListDTO dto = (SubGroupListDTO) sf_people_list.getItemAtPosition(position);
                String group_code = dto.get_group_code();
                String group_name = dto.get_group_name();
                Intent intent = new Intent(getActivity(), SubGroupDetailView_Activity.class);
                intent.putExtra("group_code", group_code);
                intent.putExtra("group_name", group_name);

                startActivityForResult(intent, 41);
                getActivity().overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);


            }
        });


        return rootView;
    }

    public void peopleDelete()
    {

    }

    public void peopleList()
    {
        people_dto_list = new ArrayList<SubGroupListDTO>();
        //people_dto_list_temp.clear();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(getActivity().getBaseContext(), "uid"));
        //params.put("searchType", searchType);
        HttpClient.post("/group/groupList", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(getActivity(), "", "데이터 수신중");
            }

            public void onFailure() {
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
                try {

                    JSONObject data = new JSONObject(response);
                    JSONArray people_list = data.getJSONArray("list");


                    for (int i = 0; i < people_list.length(); i++) {
                        JSONObject jobj = people_list.getJSONObject(i);

                        people_dto_list.add(new SubGroupListDTO(
                                jobj.getString("GROUP_CODE")
                                , jobj.getString("GROUP_NAME")
                                , jobj.getString("ALL_PEOPLE_NAME")
                        ));

                    }

                    people_adapter_list = new SubGroupListAdapter(getActivity().getBaseContext(), R.layout.sub_group_row_list, people_dto_list);
                    sf_people_list.setAdapter(people_adapter_list);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("people_gram", String.valueOf(requestCode) + ":::" + resultCode);
        if(requestCode == 41) {
            peopleList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
