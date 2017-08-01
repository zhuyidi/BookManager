package test;

import Dao.DaoFactory.DaoFactory;
import Dao.IDao.BookDao;
import Dao.ValueObject.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dela on 7/24/17.
 */
public class booksTest {
    public static void main(String args[]){
        List<Book> books = new ArrayList<Book>();
        Book book = null;
        BookDao bookDao = DaoFactory.getBookDaoInstance();
        books = bookDao.queryAllBook();
        System.out.println(books.size());
        Iterator<Book> bookIterator = books.iterator();
        while(bookIterator.hasNext()) {
            book = bookIterator.next();
            System.out.println(book.getName());
        }
    }
}
