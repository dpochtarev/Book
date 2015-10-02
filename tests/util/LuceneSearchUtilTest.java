package util;

import book.User;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public class LuceneSearchUtilTest {


    public static void main(String[] args) throws IOException, ParseException {
        User user = new User("vasa", "2-12-23", "Kirova 15", 1l);
        LuceneSearchUtil su = LuceneSearchUtil.getInstance();
        su.addDoc(user);
        System.out.println(su.search("2"));
    }


}
