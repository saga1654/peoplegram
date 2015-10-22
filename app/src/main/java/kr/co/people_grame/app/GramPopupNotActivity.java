package kr.co.people_grame.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GramPopupNotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gram_popup_not);
    }

    public void pointSet(View v)
    {
        finish();
        Intent intent = new Intent(this, SubGramPoint.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

    }

    public void backBtn(View v) {
        finish();
    }

}
