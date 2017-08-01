package Dao.DaoFactory;

import Dao.DaoObject.*;
import Dao.IDao.*;

/**
 * Created by dela on 7/21/17.
 */
public class DaoFactory {
    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }

    public static BookDao getBookDaoInstance(){
        return new BookDaoImpl();
    }

    public static Book_classDao getBook_classDaoInstance() {return new Book_classDaoImpl(); }

    public static Book_commentsDao getBook_commentsDaoInstance() {return new Book_commentsDaoImpl(); }

    public static Book_class_relationDao getBook_class_relationDAOInstance() {return new Book_class_relationDaoImpl(); }

    public static Borrow_infoDao getBorrow_infoDaoInstance() {return new Borrow_infoDaoImpl(); }
}
