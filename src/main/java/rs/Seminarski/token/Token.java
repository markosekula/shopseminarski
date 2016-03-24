package rs.Seminarski.token;

public class Token {

	private String token;
	private int id;
	private Byte admin;

	public Token() {
		super();	
	}

	public Token(String token, int id, Byte admin) {
		super();
		this.token = token;
		this.id = id;
		this.admin = admin;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Byte getAdmin() {
		return admin;
	}


	public void setAdmin(Byte admin) {
		this.admin = admin;
	}
	

	

	
	
}
