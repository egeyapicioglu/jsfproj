package egeproject;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionClass {

	private Connection connection;
	

	public Connection getConnection() {
		return connection;
	}


	public void setConnection() {
	
		String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/theDatabase";
		String connName = "postgres";
		String connPass = "22112211";
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("PostgreSql JSBC Driver couldn't be found");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection currConnection = null;

		try {

			currConnection = DriverManager.getConnection(connectionUrl, connName, connPass);
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (currConnection != null) {
			System.out.println("Database connection established");
		} else {
			System.out.println("Failed to make connection!");
		}
		this.connection = currConnection;
	}


}


