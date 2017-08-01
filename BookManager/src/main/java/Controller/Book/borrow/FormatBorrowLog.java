package Controller.Book.borrow;

import Dao.DaoFactory.DaoFactory;
import Dao.ValueObject.BorrowLog;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-7-29.
 */
//得到借阅日志
public class FormatBorrowLog {
    public static List<String> getLog() {
        List<BorrowLog> borrowLogs = DaoFactory.getBorrow_infoDaoInstance().
                queryBorrowLog();
        List<String> strings = new ArrayList<String>();
        String string;

        for (int i = borrowLogs.size()-1; i >= 0; i--) {
            if (borrowLogs.get(i).getFlag() == 1) {
                string = borrowLogs.get(i).getDate() + "  " + borrowLogs.get(i)
                        .getBorrowUser() + "从" + borrowLogs.get(i).getOwner() + "借阅《"
                        + borrowLogs.get(i).getBookname() + "》";
            } else {
                string = borrowLogs.get(i).getDate() + "  " + borrowLogs.get(i)
                        .getBorrowUser() + "向" + borrowLogs.get(i).getOwner() + "归还《"
                        + borrowLogs.get(i).getBookname() + "》";
            }

            strings.add(string);
        }

        return strings;
    }

    //测试
//    public static void main(String[] args) {
//        List<String> strings = getLog();
//
//        for (String string : strings) {
//            out.println(string);
//        }
//    }
}
