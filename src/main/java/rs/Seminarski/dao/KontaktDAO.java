package rs.Seminarski.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import rs.Seminarski.model.Kontakt;

public class KontaktDAO {
	private DataSource ds;
	
	private static String SELECTALLMESSAGE = "SELECT * FROM kontakt";
	private static String INSERTKONTAKT = "INSERT INTO kontakt (ime, email, poruka) values (?,?,?)";
	private static String DELETEKONTAKT = "DELETE FROM kontakt WHERE id=?";
	
	public KontaktDAO(){
		try {
			InitialContext cxt = new InitialContext();
			if ( cxt == null ) { 
			} 
			ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
			if ( ds == null ) { 
			} 		
			} catch (NamingException e) {
			}
		}
	
	public ArrayList<Kontakt> selectMessage(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<Kontakt> lo = new ArrayList<Kontakt>();
		Kontakt a = null;
				
          try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTALLMESSAGE);

			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
	
				a = new Kontakt();

				a.setId(rs.getInt("id"));
				a.setIme(rs.getString("ime"));		
				a.setEmail(rs.getString("email"));
				a.setPoruka(rs.getString("poruka"));
				
				lo.add(a);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lo; 
	}

	public void insertKontakt (Kontakt k) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		
		try {
			con=ds.getConnection();
			pstm=con.prepareStatement(INSERTKONTAKT);
			
			pstm.setString(1, k.getIme());
			pstm.setString(2, k.getEmail());
			pstm.setString(3, k.getPoruka());
			
			pstm.execute();
			
		} catch (Exception e) {
			
			
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
}

	public void deleteKontakt(int id) {
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			
			con=ds.getConnection();
			pstm=con.prepareStatement(DELETEKONTAKT);
			
			pstm.setInt(1, id);
			pstm.execute();

			
		} catch (Exception e) {
			
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
}
