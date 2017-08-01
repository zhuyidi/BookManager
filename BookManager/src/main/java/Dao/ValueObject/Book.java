package Dao.ValueObject;


/**
 * Created by dela on 7/20/17.
 */
public class Book {
    private int id;
    private String name;
    private String author;
    private String owner;
    private int amount;
    private String upload_date;
    private String describe;
    private int borrow_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getBorrow_num() {
        return borrow_num;
    }

    public void setBorrow_num(int borrow_num) {
        this.borrow_num = borrow_num;
    }

}
