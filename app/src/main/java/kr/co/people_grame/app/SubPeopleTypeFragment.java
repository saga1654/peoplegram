package kr.co.people_grame.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

    public SubPeopleTypeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_peopletype, container, false);

        profile_img = (CircularImageView) rootView.findViewById(R.id.profile_img);
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
