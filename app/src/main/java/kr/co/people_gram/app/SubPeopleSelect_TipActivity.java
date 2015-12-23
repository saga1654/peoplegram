package kr.co.people_gram.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SubPeopleSelect_TipActivity extends AppCompatActivity {

    private TextView popup_contents_title;
    private WebView people_content_webview;

    private PeopleData pd;
    private String gubun1 = "";
    private String gubun2 = "";
    private String mytype = "";
    private String peopletype = "";
    private String people_username = "";
    private String my_speed = "";
    private String my_control = "";
    private String people_speed = "";
    private String people_control = "";

    private String viewType;
    private String tipType;
    private LinearLayout shareLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_select_tip_);
        shareLinear = (LinearLayout) findViewById(R.id.shareLinear);

        ImageView btn= (ImageView)findViewById(R.id.sharebtn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                share();
            }
        });
        pd = new PeopleData();
        Intent intent = getIntent();
        if(intent != null) {
            viewType = intent.getStringExtra("viewType");
            tipType = intent.getStringExtra("tipType");
        }

        peopletype = pd.get_people_type();
        gubun1 = pd.get_people_gubun1();
        gubun2 = pd.get_people_gubun2();
        people_username = pd.get_people_username();

        mytype = SharedPreferenceUtil.getSharedPreference(SubPeopleSelect_TipActivity.this, "mytype");


        Log.d("people_gram", "tip_check="+tipType);

        people_content_webview = (WebView) findViewById(R.id.people_content_webview);
        WebSettings webSettings = people_content_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        people_content_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                people_content_webview.loadUrl("javascript:nameView('" + SharedPreferenceUtil.getSharedPreference(SubPeopleSelect_TipActivity.this, "username") + "','"+people_username+"')");
                super.onPageFinished(view, url);
            }
        });
        if(tipType.equals("")){
            people_content_webview.loadUrl("file:///android_asset/tip/DEFAULT/tip1_YOU_" + peopletype + ".html");
        } else {
            if(gubun1.equals("F")) {
                if (tipType.equals("2")) {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + "/tip" + tipType + "_" + gubun1 + "_ME_" + mytype + ".html");
                } else {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + "/tip" + tipType + "_" + gubun1 + "_YOU_" + peopletype + ".html");
                }
            } else if(gubun1.equals("L")) {
                if (tipType.equals("2")) {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + "/tip" + tipType + "_" + gubun1 + "_YOU_" + peopletype + ".html");
                } else {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + "/tip" + tipType + "_" + gubun1 + "_YOU_" + peopletype + ".html");
                }
            } else if(gubun1.equals("S")) {
                people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + "/tip" + tipType + "_" + gubun1 + "_YOU_" + peopletype + ".html");
            } else if(gubun1.equals("P") == true && gubun2.equals("D") == true) {
                if(tipType.equals("2") ) {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 + "_MY_" + mytype + ".html");
                } else {
                    people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 +  "_YOU_" + peopletype + ".html");
                }

            } else {
                people_content_webview.loadUrl("file:///android_asset/tip/" + gubun1 + gubun2 + "/tip" + tipType + "_" + gubun1 + gubun2 + "_YOU_" + peopletype + ".html");
            }
        }


    }
    public void share()
    {

        String folder = "Test_Directory"; // 폴더 이름

        try {
            // 현재 날짜로 파일을 저장하기
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            // 년월일시분초
            Date currentTime_1 = new Date();
            String dateString = formatter.format(currentTime_1);
            File sdCardPath = Environment.getExternalStorageDirectory();
            File dirs = new File(Environment.getExternalStorageDirectory(), folder);

            if (!dirs.exists()) { // 원하는 경로에 폴더가 있는지 확인
                dirs.mkdirs(); // Test 폴더 생성
                Log.d("CAMERA_TEST", "Directory Created");
            }
            shareLinear.buildDrawingCache();
            Bitmap captureView = shareLinear.getDrawingCache();
            FileOutputStream fos;
            String save;

            try {
                save = sdCardPath.getPath() + "/" + folder + "/" + dateString + ".jpeg";
                // 저장 경로
                fos = new FileOutputStream(save);
                captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos); // 캡쳐

                // 미디어 스캐너를 통해 모든 미디어 리스트를 갱신시킨다.
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://" + Environment.getExternalStorageDirectory())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), dateString + ".jpeg 저장",
                    Toast.LENGTH_LONG).show();

            Intent it3=getIntent(); //파일명을 가져오기 위한 인텐트(에디트텍스트에서 이름입력받은 걸 파일명으로 쓰기 위해)

            String str_name=it3.getStringExtra(dateString); //이름을 가져온다.

            File fileRoute = null;

            fileRoute = Environment.getExternalStorageDirectory(); //sdcard 파일경로 선언


            File files = new File(sdCardPath.getPath() + "/" + folder + "/" + dateString + ".jpeg");



            if(files.exists()==true)  //파일유무확인
            {
                Intent intentSend  = new Intent(Intent.ACTION_SEND);
                intentSend.setType("image/jpeg");
                //이름으로 저장된 파일의 경로를 넣어서 공유하기
                //intentSend.putExtra(Intent.EXTRA_STREAM, Uri.parse(sdCardPath.getPath() + "/" + folder + "/" + dateString + ".jpeg"));

                intentSend.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(sdCardPath.getPath() + "/" + folder + "/" + dateString + ".jpeg")));
                Log.d("people_gram", sdCardPath.getPath() + "/" + folder + "/" + dateString + ".jpeg");
                intentSend.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(intentSend, "공유")); //공유하기 창 띄우기
            } else {
                //파일이 없다면 저장을 해달라는 토스트메세지를 띄운다.
                Toast.makeText(getApplicationContext(), "저장을 먼저 해주세요", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Screen", "" + e.toString());
        }
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

    public void closeBtn(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
