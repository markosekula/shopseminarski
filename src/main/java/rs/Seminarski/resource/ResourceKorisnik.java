package rs.Seminarski.resource;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rs.Seminarski.token.Token;

import rs.Seminarski.dao.KorisnikDAO;
import rs.Seminarski.model.Credentials;
import rs.Seminarski.model.Korisnik;
import rs.Seminarski.service.ServiceKorisnik;

@Path("/korisnik")
public class ResourceKorisnik {
	
	KorisnikDAO daok =  new KorisnikDAO (); 
	ServiceKorisnik servicekorisnik = new ServiceKorisnik();
	
	@POST
	@Path ("/exist")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Token existUser (Credentials k){
		if (daok.daLiPostojiKorisnik(k.getEmail(), k.getPass())!=null) {
			Token t = new Token (Jwts.builder()
					.setSubject(String.valueOf(daok.daLiPostojiKorisnik(k.getEmail(), k.getPass()).getId()))
					.claim("admin",String.valueOf(daok.daLiPostojiKorisnik(k.getEmail(), k.getPass()).getAdmin()) )
					.signWith(SignatureAlgorithm.HS256, "kljuc")
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime()+15*60L*1000))
					.compact(), daok.daLiPostojiKorisnik(k.getEmail(), k.getPass()).getId(), daok.daLiPostojiKorisnik(k.getEmail(), k.getPass()).getAdmin());
			
			return t;
			
		}
		
		return null;
	} 
	
	@GET
	@Path ("/get")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Korisnik>selectLogin (){
		return daok.selectLogin();
	}
	
	@POST
	@Path ("/existkorisnik")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean existKorisnik(Korisnik k) {
		if (servicekorisnik.existEmail(k.getEmail())) {
			servicekorisnik.addKorisnik(k);	
			return true;
		}
		return false;	
	}
	
	@GET
	@Path ("/getusers")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Korisnik>getAllUsers (){
		return daok.getAllUsers();
	}
	
	@DELETE
	@Path("/delete/{delete}")
	public void deleteUser (@PathParam ("delete")int id) {
		daok.deleteUser(id);
	}
	
	
	
}
