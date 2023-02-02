package com.example.enchere.Base;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
//	private static String url = "jdbc:postgresql://containers-us-west-185.railway.app:5889/railway";
//    private static String user="postgres";
//    private static String passwd = "NxPX0hpksmxrlYaTnqg5";
    private static String url = "jdbc:postgresql://localhost:5432/enchere";
    private static String user="enchere";
    private static String passwd = "enchere";
	private  Connection connect;
	public  Connection setConnect() throws Exception
	{
		if (connect == null) {
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (Exception e) {
                throw e;
            }
        }
        return connect;
	}
}
