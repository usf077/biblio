import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class Test {

private static Logger logger = Logger.getLogger(Test.class.getCanonicalName());
	
    public static void main(String[] a)
            throws Exception {
    	
    	 logger.info("Demarrage");
    	 Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
        getConnection("jdbc:h2:~/test", "sa", "");
        System.out.println("jdbc Version : "+ DriverManager.getDrivers().nextElement().getMajorVersion() +"."+DriverManager.getDrivers().nextElement().getMinorVersion());
        conn.close();
    }
}