package test;

import Dao.DaoFactory.DaoFactory;
import Dao.ValueObject.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dela on 7/24/17.
 */
public class bookqueryByNAOTest {
    public static void main(String args[]){
        String str = "学习笔记";
        List<Book> books = new ArrayList<Book>();
        books = DaoFactory.getBookDaoInstance().queryByNAO(str);
        Iterator<Book> bookIterator = books.iterator();
        Book book = null;

        while(bookIterator.hasNext()){
            book = bookIterator.next();
            System.out.println(book.getName());
        }

    }
}
