package com.prowess.bms.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CC {

	public static final String DELETE_QUERY = "DELETE FROM BOOK WHERE BOOK_ID = ?";
	public static final String UPDATE_PRICE_QUERY = "UPDATE BOOK SET BOOK_PRICE = ? WHERE BOOK_ID = ?";
	public static Connection connection = null;
	public static String propertieFilePath = "";
	public static String GET_BOOKS_BY_ID = "SELECT * FROM BOOK WHERE BOOK_ID= ?";
	public static String GET_BOOKS_BY_COLUMN = "SELECT * FROM BOOK WHERE ";
	public static String INSERT_QUERY = "INSERT INTO BOOK VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String GET_ALL_RECORDS_QUERY = "SELECT * FROM BOOK";
	public static String createdUser = "ajay@prowess.com";
	public static String PRICE_IN_BETWEEN_QUERY = "SELECT * FROM BOOK WHERE BOOK_PRICE BETWEEN ? AND ?";

	public static Connection getMySqlDBConnection() {

		Properties properties = readPropertiesFromFile("D:\\prop\\trainingMySql.properties.txt");

		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String regDriver = properties.getProperty("regDriver");
		try {
			Class.forName(regDriver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Properties readPropertiesFromFile(String propertieFilePath) {
		FileInputStream fileInputStream = null;
		Properties properties = new Properties();
		try {
			fileInputStream = new FileInputStream(propertieFilePath);
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
