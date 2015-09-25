package kr.co.people_grame.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by 광희 on 2015-09-15.
 */
public class SubFeellingFragment extends Fragment implements View.OnClickListener {
    public SubFeellingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sub_fragment_feelling, container, false);

        Button feelling_btn = (Button) rootView.findViewById(R.id.feelling_btn);
        feelling_btn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.feelling_btn:
                Intent intent = new Intent(getActivity(), MyFilling.class);
                getActivity().startActivityForResult(intent, 12345);
                getActivity().overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                break;
        }
    }
}
