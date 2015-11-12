package kr.co.people_grame.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.plus.People;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubPeopleListAdapter extends BaseAdapter{
    private Context mContext;

    private int layout;
    private final ArrayList<SubPeopleListDTO> peoplelist;
    LayoutInflater inf;

    public SubPeopleListAdapter(Context mContext, int layout, ArrayList<SubPeopleListDTO> peoplelist)
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
        } else {
        }


        SubPeopleListDTO dto = peoplelist.get(position);

        TextView listview_people_list_username = (TextView) convertView.findViewById(R.id.listview_people_list_username);
        TextView listview_people_list_email = (TextView) convertView.findViewById(R.id.listview_people_list_email);
        ImageView listview_proplelist_img = (ImageView) convertView.findViewById(R.id.listview_proplelist_img);
        TextView listview_people_list_cnt = (TextView) convertView.findViewById(R.id.listview_people_list_cnt);

        LinearLayout new_cnt = (LinearLayout) convertView.findViewById(R.id.new_cnt);

        TextView listview_people_list_new_cnt = (TextView) convertView.findViewById(R.id.listview_people_list_new_cnt);

        listview_people_list_username.setText(dto.get_profile_username());
        listview_people_list_email.setText(dto.get_profile_email());
        listview_people_list_cnt.setText(dto.get_profile_cnt() + "명");

        //Log.d("people_gram", String.valueOf(dto.get_profile_new_cnt()));
        listview_people_list_new_cnt.setText(String.valueOf(dto.get_profile_new_cnt()));

        if(dto.get_profile_new_cnt() == 0) {
            new_cnt.setVisibility(View.GONE);
        } else {
            new_cnt.setVisibility(View.VISIBLE);
        }


        final String people_type = dto.get_profile_type();



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

            case "default":
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                break;
            /*

            default:

                listview_proplelist_img.setVisibility(View.GONE);
                break;
             */
        }

        //listview_proplelist_img.setTag(position);
        /*
        listview_proplelist_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SubMyType_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("mytype", people_type);
                mContext.startActivity(intent);



            }
        });
        */

        return convertView;
    }

}
