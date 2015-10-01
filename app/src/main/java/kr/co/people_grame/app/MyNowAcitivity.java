package kr.co.people_grame.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MyNowAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_now_acitivity);
    }

    public void finish()
    {
        overridePendingTransition(R.anim.slide_clode_up_info, R.anim.slide_close_down_info);
        super.finish();
    }
}
