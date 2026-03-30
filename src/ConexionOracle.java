import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
        String user = "system";
        String password = "Tapiero123";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(url, user, password);
    }
}   
