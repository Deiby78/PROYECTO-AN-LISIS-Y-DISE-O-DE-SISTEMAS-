package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class connection implements parametrizable{
	
	private Connection conn= null;
	protected Statement stmt = null;
	protected ResultSet res = null;
	
	public Connection openConn() {
		try {
			conn = DriverManager.getConnection(getURL(), user, psw);
			if(conn!=null) {
				System.out.println("conexión exitosa");
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "CONEXTION:"+e);
		}
		return conn;
	}
	
	public void closeConn() {
		if(conn!=null) {
			try {
				conn.close();
				if(stmt!=null){
					stmt.close();
				}
				System.out.println("CONNEXION CERRADA");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

}
