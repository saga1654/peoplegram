package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubPeopleMatchListDTO {
    String profile_uid = "";
    String profile_username = "";
   // String profile_email = "";
    String profile_type = "";
    String profile_gubun1 = "";
    String profile_gubun2 = "";
    int profile_people_speed = 0;
    int profile_people_control = 0;
    int profile_total_value = 0;
    int profile_score_temp = 0;
    double profile_match_value = 0;

    public SubPeopleMatchListDTO() {

    }

    public SubPeopleMatchListDTO(String profile_uid, String profile_username,  String profile_type, String profile_gubun1, String profile_gubun2, int profile_people_speed, int profile_people_control, int profile_total_value, int profile_score_temp, double profile_match_value)
    {
        this.profile_uid = profile_uid;
        this.profile_username = profile_username;
       // this.profile_email = profile_email;
        this.profile_type = profile_type;
        this.profile_gubun1 = profile_gubun1;
        this.profile_gubun2 = profile_gubun2;
        this.profile_people_speed = profile_people_speed;
        this.profile_people_control = profile_people_control;
        this.profile_total_value = profile_total_value;
        this.profile_score_temp = profile_score_temp;
        this.profile_match_value = profile_match_value;



    }

    public void set_profile_uid(String profile_uid) { this.profile_uid = profile_uid; }
    public String get_profile_uid() { return this.profile_uid; }


    public void set_profile_username(String profile_username) { this.profile_username = profile_username; }
    public String get_profile_username() { return this.profile_username; }

   // public void set_profile_email(String profile_email) { this.profile_username = profile_email; }
   // public String get_profile_email() { return this.profile_email; }

    public void set_profile_type(String profile_type) { this.profile_type = profile_type; }
    public String get_profile_type() { return this.profile_type; }

    public void set_profile_gubun1(String profile_gubun1) { this.profile_gubun1 = profile_gubun1; }
    public String get_profile_gubun1() { return this.profile_gubun1; }

    public void set_profile_gubun2(String profile_gubun2) { this.profile_gubun2 = profile_gubun2; }
    public String get_profile_gubun2() { return this.profile_gubun2; }

    public void set_profile_speed(int profile_people_speed) { this.profile_people_speed = profile_people_speed; }
    public int get_profile_speed() { return this.profile_people_speed; }

    public void set_profile_control(int profile_people_control) { this.profile_people_control = profile_people_control; }
    public int get_profile_control() { return this.profile_people_control; }

    public void set_profile_total_value(int profile_total_value) { this.profile_total_value = profile_total_value; }
    public int get_profile_total_value() { return this.profile_total_value; }

    public void set_profile_score(int profile_score_temp) { this.profile_score_temp = profile_score_temp; }
    public int get_profile_score() { return this.profile_score_temp; }

    public void set_profile_match_value(int profile_match_value) { this.profile_match_value = profile_match_value; }
    public double get_profile_match_value() { return this.profile_match_value; }



}
