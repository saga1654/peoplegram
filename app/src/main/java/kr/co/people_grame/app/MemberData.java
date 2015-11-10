package kr.co.people_grame.app;

/**
 * Created by 광희 on 2015-11-10.
 */
public class MemberData {
    static String userid = "";
    static String userpw = "";
    static String nickname = "";
    static String sex = "";
    static String birthday = "";
    static String area1 = "";
    static String area2 = "";
    static String area3 = "";
    static String phone = "";
    static String telecom = "";

    public void set_userid(String userid) { this.userid = userid; }
    public String get_userid() {
        return this.userid;
    }

    public void set_userpw(String userpw) { this.userpw = userpw; }
    public String get_userpw() { return this.userpw; }

    public void set_nickname(String nickname) { this.nickname = nickname; }
    public String get_nickname() { return this.nickname; }

    public void set_sex(String sex) { this.sex = sex; }
    public String get_sex() { return this.sex; }

    public void set_birthday(String birthday) { this.birthday = birthday; }
    public String get_birthday() { return this.birthday; }

    public void set_area1(String area1) { this.area1 = area1; }
    public String get_area1() { return this.area1; }

    public void set_area2(String area2) { this.area2 = area2; }
    public String get_area2() { return this.area2; }

    public void set_area3(String area3) { this.area3 = area3; }
    public String get_area3() { return this.area3; }

    public void set_phone(String phone) { this.phone = phone; }
    public String get_phone() { return this.phone; }

    public void set_telecom(String telecom) { this.telecom = telecom; }
    public String get_telecom() { return this.telecom; }


}
