package Model;

/**
 * Created by dela on 7/24/17.
 */
public class DealKeywords {
    public static String dealKeyWords(String keyWords){
        System.out.println("###");
        System.out.println(keyWords);
        String result = "%";
        int i = 0;
        for(i = 0; i < keyWords.length(); i++){
            result += keyWords.charAt(i);
            result += "%";
        }
        return result;
    }
}
