import java.io.IOException;
import java.util.ArrayList;

public class sp312{
    public static void main(String[] arg) throws IOException {   //主方法
        HtmlParser1 HP = new HtmlParser1("http://www.google.com/search?q=附中&oe=utf8&num=10");
        ArrayList<String> hrefList = HP.parser();
        for (int i = 0; i < hrefList.size(); i++)
            System.out.println(hrefList.get(i));
        HP.getURL();
    }
}