package com.err;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {
	private static final Logger log= Logger.getLogger( Test.class.getName() );
	public static void main(String[] args) {

		  log.log(Level.INFO,"————————  Démarrage test de Connexion H2 JDBC ! ————");
	        try {
	            Connection connection = null;
	            
	            
	            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
	            if (connection != null) {
	            	String version = null;
	            	StringBuilder strVersionBuilder = new StringBuilder();
	            	strVersionBuilder.append(DriverManager.getDriver("jdbc:h2:~/test").getMajorVersion());
	            	strVersionBuilder.append('.');
	            	version = strVersionBuilder.append(DriverManager.getDriver("jdbc:h2:~/test").getMinorVersion()).toString();
	            	log.log(Level.INFO,"Connexion avec succès");	            	
	            	log.log(Level.INFO,"La vesrion du driver est : {0}" ,version);
	            } else {
	            	log.log(Level.SEVERE,"Erreur de Connexion");
	            }
	        } catch (Exception e) {
	        	log.log(Level.SEVERE,"Erreur de Connexion");
	            log.throwing(Test.class.getName(), "Main",e);
	        }
		
	}
}
