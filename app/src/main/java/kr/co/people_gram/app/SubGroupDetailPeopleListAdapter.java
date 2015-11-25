package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubGroupDetailPeopleListAdapter extends BaseAdapter{
    private Context mContext;

    private int layout;
    private final ArrayList<SubGroupDetailPeopleListDTO> peoplelist;
    LayoutInflater inf;

    public final View convertView;
    private String group_code;
    private String people_uid;

    public SubGroupDetailPeopleListAdapter(Context mContext, int layout, ArrayList<SubGroupDetailPeopleListDTO> peoplelist)
    {
        this.mContext = mContext;
        this.layout = layout;

        this.peoplelist = peoplelist;
        inf = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inf.inflate(layout, null);
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
        SubGroupDetailPeopleListDTO dto = peoplelist.get(position);

        group_code = dto.get_group_code();


    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        final View convertView_return = convertView;

        if(convertView == null) {
            convertView = inf.inflate(layout, null);

            viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.group_delete);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        SubGroupDetailPeopleListDTO dto = peoplelist.get(position);
        final String group_code = dto.get_group_code();
        final String people_uid = dto.get_profile_uid();


        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.group_delete);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SharedPreferenceUtil.getSharedPreference(mContext, "uid").equals(people_uid)) {
                    Toast.makeText(mContext, "본인은 삭제하실수 없습니다.", Toast.LENGTH_LONG).show();
                } else {

                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RequestParams params = new RequestParams();
                            params.put("uid", SharedPreferenceUtil.getSharedPreference(mContext, "uid"));
                            params.put("group_code", group_code);
                            params.put("people_uid", people_uid);
                            HttpClient.post("/group/groupDetailDelete", params, new AsyncHttpResponseHandler() {
                                public void onStart() {

                                }

                                public void onFailure() {
                                }

                                public void onFinish() {

                                }

                                @Override
                                public void onSuccess(String response) {
                                    peoplelist.remove(position);
                                    notifyDataSetChanged();
                                }
                            });
                        }
                    });
                    alert.setMessage("삭제하시겠습니까?");
                    alert.setNegativeButton("취소", null);
                    alert.show();
                }

            }
        });

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

    public class ViewHolder
    {
        private LinearLayout layout;
    }
}
