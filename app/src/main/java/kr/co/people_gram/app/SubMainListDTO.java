package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubMainListDTO {
    String my_profile_img = "";
    String my_profile_nickname = "";
    String my_profile_type = "";
    String you_profile_img = "";
    String you_profile_nickname = "";
    String you_profile_type = "";

    String contents = "";
    String insert_datetime;
    String gonggam_cnt = "";
    String comment_cnt = "";

    public SubMainListDTO() {

    }

    public SubMainListDTO(String my_profile_img, String my_profile_nickname, String my_profile_type, String you_profile_img, String you_profile_nickname, String you_profile_type, String Contents, String insert_datetime, String gonggam_cnt, String comment_cnt) {

        this.my_profile_img = my_profile_img;
        this.my_profile_nickname = my_profile_nickname;
        this.my_profile_type = my_profile_type;

        this.you_profile_img = you_profile_img;
        this.you_profile_nickname = you_profile_nickname;
        this.you_profile_type= you_profile_type;

        this.contents = Contents;

        this.insert_datetime = insert_datetime;
        this.gonggam_cnt = gonggam_cnt;
        this.comment_cnt = comment_cnt;
    }

    /* my profile */
    public void set_my_profile_img(String my_profile_img) { this.my_profile_img = my_profile_img; }
    public String get_my_profile_img() { return this.my_profile_img; }
    public void set_my_profile_nickname(String my_profile_nickname) { this.my_profile_nickname = my_profile_nickname; }
    public String get_my_profile_nickname() { return this.my_profile_nickname; }
    public void set_my_profile_type(String my_profile_type) { this.my_profile_type = my_profile_type; }
    public String get_my_profile_type() { return this.my_profile_type; }


    /* you profile */
    public void set_you_profile_img(String you_profile_img) { this.you_profile_img = you_profile_img; }
    public String get_you_profile_img() { return this.you_profile_img; }
    public void set_you_profile_nickname(String you_profile_nickname) { this.you_profile_nickname = you_profile_nickname; }
    public String get_you_profile_nickname() { return this.you_profile_nickname; }
    public void set_you_profile_type(String you_profile_type) { this.you_profile_type = you_profile_type; }
    public String get_you_profile_type() { return this.you_profile_type; }

    /* contents */

    public void set_contents(String Contents) { this.contents = Contents; }
    public String get_contents() { return this.contents; }

    public void set_insert_datetime(String insert_datetime) { this.insert_datetime = insert_datetime; }
    public String get_insert_datetime() { return this.insert_datetime; }
    public void set_gonggam_cnt(String gonggam_cnt) { this.gonggam_cnt = gonggam_cnt; }
    public String get_gonggam_cnt() { return this.gonggam_cnt; }
    public void set_comment_cnt(String comment_cnt) { this.comment_cnt = comment_cnt; }
    public String get_comment_cnt() { return this.comment_cnt; }

}
