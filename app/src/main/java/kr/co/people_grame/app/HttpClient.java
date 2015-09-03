package kr.co.people_grame.app;

import com.loopj.android.http.*;

public class HttpClient {
    private static final String BASE_URL = "http://app.marketlink.co.kr/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static AsyncHttpClient getInstance()
    {
        return HttpClient.client;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
    {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
    {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        // TODO Auto-generated method stub
        return BASE_URL + relativeUrl;
    }
}
