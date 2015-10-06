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

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

public class ProfileActivity extends AppCompatActivity {

    private TextView profile_username;

    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;

    private String Filename;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_username = (TextView) findViewById(R.id.profile_username);
        profile_username.setText(SharedPreferenceUtil.getSharedPreference(this, "username"));
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
                Log.d("people_gram", response);
                SharedPreferenceUtil.putSharedPreference(ProfileActivity.this, "profile_image", response);

            }
        });
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

}
