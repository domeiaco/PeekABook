package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Utente {
	private int id;
	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
	private String via;
	private int civico;
	private String citta;
	private int cap;
	private int admin;
	private List<Ordine> ordini;
	private Carrello carrello;
	
	public Carrello getCarrello() {
		return carrello;
	}
	
	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}
	
	public List<Ordine> getOrdini(){
		return ordini;
	}
	
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(password.getBytes(StandardCharsets.UTF_8));
			this.password = String.format("%0128x", new BigInteger(1,digest.digest()));
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public int getCivico() {
		return civico;
	}
	
	public void setCivico(int civico) {
		this.civico = civico;
	}
	
	public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
