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
public class NoticeAdapter extends BaseAdapter {
    private Context mContext;

    private int layout;
    private ArrayList<NoticeDTO> contentsList;
    LayoutInflater inf;


    public NoticeAdapter(Context mContext, int layout, ArrayList<NoticeDTO> contentsList)
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

        NoticeDTO dto = contentsList.get(position);


        TextView noticeTitle = (TextView) convertView.findViewById(R.id.noticeTitle);
        TextView noticedatetime = (TextView) convertView.findViewById(R.id.noticedatetime);
        noticeTitle.setText(dto.get_NoticeTitle());
        noticedatetime.setText(dto.get_CreateJoin());

        /*
        TextView qnaTitle = (TextView) convertView.findViewById(R.id.qnaTitle);
        TextView qna_datetime = (TextView) convertView.findViewById(R.id.qnadatetime);


        qnaTitle.setText(dto.get_QnaTitle());
        qna_datetime.setText(dto.get_CreateJoin());
        */


        return convertView;
    }
}
