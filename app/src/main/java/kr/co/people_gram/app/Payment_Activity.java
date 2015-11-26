package kr.co.people_gram.app;


import com.android.vending.billing.IInAppBillingService;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.people_gram.app.util.IabHelper;
import kr.co.people_gram.app.util.IabResult;
import kr.co.people_gram.app.util.Inventory;
import kr.co.people_gram.app.util.Purchase;

public class Payment_Activity extends AppCompatActivity {

    IInAppBillingService mService;
    IabHelper mHelper;

    private String point_payment = "";

    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        point_payment = intent.getStringExtra("point");

        bindService(new Intent("com.android.vending.billing.InAppBillingService.BIND"), mServiceConn, Context.BIND_AUTO_CREATE);

        // 구글에서 발급받은 바이너리키를 입력해줍니다
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAySWhOImmkWBYbHSJj3VKl8m4XOG1XaVIJ8h5wvImw1LX/hFOFx0QnUDAGaeJJGTA21+LvZAezy+4FSL60iYP0kNM3r5qwOQUEtryzdr22DI1cpoqS2DDAXV9FEOM2Rp2rT9YqlSWauQRE6QJyb6HDsrqQ6Rof2FEzFStsD7MXQccjDXMJenXeYK1XBaE4ZVkHSxG5Pc8mzDMOyiHYdAHZiIuhmalV1ZsufdhjS6cgXlfQD8xi1mwUUzUeFwd4CTXy8UUF6e02HBMxxA2buX3JxNJIwpa4IeO+cm4TWTE5f1uqwf5sodaayszA8TSfsy4lCILuIQhb3iSnQEAbHD3SwIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.enableDebugLogging(true);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Toast.makeText(Payment_Activity.this, "실패", Toast.LENGTH_LONG).show();
                    // 구매오류처리 ( 토스트하나 띄우고 결제팝업 종료시키면 되겠습니다 )
                }

                // 구매목록을 초기화하는 메서드입니다.
                // v3으로 넘어오면서 구매기록이 모두 남게 되는데 재구매 가능한 상품( 게임에서는 코인같은아이템은 ) 구매후 삭제해주어야 합니다.
                // 이 메서드는 상품 구매전 혹은 후에 반드시 호출해야합니다. ( 재구매가 불가능한 1회성 아이템의경우 호출하면 안됩니다 )
                AlreadyPurchaseItems();

                Buy(point_payment);
            }
        });

    }

    public void AlreadyPurchaseItems() {
        try {
            Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);
            int response = ownedItems.getInt("RESPONSE_CODE");
            if (response == 0) {

                ArrayList purchaseDataList = ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                String[] tokens = new String[purchaseDataList.size()];
                for (int i = 0; i < purchaseDataList.size(); ++i) {
                    String purchaseData = (String) purchaseDataList.get(i);
                    JSONObject jo = new JSONObject(purchaseData);
                    tokens[i] = jo.getString("purchaseToken");
                    // 여기서 tokens를 모두 컨슘 해주기
                    mService.consumePurchase(3, getPackageName(), tokens[i]);
                }
            }
            // 토큰을 모두 컨슘했으니 구매 메서드 처리
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 구매
    public void Buy(String id_item) {
        try {
            Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(),	id_item, "inapp", SharedPreferenceUtil.getSharedPreference(Payment_Activity.this, "uid"));
            PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");

            if (pendingIntent != null) {
                mHelper.launchPurchaseFlow(this, getPackageName(), 1001,  mPurchaseFinishedListener, SharedPreferenceUtil.getSharedPreference(Payment_Activity.this, "uid"));

            } else {
                Toast.makeText(Payment_Activity.this, "결제실패", Toast.LENGTH_LONG).show();
                // 결제가 막혔다면
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener  = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {


            if(result.isFailure()) {
                Toast.makeText(Payment_Activity.this, "결제 실패되었습니다.", Toast.LENGTH_LONG).show();
            } else if(purchase.getSku().equals(point_payment)) {

                if(purchase != null) {
                    String payment_string = point_payment;
                    RequestParams params = new RequestParams();
                    params.put("uid", SharedPreferenceUtil.getSharedPreference(Payment_Activity.this, "uid"));
                    params.put("getOriginalJson", purchase.getOriginalJson());
                    params.put("getSignature", purchase.getSignature());
                    HttpClient.post("/payment/paymentInsert", params, new AsyncHttpResponseHandler() {
                        public void onStart() {

                        }

                        public void onFinish() {

                        }

                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(Payment_Activity.this, response, Toast.LENGTH_LONG).show();
                            SharedPreferenceUtil.putSharedPreference(Payment_Activity.this, "point", response);
                        }
                    });


                    finish();
                }
                //Log.d("people_gram", "구매성공");
            }



            // 여기서 아이템 추가 해주시면 됩니다.
            // 만약 서버로 영수증 체크후에 아이템 추가한다면, 서버로 purchase.getOriginalJson() , purchase.getSignature() 2개 보내시면 됩니다.
        }
    };

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (mServiceConn != null) {
            unbindService(mServiceConn);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mHelper ==null) return; //iabHelper가 null값인경우 리턴

        if(!mHelper.handleActivityResult(requestCode, resultCode, data)){ //iabHelper가 데이터를 핸들링하도록 데이터 전달
            super.onActivityResult(requestCode, resultCode, data);
        }
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

    public void finish()
    {
        Intent intent = getIntent();
        setResult(62, intent);
        super.finish();
    }


}
