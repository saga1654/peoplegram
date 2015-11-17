package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class NoticeDTO {
    String title;
    String create_join;
    String code;

    public NoticeDTO()
    {
    }

    public NoticeDTO(String code, String title, String create_join)
    {
        this.code = code;
        this.title = title;
        this.create_join = create_join;
    }

    public void set_NoticeCode(String code) { this.code = code; }
    public String get_NoticeCode() { return this.code; }

    public void set_NoticeTitle(String title)
    {
        this.title = title;
    }
    public String get_NoticeTitle()
    {
        return this.title;
    }

    public void set_CreateJoin(String create_join)
    {
        this.create_join = create_join;
    }
    public String get_CreateJoin() { return this.create_join; }
}
