package util;

import book.User;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public class LuceneSearchUtilTest {


    public static void main(String[] args) throws IOException, ParseException {
        User user = new User("vasa", "2-12-23", "Kirova 15", 1l);
        User user1 = new User("vasiliy", "2-8798723", "Gagarina 20", 2l);
        LuceneSearchUtil su = LuceneSearchUtil.getInstance();
        su.addDoc(user);
        su.addDoc(user1);
        for (User u:su.search("vasa")){
            System.out.println(u.getName()+" "+u.getAddress());
        }
    }
}
