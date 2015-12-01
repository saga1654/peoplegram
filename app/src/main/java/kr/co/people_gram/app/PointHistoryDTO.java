package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class PointHistoryDTO {
    String type;
    String datetime;
    String title;
    String point;
    String now_point;

    public PointHistoryDTO()
    {
    }

    public PointHistoryDTO(String type, String datetime, String title, String point, String now_point)
    {
        this.type = type;
        this.datetime = datetime;
        this.title = title;
        this.point = point;
        this.now_point = now_point;
    }

    public void set_type(String type) { this.type = type; }
    public String get_type() { return this.type; }


    public void set_datetime(String code) { this.datetime = datetime; }
    public String get_datetime() { return this.datetime; }

    public void set_title(String title)
    {
        this.title = title;
    }
    public String get_title()
    {
        return this.title;
    }

    public void set_point(String create_join)
    {
        this.point = point;
    }
    public String get_point() { return this.point; }


    public void set_now_point(String now_point)
    {
        this.now_point = now_point;
    }
    public String get_now_point() { return this.now_point; }
}
