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
	
	private static String INSERTPURCHASEDITEMS = "INSERT INTO kupljeniproizvodi (id_kupovine, id_proizvoda, kolicina) values (?,?,?)";

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
	
	public void insertKupljeniProzivodi (int idKupovine, int idProizvoda, int kolicina) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			 
			con=ds.getConnection();
			pstm=con.prepareStatement(INSERTPURCHASEDITEMS);
			
			pstm.setInt(1, idKupovine);
			pstm.setInt(2, idProizvoda);
			pstm.setInt(3, kolicina);
			
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
