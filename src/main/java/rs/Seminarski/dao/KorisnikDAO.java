package rs.Seminarski.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import rs.Seminarski.model.Credentials;
import rs.Seminarski.model.Korisnik;


import java.util.ArrayList;

public class KorisnikDAO {
	private DataSource ds;
	
	private static String EXISTLOGIN = "SELECT * FROM korisnik where email = ? and pass=?";
	private static String SELECTLOGIN = "SELECT * FROM korisnik";
	private static String INSERTKLIENT = "INSERT INTO korisnik (ime, prezime, email, pass) VALUES(?,?,?,?)";
	private static String EXISTKLIENT = "SELECT * FROM korisnik where email = ? ";
	
	private static String EXISTUSERFORCHANGEPASSWORD = "SELECT * FROM korisnik where email = ? ";
	private static String CHANGEPASSWORD = "UPDATE korisnik SET pass=? WHERE email=?";
	
	private static String EXISTUSER = "SELECT * FROM korisnik where email=? and pass=?";
	private static String GETALLUSERS =  "SELECT * FROM korisnik WHERE admin=0 ";
	private static String DELETEUSER = "DELETE FROM korisnik WHERE id=?";
	
	public KorisnikDAO(){
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
	
	public boolean existUser(String email, String pass){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
	
		Korisnik a = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(EXISTLOGIN);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, email);
			pstm.setString(2, pass);
			pstm.execute();

//****POCETAK	AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if AKO UPIT VRACA JEDAN REZULTAT
				a = new Korisnik();
				a.setId(rs.getInt("id"));
				a.setIme(rs.getString("ime"));
				a.setPrezime(rs.getString("prezime"));
				a.setEmail(rs.getString("email"));
				a.setPass(rs.getString("pass"));
				a.setAdmin(rs.getByte("admin"));
				
				return true;
			}
//****KRAJ		KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}

	public Korisnik daLiPostojiKorisnik(String email, String pass){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Korisnik k = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(EXISTUSER);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, email);
			pstm.setString(2, pass);
			pstm.execute();

//****POCETAK	AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

				if(rs.next()){
					// if AKO UPIT VRACA JEDAN REZULTAT
				k = new Korisnik();
				
				k.setId(rs.getInt("id"));
				k.setIme(rs.getString("ime"));
				k.setPrezime(rs.getString("prezime"));
				k.setEmail(rs.getString("email"));
				k.setPass(rs.getString("pass"));
				k.setAdmin(rs.getByte("admin"));
				
			
			}
//****KRAJ		KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
	}
	
	public ArrayList<Korisnik> selectLogin(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Korisnik> lo = new ArrayList<Korisnik>();
		Korisnik a = null;
				
          try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTLOGIN);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				a = new Korisnik();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				a.setId(rs.getInt("id"));
				a.setIme(rs.getString("ime"));		
				a.setPrezime(rs.getString("prezime"));
				a.setEmail(rs.getString("email"));
				a.setPass(rs.getString("pass"));
				
				a.setAdmin(rs.getByte("admin"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lo.add(a);
			
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
	
	public void insertKlient (Korisnik k) {

			Connection con = null;
			PreparedStatement pstm = null;
			
			
			try {
				con=ds.getConnection();
				pstm=con.prepareStatement(INSERTKLIENT);
				
			
				pstm.setString(1, k.getIme());
				pstm.setString(2, k.getPrezime());
				pstm.setString(3, k.getEmail());
				pstm.setString(4, k.getPass());
				
				
				pstm.execute();
				
			} catch (Exception e) {
				
				
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	
	public boolean existUserWithEmail(String email){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
	
		Korisnik a = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(EXISTKLIENT);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			pstm.setString(1, email);
			
			pstm.execute();

//****POCETAK	AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			if(rs.next()){ // if AKO UPIT VRACA JEDAN REZULTAT
				a = new Korisnik();
				a.setId(rs.getInt("id"));
				a.setIme(rs.getString("ime"));
				a.setPrezime(rs.getString("prezime"));
				a.setEmail(rs.getString("email"));
				a.setPass(rs.getString("pass"));
				a.setAdmin(rs.getByte("admin"));
				
				return true;
			}
//****KRAJ		KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}

	public ArrayList<Korisnik> getAllUsers(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Korisnik> lo = new ArrayList<Korisnik>();
		Korisnik a = null;
				
          try {
			con = ds.getConnection();
			pstm = con.prepareStatement(GETALLUSERS);

			// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
			
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				// KREIRANJE INSTANCE KLASE PREKO PODRAZUMEVANOG KONSTRUKTORA
				a = new Korisnik();
				// SET-OVANJE SVIH ATRIBUTA KLASE SA ODGOVARAJUCIM VREDNOSTIMA IZ RESULTSET-A rs
				a.setId(rs.getInt("id"));
				a.setIme(rs.getString("ime"));		
				a.setPrezime(rs.getString("prezime"));
				a.setEmail(rs.getString("email"));
				a.setPass(rs.getString("pass"));
				
				a.setAdmin(rs.getByte("admin"));
				// DODAVANJE INSTANCE U LISTU AKO METODA VRACA LISTU, AKO NE VRACA ONDA NE TREBA 
				lo.add(a);
			
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

	public void deleteUser(int id) {
		Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			
			con=ds.getConnection();
			pstm=con.prepareStatement(DELETEUSER);
			
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

	public String existUserWithEmailForChangePassword(String email){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		Korisnik a = null;
				
            try {
			con = ds.getConnection();
			pstm = con.prepareStatement(EXISTUSERFORCHANGEPASSWORD);
 
			pstm.setString(1, email);
			
			pstm.execute();

			rs = pstm.getResultSet();

			if(rs.next()){
				a = new Korisnik();
				a.setId(rs.getInt("id"));
				a.setIme(rs.getString("ime"));
				a.setPrezime(rs.getString("prezime"));
				a.setEmail(rs.getString("email"));
				a.setPass(rs.getString("pass"));
				a.setAdmin(rs.getByte("admin"));
				
				return "";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	public void changePassword(Korisnik k){
		Connection con = null;
		PreparedStatement pstm = null;
		
       try {
			con = ds.getConnection();
			pstm = con.prepareStatement(CHANGEPASSWORD);
			
			pstm.setString(1, k.getPass());
			pstm.setString(2, k.getEmail());
		
			
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

}





      