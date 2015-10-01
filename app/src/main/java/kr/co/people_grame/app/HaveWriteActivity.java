package kr.co.people_grame.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class HaveWriteActivity extends AppCompatActivity {
    ImageView img_logo;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;
    Bitmap profilBit = null;
    Bitmap bitmap;

    private EditText have_edit;


    String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_write);

        //have_edit = (EditText) findViewById(R.id.have_edit);
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
                    Bitmap resizeBitmap = Bitmap.createScaledBitmap(tmpPhoto, 1000, 1000, true);
                    Drawable drawable = (Drawable)(new BitmapDrawable(resizeBitmap));

                    ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
                    imageView3.setImageDrawable(drawable);





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
