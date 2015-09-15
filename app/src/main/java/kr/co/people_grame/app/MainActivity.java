package kr.co.people_grame.app;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;

    private Intent intent;

    private final String[] fragments = {
            "kr.co.people.app.Main_Fragment",
            "kr.co.people.app.People_Fragment"
    };

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);

        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);


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
                        SubMainFragment sub_m_fragment = new SubMainFragment();
                        ft.replace(R.id.fragment_main, sub_m_fragment);
                        ft.commit();
                        break;

                    case R.id.navigation_item_people :
                        SubPeopleFragment sub_p_fragment = new SubPeopleFragment();
                        ft.replace(R.id.fragment_main, sub_p_fragment);
                        ft.commit();
                        break;

                    case R.id.navigation_item_mypage:

                        SubMypageFragment sub_my_fragment = new SubMypageFragment();
                        ft.replace(R.id.fragment_main, sub_my_fragment);
                        ft.commit();

                        break;

                    case R.id.navigation_item_setting:
                        SubSettingFragment sub_setting_fragment = new SubSettingFragment();
                        ft.replace(R.id.fragment_main, sub_setting_fragment);
                        ft.commit();
                        break;
                }

                //Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

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
}

