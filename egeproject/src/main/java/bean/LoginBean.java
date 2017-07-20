package bean;

import egeproject.ConnectionClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import java.io.IOException;
import java.sql.Connection;
import model.User;

@ManagedBean(name = "LoginBean")
@RequestScoped


public class LoginBean {

	//private User user = new User();
	
	ConnectionClass connectionClass = new ConnectionClass();
	

	public Connection getDBConnection() {
		Connection connection = null;
		connectionClass.setConnection();
		connection = connectionClass.getConnection();
		return connection;
	}

	public Boolean isUserReal(User user) {
		Boolean resultBool = false;
		Connection connection = getDBConnection();
	
		String selectionSql = "SELECT USER_NAME, USER_PASSWORD FROM User_Table WHERE USER_NAME = ? AND USER_PASSWORD = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectionSql);
		    preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());

			ResultSet set = preparedStatement.executeQuery();
			if (set.next())
				resultBool = true;
			
			connection.close();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
			System.out.println(e);
		}

		return resultBool;

	}

	public void loginButtonAction(User user) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		MsgBean msgbean = new MsgBean();
		if (this.isUserReal(user)) {
			

			try {
				externalContext.redirect("Default.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else {
			msgbean.showMessage("Either your username or password is wrong or you don't belong to here");
		}
	}

}
