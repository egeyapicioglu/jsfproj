package bean;

import egeproject.ConnectionClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.sql.Connection;
import model.User;

@ManagedBean (name = "LoginBean") 
@RequestScoped

public class LoginBean {

	User user = new User();
	ConnectionClass connectionClass = new ConnectionClass();
	String connUrl = "jdbc:postgresql://127.0.0.1:5432/theDatabase";
	String connName = "postgres";
	String connPass = "22112211";
	

	public Connection getDBConnection()
	{
		Connection connection = null;
		connectionClass.setConnection(connUrl, connName, connPass);
		connection = connectionClass.getConnection();
		return connection;
	}
	
	public Boolean isUserReal(String userName, int password)
	{
		Boolean resultBool = false;
		Connection connection = getDBConnection();

		String selectionSql = "SELECT USER_NAME, USER_PASSWORD FROM User_Table WHERE USER_NAME = \'" + userName + "\' AND USER_PASSWORD = \'" + password + "\'"; 
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectionSql);
			//preparedStatement.setInt(1,1001);
			
			ResultSet set = preparedStatement.executeQuery();
			if(set.next())
				resultBool = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e);
		}
		
		return resultBool;
		
		
	}
	
	public void loginButtonAction(String userName, int password)
	{
		MsgBean msgbean = new MsgBean();
		if(this.isUserReal(userName, password))
		{
			msgbean.showMessage("Welcome to the database");
		}
		else
			msgbean.showMessage("Either your username or password is wrong or you don't belong to here");
	}
	
	

}
