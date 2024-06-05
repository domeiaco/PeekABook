package model;

import java.util.List;

public class Autore{
	private int codice;
	private String nome;
	private String cognome;
	private List<Libro> libri;
	
	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int codice) {
		this.codice = codice;
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
	
	public List<Libro> getLibri(){
		return libri;
	}
	
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
}