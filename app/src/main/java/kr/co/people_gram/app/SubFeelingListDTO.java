package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubFeelingListDTO {
    String profile_uid = "";
    String profile_img = "";
    String profile_username = "";
    String profile_email = "";
    String feeling_type = "";
    String feeling_msg = "";
    String feeling_select1 = "";
    String feeling_select2 = "";
    String feeling_select3 = "";
    String feeling_select4 = "";
    String feeling_select5 = "";

    public SubFeelingListDTO() {

    }

    public SubFeelingListDTO(String profile_uid, String profile_img, String profile_username, String profile_email, String feeling_type, String feeling_msg, String feeling_select1, String feeling_select2, String feeling_select3, String feeling_select4, String feeling_select5)
    {
        this.profile_uid = profile_uid;
        this.profile_img = profile_img;
        this.profile_username = profile_username;
        this.profile_email = profile_email;

        this.feeling_type = feeling_type;
        this.feeling_msg = feeling_msg;
        this.feeling_select1 = feeling_select1;
        this.feeling_select2 = feeling_select2;
        this.feeling_select3 = feeling_select3;
        this.feeling_select4 = feeling_select4;
        this.feeling_select5 = feeling_select5;
    }

    public void set_profile_uid(String profile_uid) { this.profile_uid = profile_uid; }
    public String get_profile_uid() { return this.profile_uid; }

    public void set_profile_img(String profile_uid) { this.profile_uid = profile_img; }
    public String get_profile_img() { return this.profile_img; }

    public void set_profile_username(String profile_username) { this.profile_username = profile_username; }
    public String get_profile_username() { return this.profile_username; }

    public void set_profile_email(String profile_email) { this.profile_email= profile_email; }
    public String get_profile_email() { return this.profile_email; }

    public void set_feeling_type(String feeling_type) { this.feeling_type = feeling_type; }
    public String get_feeling_type() { return this.feeling_type; }

    public void set_feeling_msg(String feeling_msg) { this.feeling_msg = feeling_msg; }
    public String get_feeling_msg() { return this.feeling_msg; }

    public void set_feeling_select1(String feeling_select1) { this.feeling_select1 = feeling_select1; }
    public String get_feeling_select1() { return this.feeling_select1; }

    public void set_feeling_select2(String feeling_select2) { this.feeling_select2 = feeling_select2; }
    public String get_feeling_select2() { return this.feeling_select2; }

    public void set_feeling_select3(String feeling_select3) { this.feeling_select3 = feeling_select3; }
    public String get_feeling_select3() { return this.feeling_select3; }

    public void set_feeling_select4(String feeling_select4) { this.feeling_select4 = feeling_select4; }
    public String get_feeling_select4() { return this.feeling_select4; }

    public void set_feeling_select5(String feeling_select5) { this.feeling_select5 = feeling_select5; }
    public String get_feeling_select5() { return this.feeling_select5; }

}
