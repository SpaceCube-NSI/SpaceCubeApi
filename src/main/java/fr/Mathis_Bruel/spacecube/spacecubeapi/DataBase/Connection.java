package fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class Connection {
	
	private DbCredentials dbCredentials;
	private java.sql.Connection connection;
	
	public Connection(DbCredentials dbCredentials) {
		this.dbCredentials = dbCredentials;
		this.connect();
		
		
	}
	
	private void connect() {
		
		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				this.connection = DriverManager.getConnection(this.dbCredentials.toURI(), this.dbCredentials.getUser(), this.dbCredentials.getPass());
				Logger.getLogger("minecraft").info("successfully connected to DB.");
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void close() throws SQLException {
		
		if(this.connection != null) {
			if(!this.connection.isClosed()){

					this.connection.close();
				
			}
		}
		
	}
	
	public java.sql.Connection getConnection() throws SQLException {
		
		if(this.connection != null) {
			if(!this.connection.isClosed()) {
				return (java.sql.Connection) this.connection;
			}
		}
		connect();
		return (java.sql.Connection) this.connection;
		
	}

	

	


	
	
}
