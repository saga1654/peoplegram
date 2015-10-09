package kr.co.people_grame.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MyNowAcitivity extends AppCompatActivity {
    private AutoCompleteTextView search_mynow;


    private String[] list = {"테스트", "테스트1", "테스트2", "테스트3", "테스트4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_now_acitivity);


        search_mynow = (AutoCompleteTextView) findViewById(R.id.search_mynow);
        search_mynow.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }

    public void finish()
    {
        overridePendingTransition(R.anim.slide_clode_up_info, R.anim.slide_close_down_info);
        super.finish();
    }
}
