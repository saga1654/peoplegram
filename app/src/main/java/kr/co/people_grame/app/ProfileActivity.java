package kr.co.people_grame.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

public class ProfileActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private String people_type_return = "";
    private ImageView mytype_me, mytype_people;
    private TextView mytype_per, profile_sex, profile_age, profile_area;
    private TextView profile_username, profile_email, profile_point;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        mytype_me = (ImageView) findViewById(R.id.mytype_me);
        mytype_people = (ImageView) findViewById(R.id.mytype_people);

        mytype_per = (TextView) findViewById(R.id.mytype_per);
        profile_sex = (TextView) findViewById(R.id.profile_sex);
        profile_age = (TextView) findViewById(R.id.profile_age);
        profile_area = (TextView) findViewById(R.id.profile_area);
        profile_username = (TextView) findViewById(R.id.profile_username);
        profile_email = (TextView) findViewById(R.id.profile_email);
        profile_point = (TextView) findViewById(R.id.profile_point);

        profile_username.setText(SharedPreferenceUtil.getSharedPreference(this, "username"));
        profile_email.setText(SharedPreferenceUtil.getSharedPreference(this, "email"));
        profile_point.setText(SharedPreferenceUtil.getSharedPreference(this, "point"));

        switch (SharedPreferenceUtil.getSharedPreference(this, "mytype")) {
            case "I":
                mytype_me.setImageResource(R.mipmap.people_type_i);
                break;
            case "D":
                mytype_me.setImageResource(R.mipmap.people_type_d);
                break;
            case "E":
                mytype_me.setImageResource(R.mipmap.people_type_e);
                break;
            case "A":
                mytype_me.setImageResource(R.mipmap.people_type_a);
                break;
            default:
                mytype_me.setImageResource(R.mipmap.people_type_default);
                break;
        }

        mytype_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubMyType_Activity.class);
                intent.putExtra("mytype",SharedPreferenceUtil.getSharedPreference(ProfileActivity.this, "mytype"));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

        mytype_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SubMyType_Activity.class);
                intent.putExtra("mytype", people_type_return);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });



        //mytype

        /*
        profile_username = (TextView) findViewById(R.id.profile_username);
        profileView_username = (TextView) findViewById(R.id.profileView_username);
        profileView_email = (TextView) findViewById(R.id.profileView_email);
        profileView_type = (TextView) findViewById(R.id.profileView_type);
        profileView_point = (TextView) findViewById(R.id.profileView_point);

        profileView_typeImg = (ImageView) findViewById(R.id.profileView_typeImg);


        profile_sex = (TextView) findViewById(R.id.profile_sex);
        profile_age = (TextView) findViewById(R.id.profile_age);
        profile_area = (TextView) findViewById(R.id.profile_area);

        profile_username.setText(SharedPreferenceUtil.getSharedPreference(this, "username"));
        profileView_username.setText(SharedPreferenceUtil.getSharedPreference(this, "username"));
        profileView_email.setText(SharedPreferenceUtil.getSharedPreference(this, "email"));
        profileView_point.setText(SharedPreferenceUtil.getSharedPreference(this, "point") + "그램");

        switch (SharedPreferenceUtil.getSharedPreference(this, "mytype")) {
            case "I":
                profileView_type.setText("우호형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_i);
                break;
            case "D":
                profileView_type.setText("주도형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_d);
                break;
            case "E":
                profileView_type.setText("표현형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_e);
                break;
            case "A":
                profileView_type.setText("분석형");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_a);
                break;
            default:
                profileView_type.setText("자기진단안함");
                profileView_typeImg.setImageResource(R.mipmap.peoplelist_type_default);
                break;
        }
        (/







        /*
        profile_img = (CircularImageView) findViewById(R.id.profile_img);
        String filename = SharedPreferenceUtil.getSharedPreference(ProfileActivity.this, "profile_image");
        profile_img_view(filename);
        */
    }





    public void onStart()
    {
        super.onStart();
        RequestParams params = new RequestParams();
        params.put("uid", SharedPreferenceUtil.getSharedPreference(this, "uid"));
        HttpClient.post("/user/profile_user_select", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(ProfileActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                Log.d("people_gram", response);
                try {
                    JSONObject jobj = new JSONObject(response);
                    JSONObject profile = new JSONObject(jobj.getString("profile"));
                    JSONObject my_data = new JSONObject(jobj.getString("my_data"));
                    JSONObject you_data = new JSONObject(jobj.getString("people_data"));

                    if (profile.getString("SEX").equals("M")) {
                        profile_sex.setText("남성");
                    } else {
                        profile_sex.setText("여성");
                    }

                    profile_age.setText(Utilities.age_return(profile.getString("BIRTHDAY")));
                    profile_area.setText(profile.getString("SIDO") + " " + profile.getString("GUGUN") + " " + profile.getString("DONG"));

                    mytype_people.setImageResource(R.mipmap.people_type_default);

                    float my_speed = Float.parseFloat(my_data.getString("SPEED"));
                    float my_control = Float.parseFloat(my_data.getString("CONTROL"));

                    float people_speed = Float.parseFloat(you_data.getString("sumdata_speed"));
                    float people_control = Float.parseFloat(you_data.getString("sumdata_control"));

                    people_type_return = you_data.getString("peopleType");

                    switch (you_data.getString("peopleType"))
                    {
                        case "I":
                            mytype_people.setImageResource(R.mipmap.people_type_i);
                            break;
                        case "D":
                            mytype_people.setImageResource(R.mipmap.people_type_d);
                            break;
                        case "E":
                            mytype_people.setImageResource(R.mipmap.people_type_e);
                            break;
                        case "A":
                            mytype_people.setImageResource(R.mipmap.people_type_a);
                            break;
                        default:
                            mytype_people.setImageResource(R.mipmap.people_type_default);
                            break;
                    }

                    String per = String.valueOf(Math.ceil(Utilities.people_match_int(my_speed, people_speed, my_control, people_control)));
                    if(you_data.getString("peopleType").equals("")) {
                        mytype_per.setText("?");
                    } else {
                        mytype_per.setText(per+"%");
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
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
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
