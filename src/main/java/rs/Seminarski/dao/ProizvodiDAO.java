package rs.Seminarski.dao;

import rs.Seminarski.model.Proizvod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.ArrayList;


public class ProizvodiDAO {
 private DataSource ds;

//DEFINICIJA KONEKCIONIH STRINGOVA
 
 private static String CHOOSEITEM = "SELECT * FROM proizvod WHERE vrsta = ?";
 private static String RANDOMITEM = "SELECT * FROM proizvod ORDER BY RAND() LIMIT 15";
 private static String GETBYID = "SELECT * FROM proizvod WHERE id=?";
 private static String ITEMACTION = "SELECT * FROM proizvod WHERE (akcija=1) ORDER BY RAND() LIMIT 15 ";
 private static String INSERTITEM = "INSERT INTO proizvod (vrsta, proizvodjac, model, cena, garancija, slika, akcija, tip, kapacitet, socket, dijagonala, takt) values (?,?,?,?,?,?,?,?,?,?,?,?)";
 private static String UPDATEITEM = "UPDATE proizvod SET vrsta=?, proizvodjac=?, model=?, cena=?, garancija=?, slika=?, akcija=?, tip=?, kapacitet=?, socket=?, dijagonala=?, takt=? WHERE id=? ";
 private static String RETURNTYPE = "SELECT DISTINCT vrsta FROM proizvod";
 private static String DELETEITEM = "DELETE FROM proizvod WHERE id=?";
 
 
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE � UVEK ISTO
	public ProizvodiDAO(){
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
	
	public ArrayList<Proizvod> getChooseItem (String vrsta){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Proizvod> lo = new ArrayList<Proizvod>();
		Proizvod t=null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
				
       try {
			con = ds.getConnection();
			pstm = con.prepareStatement(CHOOSEITEM);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, vrsta);
			pstm.execute();
			
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				t = new Proizvod();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
			
				t.setId(rs.getInt("id"));
				t.setVrsta(rs.getString("vrsta"));
				t.setProizvodjac(rs.getString("proizvodjac"));
				t.setGarancija(rs.getString("garancija"));
				t.setModel(rs.getString("model"));
				t.setSlika(rs.getString("slika"));
				t.setTip(rs.getString("tip"));
				t.setKapacitet(rs.getString("kapacitet"));
				t.setSocket(rs.getString("socket"));
				t.setTakt(rs.getString("takt"));
				t.setCena(rs.getBigDecimal("cena"));
				t.setDijagonala(rs.getBigDecimal("dijagonala"));
				t.setAkcija(rs.getByte("akcija"));
				
				lo.add(t);
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
			}

		} catch (SQLException e) {	//System.out.println(credentials.getEmail() +" "+ credentials.getPassword());
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lo; 

	}

	public Proizvod getById (int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Proizvod t=null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
				
       try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETBYID);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setInt(1, id);
			pstm.execute();
			
			rs = pstm.getResultSet();

