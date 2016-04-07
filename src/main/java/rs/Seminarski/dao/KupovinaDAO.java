package rs.Seminarski.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;


public class KupovinaDAO {
	private DataSource ds;
	
	private static String INSERTKUPOVINA = "INSERT INTO kupovina (korisnik_id, ukupna_cena) values (?,?)";

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

}
