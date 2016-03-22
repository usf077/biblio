package fr.dauphine.lamsade.hib.biblio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnexion {
	
	  // Les données sur le driver
	   static final String JDBC_DRIVER = "org.postgresql.Driver";  
	   static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/biblio";

	   //  UserName et password de la base
	   static final String USER = "postgres";
	   static final String PASS = "123456";


	   public static Connection  getConnexion() throws SQLException {
	   return  DriverManager.getConnection(DB_URL, USER, PASS);
	   }
}
