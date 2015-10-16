package kr.co.people_grame.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PeopleSyncSelect_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_sync_select_);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
