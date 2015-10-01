package kr.co.people_grame.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubFeelingListAdapter extends BaseAdapter {
    private Context mContext;

    private int layout;
    private ArrayList<SubFeelingListDTO> feelingList;
    LayoutInflater inf;

    public SubFeelingListAdapter(Context mContext, int layout, ArrayList<SubFeelingListDTO> feelingList)
    {
        this.mContext = mContext;
        this.layout = layout;
        this.feelingList = feelingList;
        inf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inf.inflate(layout, null);
    }

    @Override
    public int getCount() {
        return feelingList.size();
    }

    @Override
    public Object getItem(int position) {
        return feelingList.get(position);
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

        TextView listview_feeling_list_type = (TextView) convertView.findViewById(R.id.listview_feeling_list_type);
        TextView listview_feeling_list_comment = (TextView) convertView.findViewById(R.id.listview_feeling_list_comment);
        SubFeelingListDTO dto = feelingList.get(position);
        switch (dto.get_feeling_type()) {
            case "1":
                listview_feeling_list_type.setText("엄청");
                break;
            case "2":
                listview_feeling_list_type.setText("매우좋은");
                break;
            case "3":
                listview_feeling_list_type.setText("좋은");
                break;
            case "4":
                listview_feeling_list_type.setText("보통");
                break;
            case "5":
                listview_feeling_list_type.setText("좋지않은");
                break;
            case "6":
                listview_feeling_list_type.setText("나쁜");
                break;
            case "7":
                listview_feeling_list_type.setText("끔직한");
                break;
        }

        String feeling = "";

        //Log.d("people_gram", dto.get_feeling_select1().toString() + ":::" + dto.get_feeling_select2().toString() + ":::" + dto.get_feeling_select3().toString() + ":::" + dto.get_feeling_select4().toString() + ":::" + dto.get_feeling_select5().toString());

        if(dto.get_feeling_select1().toString().equals("") == false) {
            if(feeling != "") {
                feeling += ", #"+dto.get_feeling_select1().toString();
            } else {
                feeling = "#"+dto.get_feeling_select1().toString();
            }
        }

        if(dto.get_feeling_select2().toString().equals("") == false) {
            if(feeling != "") {
                feeling += ", #"+dto.get_feeling_select2().toString();
            } else {
                feeling = "#"+dto.get_feeling_select2().toString();
            }
        }

        if(dto.get_feeling_select3().toString().equals("") == false) {
            if(feeling != "") {
                feeling += ", #"+dto.get_feeling_select3().toString();
            } else {
                feeling = "#"+dto.get_feeling_select3().toString();
            }
        }

        if(dto.get_feeling_select4().toString().equals("") == false) {
            if(feeling != "") {
                feeling += ", #"+dto.get_feeling_select4().toString();
            } else {
                feeling = "#"+dto.get_feeling_select4().toString();
            }
        }

        if(dto.get_feeling_select5().toString().equals("") == false) {
            if(feeling != "") {
                feeling += ", #"+dto.get_feeling_select5().toString();
            } else {
                feeling = "#"+dto.get_feeling_select5().toString();
            }
        }

        listview_feeling_list_comment.setText(feeling);


        //listview_feeling_list_comment.setText(dto.get_feeling_msg());

        /*
        SubPeopleListDTO dto = peoplelist.get(position);

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
