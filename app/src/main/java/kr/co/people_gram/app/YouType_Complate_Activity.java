package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class YouType_Complate_Activity extends AppCompatActivity {
    private String people_uid, people_username, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10;
    private String speed, control;
    private String youtype;


    private ImageView mytype_activity_typeImg;
    private TextView mytype_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_type__complate_);

        mytype_activity_typeImg = (ImageView) findViewById(R.id.mytype_activity_typeImg);
        mytype_tv = (TextView) findViewById(R.id.mytype_tv);

        Intent intent = getIntent();
        if(intent != null) {
            people_uid = intent.getStringExtra("people_uid");
            people_username = intent.getStringExtra("people_username");
            data1 = intent.getStringExtra("data1");
            data2 = intent.getStringExtra("data2");
            data3 = intent.getStringExtra("data3");
            data4 = intent.getStringExtra("data4");
            data5 = intent.getStringExtra("data5");
            data6 = intent.getStringExtra("data6");
            data7 = intent.getStringExtra("data7");
            data8 = intent.getStringExtra("data8");
            data9 = intent.getStringExtra("data9");
            data10 = intent.getStringExtra("data10");
            float speed = Float.parseFloat(intent.getStringExtra("speed"));
            float control = Float.parseFloat(intent.getStringExtra("control"));
            youtype = intent.getStringExtra("youtype");


            Log.d("people_gram", people_username);
            if(speed > 0 && control > 0) {


                if(speed <= 5 && control <= 1){
                    if(speed <= 1 && control <= 1){
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>" + people_username + "</font>님의 진단 결과<br><b>꼼꼼하기도 하면서 때론 완벽을 추구하는 <b color='#ff8a55'>강한 추진력과 모험심 있는 스타일</b>로 진단되었습니다."));
                    }else{
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>사람과의 관계와 소통을 중요시하며 <b color='#ff8a55'>때론 추진력과 도전을 좋아하는 스타일</b></b><br>로 진단되었습니다."));
                    }
                }else if(speed <= 1 && control <= 5){
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>꼼꼼하면서 <b color='#ff8a55'>밀어부치기도 도전을 좋아하기도 하는 스타일</b></b><br>로 진단되었습니다."));
                }else{
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b color='#ff8a55'>새로운 도전과 모험, 강한 추진력을 가진 스타일</b>로 진단되었습니다."));
                }
            }
            if(speed > 0 && control < 0) {

                if(speed <= 5 && control >= -1){
                    if(speed <= 1 && control >= -1){
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>가끔 밀어부치기도 하고 그러나 상대방을 배려하는 이해심도 가진<b color='#aa64f8'>소통의 달인</b></b><br>으로 진단되었습니다."));
                    }else{
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>사람을 끄는 힘을 가진<b color='#aa64f8'>관계 형성이 좋은 스타일</b></b><br>로 진단되었습니다."));
                    }
                }else if(speed <= 1 && control >= -5){
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>상대에 대한 이해심이 높고 믿을 수 있는 <b color='#aa64f8'>소통하고 싶은 스타일</b></b><br>로 진단되었습니다."));
                }else{
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b color='#aa64f8'>인간관계가 중요하고 큰 문제가 없으며 상대의 마음을 진심으로 대하는 스타일</b>로 진단되었습니다."));
                }
            }
            if(speed < 0 && control > 0) {

                if(speed >= -5 && control <= 1){
                    if(speed >= -1 && control <= 1){
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>도전정신을 가진 이해심 깊은<b color='#37afec'>완벽주의자</b></b><br>로 진단되었습니다."));
                    }else{
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>속 깊이 잘 챙기는<b color='#37afec'>꼼꼼주의자</b></b><br>로 진단되었습니다."));
                    }
                }else if(speed >= -1 && control <= 5){
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>새로운 것을 마다하지 않고 끝까지 완력하게 꼼꼼히 이루는 스타일<br>로 진단되었습니다."));
                }else{
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b color='#37afec'>꼼꼼한 완벽주의자</b>로 진단되었습니다."));
                }
            }
            if(speed < 0 && control < 0) {

                if(speed >= -5 && control >= -1){
                    if(speed >= -1 && control >= -1){
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>사람과의 관계에서 이해심이 높지만 그래도 나름 계산하여 마음을 보이는 스타일</b><br>로 진단되었습니다."));
                    }else{
                        mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>꼼꼼히 상대를 알아보고 끝까지 믿어주는 스타일</b><br>로 진단되었습니다."));
                    }
                }else if(speed >= -1 && control >= -5){
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b>상대를 이해하며 좋은 인간관계를 끝까지 유지하는 스타일</b><br>로 진단되었습니다."));
                }else{
                    mytype_tv.setText(Html.fromHtml("<font color='#ccc'>"+people_username+"</font>님의 진단 결과<br><b color='#52d935'>상대방에 대한 배려심 좋고 신뢰성 있는 스타일</b>로 진단되었습니다."));
                }
            }
            switch (youtype)
            {
                case "I":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_i);
                    break;
                case "D":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_d);
                    break;
                case "E":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_e);
                    break;
                case "A":
                    mytype_activity_typeImg.setImageResource(R.drawable.mytype_a);
                    break;
            }
        }

    }
    public void peopleView_btn(View v) {
        Log.d("people_gram", "타입=" + youtype.toString());


            Intent intent = new Intent(YouType_Complate_Activity.this, SubPeopleListSelect_Activity.class);


            intent.putExtra("people_uid", people_uid);
            intent.putExtra("people_username", people_username);
            //intent.putExtra("people_mood", people_mood);
            //intent.putExtra("people_type", people_type);

            finish();
            startActivity(intent);
            overridePendingTransition(R.anim.start_enter, R.anim.start_exit);


    }
    public void mytype_re_btn(View v)
    {
        Intent intent = new Intent(YouType_Complate_Activity.this, YouType_Actvity_step1.class);
        intent.putExtra("people_uid", people_uid);
        intent.putExtra("people_username", people_username);
        startActivity(intent);
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
        closeView();
    }

    public void mytype_view_btn(View v)
    {
        //mytype_view_btn
        Intent intent = new Intent(YouType_Complate_Activity.this, YouType_Activity.class);
        intent.putExtra("youtype", youtype);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
    }

    public void start_btn(View v) {
        finish();
    }

    public void closeView()
    {
        super.finish();
    }

    public void prevBtn(View v) {
        finish();
    }
    public void closeBtn(View v) {
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
