package kr.co.people_gram.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Setting_QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__question);
    }

    public void finish()
    {
        super.finish();
        this.overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
