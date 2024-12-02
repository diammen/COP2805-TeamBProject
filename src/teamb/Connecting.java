package teamb;

/**
 *
 * @author Jaden
 */
import java.sql.*;
public class Connecting {
    public static Connection letConnect() {
        Connection conn = null;
        String url = "jdbc:derby:MovieTheaterDB";
        
        try{Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
