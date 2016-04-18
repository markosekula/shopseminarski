package rs.Seminarski.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import rs.Seminarski.model.KorisnikKomentari;

public class KorisnikKomentariDAO {
	private DataSource ds;
	
	private static String GETCOMMENTS= "SELECT korisnik.ime, korisnik.prezime, komentari.komentar, komentari.vreme "
			+ "FROM komentari "
			+ "JOIN korisnik ON korisnik.id = komentari.id_korisnika "
			+ "JOIN proizvod ON proizvod.id = komentari.id_proizvoda "
			+ "WHERE proizvod.id = ? "
			+ "ORDER BY komentari.vreme ASC"; 
	
	private static String ADMINGETDISTINCTCOMMENTS= "SELECT DISTINCT  komentari.id_proizvoda, proizvod.vrsta, proizvod.model "
			+ "FROM komentari "
			+ "JOIN proizvod ON proizvod.id=komentari.id_proizvoda  "
			+ "ORDER BY komentari.vreme DESC";
	
	private static String ADMINGETALLCOMMENTS = "SELECT komentari.id AS id_komentara, korisnik.ime, korisnik.prezime, komentari.komentar, komentari.id_proizvoda, proizvod.vrsta, proizvod.proizvodjac, proizvod.model, komentari.vreme "
			+ "FROM komentari "
			+ "JOIN proizvod ON proizvod.id=komentari.id_proizvoda "
			+ "JOIN korisnik ON korisnik.id=komentari.id_korisnika "
			+ "WHERE komentari.id_proizvoda=? "
			+ "ORDER BY komentari.vreme ASC";

	private static String DELETEALLCOMMENTS = "DELETE FROM komentari WHERE id_proizvoda=?";
	
	private static String COUNTCOMMENTS = "SELECT COUNT(id) AS id_komentara FROM komentari WHERE id_proizvoda=?";
	
	private static String DELETEONECOMMENT = "DELETE FROM komentari WHERE id=?";
	
	
	public KorisnikKomentariDAO(){
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
	
	public ArrayList<KorisnikKomentari> getComments(int id_proizvoda) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<KorisnikKomentari> lak = new ArrayList<KorisnikKomentari>();
		KorisnikKomentari ak = null;
		
		try {
			con = ds.getConnection();
		
			pstm = con.prepareStatement(GETCOMMENTS);
			
			pstm.setInt(1, id_proizvoda);
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			while(rs.next()){ 
	
				ak = new KorisnikKomentari();
				
				ak.setIme(rs.getString("ime"));
				ak.setPrezime(rs.getString("prezime"));
				ak.setKomentar(rs.getString("komentar"));
				ak.setVreme(rs.getTimestamp("vreme"));
				
				lak.add(ak);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lak;
	}

	public ArrayList<KorisnikKomentari> getAdminDistinctComments() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<KorisnikKomentari> lak = new ArrayList<KorisnikKomentari>();
		KorisnikKomentari ak = null;
		
		try {
			con = ds.getConnection();
		
			pstm = con.prepareStatement(ADMINGETDISTINCTCOMMENTS);
			
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			while(rs.next()){ 
	
				ak = new KorisnikKomentari();
				
				ak.setId_proizvoda(rs.getInt("id_proizvoda"));
				ak.setVrsta(rs.getString("vrsta"));
				ak.setModel(rs.getString("model"));
				
				//setovanje count-a
				ak.setCount(getCount(ak.getId_proizvoda()));
	
				lak.add(ak);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lak;
		
		
	}

	public void deleteComments(int id) {
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			
			con=ds.getConnection();
			pstm=con.prepareStatement(DELETEALLCOMMENTS);
			
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

	public int getCount (int  id) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		/*KorisnikKomentari ak =  new KorisnikKomentari();*/
		
		int count= 0;
		
		try {
			con=ds.getConnection();
			pstm=con.prepareStatement(COUNTCOMMENTS);
		
			pstm.setInt(1, id);
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			if(rs.next()){ 	
				count=rs.getInt("id_komentara");
				/*ak.setCount(count); */
			}
			
		} catch (Exception e) {
				
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*return ak;*/
		return count; 
		
}

	public ArrayList<KorisnikKomentari> getAllComments(int id_proizvoda) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<KorisnikKomentari> lak = new ArrayList<KorisnikKomentari>();
		KorisnikKomentari ak = null;
		
		try {
			con = ds.getConnection();
		
			pstm = con.prepareStatement(ADMINGETALLCOMMENTS);
			
			pstm.setInt(1, id_proizvoda);
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			while(rs.next()){ 
	
				ak = new KorisnikKomentari();
				
				ak.setId(rs.getInt("id_komentara"));
				ak.setIme(rs.getString("ime"));
				ak.setPrezime(rs.getString("prezime"));
				ak.setKomentar(rs.getString("komentar"));
				ak.setId_proizvoda(rs.getInt("id_proizvoda"));
				ak.setVrsta(rs.getString("vrsta"));
				ak.setProizvodjac(rs.getString("proizvodjac"));
				ak.setModel(rs.getString("model"));
				ak.setVreme(rs.getTimestamp("vreme"));
				
				lak.add(ak);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lak;
	}

	public void deleteOneComment(int id) {
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			
			con=ds.getConnection();
			pstm=con.prepareStatement(DELETEONECOMMENT);
			
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
