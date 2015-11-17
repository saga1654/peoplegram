package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Calendar;


public class ProfileDetailActivity extends AppCompatActivity {

    private Spinner sex_spinner, year_spinner,  area_spinner;
    ArrayAdapter<CharSequence> adspin1;
    ArrayAdapter<CharSequence> adspin2;
    ArrayList<String> yearArray;

    private ProgressDialog dialog;

    int year = 1960;

    private String select_sex, select_year, select_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        sex_spinner = (Spinner) findViewById(R.id.sex_spinner);
        year_spinner = (Spinner) findViewById(R.id.year_spinner);
        area_spinner = (Spinner) findViewById(R.id.area_spinner);
        sex_spinner.setPrompt("성별?");
        year_spinner.setPrompt("태어난 연도");
        area_spinner.setPrompt("사는 곳");

        adspin1 = ArrayAdapter.createFromResource(this, R.array.profile_sex, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex_spinner.setAdapter(adspin1);
        sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_sex = String.valueOf(adspin1.getItem(position));
                //Toast.makeText(ProfileDetailActivity.this, adspin.getItem(position) + "을 선택 했습니다.", Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        Calendar calendar = Calendar.getInstance();
        int endyear = calendar.get(Calendar.YEAR) - 10;

        yearArray = new ArrayList<String>();
        yearArray.add("태어난 연도");
        for(int i = endyear; i>=year; i--) {
            yearArray.add(String.valueOf(i));
            //Log.d("people_gram", String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, yearArray);
        year_spinner.setAdapter(adapter);
        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_year = String.valueOf(yearArray.get(position));
                //select_year = String.valueOf(adspin.getItem(position));
                //Toast.makeText(ProfileDetailActivity.this, adspin.getItem(position) + "을 선택 했습니다.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        adspin2 = ArrayAdapter.createFromResource(this, R.array.profile_area, android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_spinner.setAdapter(adspin2);
        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_area = String.valueOf(adspin2.getItem(position));
                //Toast.makeText(ProfileDetailActivity.this, adspin.getItem(position) + "을 선택 했습니다.", Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, R.array.profile_sex);

    }


    public void update_btn(View v) {
        if(select_sex.equals("성별은?")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(ProfileDetailActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("성별을 선택해주세요.");
            alert.show();
            return;
            //Toast.makeText(this, "성별을 선택해주세요.", Toast.LENGTH_LONG).show();
        }

        if(select_year.equals("태어난 연도")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(ProfileDetailActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("태어난 연도를 선택해주세요.");
            alert.show();
            return;
        }

        if(select_area.equals("사는곳")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(ProfileDetailActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.setMessage("사는곳을 선택해주세요.");
            alert.show();
            return;
        }

        dataSend();
    }

    public void dataSend()
    {

        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(ProfileDetailActivity.this, "uid"));
        params.put("sex", select_sex);
        params.put("area", select_area);
        params.put("year", select_year);

        HttpClient.post("/user/profile_user_update", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                //Log.d("people_gram", "시작");
                dialog = ProgressDialog.show(ProfileDetailActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                //Log.d("people_gram", "완료");
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response)
            {
                if(response.equals("000")) {
                    finish();
                }
            }
        });
    }

    public void btn_back(View v) {
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }
}
