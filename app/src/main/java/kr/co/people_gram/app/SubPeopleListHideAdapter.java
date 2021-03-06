package kr.co.people_gram.app;

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
public class SubPeopleListHideAdapter extends BaseAdapter{
    private Context mContext;

    private int layout;
    private final ArrayList<SubPeopleListDTO> peoplelist;
    LayoutInflater inf;

    private ViewHolder viewHolder = null;
    public String[] uid_check;
    public boolean[] isCheckedConfrim;

    public SubPeopleListHideAdapter(Context mContext, int layout, ArrayList<SubPeopleListDTO> peoplelist)
    {
        this.mContext = mContext;
        this.layout = layout;

        this.peoplelist = peoplelist;
        inf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inf.inflate(layout, null);

        this.isCheckedConfrim = new boolean[peoplelist.size()];
        this.uid_check = new String[peoplelist.size()];
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
        SubPeopleListDTO dto = peoplelist.get(position);

        if(isCheckedConfrim[position] == true) {
            uid_check[position] = "";
        } else {
            uid_check[position] = dto.get_profile_uid();
        }
        isCheckedConfrim[position] = !isCheckedConfrim[position];
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inf.inflate(layout, null);
            viewHolder.cBox = (CheckBox) convertView.findViewById(R.id.hide_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.cBox.setClickable(false);
        viewHolder.cBox.setFocusable(false);

        //viewHolder.cBox.setText(sArrayList.get(position));
        //viewHolder.cBox.setChecked(isCheckedConfrim[position]);



        SubPeopleListDTO dto = peoplelist.get(position);



        viewHolder.cBox.setChecked(isCheckedConfrim[position]);


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

    class ViewHolder {
        // 새로운 Row에 들어갈 CheckBox
        private CheckBox cBox = null;
    }


}
