package fr.Mathis_Bruel.spacecube.spacecubeapi.DataBase;

import java.sql.SQLException;

public class DbManageur {
	
	private Connection connectionmysql;
	
	public DbManageur() {
		this.connectionmysql = new Connection(new DbCredentials("192.168.11.206", "SpaceCube", "YJehY(SYm13Bxpxx", "SpaceCube", 3306));
	}
	
	public Connection getConnection() {
		return connectionmysql;
	}

	public void close() {
		try {
			this.connectionmysql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
