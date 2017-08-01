package Dao.ValueObject;

import java.util.Date;

/**
 * Created by dela on 7/22/17.
 */
public class Book_comments {
    private int book_id;
    private int user_id;
    private String detail;
    private String comment_datetime;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getComment_datetime() {
        return comment_datetime;
    }

    public void setComment_datetime(String comment_datetime) {
        this.comment_datetime = comment_datetime;
    }
}
