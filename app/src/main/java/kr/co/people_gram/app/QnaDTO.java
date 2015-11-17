package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class QnaDTO {
    String title;
    String create_join;
    String code;
    String qna_yn;

    public QnaDTO()
    {
    }

    public QnaDTO(String code, String title, String create_join, String qna_yn)
    {
        this.code = code;
        this.title = title;
        this.create_join = create_join;
    }

    public void set_QnaCode(String code) { this.code = code; }
    public String get_QnaCode() { return this.code; }

    public void set_QnaTitle(String title)
    {
        this.title = title;
    }
    public String get_QnaTitle()
    {
        return this.title;
    }

    public void set_CreateJoin(String create_join)
    {
        this.create_join = create_join;
    }
    public String get_CreateJoin() { return this.create_join; }

    public void set_QnaYn(String qna_yn) { this.qna_yn = qna_yn; }
    public String get_QnaYn() { return this.qna_yn; }
}
