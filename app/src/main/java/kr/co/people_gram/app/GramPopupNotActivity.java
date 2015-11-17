package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
