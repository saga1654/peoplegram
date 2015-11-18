package kr.co.people_gram.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;


public class KakaoSignupActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_kakao_signup);

    }

    /**
     * 자동가입앱인 경우는 가입안된 유저가 나오는 것은 에러 상황.
     */
    protected void showSignup() {
        Logger.d("not registered user");
//        redirectLoginActivity();
        String message = "not registered user.\nYou should signup at UserManagememt menu.";
        /*
        Dialog dialog = new DialogBuilder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();


        dialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
        dialog.show();
        */
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {

            @Override
            public void onSuccess(UserProfile userProfile) {
                Logger.d("UserProfile : " + userProfile);
                redirectMainActivity();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
                showSignup();
            }
        });
    }

    private void redirectMainActivity() {
        startActivity(new Intent(this, KakaoServiceListActivity.class));
        finish();
    }





}
