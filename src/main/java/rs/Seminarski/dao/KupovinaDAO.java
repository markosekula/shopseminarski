package rs.Seminarski.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import rs.Seminarski.model.KupljeniProizvodi;
import rs.Seminarski.model.Kupovina;


public class KupovinaDAO {
	private DataSource ds;
	
	private static String INSERTKUPOVINA = "INSERT INTO kupovina (korisnik_id, ukupna_cena) values (?,?)";
	
	private static String SELECTTIME = "SELECT * FROM kupovina ORDER BY vreme DESC";
	
	private static String GETKUPOVINA = "SELECT * FROM kupovina WHERE id = ?";

	public KupovinaDAO(){
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
	
	public int insertKupovina (BigDecimal cena, int  idKorisnika) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		int idKupovine = -1;
		try {
			con=ds.getConnection();
			pstm=con.prepareStatement(INSERTKUPOVINA,Statement.RETURN_GENERATED_KEYS);
		
			pstm.setInt(1, idKorisnika);
			pstm.setBigDecimal(2, cena);
		
			pstm.execute();
			
			rs = pstm.getGeneratedKeys();
			rs.next();
			
			idKupovine = rs.getInt(1);
			
		} catch (Exception e) {
				
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idKupovine;
		
}
	
	public ArrayList<Kupovina> getKupovine (int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Kupovina> lo = new ArrayList<Kupovina>();
		Kupovina t=null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
				
       try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETKUPOVINA);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.execute();
			
			rs = pstm.getResultSet();

			while(rs.next()){ 
				t = new Kupovina();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
			
				t.setId(rs.getInt("id"));
				
				lo.add(t);
			
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
	
	public ArrayList<Kupovina> getTime (){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Kupovina> lo = new ArrayList<Kupovina>();
		Kupovina t=null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
				
       try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTTIME);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.execute();
			
			rs = pstm.getResultSet();

			while(rs.next()){ 
				t = new Kupovina();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
			
				t.setId(rs.getInt("id"));
				t.setUkupna_cena(rs.getBigDecimal("ukupna_cena"));
				t.setVreme(rs.getTimestamp("vreme"));
	
				lo.add(t);
			
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

}
