package kr.co.people_gram.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;


public class TestActivity extends BaseActivity {
    private SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("people_gram", "로그인");
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.d("people_gram", "실패");
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    protected void redirectSignupActivity() {
        Log.d("people_gram", "로그인 성공");

        final Intent intent = new Intent(this, KakaoLinkMainActivity.class);
        startActivity(intent);
        finish();

    }


}
