package kr.co.people_gram.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    public static AppCompatActivity mainActivity;

    final int SubPeopleListFragmentCode = 1;
    final int SubPeopleTypeFragmentCode = 2;
    private TextView mainTitle;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.mipmap.top_01_off,
            R.mipmap.top_02_off,
            R.mipmap.top_03_off,
            R.mipmap.top_04_off
    };

    private ImageView menu_icon1, menu_icon2, menu_icon3, menu_icon4;
    private LinearLayout menu1, menu2, menu3, menu4, menu_line1, menu_line2, menu_line3, menu_line4;

    private LinearLayout searchbtn, settingbtn;

    private String menuType = "people";

    private DataProfileCount dpc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        dpc = new DataProfileCount();

        //Toast.makeText(MainActivity.this, "진단되었습니다="+dpc.get_user_count(), Toast.LENGTH_LONG).show();
        /*
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        mainTitle = (TextView) findViewById(R.id.mainTitle);





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

        //dpc.set_user_count(10);

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
                Intent intent = new Intent(MainActivity.this, SubPeopleSearch_Activity.class);
                startActivityForResult(intent, SubPeopleListFragmentCode);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });

        settingbtn = (LinearLayout) findViewById(R.id.settingbtn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);
            }
        });



    }

    private void menu1(View v) {
        /*
        menu_icon1.setImageResource(R.mipmap.top_01_on);
        menu_line1.setBackgroundColor(Color.rgb(50,53,77));

        menu_icon2.setImageResource(R.mipmap.top_02_off);
        menu_line2.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon3.setImageResource(R.mipmap.top_03_off);
        menu_line3.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon4.setImageResource(R.mipmap.top_04_off);
        menu_line4.setBackgroundColor(Color.rgb(220,220,221));
        */
    }

    private void menu2(View v) {
        /*
        menu_icon1.setImageResource(R.mipmap.top_01_off);
        menu_line1.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon2.setImageResource(R.mipmap.top_02_on);
        menu_line2.setBackgroundColor(Color.rgb(50,53,77));

        menu_icon3.setImageResource(R.mipmap.top_03_off);
        menu_line3.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon4.setImageResource(R.mipmap.top_04_off);
        menu_line4.setBackgroundColor(Color.rgb(220,220,221));
        */
    }

    private void menu3(View v) {
        /*
        menu_icon1.setImageResource(R.mipmap.top_01_off);
        menu_line1.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon2.setImageResource(R.mipmap.top_02_off);
        menu_line2.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon3.setImageResource(R.mipmap.top_03_on);
        menu_line3.setBackgroundColor(Color.rgb(50,53,77));

        menu_icon4.setImageResource(R.mipmap.top_04_off);
        menu_line4.setBackgroundColor(Color.rgb(220,220,221));
        */
    }

    private void menu4(View v) {
        /*
        menu_icon1.setImageResource(R.mipmap.top_01_off);
        menu_line1.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon2.setImageResource(R.mipmap.top_02_off);
        menu_line2.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon3.setImageResource(R.mipmap.top_03_off);
        menu_line3.setBackgroundColor(Color.rgb(220,220,221));

        menu_icon4.setImageResource(R.mipmap.top_04_on);
        menu_line4.setBackgroundColor(Color.rgb(50,53,77));
        */
    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.top_01_off, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.top_02_off, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.top_03_off, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);


        TextView tabfour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabfour.setText("");
        tabfour.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.top_04_off, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabfour);

    }


    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new SubPeopleFragment(), "PEOPLE_LIST");
        adapter.addFrag(new SubPeopleTypeFragment(), "PEOPLE_TYPE");
        adapter.addFrag(new SubMypageFragment(), "MYPAGE");
        adapter.addFrag(new SubTypeFragment(), "TYPE");
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.notifyDataSetChanged();
                switch (position)
                {
                    case 0:
                        mainTitle.setText("피플그램");
                        break;
                    case 1:
                        mainTitle.setText("피플들이 생각하는 나의타입");


                        break;
                    case 2:
                        mainTitle.setText("마이페이지");
                        break;
                    case 3:
                        mainTitle.setText("유형특징");
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("people_gram", "체크="+requestCode+":::"+resultCode + "::::"+RESULT_OK);


        if (resultCode==RESULT_OK) {
            if(requestCode == 6) {
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

                Intent intent = new Intent(MainActivity.this, SubPeopleTypeContents_Activity.class);
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

        public ViewPagerAdapter(FragmentManager manager) {
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

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.setMessage("종료하시겠습니까?");
                alert.setNegativeButton("취소", null);
                alert.show();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}