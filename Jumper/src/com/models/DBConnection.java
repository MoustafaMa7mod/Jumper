package com.models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;
/**
 *  make connection with Application DataBase 
 * @return
 */
	public static Connection getActiveConnection() {
		/*String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		System.out.println(host);*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection = DriverManager
			//		.getConnection("jdbc:mysql://127.8.100.2:3306/se2firstapp?"
			//				+ "user=adminYKFs38v&password=QG9RmdNVFgmc&characterEncoding=utf8");
			connection = DriverManager
					.getConnection("jdbc:mysql://127.2.13.2:3306/jumper?"
							+ "user=admin4BkrBVg&password=pnQtRCR_uzC2&characterEncoding=utf8");
//			connection = DriverManager
//				.getConnection("jdbc:mysql://127.7.144.2:3306/backend?"
//							+ "user=admin2Wuf4Wp&password=t8TfgF2tJa9e&characterEncoding=utf8");
		
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
