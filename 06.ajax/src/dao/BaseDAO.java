package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "madang";
	public static final String PASSWORD = "madang";

	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (SQLException e01) {
			e01.printStackTrace();

		} catch (ClassNotFoundException e02) {
			e02.printStackTrace();

		}

		return connection;

	}

	public void closeDBObjects(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {

			try {
				resultSet.close();

			} catch (SQLException e01) {
				e01.printStackTrace();
			}

		}
		if (statement != null) {
			try {
				statement.close();
			}catch(SQLException e02) {
				e02.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
				
			}catch(SQLException e03) {
				e03.printStackTrace();
			}
			
		}

	}
}
