package kr.co.people_gram.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

public class KakaoLinkMainActivity extends BaseActivity {

    private KakaoLink kakaoLink;
    private KakaoDialogSpinner text, link, image, button;
    private CheckBox forwardable;
    private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;
    private final String imageSrc = "http://mud-kage.kakao.co.kr/14/dn/btqb9rFG3H5/esjGPSigv4Gv2qokXyTbGK/o.jpg";
    private final String weblink = "http://www.kakao.com/services/8";
    private final String inWeblink = "http://www.kakao.com/services/8";
    private final String msg = "이 테스트 해봤어요?\n" +
            "\n" +
            "나는 누구일까요? \n" +
            "다른 사람은 나를 어떻게 생각할까요?  \n" +
            "좋은 관계를 만들려면 어떻게 해야 할까요? \n" +
            "\n" +
            "나와 다른 사람의 행동 유형이 궁금하다면 지금 진단해 보세요!\n\n(아이폰유저는 1월말 출시예정)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_kakao_link_main);
        try {
            kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }

        try {
            Log.d("people_gram", "성공");
            kakaoTalkLinkMessageBuilder.addText(msg);
            kakaoTalkLinkMessageBuilder.addAppButton("피플그램", new AppActionBuilder()
                    .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder().setExecuteParam("execparamkey2=2222").setMarketParam("referrer=kakaotalklink").build())
                    .addActionInfo(AppActionInfoBuilder.createiOSActionInfoBuilder(AppActionBuilder.DEVICE_TYPE.PHONE).setExecuteParam("execparamkey2=2222").build())
                            //.setUrl("http://www.peoplegram.co.kr")
                    .build());


            //kakaoTalkLinkMessageBuilder.addAppLink("자세히 보기", new AppActionBuilder().setUrl("market://details?id=kr.co.people_gram.app").build()); // PC 카카오톡 에서 사용하게 될 웹사이트 주소
            //kakaoTalkLinkMessageBuilder.addText("피플그램 접속").addAppButton("kakao0009ba5b604d156639ccb9670f4aabef://kakaolink?execparamkey1=1111 ", new AppActionBuilder().setUrl("http://www.peoplegram.co.kr").build()); // PC 카카오톡 에서 사용하게 될 웹사이트 주소
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
            finish();

        } catch (KakaoParameterException e) {
            Log.d("people_gram", "실패");
            alert(e.getMessage());
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }





    private void sendKakaoTalkLink(String textType, String linkType, String imageType, String buttonType) {

        try {
            if (textType.equals(getString(R.string.use_text)))
                kakaoTalkLinkMessageBuilder.addText(getString(R.string.kakaolink_text));

            if (imageType.equals(getString(R.string.use_image)))
                kakaoTalkLinkMessageBuilder.addImage(imageSrc, 300, 200);

            // 앱이 설치되어 있는 경우 kakao<app_key>://kakaolink?execparamkey1=1111 로 이동. 앱이 설치되어 있지 않은 경우 market://details?id=com.kakao.sample.kakaolink&referrer=kakaotalklink 또는 https://itunes.apple.com/app/id12345로 이동
            if (linkType.equals(getString(R.string.use_applink))){
                kakaoTalkLinkMessageBuilder.addAppLink(getString(R.string.kakaolink_applink),
                        new AppActionBuilder()
                                .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder().setExecuteParam("execparamkey1=1111").setMarketParam("referrer=kakaotalklink").build())
                                .addActionInfo(AppActionInfoBuilder.createiOSActionInfoBuilder(AppActionBuilder.DEVICE_TYPE.PHONE).setExecuteParam("execparamkey1=1111").build())
                                //.setUrl("http://www.kakao.com")
                                .build());
            }
            // 웹싸이트에 등록한 "http://www.kakao.com"을 overwrite함. overwrite는 같은 도메인만 가능.
            else if (linkType.equals(getString(R.string.use_weblink))) {
                kakaoTalkLinkMessageBuilder.addWebLink(getString(R.string.kakaolink_weblink), weblink);
            }
            else if (linkType.equals(getString(R.string.use_inweblink))) {
                kakaoTalkLinkMessageBuilder.addInWebLink(getString(R.string.use_inweblink), inWeblink);
            }

            // 웹싸이트에 등록된 kakao<app_key>://kakaolink로 이동
            if (buttonType.equals(getString(R.string.use_appbutton)))
                kakaoTalkLinkMessageBuilder.addAppButton(getString(R.string.kakaolink_appbutton), new AppActionBuilder()
                        .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder().setExecuteParam("execparamkey2=2222").setMarketParam("referrer=kakaotalklink").build())
                        .addActionInfo(AppActionInfoBuilder.createiOSActionInfoBuilder(AppActionBuilder.DEVICE_TYPE.PHONE).setExecuteParam("execparamkey2=2222").build())
                        .setUrl("http://www.kakao.com").build());
                // 웹싸이트에 등록한 "http://www.kakao.com"으로 이동.
            else if (buttonType.equals(getString(R.string.use_webbutton)))
                kakaoTalkLinkMessageBuilder.addWebButton(getString(R.string.kakaolink_webbutton), null);

            kakaoTalkLinkMessageBuilder.setForwardable(forwardable.isChecked());

            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
        } catch (KakaoParameterException e) {
            alert(e.getMessage());
        }
    }

    private void alert(String message) {
        new DialogBuilder(KakaoLinkMainActivity.this)
//        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .create().show();
    }

}
