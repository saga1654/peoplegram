package kr.co.people_grame.app;

import android.content.Context;
import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubMainListAdapter extends BaseAdapter {
    private Context mContext;

    private int layout;
    private ArrayList<SubMainListDTO> contentsList;
    LayoutInflater inf;

    public SubMainListAdapter(Context mContext, int layout, ArrayList<SubMainListDTO> contentsList)
    {
        this.mContext = mContext;
        this.layout = layout;
        this.contentsList = contentsList;
        inf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inf.inflate(layout, null);
    }

    @Override
    public int getCount() {
        return contentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inf.inflate(layout, null);
        }

        SubMainListDTO dto = contentsList.get(position);


        TextView my_profile_nickname = (TextView) convertView.findViewById(R.id.my_profile_nickname);
        TextView you_profile_nickname = (TextView) convertView.findViewById(R.id.you_profile_nickname);
        TextView insert_datetime = (TextView) convertView.findViewById(R.id.insert_datetime);
        TextView gonggam_cnt = (TextView) convertView.findViewById(R.id.gonggambox_cnt);
        TextView comment_cnt = (TextView) convertView.findViewById(R.id.commnect_cnt);

        my_profile_nickname.setText(dto.get_my_profile_nickname());
        you_profile_nickname.setText(dto.get_you_profile_nickname());
        insert_datetime.setText(dto.insert_datetime);
        gonggam_cnt.setText(dto.gonggam_cnt);
        comment_cnt.setText(dto.comment_cnt);

        /*
        TextView listview_people_list_username = (TextView) convertView.findViewById(R.id.listview_people_list_username);
        TextView listview_people_list_email = (TextView) convertView.findViewById(R.id.listview_people_list_email);
        ImageView listview_proplelist_img = (ImageView) convertView.findViewById(R.id.listview_proplelist_img);

        listview_people_list_username.setText(dto.get_profile_username());
        listview_people_list_email.setText(dto.get_profile_email());

        String people_type = dto.get_profile_type();



        switch (people_type) {
            case "A":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_a);
                break;
            case "I":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_i);
                break;
            case "E":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_e);
                break;
            case "D":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_d);
                break;
            case "":
                listview_proplelist_img.setVisibility(View.GONE);
                break;
            default:

                listview_proplelist_img.setVisibility(View.GONE);
                break;
        }
        */

        return convertView;
    }
}
