package kr.co.people_grame.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kr.co.people_grame.app.NoticeDTO;

/**
 * Created by 광희 on 2015-09-18.
 */
public class NoticeAdapter extends BaseAdapter {
    private Context mContext;

    private int layout;
    private ArrayList<NoticeDTO> noticelist;
    LayoutInflater inf;

    public NoticeAdapter(Context mContext, int layout, ArrayList<NoticeDTO> noticelist)
    {
        this.mContext = mContext;
        this.layout = layout;
        this.noticelist = noticelist;
        inf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inf.inflate(layout, null);
    }

    @Override
    public int getCount() {
        return noticelist.size();
    }

    @Override
    public Object getItem(int position) {
        return noticelist.get(position);
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
        NoticeDTO dto = noticelist.get(position);


        TextView tv_notice_subject = (TextView) convertView.findViewById(R.id.tv_notice_subject);
        TextView tv_notice_create_datetime = (TextView) convertView.findViewById(R.id.tv_notice_create_datetime);

        tv_notice_subject.setText(dto.get_NoticeTitle());
        tv_notice_create_datetime.setText("등록날짜 : " + dto.get_CreateJoin());
        /*
        TextView tv_question_title = (TextView) convertView.findViewById(R.id.tv_question_title);
        TextView tv_point = (TextView) convertView.findViewById(R.id.tv_point);
        TextView tv_survey_date = (TextView) convertView.findViewById(R.id.tv_survey_date);


        //Log.d("TestLog", dto.get_point());

        tv_question_title.setText(dto.get_surveytitle());
        tv_point.setText("적립포인트 : " +dto.get_point());
        tv_survey_date.setText("참여일시 : " +dto.get_joindate());
        */
        return convertView;
    }
}
