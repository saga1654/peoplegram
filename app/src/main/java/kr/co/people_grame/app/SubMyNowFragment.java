package kr.co.people_grame.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubMyNowFragment extends Fragment implements View.OnClickListener {
    public SubMyNowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_mynow, container, false);

        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
