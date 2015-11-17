package kr.co.people_grame.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubGroupListAdapter extends BaseAdapter{
    private Context mContext;

    private int layout;
    private final ArrayList<SubGroupListDTO> peoplelist;
    LayoutInflater inf;


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




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if(convertView == null) {
            convertView = inf.inflate(layout, null);
        }


        SubGroupListDTO dto = peoplelist.get(position);
        //SubGroupPeopleListDTO dto = peoplelist.get(position);

        TextView listview_people_list_username = (TextView) convertView.findViewById(R.id.listview_people_list_username);
        TextView listview_people_list_all = (TextView) convertView.findViewById(R.id.listview_people_list_all);
        TextView listview_people_list_cnt = (TextView) convertView.findViewById(R.id.listview_people_list_cnt);

        listview_people_list_username.setText(dto.get_group_name());
        listview_people_list_all.setText(dto.get_all_people_name());
        String[] split = dto.get_all_people_name().split(",");
        listview_people_list_cnt.setText(split.length + "명");






        return convertView;
    }


}
