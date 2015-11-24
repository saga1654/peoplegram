package kr.co.people_gram.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 광희 on 2015-11-23.
 */
public class ConnReceiver extends BroadcastReceiver {

    private NetworkCheck nc;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        nc = new NetworkCheck();

        AlertDialog.Builder alert = new AlertDialog.Builder(context);




        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo.DetailedState state = info.getDetailedState();
            if (state == NetworkInfo.DetailedState.CONNECTED) {
                //Toast.makeText(context, "연결되었습니다.", Toast.LENGTH_LONG).show();
                Log.d("people_gram", "연결성공");
                nc.set_network(true);
            } else if (state == NetworkInfo.DetailedState.DISCONNECTED) {
                Toast.makeText(context, "핸드폰의 네트워크가 불안정합니다.", Toast.LENGTH_LONG).show();
                //Log.d("people_gram", "연결 끊김");
                //nc.set_network(false);
            } else {
                Log.d("people_gram", "연결오류");
                nc.set_network(false);
            }
        } else {
            Toast.makeText(context, "연결실패.", Toast.LENGTH_LONG).show();
            nc.set_network(false);
        }

        // 네트웍에 변경이 일어났을때 발생하는 부분
        /*
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
            NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            Toast.makeText(context, "Active Network Type : " + activeNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();
            Toast.makeText(context,"Mobile Network Type : " + mobNetInfo.getTypeName() , Toast.LENGTH_SHORT).show();
        }
        */
    }

}
