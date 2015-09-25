package kr.co.people_grame.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class YouType_Actvity_step1 extends AppCompatActivity {

    private ImageButton menu1_1, menu1_2, menu1_3, menu1_4, menu1_5;
    private ImageButton menu2_1, menu2_2, menu2_3, menu2_4, menu2_5;
    private String data1 = "";
    private String data2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_type__actvity_step1);


        menu1_1 = (ImageButton) findViewById(R.id.menu1_1);
        menu1_2 = (ImageButton) findViewById(R.id.menu1_2);
        menu1_3 = (ImageButton) findViewById(R.id.menu1_3);
        menu1_4 = (ImageButton) findViewById(R.id.menu1_4);
        menu1_5 = (ImageButton) findViewById(R.id.menu1_5);

        menu2_1 = (ImageButton) findViewById(R.id.menu2_1);
        menu2_2 = (ImageButton) findViewById(R.id.menu2_2);
        menu2_3 = (ImageButton) findViewById(R.id.menu2_3);
        menu2_4 = (ImageButton) findViewById(R.id.menu2_4);
        menu2_5 = (ImageButton) findViewById(R.id.menu2_5);


    }

    public void step1_btn(View v)
    {
        switch (v.getId()) {
            case R.id.menu1_1:
                if(data1 != "1") {
                    data1 = "1";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_on);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);

                } else {
                    data1 = "";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                }
                break;
            case R.id.menu1_2:
                if(data1 != "2") {
                    data1 = "2";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_on);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                } else {
                    data1 = "";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                }

                break;
            case R.id.menu1_3:
                if(data1 != "3") {
                    data1 = "3";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_on);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                } else {
                    data1 = "";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                }

                break;
            case R.id.menu1_4:
                if(data1 != "4") {
                    data1 = "4";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_on);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                } else {
                    data1 = "";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                }

                break;
            case R.id.menu1_5:
                if(data1 != "5") {
                    data1 = "5";
                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_on);
                } else {
                    data1 = "";

                    menu1_1.setImageResource(R.drawable.people_type_btn_family_off);
                    menu1_2.setImageResource(R.drawable.people_type_btn_friend_off);
                    menu1_3.setImageResource(R.drawable.people_type_btn_lover_off);
                    menu1_4.setImageResource(R.drawable.people_type_btn_company_off);
                    menu1_5.setImageResource(R.drawable.people_type_btn_sale_off);
                }

                break;
        }
        data2 = "";
        menu2();
    }

    private void menu2()
    {
        switch (data1) {
            case "1":
                menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);

                break;
            case "2":

                menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);

                break;
            case "3":


                menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);


                break;
            case "4":


                menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                break;
            case "5":


                menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);

                break;
            case "":
                break;
        }
    }

    public void step2_btn(View v) {
        switch (v.getId())
        {
            case R.id.menu2_1:
                if(data2 != "1") {
                    data2 = "1";
                    switch (data1) {
                        case "1":
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_on);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_on);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_on);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_on);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_on);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                } else {
                    data2 = "";
                    switch (data1) {
                        case "1":
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                }
                break;
            case R.id.menu2_2:
                if(data2 != "2") {
                    data2 = "2";
                    switch (data1) {
                        case "1":
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_on);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_on);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_on);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_on);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_on);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                } else {
                    data2 = "";
                    switch (data1) {
                        case "1":
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                }
                break;
            case R.id.menu2_3:
                if(data2 != "3") {

                    switch (data1) {
                        case "1":
                            data2 = "3";
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_on);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            data2 = "3";
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_on);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            data2 = "3";
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_on);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            data2 = "3";
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_on);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                } else {
                    data2 = "";
                    switch (data1) {
                        case "1":
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                }
                break;
            case R.id.menu2_4:
                if(data2 != "4") {

                    switch (data1) {
                        case "1":
                            data2 = "4";
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_on);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            data2 = "4";
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_on);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            data2 = "4";
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_on);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            data2 = "";
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            data2 = "";
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                } else {
                    data2 = "";
                    switch (data1) {
                        case "1":
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                }
                break;
            case R.id.menu2_5:
                if(data2 != "5") {

                    switch (data1) {
                        case "1":
                            data2 = "5";
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_on);
                            break;
                        case "2":
                            data2 = "";
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            data2 = "";
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            data2 = "";
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            data2 = "";
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                } else {
                    data2 = "";
                    switch (data1) {
                        case "1":
                            menu2_1.setImageResource(R.drawable.people_type_btn_family_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_family_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_family_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_family_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_family_menu05_off);
                            break;
                        case "2":
                            menu2_1.setImageResource(R.drawable.people_type_btn_friend_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_friend_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_friend_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_friend_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "3":
                            menu2_1.setImageResource(R.drawable.people_type_btn_lover_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_lover_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_lover_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_lover_menu04_off);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "4":
                            menu2_1.setImageResource(R.drawable.people_type_btn_company_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_company_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_company_menu03_off);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                        case "5":
                            menu2_1.setImageResource(R.drawable.people_type_btn_sale_menu01_off);
                            menu2_2.setImageResource(R.drawable.people_type_btn_sale_menu02_off);
                            menu2_3.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_4.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            menu2_5.setImageResource(R.drawable.people_type_btn_menu_backbg);
                            break;
                    }
                }
                break;
        }
    }

    public void btnNext(View v) {
        Intent intent = new Intent(YouType_Actvity_step1.this, YouType_Activity_step2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_you_type__actvity_step1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
