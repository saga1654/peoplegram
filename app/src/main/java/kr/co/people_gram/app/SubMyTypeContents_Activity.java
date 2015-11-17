package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class SubMyTypeContents_Activity extends AppCompatActivity {

    private TextView popup_contents_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_my_type_contents_);
        popup_contents_title = (TextView) findViewById(R.id.popup_contents_title);


        Intent intent = getIntent();
        if(intent != null) {

        }



    }

}
