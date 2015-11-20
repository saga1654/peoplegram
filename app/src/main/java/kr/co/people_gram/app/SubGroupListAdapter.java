package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubGroupListAdapter extends BaseAdapter{
    private Context mContext;

    private int layout;
    private final ArrayList<SubGroupListDTO> peoplelist;
    LayoutInflater inf;

    public String group_code;


    public SubGroupListAdapter(Context mContext, int layout, ArrayList<SubGroupListDTO> peoplelist)
    {
        this.mContext = mContext;
        this.layout = layout;

        this.peoplelist = peoplelist;
        inf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inf.inflate(layout, null);

    }

    @Override
    public int getCount() {
        return peoplelist.size();
    }

    @Override
    public Object getItem(int position) {
        return peoplelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setChecked(int position) {
        SubGroupListDTO dto = peoplelist.get(position);

        group_code = dto.get_group_code();


    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = inf.inflate(layout, null);

            viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.group_delete);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final SubGroupListDTO dto = peoplelist.get(position);


        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.group_delete);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                group_code = dto.get_group_code();
                RequestParams params = new RequestParams();
                params.put("uid", SharedPreferenceUtil.getSharedPreference(mContext, "uid"));
                params.put("group_code", dto.get_group_code());
                HttpClient.post("/group/groupDelete", params, new AsyncHttpResponseHandler() {
                    public void onStart() {

                    }

                    public void onFailure() {
                    }

                    public void onFinish() {

                    }

                    @Override
                    public void onSuccess(String response) {
                        peoplelist.remove(position);
                        notifyDataSetChanged();
                    }
                });
                //Log.d("people_gram", "선택="+position);
            }
        });

        //SubGroupPeopleListDTO dto = peoplelist.get(position);


        TextView listview_people_list_username = (TextView) convertView.findViewById(R.id.listview_people_list_username);
        TextView listview_people_list_all = (TextView) convertView.findViewById(R.id.listview_people_list_all);
        TextView listview_people_list_cnt = (TextView) convertView.findViewById(R.id.listview_people_list_cnt);
        //LinearLayout group_delete = (LinearLayout) convertView.findViewById(R.id.group_delete);


        listview_people_list_username.setText(dto.get_group_name());
        listview_people_list_all.setText(dto.get_all_people_name());
        String[] split = dto.get_all_people_name().split(",");
        listview_people_list_cnt.setText(split.length + "명");






        return convertView;
    }

    public class ViewHolder
    {
        private LinearLayout layout;
    }



}
