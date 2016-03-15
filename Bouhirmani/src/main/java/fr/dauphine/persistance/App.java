package fr.dauphine.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class App 
{
	private static final Logger log = Logger.getLogger(App.class.getName());
    
	public static void main( String[] args )
	{
        	log.info(">>------Démarrage-----<<");
        	try {
				Class.forName("org.h2.Driver");
	            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
	            log.info("Version du pilot: "+DriverManager.getDrivers().nextElement().getMajorVersion()+ 
	            "."+DriverManager.getDrivers().nextElement().getMinorVersion()); 
	            connection.close();    
    		} catch (ClassNotFoundException e) {
    			log.severe(e.getMessage());
			} catch (SQLException e) {
				log.severe(e.getMessage());
			}
    }
}
