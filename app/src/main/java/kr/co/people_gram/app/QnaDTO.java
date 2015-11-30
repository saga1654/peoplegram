package kr.co.people_gram.app;

/**
 * Created by 광희 on 2015-09-18.
 */
public class QnaDTO {
    String title;
    String create_join;
    String code;
    String answer_result;

    public QnaDTO()
    {
    }

    public QnaDTO(String code, String title, String create_join, String answer_result)
    {
        this.code = code;
        this.title = title;
        this.create_join = create_join;
        this.answer_result = answer_result;
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

    public void set_answer_result(String answer_result) { this.answer_result = answer_result; }
    public String get_answer_result() { return this.answer_result; }
}
