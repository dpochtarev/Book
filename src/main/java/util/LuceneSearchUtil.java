package util;


import book.User;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

public class LuceneSearchUtil {
    private static final LuceneSearchUtil instance = new LuceneSearchUtil();
    private static IndexWriter writer = instance.writer;
    private LuceneSearchUtil(){
        try {
//            Directory directory = new RAMDirectory();
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("/index"));
            System.out.println(directory);
            IndexWriterConfig config = new IndexWriterConfig(new StopAnalyzer());
            writer = new IndexWriter(directory, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static LuceneSearchUtil getInstance() {
        return instance;
    }

    public ArrayList<User> search(String text) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        ArrayList<User> found = new ArrayList<>();
        // Store the index in memory:
//        Directory directory = new RAMDirectory();
//        Directory directory = FSDirectory.open("temp");
          // To store an index on disk, use this instead:
//        Directory directory = FSDirectory.open("temp");
//        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//        IndexWriter iwriter = new IndexWriter(directory, config);
//        Document doc = new Document();
//        String text = "12";
//        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
//        iwriter.addDocument(doc);
//        iwriter.close();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(writer.getDirectory());
        System.out.println(ireader.getDocCount("search"));
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("search", analyzer);


//        Query query = parser.parse("text");
        System.out.println(text);
        Query query = parser.parse(text);
        System.out.println(query);
        ScoreDoc[] hits = isearcher.search(query, 100).scoreDocs;
        // Iterate through the results:
        for (ScoreDoc hit : hits) {
            System.out.println(hit);
            Document hitDoc = isearcher.doc(hit.doc);
            found.add(new User(hitDoc.get("name"), hitDoc.get("phone"), hitDoc.get("address"), Long.valueOf(hitDoc.get("id"))));
        }
        ireader.close();
        writer.getDirectory().close();
        return found;
    }

    public void addDoc(User user) throws IOException {
        Document doc = buildUser(user);
        writer.addDocument(doc);
        System.out.println(doc.toString());
        writer.commit();
    }

    private Document buildUser(User user) {
        Document doc = new Document();
        doc.add(new TextField("id", String.valueOf(user.getId()), Field.Store.YES));
        doc.add(new TextField("name", String.valueOf(user.getName()), Field.Store.YES));
        doc.add(new TextField("phone", String.valueOf(user.getPhone()), Field.Store.YES));
        doc.add(new TextField("address", String.valueOf(user.getAddress()), Field.Store.YES));
        doc.add(new TextField("search", String.valueOf(user.getName()+user.getPhone()+user.getAddress()), Field.Store.YES));
        System.out.println(doc.get("search"));
        return doc;
    }

    public void reindex(User user) throws IOException {
        Document doc = buildUser(user);
        writer.updateDocument(new Term(String.valueOf(user.getId())), doc);
    }
}
