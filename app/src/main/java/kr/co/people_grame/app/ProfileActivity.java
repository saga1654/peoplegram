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


    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;

    private String Filename;

    private CircularImageView profile_img;
    private AQuery aq;

    private TextView profileView_username, profileView_email, profile_username, profileView_type, profileView_point, profile_sex, profile_age, profile_area;
    private ImageView profileView_typeImg, profile_sex_icon;

    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //mytype

        aq = new AQuery(this);

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







        /*
        profile_img = (CircularImageView) findViewById(R.id.profile_img);
        String filename = SharedPreferenceUtil.getSharedPreference(ProfileActivity.this, "profile_image");
        profile_img_view(filename);
        */
    }

    public void galleryView(View v) {

        pictureActionIntent = new Intent(Intent.ACTION_PICK);

        pictureActionIntent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        //pictureActionIntent = new Intent(
        //        MediaStore.Images.Media.CONTENT_TYPE);
        //startActivityForResult(pictureActionIntent,CAMERA_REQUEST);
        startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_PICTURE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                Bitmap tmpPhoto;
                try
                {
                    Filename =  getImageNameToUri(data.getData());
                    dataSend();


                } catch (Exception e) {

                }
            }
        }
    }

    private void dataSend()
    {
        File myFile = new File(Filename);
        RequestParams params = new RequestParams();
        try {
            params.put("file", myFile);
        } catch(FileNotFoundException e) {
            Log.d("people_gram", "file_error");
        }

        params.put("uid", SharedPreferenceUtil.getSharedPreference(this, "uid"));
        HttpClient.post("/user/profile_update", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(ProfileActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                //Log.d("people_gram", response);
                SharedPreferenceUtil.putSharedPreference(ProfileActivity.this, "profile_image", response);

                profile_img_view(response);
                dialog.dismiss();

            }
        });
    }

    private void profile_img_view(String filename)
    {
        ImageOptions options = new ImageOptions();
        options.ratio = 1;

        options.memCache = true;
        options.fileCache = true;


        String imageUrl = "http://121.162.209.41:81/"+filename;
        aq.id(profile_img).image(imageUrl, options);

    }

    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgPath;
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
                try {
                    JSONObject jobj = new JSONObject(response);
                    if (jobj.getString("CODE").equals("000")) {
                        if (jobj.getString("SEX").equals("M")) {
                            profile_sex.setText("남성");
                        } else {
                            profile_sex.setText("여성");
                        }

                        profile_age.setText(jobj.getString("YEAR"));
                        profile_area.setText(jobj.getString("AREA"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void profile_detail_btn(View v) {
        Intent intent = new Intent(ProfileActivity.this, ProfileDetailActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
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
