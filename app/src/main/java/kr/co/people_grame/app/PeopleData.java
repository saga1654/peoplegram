package kr.co.people_grame.app;

/**
 * Created by 광희 on 2015-10-02.
 */
public class PeopleData {
    static String people_uid = "";
    static String people_mood = "";
    static String people_type = "";
    static String people_username = "";
    static String people_gubun1 = "";
    static String people_gubun2 = "";


    public void set_people_uid(String people_uid) { this.people_uid = people_uid; }
    public String get_people_uid() {
        return this.people_uid;
    }

    public void set_people_username(String people_username) { this.people_username = people_username; }
    public String get_people_username() {
        return this.people_username;
    }

    public void set_people_mood(String people_mood) {
        this.people_mood = people_mood;
    }
    public String get_people_mood()
    {
        return this.people_mood;
    }

    public void set_people_type(String people_type) {
        this.people_type = people_type;
    }
    public String get_people_type() {
        return this.people_type;
    }

    public void set_people_gubun1(String people_gubun1) { this.people_gubun1 = people_gubun1; }
    public String get_people_gubun1() {
        return this.people_gubun1;
    }

    public void set_people_gubun2(String people_gubun2) { this.people_gubun2 = people_gubun2; }
    public String get_people_gubun2() {
        return this.people_gubun2;
    }

}
