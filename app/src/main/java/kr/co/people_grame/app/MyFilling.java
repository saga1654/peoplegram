package kr.co.people_grame.app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


public class MyFilling extends AppCompatActivity {

    Singleton m_Inst = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_filling);


        m_Inst.InitGUIFrame(this);
        //filling

        //RelativeLayout panel = new RelativeLayout(this);
        //setContentView(panel);

        RelativeLayout panel = (RelativeLayout) findViewById(R.id.feelling);

        TextView tv = new TextView(this);
        //tv.setText("Rotary knob control\nRadu Motisan 2013\nwww.pocketmagic.net");
        //tv.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        panel.addView(tv, lp);
        panel.setBackgroundColor(Color.rgb(71,72,90));


        /*
        final TextView tv2 = new TextView(this); tv2.setText("");
        lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        panel.addView(tv2, lp);
        */

        RoundKnobButton rv = new RoundKnobButton(this, R.drawable.myfilling_line_bg, R.drawable.myfilling_line, R.drawable.myfilling_line,
                m_Inst.Scale(450), m_Inst.Scale(450));
        lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv, lp);

        rv.setRotorPercentage(0);
        rv.SetListener(new RoundKnobButton.RoundKnobButtonListener() {
            public void onStateChange(boolean newstate) {
                Toast.makeText(MyFilling.this, "New state:" + newstate, Toast.LENGTH_SHORT).show();
            }

            public void onRotate(final int percentage) {
                /*
                tv2.post(new Runnable() {
                    public void run() {
                        /*
                        if(percentage == 0) {
                            Drawable drawable = getResources().getDrawable(R.drawable.myfilling_line_bg);
                            RelativeLayout panel = (RelativeLayout) findViewById(R.id.feelling);
                            panel.setBackground(drawable);
                        } else if(percentage >= 0) {
                            Drawable drawable = getResources().getDrawable(R.drawable.myfilling_line_bg1);
                            RelativeLayout panel = (RelativeLayout) findViewById(R.id.feelling);
                            panel.setBackground(drawable);

                        }

                        //tv2.setText("\n" + percentage + "%\n");
                    }
                });
                */
            }
        });
    }


    public void btn_comment(View v) {
        Intent intent = new Intent(MyFilling.this, MyFilling_Comment_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void btn_back(View v) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_filling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
