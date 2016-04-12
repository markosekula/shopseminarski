package rs.Seminarski.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import rs.Seminarski.model.AdminKorpa;

public class AdminKorpaDAO {
	private DataSource ds;
	
	private static String GETALLDATA= "SELECT korisnik.email, proizvod.vrsta, proizvod.proizvodjac, proizvod.model, proizvod.cena AS pojedinacna_cena, kupljeniproizvodi.kolicina, kupovina.ukupna_cena "
			+ "FROM kupovina "
			+ "JOIN korisnik ON kupovina.korisnik_id = korisnik.id "
			+ "JOIN kupljeniproizvodi ON kupovina.id = kupljeniproizvodi.id_kupovine "
			+ "JOIN proizvod ON proizvod.id = kupljeniproizvodi.id_proizvoda "
			+ "WHERE kupovina.id=? ";
	
	private static String DELETEPURCHASE = "DELETE FROM kupovina WHERE id=?";
	
	public AdminKorpaDAO(){
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
	
	public ArrayList<AdminKorpa> getAllData(int id_kupovine) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<AdminKorpa> lak = new ArrayList<AdminKorpa>();
		AdminKorpa ak = null;
		
		try {
			con = ds.getConnection();
			pstm = (PreparedStatement) con.prepareStatement(GETALLDATA); 

			pstm = con.prepareStatement(GETALLDATA);
			
			pstm.setInt(1, id_kupovine);
			pstm.execute();
			
			rs = pstm.getResultSet();
			
			while(rs.next()){ 
	
				ak = new AdminKorpa();
				
				ak.setEmail(rs.getString("email"));
				ak.setVrsta(rs.getString("vrsta"));
				ak.setProizvodjac(rs.getString("proizvodjac"));
				ak.setModel(rs.getString("model"));
				ak.setCena(rs.getBigDecimal("pojedinacna_cena"));
				ak.setKolicina(rs.getInt("kolicina"));
				ak.setUkupna_cena(rs.getBigDecimal("ukupna_cena"));
				
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
	
	public void deletePurchase(int id) {
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			
			con=ds.getConnection();
			pstm=con.prepareStatement(DELETEPURCHASE);
			
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
