package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity  {

    public static AppCompatActivity mainActivity;

    final int SubPeopleTypeFragmentCode = 2;
    private TextView mainTitle;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.mipmap.top_01_off,
            R.mipmap.top_02_off,
            R.mipmap.top_03_off,
            R.mipmap.top_04_off
    };

    private LinearLayout settingbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        /*
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        mainTitle = (TextView) findViewById(R.id.mainTitle);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new SubPeopleFragment(), "PEOPLE_LIST");
        adapter.addFrag(new SubPeopleTypeFragment(), "PEOPLE_TYPE");
        adapter.addFrag(new SubMypageFragment(), "MYPAGE");
        adapter.addFrag(new SubTypeFragment(), "TYPE");
        //adapter.addFrag(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("people_gram", "선택="+position);
                switch (position)
                {
                    case 0:
                        mainTitle.setText("피플그램");
                        break;
                    case 1:
                        mainTitle.setText("나를 생각하는 피플타입");
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

        if (resultCode==RESULT_OK) {
            if (requestCode == SubPeopleTypeFragmentCode) {
                String my_type = SharedPreferenceUtil.getSharedPreference(this, "mytype");
                String people_type = data.getStringExtra("people_type");
                String gubun1 = data.getStringExtra("gubun1");
                //Log.d("people_gram", my_type + ":::" + people_type + ":::" + gubun1);

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
            //Log.d("people_gram", "순서="+String.valueOf(position));
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
            //Log.d("people_gram", "순서="+String.valueOf(position));
            //return null;
            return mFragmentTitleList.get(position);
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