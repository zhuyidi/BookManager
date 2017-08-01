package test;

import Dao.DaoFactory.DaoFactory;
import Dao.IDao.Book_classDao;
import Dao.ValueObject.Book_class;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dela on 7/23/17.
 */
public class book_classTest {
    public static void main(String args[]){
        List<Book_class> book_classes = new ArrayList<Book_class>();
        Book_classDao book_classDao = DaoFactory.getBook_classDaoInstance();
        book_classes = book_classDao.getBookClasses(0);
        Iterator<Book_class> it = book_classes.iterator();
        System.out.println(book_classes.size());
        while(it.hasNext()) {
            Book_class book_class = it.next();
            System.out.println(book_class.getName());
        }
    }
}
