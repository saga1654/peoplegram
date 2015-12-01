package kr.co.people_gram.app;

import android.content.Context;
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
public class PointHistoryAdapter extends BaseAdapter {
    private Context mContext;

    private int layout;
    private ArrayList<PointHistoryDTO> contentsList;
    LayoutInflater inf;


    public PointHistoryAdapter(Context mContext, int layout, ArrayList<PointHistoryDTO> contentsList)
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

        PointHistoryDTO dto = contentsList.get(position);

        ImageView point_type = (ImageView) convertView.findViewById(R.id.point_type);
        TextView point_datetime = (TextView) convertView.findViewById(R.id.point_datetime);
        TextView point_title = (TextView) convertView.findViewById(R.id.point_title);
        TextView point_use = (TextView) convertView.findViewById(R.id.point_use);
        TextView now_point = (TextView) convertView.findViewById(R.id.now_point);

        point_datetime.setText(dto.get_datetime());
        point_title.setText(dto.get_title());
        point_use.setText(Utilities.comma(Integer.parseInt(dto.get_point())) + "p");
        now_point.setText("현재 포인트 : " + Utilities.comma(Integer.parseInt(dto.get_now_point())) + "p");

        if(dto.get_type().equals("O")) {
            point_type.setImageResource(R.drawable.point_icon_out);
        } else {
            point_type.setImageResource(R.drawable.point_icon_in);
        }


        return convertView;
    }
}
