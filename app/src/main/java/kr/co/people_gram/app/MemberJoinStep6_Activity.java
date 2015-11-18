package kr.co.people_gram.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Member;
import java.util.Calendar;



public class MemberJoinStep6_Activity extends AppCompatActivity {

    private EditText et_birthday;

    private LinearLayout nextLL;
    private MemberData md;

    private ImageView cal1_img, cal2_img;
    private String birthday_check = "";
    private String birthday = "";
    private EditText et_year, et_month, et_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_join_step6_);

        md = new MemberData();

        et_year = (EditText) findViewById(R.id.et_year);
        et_year = (EditText) findViewById(R.id.et_year);
        et_year = (EditText) findViewById(R.id.et_year);

        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        //nextLL.setVisibility(View.INVISIBLE);


        cal1_img = (ImageView) findViewById(R.id.cal1_img);
        cal2_img = (ImageView) findViewById(R.id.cal2_img);

        cal1_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal1_img.setImageResource(R.drawable.cal_1_new_on);
                cal2_img.setImageResource(R.drawable.cal_2_new_off);

                birthday_check = "S";

            }
        });

        cal2_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal1_img.setImageResource(R.drawable.cal_1_new_off);
                cal2_img.setImageResource(R.drawable.cal_2_new_on);

                birthday_check = "L";
            }
        });

        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(birthday_check.equals("")) {
                    Toast.makeText(MemberJoinStep6_Activity.this, "양력/음력을 선택해주세요.", Toast.LENGTH_LONG).show();
                } else if(et_year.getText().toString().length() < 4) {
                    Toast.makeText(MemberJoinStep6_Activity.this, "태어난 해를 4자리로 입력해주세요.", Toast.LENGTH_LONG).show();
                } else if(et_month.getText().toString().length() < 2) {
                    Toast.makeText(MemberJoinStep6_Activity.this, "월을 2자리로 입력해주세요.", Toast.LENGTH_LONG).show();
                } else if(et_day.getText().toString().length() < 2) {
                    Toast.makeText(MemberJoinStep6_Activity.this, "일을 2자리로 입력해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    birthday = et_year.getText().toString() + et_month.getText().toString() + et_day.getText().toString();

                    md.set_birthtype(birthday_check);
                    md.set_birthday(birthday);

                    Intent intent = new Intent(MemberJoinStep6_Activity.this, MemberJoinStep7_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                    next_finish();
                }
            }
        });


        //et_birthday = (EditText) findViewById(R.id.et_birthday);
        /*
        nextLL = (LinearLayout) findViewById(R.id.nextLL);
        nextLL.setVisibility(View.INVISIBLE);
        nextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_birthday.getText().toString().equals("") == false) {
                    Intent intent = new Intent(MemberJoinStep6_Activity.this, MemberJoinStep7_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.speed_start_end, R.anim.speed_start_exit);
                    next_finish();
                }
            }
        });


        et_birthday.setInputType(0);
        //DialogDatePicker();
        et_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.showSoftInput(et_birthday, InputMethodManager.SHOW_IMPLICIT);

                DialogDatePicker();
            }
        });
        */

    }

    private void DialogDatePicker() {
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    // onDateSet method
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String month = "";
                        String day = "";
                        if(monthOfYear + 1 < 10) {
                            month = "0" + String.valueOf(monthOfYear + 1);
                        } else {
                            month = String.valueOf(monthOfYear + 1);
                        }

                        if(dayOfMonth < 10) {
                            day = "0" + String.valueOf(dayOfMonth);
                        } else {
                            day = String.valueOf(dayOfMonth);
                        }

                        String birthDay = String.valueOf(year) + month + day;
                        et_birthday.setText(birthDay);
                        nextLL.setVisibility(View.VISIBLE);
                        md.set_birthday(birthDay);

                    }
                };
        DatePickerDialog alert =
                new DatePickerDialog(this, mDateSetListener, cyear, cmonth, cday);
        alert.show();
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


    public void next_finish()
    {
        super.finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }



    public void closeMember(View v)
    {
        finish();
    }


}
