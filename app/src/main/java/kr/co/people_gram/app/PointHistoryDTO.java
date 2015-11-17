package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class PointHistoryDTO {
    String datetime;
    String title;
    String point;

    public PointHistoryDTO()
    {
    }

    public PointHistoryDTO(String datetime, String title, String point)
    {
        this.datetime = datetime;
        this.title = title;
        this.point = point;
    }

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
}
