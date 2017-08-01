package Model;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by dela on 7/22/17.
 */
public class escapeHtml {
    public static String escape(String str){
        String result = StringEscapeUtils.escapeHtml(str);
        return result;
    }
}
