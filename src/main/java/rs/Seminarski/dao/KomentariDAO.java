package rs.Seminarski.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class KomentariDAO {
	private DataSource  ds;
	
	private static String INSERTCOMMENTS= "INSERT into komentari (id_korisnika, id_proizvoda, komentar) values (?,?,?)";
	
	public KomentariDAO(){
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
	
	public void insertComments (int idKorisnika, int idProizvoda, String komentar) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con=ds.getConnection();
			pstm=con.prepareStatement(INSERTCOMMENTS);
		
			pstm.setInt(1, idKorisnika);
			pstm.setInt(2, idProizvoda);
			pstm.setString(3, komentar);
		
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
