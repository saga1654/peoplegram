package kr.co.people_grame.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubPeopleTypeFragment extends Fragment implements View.OnClickListener {

    private CircularImageView profile_img;
    private AQuery aq;
    private TextView profile_username;
    private ImageView profile_type;

    private LinearLayout peopletype_menu1, peopletype_menu2, peopletype_menu3, peopletype_menu4, peopletype_menu5;

    private LinearLayout people_menu1, people_menu2, people_menu3, people_menu4, people_menu5;

    public SubPeopleTypeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_peopletype, container, false);

        peopletype_menu1 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu1);
        peopletype_menu2 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu2);
        peopletype_menu3 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu3);
        peopletype_menu4 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu4);
        peopletype_menu5 = (LinearLayout) rootView.findViewById(R.id.peopletype_menu5);

        people_menu1 = (LinearLayout) rootView.findViewById(R.id.people_menu1);
        people_menu2 = (LinearLayout) rootView.findViewById(R.id.people_menu2);
        people_menu3 = (LinearLayout) rootView.findViewById(R.id.people_menu3);
        people_menu4 = (LinearLayout) rootView.findViewById(R.id.people_menu4);
        people_menu5 = (LinearLayout) rootView.findViewById(R.id.people_menu5);

        peopletype_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_menu1.setVisibility(View.VISIBLE);
                people_menu2.setVisibility(View.GONE);
                people_menu3.setVisibility(View.GONE);
                people_menu4.setVisibility(View.GONE);
                people_menu5.setVisibility(View.GONE);
            }
        });

        peopletype_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_menu1.setVisibility(View.GONE);
                people_menu2.setVisibility(View.VISIBLE);
                people_menu3.setVisibility(View.GONE);
                people_menu4.setVisibility(View.GONE);
                people_menu5.setVisibility(View.GONE);
            }
        });

        peopletype_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_menu1.setVisibility(View.GONE);
                people_menu2.setVisibility(View.GONE);
                people_menu3.setVisibility(View.VISIBLE);
                people_menu4.setVisibility(View.GONE);
                people_menu5.setVisibility(View.GONE);
            }
        });

        peopletype_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_menu1.setVisibility(View.GONE);
                people_menu2.setVisibility(View.GONE);
                people_menu3.setVisibility(View.GONE);
                people_menu4.setVisibility(View.VISIBLE);
                people_menu5.setVisibility(View.GONE);
            }
        });

        peopletype_menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people_menu1.setVisibility(View.GONE);
                people_menu2.setVisibility(View.GONE);
                people_menu3.setVisibility(View.GONE);
                people_menu4.setVisibility(View.GONE);
                people_menu5.setVisibility(View.VISIBLE);
            }
        });




        //profile_img = (CircularImageView) rootView.findViewById(R.id.profile_img);
        //profile_username = (TextView) rootView.findViewById(R.id.profile_username);
        //profile_type = (ImageView) rootView.findViewById(R.id.profile_type);
       // aq = new AQuery(getActivity());

        //String filename = SharedPreferenceUtil.getSharedPreference(getActivity(), "profile_image");
        //profile_img_view(filename);

        //profile_username.setText(SharedPreferenceUtil.getSharedPreference(getActivity(), "username"));
/*
        switch (SharedPreferenceUtil.getSharedPreference(getActivity(), "mytype")) {
            case "I":
                profile_type.setImageResource(R.mipmap.peoplelist_type_i);
                break;
            case "D":
                profile_type.setImageResource(R.mipmap.peoplelist_type_d);
                break;
            case "E":
                profile_type.setImageResource(R.mipmap.peoplelist_type_e);
                break;
            case "A":
                profile_type.setImageResource(R.mipmap.peoplelist_type_a);
                break;
            default:
                profile_type.setImageResource(R.mipmap.peoplelist_type_default);
                break;
        }
*/
        return rootView;
    }


    private void profile_img_view(String filename)
    {
        ImageOptions options = new ImageOptions();
        options.ratio = 1;

        options.memCache = true;
        options.fileCache = true;


        String imageUrl = "http://121.162.209.41:81/"+filename;
        aq.id(profile_img).image(imageUrl, options);

    }

    @Override
    public void onClick(View v) {

    }
}
