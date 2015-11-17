package kr.co.people_gram.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class HaveWriteActivity extends AppCompatActivity {
    ImageView img_logo;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;
    Bitmap profilBit = null;
    Bitmap bitmap;

    private EditText have_edit, et_contents;
    private LinearLayout have_write;

    private ProgressDialog dialog;


    String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_write);

        have_edit = (EditText) findViewById(R.id.have_edit);
        et_contents = (EditText) findViewById(R.id.et_contents);
        have_write = (LinearLayout) findViewById(R.id.have_write);



        /*
        Spanned s = Html.fromHtml("<font color=\"red\">테스트</font>");
        et_contents.setText(s);
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
        Toast.makeText(this, "resultCode : " + resultCode + "::" + GALLERY_PICTURE, Toast.LENGTH_SHORT).show();


        if(requestCode == GALLERY_PICTURE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                Bitmap tmpPhoto;
                try
                {
                    /*
                    if(profilBit != null) {
                        profilBit.recycle();
                    }
                    InputStream stream = getContentResolver().openInputStream(data.getData());
                    profilBit = BitmapFactory.decodeStream(stream);
                    profilBit = tmpPhoto = Bitmap.createScaledBitmap(profilBit, 100, 100, false);
                    */


                    String name =  getImageNameToUri(data.getData());

                    tmpPhoto = BitmapFactory.decodeFile(name);
                    int width = tmpPhoto.getWidth();
                    int height = tmpPhoto.getHeight();
                    Bitmap resizeBitmap = Bitmap.createScaledBitmap(tmpPhoto, width, height, true);
                    Drawable drawable = (Drawable)(new BitmapDrawable(resizeBitmap));


                    ImageView im = new ImageView(this);
                    im.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                    im.setImageDrawable(drawable);

                    have_write.addView(im);

                    //ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
                    //imageView3.setImageDrawable(drawable);

                } catch (Exception e) {

                }
            }
        }
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


    public void haveSave()
    {
        RequestParams params = new RequestParams();
        HttpClient.post("/people/haveWriteProcess", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                dialog = ProgressDialog.show(HaveWriteActivity.this, "", "데이터 수신중");
            }

            public void onFinish() {
                dialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
            }
        });
    }

    public void closeBtn(View v) {
        //overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_close_up_info);
        finish();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
