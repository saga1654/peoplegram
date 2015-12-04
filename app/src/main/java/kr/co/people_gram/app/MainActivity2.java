package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    public static AppCompatActivity mainActivity;


    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;

    private Intent intent;
    private TextView txtTitle;

    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction ft;

    private Boolean openCheck = false;



    private ImageView guide_content_1;
    private ImageView menu_icon1, menu_icon2, menu_icon3, menu_icon4;
    private LinearLayout menu1, menu2, menu3, menu4, menu_line1, menu_line2, menu_line3, menu_line4;

    private LinearLayout searchbtn, settingbtn;

    private String menuType = "people";

    private DataProfileCount dpc;

    private PopupWindow mPopupWindow;
    final int SubPeopleListFragmentCode = 1;
    final int SubPeopleTypeFragmentCode = 2;
    private TextView menu_username, menu_point;
    private ImageView menu_mytype;

    private Handler mHandler;
    private Runnable mRunnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setDisplayHomeAsUpEnabled(true);
        }


        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                openCheck = false;
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                openCheck = true;
                super.onDrawerOpened(drawerView);
            }
        };
        dlDrawer.setDrawerListener(dtToggle);

        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        txtTitle.setText("PEOPLE GRAM");
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                menuItem.setChecked(true);
                dlDrawer.closeDrawers();
                mHandler = new Handler();
                mHandler.removeCallbacks(mRunnable);

                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        switch (menuItem.getItemId())
                        {

                            case R.id.navigation_item_point:
                                intent = new Intent(MainActivity2.this, SubGramPoint.class);
                                startActivityForResult(intent, SubPeopleListFragmentCode);
                                overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
                                break;

                            case R.id.navigation_item_type:
                                intent = new Intent(MainActivity2.this, SubTypeFragment.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                                break;

                            case R.id.navigation_item_mypage:
                                intent = new Intent(MainActivity2.this, ProfileActivity.class);
                                startActivityForResult(intent, SubPeopleListFragmentCode);
                                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                                break;

                            case R.id.navigation_item_setting:
                                intent = new Intent(MainActivity2.this, SettingActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
                                break;
                        }
                    }
                };
                mHandler = new Handler();
                mHandler.postDelayed(mRunnable, 200);






                return false;
            }
        });




        mainActivity = this;
        dpc = new DataProfileCount();


        menu_icon1 = (ImageView) findViewById(R.id.menu_icon1);
        menu_icon2 = (ImageView) findViewById(R.id.menu_icon2);
        menu_icon3 = (ImageView) findViewById(R.id.menu_icon3);
        menu_icon4 = (ImageView) findViewById(R.id.menu_icon4);


        menu_line1 = (LinearLayout) findViewById(R.id.menu_line1);
        menu_line2 = (LinearLayout) findViewById(R.id.menu_line2);
        menu_line3 = (LinearLayout) findViewById(R.id.menu_line3);
        menu_line4 = (LinearLayout) findViewById(R.id.menu_line4);

        menu1 = (LinearLayout) findViewById(R.id.menu1);
        menu2 = (LinearLayout) findViewById(R.id.menu2);
        menu3 = (LinearLayout) findViewById(R.id.menu3);
        menu4 = (LinearLayout) findViewById(R.id.menu4);

        menu_username = (TextView) findViewById(R.id.menu_username);
        menu_username.setText(SharedPreferenceUtil.getSharedPreference(MainActivity2.this, "username"));

        menu_point = (TextView) findViewById(R.id.menu_point);
        menu_point.setText(Utilities.comma(Integer.parseInt(SharedPreferenceUtil.getSharedPreference(MainActivity2.this, "point"))) + "p");

        menu_mytype = (ImageView) findViewById(R.id.menu_mytype);

        switch (SharedPreferenceUtil.getSharedPreference(MainActivity2.this, "mytype")) {
            case "A":
                menu_mytype.setImageResource(R.mipmap.people_type_a);
                break;

            case "I":
                menu_mytype.setImageResource(R.mipmap.people_type_i);
                break;

            case "D":
                menu_mytype.setImageResource(R.mipmap.people_type_d);
                break;

            case "E":
                menu_mytype.setImageResource(R.mipmap.people_type_e);
                break;

            default:
                menu_mytype.setImageResource(R.mipmap.people_type_default);
                break;
        }

        if(dpc.get_user_count() > 0) {
            menu_icon1.setImageResource(R.mipmap.top_01_on_new);
        }

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuType.equals("people") == false) {
                    if(dpc.get_user_count() > 0) {
                        menu_icon1.setImageResource(R.mipmap.top_01_on_new);
                    } else {
                        menu_icon1.setImageResource(R.mipmap.top_01_on);
                    }
                    menu_line1.setBackgroundColor(Color.rgb(50, 53, 77));

                    menu_icon2.setImageResource(R.mipmap.top_02_off);
                    menu_line2.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon3.setImageResource(R.mipmap.top_03_off);
                    menu_line3.setBackgroundColor(Color.rgb(220,220,221));

                    menu_icon4.setImageResource(R.mipmap.top_04_off);
                    menu_line4.setBackgroundColor(Color.rgb(220,220,221));

                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();

                    SubPeopleFragment subpeople_fragment = new SubPeopleFragment();
                    ft.replace(R.id.viewpager, subpeople_fragment);
                    ft.commit();

                    menuType = "people";
                }

            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(menuType.equals("mytype") == false) {

                    //if(SharedPreferenceUtil.getSharedPreference(MainActivity.this, "step2").equals("Y") == false) {
                    /*
                        Intent intent = new Intent(MainActivity.this, GuideActivityStep1.class);
                        intent.putExtra("step", "2");
                        SharedPreferenceUtil.putSharedPreference(MainActivity.this, "step2", "Y");
                        startActivity(intent);
                    */
                    //}





                    dpc.set_user_count(0);

                    menu_icon1.setImageResource(R.mipmap.top_01_off);

                    menu_line1.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon2.setImageResource(R.mipmap.top_02_on);
                    menu_line2.setBackgroundColor(Color.rgb(50, 53, 77));

                    menu_icon3.setImageResource(R.mipmap.top_03_off);
                    menu_line3.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon4.setImageResource(R.mipmap.top_04_off);
                    menu_line4.setBackgroundColor(Color.rgb(220, 220, 221));

                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();

                    SubPeopleTypeFragment subpeopletype_fragment = new SubPeopleTypeFragment();
                    ft.replace(R.id.viewpager, subpeopletype_fragment);
                    ft.commit();

                    menuType = "mytype";
                }
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuType.equals("mypage") == false) {
                    dpc.set_user_count(0);

                    if(SharedPreferenceUtil.getSharedPreference(MainActivity2.this, "step3").equals("Y") == false) {
                        View popupView = getLayoutInflater().inflate(R.layout.activity_guide_activity_step1, null);
                        mPopupWindow = new PopupWindow(popupView,
                                RelativeLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                        guide_content_1 = (ImageView) popupView.findViewById(R.id.guide_img);
                        guide_content_1.setImageResource(R.drawable.guide_content_3);
                        SharedPreferenceUtil.putSharedPreference(MainActivity2.this, "step3", "Y");
                    }


                    menu_icon1.setImageResource(R.mipmap.top_01_off);
                    menu_line1.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon2.setImageResource(R.mipmap.top_02_off);
                    menu_line2.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon3.setImageResource(R.mipmap.top_03_on);
                    menu_line3.setBackgroundColor(Color.rgb(50, 53, 77));

                    menu_icon4.setImageResource(R.mipmap.top_04_off);
                    menu_line4.setBackgroundColor(Color.rgb(220, 220, 221));

                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();

                    SubMypageFragment submypage_fragment = new SubMypageFragment();
                    ft.replace(R.id.viewpager, submypage_fragment);
                    ft.commit();
                    menuType = "mypage";
                }
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(menuType.equals("group") == false) {

                    if(SharedPreferenceUtil.getSharedPreference(MainActivity2.this, "step4").equals("Y") == false) {
                        View popupView = getLayoutInflater().inflate(R.layout.activity_guide_activity_step1, null);
                        mPopupWindow = new PopupWindow(popupView,
                                RelativeLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                        guide_content_1 = (ImageView) popupView.findViewById(R.id.guide_img);
                        guide_content_1.setImageResource(R.drawable.guide_content_4);
                        SharedPreferenceUtil.putSharedPreference(MainActivity2.this, "step4", "Y");
                    }


                    dpc.set_user_count(0);


                    menu_icon1.setImageResource(R.mipmap.top_01_off);
                    menu_line1.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon2.setImageResource(R.mipmap.top_02_off);
                    menu_line2.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon3.setImageResource(R.mipmap.top_03_off);
                    menu_line3.setBackgroundColor(Color.rgb(220, 220, 221));

                    menu_icon4.setImageResource(R.mipmap.top_04_on);
                    menu_line4.setBackgroundColor(Color.rgb(50, 53, 77));


                    fragmentManager = getSupportFragmentManager();
                    ft = fragmentManager.beginTransaction();

                    SubGroupFragment subgroup_fragment = new SubGroupFragment();
                    ft.replace(R.id.viewpager, subgroup_fragment);
                    ft.commit();
                    menuType = "group";
                }
            }
        });





        //viewPager = (ViewPager) findViewById(R.id.viewpager);




        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        SubPeopleFragment subpeople_fragment = new SubPeopleFragment();
        ft.replace(R.id.viewpager, subpeople_fragment);
        ft.commit();




        /*
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(1);
        //viewPager.invalidate();


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        settingbtn = (LinearLayout) findViewById(R.id.settingbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });
        */

        searchbtn = (LinearLayout) findViewById(R.id.searchbtn);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, SubPeopleSearch_Activity.class);
                startActivityForResult(intent, SubPeopleListFragmentCode);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

        settingbtn = (LinearLayout) findViewById(R.id.settingbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, SettingActivity.class);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

    }

    public void mainmenu_close_btn(View v) {
        dlDrawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                dlDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        menu_point.setText(Utilities.comma(Integer.parseInt(SharedPreferenceUtil.getSharedPreference(MainActivity2.this, "point"))) + "p");
        menu_mytype = (ImageView) findViewById(R.id.menu_mytype);

        switch (SharedPreferenceUtil.getSharedPreference(MainActivity2.this, "mytype")) {
            case "A":
                menu_mytype.setImageResource(R.mipmap.people_type_a);
                break;

            case "I":
                menu_mytype.setImageResource(R.mipmap.people_type_i);
                break;

            case "D":
                menu_mytype.setImageResource(R.mipmap.people_type_d);
                break;

            case "E":
                menu_mytype.setImageResource(R.mipmap.people_type_e);
                break;

            default:
                menu_mytype.setImageResource(R.mipmap.people_type_default);
                break;
        }


        if (resultCode==RESULT_OK) {
            if(requestCode == SubPeopleListFragmentCode) {
                fragmentManager = getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();

                SubPeopleFragment subpeople_fragment = new SubPeopleFragment();
                ft.replace(R.id.viewpager, subpeople_fragment);
                ft.commit();
            }
            if (requestCode == SubPeopleTypeFragmentCode) {
                String my_type = SharedPreferenceUtil.getSharedPreference(this, "mytype");
                String people_type = data.getStringExtra("people_type");
                String gubun1 = data.getStringExtra("gubun1");

                Intent intent = new Intent(MainActivity2.this, SubPeopleTypeContents_Activity.class);
                intent.putExtra("mytype", my_type);
                intent.putExtra("people_type", people_type);
                intent.putExtra("gubun1", gubun1);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);


                switch (gubun1)
                {
                    case "P":
                        SubPeopleTypeFragment.P_check = true;
                        break;
                    case "F":
                        SubPeopleTypeFragment.F_check = true;
                        break;
                    case "L":
                        SubPeopleTypeFragment.L_check = true;
                        break;
                    case "C":
                        SubPeopleTypeFragment.C_check = true;
                        break;
                    case "S":
                        SubPeopleTypeFragment.S_check = true;
                        break;
                }
            }
        }


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
            super(manager);
        }



        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(openCheck == false) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    new AlertDialog.Builder(this)
                            .setTitle("프로그램 종료")
                            .setMessage("프로그램을 종료 하시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());

                                    //android.os.Process.killProcess(android.os.Process.myPid());
                                }
                            })
                            .setNegativeButton("아니오", null)
                            .show();

                    break;

                default:
                    break;
            }
        } else {
            /* 좌측메뉴 CLOSE */
            dlDrawer.closeDrawer(Gravity.LEFT);
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void step1_close_btn(View v) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

}
