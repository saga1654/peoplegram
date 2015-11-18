package kr.co.people_gram.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import java.text.DecimalFormat;
import java.util.Calendar;


/**
 * Created by 광희 on 2015-09-09.
 */
public class Utilities {

    private static String TAG = "people_gram";

    public static int getPixelToDp(Context context, int pixel) {
        float dp = 0;
        try {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            dp = pixel / (metrics.densityDpi / 160f);
        } catch (Exception e) {

        }
        return (int) dp;
    }

    public static int getDpToPixel(Context context, int DP) {
        float px = 0;
        try {
            px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP, context.getResources().getDisplayMetrics());
        } catch (Exception e) {

        }
        return (int) px;
    }

    public static int getDpToPixel(Context context, float DP) {
        float px = 0;
        try {
            px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP, context.getResources().getDisplayMetrics());
        } catch (Exception e) {

        }
        return (int) px;
    }

    /**
     * 단말기 density 구함
     * @param con
     * 사용법 : if(getDensity(context) == 2f && (float으로 형변환해서 사용 해야함.)
     */
    public static float getDensity(Context con) {
        float density = 0.0f;
        density  = con.getResources().getDisplayMetrics().density;
        Log.d(TAG, "density = " + density);
        return density;
    }

    /**
     * px을 dp로 변환
     * @param con
     * @param px
     * @return dp
     */
    public static int getPxToDp(Context con, int px) {
        float density = 0.0f;
        density  = con.getResources().getDisplayMetrics().density;
        Log.d(TAG, "density = " + density);
        return (int)(px / density);
    }

    /**
     * dp를 px로 변환
     * @param con
     * @param dp
     * @return px
     */
    public static int getDpToPix(Context con, double dp) {
        float density = 0.0f;
        density  = con.getResources().getDisplayMetrics().density;
        Log.d(TAG, "density = " + density);
        return (int)(dp * density + 0.5);
    }

    /**
     * 단말기 가로 해상도 구하기
     * @param activity
     * @return width
     */
    public static int getScreenWidth(Activity activity) {
        int width = 0;
        width = activity.getWindowManager().getDefaultDisplay().getWidth();
        Log.i(TAG, "Screen width = " + width);
        return width;
    }

    /**
     * 단말기 세로 해상도 구하기
     * @param activity
     * @return hight
     */
    public static int getScreenHeight(Activity activity) {
        int height = 0;
        height = activity.getWindowManager().getDefaultDisplay().getHeight();
        Log.i(TAG, "Screen height = " + height);
        return height;
    }

    /**
     * 단말기 가로 해상도 구하기
     * @param context
     */
    public static int getScreenWidth(Context context) {
        Display dis = ((WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = dis.getWidth();
        Log.i(TAG, "Screen Width = " + width);
        return width;
    }

    /**
     * 단말기 세로 해상도 구하기
     * @param context
     */
    public static int getScreenHeight(Context context) {
        Display dis = ((WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int height = dis.getHeight();
        Log.i(TAG, "Screen height = " + height);
        return height;
    }


    /**
     * 현재 네트워크 상태를 확인할 수 있는 메소드
     * @return 네트워크 상태
     *              1 : wifi
     *              2 : mibile network
     *              3 : 네트워크 사용불가
     */

    public static int getNetworkType(Context context) {
        int NETWORK_WIFI = 1;
        int NETWORK_MOBILE = 2;
        int NETWORK_NOT_AVAILABLE = 3;

        int NETWORK_CHECK = 0;

        try {
            ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();
            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
                NETWORK_CHECK = NETWORK_WIFI;

            NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState();
            if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                NETWORK_CHECK = NETWORK_MOBILE;
        } catch (NullPointerException e) {
            NETWORK_CHECK = NETWORK_NOT_AVAILABLE;
        }
        return NETWORK_CHECK;
    }

    public static String comma(int num)
    {
        DecimalFormat df = new DecimalFormat("#,##0");
        String won = String.valueOf(df.format(num));

        return won;
    }

    public static String age_return(String birthday)
    {
        Calendar cal= Calendar.getInstance();
        int year = Integer.parseInt(birthday.substring(0, 4));
        int month = Integer.parseInt(birthday.substring(4, 6));
        int day = Integer.parseInt(birthday.substring(6, 8));

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, day);


        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH)) || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return String.valueOf(age) + "세";
    }

    public static double people_match_int(float my_speed, float you_speed, float my_control, float you_control) {
        int type_score = 100;

        if(my_speed * you_speed * my_control * you_control < 0) {
            type_score = 90;
        } else {
            if(my_speed * you_speed < 0 && my_control * you_control < 0) {
                type_score = 80;
            } else {
                type_score = 100;
            }
        }



        double score_temp = Math.pow((my_speed - you_speed),2) + Math.pow((my_control - you_control),2);
        double score = 4 * Math.sqrt(2) * Math.sqrt(score_temp);


        double total = type_score - score;
        return total;
    }

    private double people_match_temp(float my_speed, float you_speed, float my_control, float you_control)
    {
        double temp = Math.pow((my_speed - you_speed),2) + Math.pow((my_control - you_control),2);

        return temp;
    }

    private int people_match_typescore(float my_speed, float you_speed, float my_control, float you_control)
    {
        int type_score = 100;

        if(my_speed * you_speed * my_control * you_control < 0) {
            type_score = 90;
        } else {
            if(my_speed * you_speed < 0 && my_control * you_control < 0) {
                type_score = 80;
            } else {
                type_score = 100;
            }
        }

        return type_score;
    }

    public static int getBitmapOfWidth(String fileName) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, options);
            return options.outWidth;
        } catch(Exception e) {
            return 0;
        }
    }

    public static int getBitmapOfHeight(String fileName) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, options);

            return options.outHeight;
        } catch(Exception e) {
            return 0;
        }
    }

    public static String peopleContectView(Context context, String gubun, double total_num)
    {
        String text = "";
        switch (gubun) {
            case "P":
                if(total_num <= 10) {
                    text = context.getString(R.string.p_1).toString();
                } else if(total_num > 10 && total_num <= 20) {
                    text = context.getString(R.string.p_2).toString();
                } else if(total_num > 20 && total_num <= 40) {
                    text = context.getString(R.string.p_3).toString();
                } else if(total_num > 40 && total_num <= 60) {
                    text = context.getString(R.string.p_4).toString();
                } else if(total_num > 60 && total_num <= 75) {
                    text = context.getString(R.string.p_5).toString();
                } else if(total_num > 75 && total_num <= 89) {
                    text = context.getString(R.string.p_6).toString();
                } else {
                    text = context.getString(R.string.p_7).toString();
                }
                break;
            case "F":
                if(total_num <= 10) {
                    text = context.getString(R.string.f_1).toString();
                } else if(total_num > 10 && total_num <= 20) {
                    text = context.getString(R.string.f_2).toString();
                } else if(total_num > 20 && total_num <= 40) {
                    text = context.getString(R.string.f_3).toString();
                } else if(total_num > 40 && total_num <= 60) {
                    text = context.getString(R.string.f_4).toString();
                } else if(total_num > 60 && total_num <= 75) {
                    text = context.getString(R.string.f_5).toString();
                } else if(total_num > 75 && total_num <= 89) {
                    text = context.getString(R.string.f_6).toString();
                } else {
                    text = context.getString(R.string.f_7).toString();
                }
                break;
            case "L":
                if(total_num <= 10) {
                    text = context.getString(R.string.l_1).toString();
                } else if(total_num > 10 && total_num <= 20) {
                    text = context.getString(R.string.l_2).toString();
                } else if(total_num > 20 && total_num <= 40) {
                    text = context.getString(R.string.l_3).toString();
                } else if(total_num > 40 && total_num <= 60) {
                    text = context.getString(R.string.l_4).toString();
                } else if(total_num > 60 && total_num <= 75) {
                    text = context.getString(R.string.l_5).toString();
                } else if(total_num > 75 && total_num <= 89) {
                    text = context.getString(R.string.l_6).toString();
                } else {
                    text = context.getString(R.string.l_7).toString();
                }
                break;
            case "C":
                if(total_num <= 10) {
                    text = context.getString(R.string.c_1).toString();
                } else if(total_num > 10 && total_num <= 20) {
                    text = context.getString(R.string.c_2).toString();
                } else if(total_num > 20 && total_num <= 40) {
                    text = context.getString(R.string.c_3).toString();
                } else if(total_num > 40 && total_num <= 60) {
                    text = context.getString(R.string.c_4).toString();
                } else if(total_num > 60 && total_num <= 75) {
                    text = context.getString(R.string.c_5).toString();
                } else if(total_num > 75 && total_num <= 89) {
                    text = context.getString(R.string.c_6).toString();
                } else {
                    text = context.getString(R.string.c_7).toString();
                }
                break;
            case "S":
                if(total_num <= 10) {
                    text = context.getString(R.string.s_1).toString();
                } else if(total_num > 10 && total_num <= 20) {
                    text = context.getString(R.string.s_2).toString();
                } else if(total_num > 20 && total_num <= 40) {
                    text = context.getString(R.string.s_3).toString();
                } else if(total_num > 40 && total_num <= 60) {
                    text = context.getString(R.string.s_4).toString();
                } else if(total_num > 60 && total_num <= 75) {
                    text = context.getString(R.string.s_5).toString();
                } else if(total_num > 75 && total_num <= 89) {
                    text = context.getString(R.string.s_6).toString();
                } else {
                    text = context.getString(R.string.s_7).toString();
                }
                break;
        }

        return text;
    }

}