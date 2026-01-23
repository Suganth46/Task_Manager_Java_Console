import java.sql.*;

public class test {
    public static void main(String[] args) {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER_NAME");
        String password = System.getenv("DB_PASSWORD");

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL!");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
