package kr.co.people_gram.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubPeopleMatchListAdapter extends BaseAdapter{
    private Context mContext;

    private int layout;
    private final ArrayList<SubPeopleMatchListDTO> peoplelist;
    LayoutInflater inf;

    public SubPeopleMatchListAdapter(Context mContext, int layout, ArrayList<SubPeopleMatchListDTO> peoplelist)
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


        SubPeopleMatchListDTO dto = peoplelist.get(position);

        TextView number = (TextView) convertView.findViewById(R.id.number);
        number.setText("TOP"+String.valueOf(position+1)+".");

        TextView listview_people_list_username = (TextView) convertView.findViewById(R.id.listview_people_list_username);
        TextView listview_people_list_email = (TextView) convertView.findViewById(R.id.listview_people_list_email);
        ImageView listview_proplelist_img = (ImageView) convertView.findViewById(R.id.listview_proplelist_img);

        TextView listview_people_list_match_value = (TextView) convertView.findViewById(R.id.listview_people_list_match_value);


        listview_people_list_username.setText(dto.get_profile_username());
       /*
        if(dto.get_profile_email().equals("null")) {
            listview_people_list_email.setText("미가입");
        } else {
            listview_people_list_email.setText(dto.get_profile_email());
        }
       */
        listview_people_list_match_value.setText(String.valueOf(Math.round(dto.get_profile_match_value())) + "%");




        String people_type = dto.get_profile_type();



        /*
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
                listview_proplelist_img.setImageResource(R.mipmap.peoplelist_type_default);
                break;

        }
        */

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
