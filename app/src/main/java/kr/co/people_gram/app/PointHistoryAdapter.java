package kr.co.people_gram.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        TextView point_datetime = (TextView) convertView.findViewById(R.id.point_datetime);
        TextView point_title = (TextView) convertView.findViewById(R.id.point_title);
        TextView point_use = (TextView) convertView.findViewById(R.id.point_use);

        point_datetime.setText(dto.get_datetime());
        point_title.setText(dto.get_title());
        point_use.setText(dto.get_point());


        return convertView;
    }
}
