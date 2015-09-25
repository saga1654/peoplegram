package kr.co.people_grame.app;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;

    private Intent intent;
    private TextView txtTitle;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    private Boolean openCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
        SubMainFragment sub_m_fragment = new SubMainFragment();
        ft.replace(R.id.fragment_main, sub_m_fragment);
        ft.commit();
        txtTitle.setText("PEOPLE GRAM");


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                fragmentManager = getSupportFragmentManager();
                ft = fragmentManager.beginTransaction();


                menuItem.setChecked(true);
                dlDrawer.closeDrawers();

                switch (menuItem.getItemId())
                {
                    case R.id.navigation_item_home :

                        SubMyNowFragment sub_m_fragment = new SubMyNowFragment();
                        ft.replace(R.id.fragment_main, sub_m_fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        txtTitle.setText("지금 나는");
                        break;

                    case R.id.navigation_item_people :
                        SubPeopleFragment sub_p_fragment = new SubPeopleFragment();
                        ft.replace(R.id.fragment_main, sub_p_fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        txtTitle.setText("피플");
                        break;

                    case R.id.navigation_item_mypage:

                        SubMypageFragment sub_my_fragment = new SubMypageFragment();
                        ft.replace(R.id.fragment_main, sub_my_fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        txtTitle.setText("마이페이지");

                        break;

                    case R.id.navigation_item_setting:
                        SubSettingFragment sub_setting_fragment = new SubSettingFragment();
                        ft.replace(R.id.fragment_main, sub_setting_fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        txtTitle.setText("설정");
                        break;
                }

                //Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    public void mainmenu_close_btn(View v) {
        dlDrawer.closeDrawer(Gravity.LEFT);
    }

    public void peopletype_btn(View v) {
        dlDrawer.closeDrawer(Gravity.LEFT);
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        SubPeopleTypeFragment sub_m_fragment = new SubPeopleTypeFragment();
        ft.replace(R.id.fragment_main, sub_m_fragment);
        ft.addToBackStack(null);
        ft.commit();
        txtTitle.setText("피플타입");
    }

    public void peoplegram_btn(View v) {
        dlDrawer.closeDrawer(Gravity.LEFT);
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        SubMainFragment sub_m_fragment = new SubMainFragment();
        ft.replace(R.id.fragment_main, sub_m_fragment);
        ft.addToBackStack(null);
        ft.commit();
        txtTitle.setText("PEOPLE GRAM");
    }

    public void feelling_menu_btn(View v) {
        dlDrawer.closeDrawer(Gravity.LEFT);
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        SubFeellingFragment sub_m_fragment = new SubFeellingFragment();
        ft.replace(R.id.fragment_main, sub_m_fragment);
        ft.addToBackStack(null);
        ft.commit();
        txtTitle.setText("피플필링");
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
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btn_people(View v) {
    }

    public void removeCurrentFragment()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment currentFrag =  getSupportFragmentManager().findFragmentById(R.id.fragment_main);

        String fragName = "NONE";

        if (currentFrag!=null)
            fragName = currentFrag.getClass().getSimpleName();

        if (currentFrag != null)
            transaction.remove(currentFrag);

        transaction.commit();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(openCheck == false) {
            /* 종료메세지 출력 및 Fragment BACK */
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
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
                    } else {
                        getSupportFragmentManager().popBackStack();
                        //dtToggle.syncState();
                    }

                    break;

                default:
                    break;

            }
            return super.onKeyDown(keyCode, event);
        } else {
            /* 좌측메뉴 CLOSE */
            dlDrawer.closeDrawer(Gravity.LEFT);
            return false;
        }






        //Log.d("people_gram", String.valueOf(dtToggle.syncState()));

    }

}

