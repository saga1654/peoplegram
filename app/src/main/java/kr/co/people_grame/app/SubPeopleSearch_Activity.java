package kr.co.people_grame.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SubPeopleSearch_Activity extends AppCompatActivity {

    private EditText serachET;
    private ArrayList<SubPeopleListDTO> people_dto_list;
    private ArrayList<SubPeopleListDTO_Temp> people_dto_list_temp;
    private SubPeopleListAdapter people_adapter_list;
    private String searchTEXT = "";
    private ListView sf_people_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_people_search_);


        people_dto_list = new ArrayList<SubPeopleListDTO>();
        people_dto_list_temp = SubPeopleFragment.people_dto_list_temp;

        sf_people_list = (ListView) findViewById(R.id.sf_people_list);
        sf_people_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                finish();

                    SubPeopleListDTO dto = (SubPeopleListDTO) sf_people_list.getItemAtPosition(position);
                    Intent intent = new Intent(SubPeopleSearch_Activity.this, SubPeopleListPopup_Activity.class);

                    intent.putExtra("people_uid", dto.get_profile_uid());
                    intent.putExtra("people_email", dto.get_profile_email());
                    intent.putExtra("people_username", dto.get_profile_username());
                    intent.putExtra("people_mood", dto.get_profile_mood());
                    intent.putExtra("people_type", dto.get_profile_type());
                    intent.putExtra("people_gubun1", dto.get_profile_gubun1());
                    intent.putExtra("people_gubun2", dto.get_profile_gubun2());
                    intent.putExtra("people_speed", dto.get_profile_speed());
                    intent.putExtra("people_control", dto.get_profile_control());
                    intent.putExtra("people_result_count", dto.get_profile_cnt());
                    intent.putExtra("people_friend_count", dto.get_profile_friend_cnt());

                    startActivityForResult(intent, 000001);
                    overridePendingTransition(R.anim.slide_up_info, R.anim.slide_down_info);


            }
        });


        serachET = (EditText) findViewById(R.id.serachET);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                people_dto_list.clear();
                for(int i = 0; i<people_dto_list_temp.size(); i++) {
                    searchTEXT = String.valueOf(serachET.getText());
                    Log.d("people_gram", searchTEXT);

                    SubPeopleListDTO_Temp dto = people_dto_list_temp.get(i);
                    if(dto.get_profile_username().contains(searchTEXT)) {
                        people_dto_list.add(new SubPeopleListDTO(
                                dto.get_profile_uid()
                                , ""
                                , dto.get_profile_username()
                                , dto.get_profile_email()
                                , dto.get_profile_type()
                                , ""
                                , dto.get_profile_gubun1()
                                , dto.get_profile_gubun2()
                                , dto.get_profile_speed()
                                , dto.get_profile_control()
                                , dto.get_profile_cnt()
                                , dto.get_profile_friend_cnt()
                                , dto.get_profile_new_cnt()
                        ));
                    } else {
                        //Log.d("people_gram", "없음");
                    }

                }

                people_adapter_list = new SubPeopleListAdapter(SubPeopleSearch_Activity.this, R.layout.sub_people_rowsearch_list, people_dto_list);
                sf_people_list.setAdapter(people_adapter_list);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        serachET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(serachET.getWindowToken(), 0);
                        //finish();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });

        serachET.addTextChangedListener(watcher);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_close_down_info, R.anim.slide_clode_up_info);
    }

}
