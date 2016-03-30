package fr.dauphine.lamsade.hib.biblio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Author : Mohamed Youssef Errayhani 
 * Date : 
 */

@Deprecated
public class DBConnexion {
	
	  // Les données sur le driver
	   static final String JDBC_DRIVER = "org.postgresql.Driver";  
	   static final String DB_URL = "jdbc:postgresql://localhost:5432/biblio";

	   //  UserName et password de la base
	   static final String USER = "postgres";
	   static final String PASS = "123456";


	   public static Connection  getConnexion() throws SQLException, ClassNotFoundException {
		   //Dans le cour on a vu que l'instruction  Class.forName(JDBC_DRIVER) n'est plus obligatoire 
		   //par contre si on l'enlève dans notre cas, le programme léve une exception de type Driver not found
	   Class.forName(JDBC_DRIVER);
	   return  DriverManager.getConnection(DB_URL, USER, PASS);
	   }
}
