package kr.co.people_gram.app;

import android.content.Context;
import android.content.Intent;
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
public class QnaAdapter extends BaseAdapter {
    private Context mContext;
    private String answer;

    private int layout;
    private ArrayList<QnaDTO> contentsList;
    LayoutInflater inf;


    public QnaAdapter(Context mContext, int layout, ArrayList<QnaDTO> contentsList)
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

        QnaDTO dto = contentsList.get(position);

        TextView qnaTitle = (TextView) convertView.findViewById(R.id.qnaTitle);
        TextView qna_datetime = (TextView) convertView.findViewById(R.id.qnadatetime);
        ImageView answer_yn = (ImageView) convertView.findViewById(R.id.answer_yn);

        qnaTitle.setText(dto.get_QnaTitle());
        qna_datetime.setText(dto.get_CreateJoin());


        String qna_subject = dto.get_answer_result();
        //Log.d("people_gram", "선택="+qna_subject + ":::" + dto.get_QnaTitle());

        if(qna_subject.equals("")){
            answer_yn.setImageResource(R.drawable.answer_wait);
        } else {
            answer_yn.setImageResource(R.drawable.answer_complete);
        }




        //내용 유무를 판별 하여 이미지 변경
        /*
        if(dto.get_QnaYn().equals("null")) {
            answer_yn.setImageResource(R.drawable.answer_wait);
        }else{
            answer_yn.setImageResource(R.drawable.answer_complete);
        }
        */

        return convertView;
    }
}