			if(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				t = new Proizvod();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
			
				t.setId(rs.getInt("id"));
				t.setVrsta(rs.getString("vrsta"));
				t.setProizvodjac(rs.getString("proizvodjac"));
				t.setGarancija(rs.getString("garancija"));
				t.setModel(rs.getString("model"));
				t.setSlika(rs.getString("slika"));
				t.setTip(rs.getString("tip"));
				t.setKapacitet(rs.getString("kapacitet"));
				t.setSocket(rs.getString("socket"));
				t.setTakt(rs.getString("takt"));
				t.setCena(rs.getBigDecimal("cena"));
				t.setDijagonala(rs.getBigDecimal("dijagonala"));
				t.setAkcija(rs.getByte("akcija"));
				
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
			}

		} catch (SQLException e) {	//System.out.println(credentials.getEmail() +" "+ credentials.getPassword());
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return t; 

	}
	
	public ArrayList<Proizvod> selectRandom(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Proizvod> lo = new ArrayList<Proizvod>();
		Proizvod t = null;
				
          try {
			con = ds.getConnection();
			pstm = con.prepareStatement(RANDOMITEM);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ 
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				t = new Proizvod();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				t.setId(rs.getInt("id"));
				t.setVrsta(rs.getString("vrsta"));
				t.setProizvodjac(rs.getString("proizvodjac"));
				t.setGarancija(rs.getString("garancija"));
				t.setModel(rs.getString("model"));
				t.setSlika(rs.getString("slika"));
				t.setTip(rs.getString("tip"));
				t.setKapacitet(rs.getString("kapacitet"));
				t.setSocket(rs.getString("socket"));
				t.setTakt(rs.getString("takt"));
				t.setCena(rs.getBigDecimal("cena"));
				t.setDijagonala(rs.getBigDecimal("dijagonala"));
				t.setAkcija(rs.getByte("akcija"));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lo.add(t);
			
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lo; 
	}

	public ArrayList<Proizvod> selectRandomOnAction(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Proizvod> lo = new ArrayList<Proizvod>();
		Proizvod t = null;
				
          try {
			con = ds.getConnection();
			pstm = con.prepareStatement(ITEMACTION);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				t = new Proizvod();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				t.setId(rs.getInt("id"));
				t.setVrsta(rs.getString("vrsta"));
				t.setProizvodjac(rs.getString("proizvodjac"));
				t.setGarancija(rs.getString("garancija"));
				t.setModel(rs.getString("model"));
				t.setSlika(rs.getString("slika"));
				t.setTip(rs.getString("tip"));
				t.setKapacitet(rs.getString("kapacitet"));
				t.setSocket(rs.getString("socket"));
				t.setTakt(rs.getString("takt"));
				t.setCena(rs.getBigDecimal("cena"));
				t.setDijagonala(rs.getBigDecimal("dijagonala"));
				t.setAkcija(rs.getByte("akcija"));
				
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lo.add(t);
			
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lo; 
	}
	
	public void insertItem (Proizvod p) {
			Connection con = null;
			PreparedStatement pstm = null;
		
			try {
				con=ds.getConnection();
				pstm=con.prepareStatement(INSERTITEM);
				
				pstm.setString(1, p.getVrsta());
				pstm.setString(2, p.getProizvodjac());
				pstm.setString(3, p.getModel());
				pstm.setBigDecimal(4, p.getCena());
				pstm.setString(5, p.getGarancija());
				pstm.setString(6, p.getSlika());
				pstm.setByte(7, p.getAkcija());
				pstm.setString(8, p.getTip());
				pstm.setString(9, p.getKapacitet());
				pstm.setString(10, p.getSocket());
				pstm.setBigDecimal(11, p.getDijagonala());
				pstm.setString(12, p.getTakt());
				
				pstm.execute();
				
			} catch (Exception e) {
				
				
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
	}
	
	public void updateItem (Proizvod pro ){
		Connection con = null;
		PreparedStatement pstm = null;
		
       try {
			con = ds.getConnection();
			pstm = con.prepareStatement(UPDATEITEM);
			
			pstm.setString(1, pro.getVrsta());
			pstm.setString(2, pro.getProizvodjac());
			pstm.setString(3, pro.getModel());
			pstm.setBigDecimal(4, pro.getCena());
			pstm.setString(5, pro.getGarancija());
			pstm.setString(6, pro.getSlika());
			pstm.setByte(7, pro.getAkcija());
			pstm.setString(8, pro.getTip());
			pstm.setString(9, pro.getKapacitet());
			pstm.setString(10, pro.getSocket());
			pstm.setBigDecimal(11, pro.getDijagonala());
			pstm.setString(12, pro.getTakt());
			pstm.setInt(13, pro.getId());
			
			pstm.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
	
	}
	
	public ArrayList<Proizvod> returnDistinctType(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ArrayList<Proizvod> lo = new ArrayList<Proizvod>();
		Proizvod t = null;
				
          try {
			con = ds.getConnection();
			pstm = con.prepareStatement(RETURNTYPE);
			
			pstm.execute();

			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
			
				t = new Proizvod();
				t.setVrsta(rs.getString("vrsta"));
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
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return lo; 
	}
	
	public void deleteItem(int id) {
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			
			con=ds.getConnection();
			pstm=con.prepareStatement(DELETEITEM);
			
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
	 
	
	
	
	



	



