package rs.Seminarski.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import rs.Seminarski.model.KupljeniProizvodi;

public class KupljeniProizvodiDAO {
	private DataSource ds;
	
	private static String INSERTPURCHASEDITEMS = "INSERT INTO kupljeniproizvodi (id_kupovine, id_proizvoda) values (?,?)";
	
	private static String GETPURCHASEDITEMS = "SELECT * FROM kupljeniproizvodi WHERE id = ?";

	public KupljeniProizvodiDAO(){
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
	
	public void insertPurchasedItems (int idKupovine, int idProizvoda) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			 
			con=ds.getConnection();
			pstm=con.prepareStatement(INSERTPURCHASEDITEMS);
			
			pstm.setInt(1, idKupovine);
			pstm.setInt(2, idProizvoda);
			
			pstm.execute();
			
		} catch (Exception e) {
			
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
}

	public ArrayList<KupljeniProizvodi> getPurchasedItems (int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<KupljeniProizvodi> lo = new ArrayList<KupljeniProizvodi>();
		KupljeniProizvodi t=null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
				
       try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETPURCHASEDITEMS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.execute();
			
			rs = pstm.getResultSet();

			while(rs.next()){ 
				t = new KupljeniProizvodi();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
			
				t.setId(rs.getInt("id"));
				t.setId_kupovine(rs.getInt("id_kupovine"));
				t.setId_proizvoda(rs.getInt("id_proizvoda"));
	
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
