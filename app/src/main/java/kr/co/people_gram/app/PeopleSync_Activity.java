package kr.co.people_gram.app;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;


public class PeopleSync_Activity extends AppCompatActivity {
    private String userdataArray[];
    private String userString = "";
    private ProgressBar dialog;
    private TextView progressBarTxt;
    private ProgressDialog dialog2;
    private int totalCnt = 0;
    private int nowCnt = 0;

    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_sync_);

        try {
            Intent intent = getIntent();
            if (intent != null) {
                if (intent.getStringExtra("mypage").equals("ok")) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch(Exception e) {
            Log.d("people_gram", "오류");
            check = false;
        }

        dialog2 = ProgressDialog.show(PeopleSync_Activity.this, "", "데이터 수신중");

        dialog = (ProgressBar) findViewById(R.id.progressBar);
        progressBarTxt = (TextView) findViewById(R.id.progressBarTxt);
        dialog.setVisibility(View.INVISIBLE);
        progressBarTxt.setVisibility(View.INVISIBLE);

        readContacts();
    }

    /*
    public void all_people_btn(View v) {
        readContacts();
    }
    */

    public void select_people_btn(View v) {
        Intent intent = new Intent(PeopleSync_Activity.this, PeopleSyncSelect_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    public final Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {

            dialog.setVisibility(View.VISIBLE);
            progressBarTxt.setVisibility(View.VISIBLE);
            dialog.setMax(totalCnt);
            dialog.incrementProgressBy(1);
            if(nowCnt == totalCnt) {
                progressBarTxt.setText("데이터 등록중입니다.");
            } else {
                progressBarTxt.setText(nowCnt + "/" + totalCnt);
            }
            //nowCnt
            //dialog.setProgress(Integer.parseInt(String.valueOf(msg)));
        };
    };

    boolean isRunning = false;


    private void readContacts() {

        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                //dialog.setVisibility(View.VISIBLE);
                //dialog.setMax(cur.getCount());
                try {




                    ContentResolver cr = getContentResolver();
                    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                    userdataArray = new String[cur.getCount()];
                    String phone = null;
                    //Log.d("people_gram", "count=" + String.valueOf(cur.getCount()));

                    totalCnt = cur.getCount();

                    if (cur.getCount() > 0) {
                        int i = 0;
                        int total = 1;
                        while (cur.moveToNext()) {

                            //Log.d("people_gram", total + ":::" + totalCnt);
                            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                                Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                                while (pCur.moveToNext()) {
                                    name = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                                    phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    //userdataArray[i] = name + ":::" + phone;

                                    if(userString.equals("")) {
                                        userString = name+":::"+phone;
                                    } else {
                                        userString += "///" + name+":::"+phone;
                                    }

                                    //Log.d("people_gram", name+":::"+phone);

                                    nowCnt = total;
                                }
                                pCur.close();
                            }

                            handler.sendEmptyMessage(total);
                            if(total == totalCnt) {

                                SystemClock.sleep(1000L);

                                Log.d("people_gram", "종료");

                                RequestParams params = new RequestParams();
                                params.put("uid", SharedPreferenceUtil.getSharedPreference(PeopleSync_Activity.this, "uid"));

                                params.put("people_list", userString);
                                HttpClient.post("/user/contact_people", params, new AsyncHttpResponseHandler() {
                                    public void onStart() {

                                    }

                                    public void onFinish() {
                                        dialog2.dismiss();
                                    }

                                    @Override
                                    public void onSuccess(String response) {
                                        if(response.equals("000")) {
                                            Log.d("people_gram", "동기화="+check);
                                            if(check == false) {
                                                Intent intent = new Intent(PeopleSync_Activity.this, MainActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                                                finish();
                                            } else {
                                                Intent intent = new Intent();
                                                setResult(RESULT_OK, intent);
                                                overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
                                                finish();
                                            }
                                        }
                                        /*
                                        Log.d("people_gram", response);
                                        */
                                    }
                                });

                            }


                            i++;
                            total++;
                        }
                        cur.close();
                    }
                } catch (Throwable e) {
                    Log.d("people_gram", "오류");
                }
            }
        });

        isRunning = true;
        background.start();
    }

    public void onStop()
    {
        super.onStop();
        isRunning = false;
    }
}
