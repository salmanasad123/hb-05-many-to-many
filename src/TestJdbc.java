import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        // setup JDBC url to connect to our database, this is called connection string
        // we give our database name as well which is hb_student_tracker
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-05-many-to-many?useSSL=false";
        // define username and password to connect to our database
        String user = "hbstudent";
        String password = "hbstudent";

        try {
            System.out.println("Connecting to database " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("Connection Successful");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
