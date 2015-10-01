package kr.co.people_grame.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubPeopleListDTO {
    String profile_uid = "";
    String profile_img = "";
    String profile_username = "";
    String profile_email = "";
    String profile_type = "";
    String profile_mood = "";

    public SubPeopleListDTO() {

    }

    public SubPeopleListDTO(String profile_uid, String profile_img, String profile_username, String profile_email, String profile_type, String profile_mood)
    {
        this.profile_uid = profile_uid;
        this.profile_img = profile_img;
        this.profile_username = profile_username;
        this.profile_email = profile_email;
        this.profile_type = profile_type;
        this.profile_mood = profile_mood;
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

}
