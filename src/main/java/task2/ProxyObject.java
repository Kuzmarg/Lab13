package task2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProxyObject {
    static String QUERY = "SELECT url, data FROM data_class";
    private Connection connection;

    public ProxyObject(){
        this.connection = Connection.getConnection();
    }
    public String searchData(String url) throws SQLException, IOException {

        ResultSet result = connection.executeQuery(QUERY);
        while (result.next()) {
            if(result.getString("url").equals(url)) {
                return result.getString("data");
            }
        }
        Document doc = Jsoup.connect(url).get();
        connection.executeQuery("insert into data_class (url, data) values (" + url + "," + doc.toString() + ");");
        return doc.toString();
    }
}
