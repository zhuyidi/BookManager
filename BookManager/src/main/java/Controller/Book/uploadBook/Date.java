package Controller.Book.uploadBook;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hg_yi on 17-7-26.
 */
public class Date {
    public static String getCurrentTime() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String nowTime = sdf.format(now.getTime());

        return nowTime;
    }
}
