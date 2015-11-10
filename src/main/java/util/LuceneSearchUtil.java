package util;

import book.User;
import logic.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
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
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LuceneSearchUtil {
    private static final File file = new File("/index");
    private static final LuceneSearchUtil instance = new LuceneSearchUtil();
    private static IndexWriter writer;

    private LuceneSearchUtil() {
        try {
            //            Directory directory = new RAMDirectory();
            Directory directory = FSDirectory.open(file);
            System.out.println(directory);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, new StandardAnalyzer(Version.LUCENE_40));
            writer = new IndexWriter(directory, config);
            if (writer.numDocs()==0) createIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LuceneSearchUtil getInstance() {
        return instance;
    }


    private void createIndex() throws IOException {
        ArrayList<Document> list = new ArrayList<>();
        for(User user:Book.getList()) list.add(buildUser(user));
        writer.addDocuments(list);
        writer.commit();
    }

    public ArrayList<User> search(String text) throws IOException, ParseException {
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        Analyzer analyzer = new WhitespaceAnalyzer(Version.LUCENE_40);
        ArrayList<User> found = new ArrayList<>();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(writer.getDirectory());
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser(Version.LUCENE_40, "search", analyzer);


        System.out.println(text);
        if (!text.equals("")) {
            Query query = parser.parse(text + "*");
            System.out.println(query);
            ScoreDoc[] hits = isearcher.search(query, 100).scoreDocs;
            // Iterate through the results:
            for (ScoreDoc hit : hits) {
                Document hitDoc = isearcher.doc(hit.doc);
                System.out.println(hitDoc.get("name")+" "+hitDoc.get("address"));
                found.add(new User(hitDoc.get("name"), hitDoc.get("phone"), hitDoc.get("address"), Long.valueOf(hitDoc.get("id"))));
            }
        }
        else {
            for (int i=0; i<ireader.numDocs(); i++) {
                Document hitDoc = ireader.document(i);
                found.add(new User(hitDoc.get("name"), hitDoc.get("phone"), hitDoc.get("address"), Long.valueOf(hitDoc.get("id"))));
            }
        }
        ireader.close();
        return found;
    }

    public void addDoc(User user) throws IOException {
        Document doc = buildUser(user);
        writer.addDocument(doc);
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
        writer.updateDocument(new Term("id", String.valueOf(user.getId())), doc);
        writer.commit();
    }

    public void delete(User user) throws IOException {
        writer.deleteDocuments(new Term("id", String.valueOf(user.getId())));
        writer.forceMergeDeletes();
        writer.commit();
    }
}