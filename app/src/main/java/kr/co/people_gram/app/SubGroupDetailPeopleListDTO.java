package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubGroupDetailPeopleListDTO {
    String group_code = "";
    String profile_uid = "";
    String profile_img = "";
    String profile_username = "";
    String profile_email = "";
    String profile_mytype = "";
    String profile_type = "";
    String profile_mood = "";
    String profile_gubun1 = "";
    String profile_gubun2 = "";
    int profile_speed = 0;
    int profile_control = 0;
    int profile_cnt = 0;
    int profile_friend_cnt = 0;
    int profile_new_cnt = 0;

    public SubGroupDetailPeopleListDTO() {

    }

    public void set_profile_uid(String profile_uid) { this.profile_uid = profile_uid; }

    public SubGroupDetailPeopleListDTO(String group_code, String profile_uid, String profile_img, String profile_username, String profile_email, String profile_mytype,  String profile_type, String profile_mood, String profile_gubun1, String profile_gubun2, int profile_speed, int profile_control, int profile_cnt, int profile_friend_cnt, int profile_new_cnt)
    {
        this.group_code = group_code;
        this.profile_uid = profile_uid;
        this.profile_img = profile_img;
        this.profile_username = profile_username;
        this.profile_email = profile_email;
        this.profile_type = profile_type;
        this.profile_mood = profile_mood;
        this.profile_gubun1 = profile_gubun1;
        this.profile_gubun2 = profile_gubun2;

        this.profile_speed = profile_speed;
        this.profile_control = profile_control;
        this.profile_cnt = profile_cnt;
        this.profile_friend_cnt = profile_friend_cnt;

        this.profile_new_cnt = profile_new_cnt;

        this.profile_mytype = profile_mytype;

    }
    public String get_profile_uid() { return this.profile_uid; }

    public void set_profile_img(String profile_img) { this.profile_uid = profile_img; }
    public String get_profile_img() { return this.profile_img; }

    public void set_profile_username(String profile_username) { this.profile_username = profile_username; }
    public String get_profile_username() { return this.profile_username; }

    public void set_profile_email(String profile_email) { this.profile_username = profile_email; }
    public String get_profile_email() { return this.profile_email; }

    public void set_profile_type(String profile_type) { this.profile_type = profile_type; }
    public String get_profile_type() { return this.profile_type; }

    public void set_profile_mood(String profile_mood) { this.profile_username = profile_mood; }
    public String get_profile_mood() { return this.profile_mood; }

    public void set_profile_gubun1(String profile_gubun1) { this.profile_gubun1 = profile_gubun1; }
    public String get_profile_gubun1() { return this.profile_gubun1; }

    public void set_profile_gubun2(String profile_gubun2) { this.profile_gubun2 = profile_gubun2; }
    public String get_profile_gubun2() { return this.profile_gubun2; }

    public void set_profile_speed(int profile_speed) { this.profile_speed = profile_speed; }
    public int get_profile_speed() { return this.profile_speed; }

    public void set_profile_control(int profile_control) { this.profile_control = profile_control; }
    public int get_profile_control() { return this.profile_control; }

    public void set_profile_cnt(int profile_cnt) { this.profile_cnt = profile_cnt; }
    public int get_profile_cnt() { return this.profile_cnt; }

    public void set_profile_friend_cnt(int profile_cnt) { this.profile_friend_cnt = profile_friend_cnt; }
    public int get_profile_friend_cnt() { return this.profile_friend_cnt; }


    public void set_profile_new_cnt(int profile_new_cnt) { this.profile_new_cnt = profile_new_cnt; }
    public int get_profile_new_cnt() { return this.profile_new_cnt; }


    public void set_group_code(String group_code) { this.group_code = group_code; }
    public String get_group_code() { return this.group_code; }


    public void set_profile_mytype(String profile_mytype) { this.profile_mytype = profile_mytype; }
    public String get_profile_mytype() { return this.profile_mytype; }

}
