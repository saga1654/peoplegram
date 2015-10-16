package kr.co.people_grame.app;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PeopleSync_Activity extends AppCompatActivity {
    private String userdataArray[];
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_sync_);
    }

    public void all_people_btn(View v) {

        readContacts();

        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(this, "uid"));
        params.put("people_list", userdataArray);
        HttpClient.post("/user/contact_people", params, new AsyncHttpResponseHandler() {
            public void onStart() {

            }

            public void onFinish() {
                Intent intent = new Intent(PeopleSync_Activity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                finish();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
            }
        });

        //Intent intent = new Intent(PeopleSync_Activity.this, )
    }

    public void select_people_btn(View v) {
        Intent intent = new Intent(PeopleSync_Activity.this, PeopleSyncSelect_Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }


    private void readContacts() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String phone = null;
        Log.d("people_gram", "count=" + String.valueOf(cur.getCount()));
        userdataArray = new String[cur.getCount()];
        if (cur.getCount() > 0) {
            int i = 0;
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                    while(pCur.moveToNext()) {
                        name = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        userdataArray[i] = name+":::"+phone;

                        Log.d("people_gram", name+":::"+phone);
                    }
                    pCur.close();
                }
                i++;
            }
            cur.close();
        }
    }
}
