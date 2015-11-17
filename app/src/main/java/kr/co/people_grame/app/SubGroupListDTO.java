package kr.co.people_grame.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class SubGroupListDTO {
    String group_code = "";
    String group_name = "";
    String all_people_name = "";


    public SubGroupListDTO() {

    }

    public SubGroupListDTO(String group_code, String group_name, String all_people_name)
    {
        this.group_code = group_code;
        this.group_name = group_name;
        this.all_people_name = all_people_name;
    }

    public String get_group_code()
    {
        return this.group_code;
    }

    public String get_group_name()
    {
        return this.group_name;
    }

    public String get_all_people_name()
    {
        return this.all_people_name;
    }


}
