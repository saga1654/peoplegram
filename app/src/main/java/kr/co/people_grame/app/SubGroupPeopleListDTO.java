package kr.co.people_grame.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubGroupPeopleListDTO {
    String profile_uid = "";
    String profile_img = "";
    String profile_username = "";
    String profile_email = "";
    String profile_type = "";
    String profile_mood = "";
    String profile_gubun1 = "";
    String profile_gubun2 = "";
    int profile_speed = 0;
    int profile_control = 0;

    public SubGroupPeopleListDTO() {

    }

    public SubGroupPeopleListDTO(String profile_uid, String profile_img, String profile_username, String profile_email, String profile_type, String profile_mood, String profile_gubun1, String profile_gubun2, int profile_speed, int profile_control)
    {
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

    }

    public void set_profile_uid(String profile_uid) { this.profile_uid = profile_uid; }
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


}
