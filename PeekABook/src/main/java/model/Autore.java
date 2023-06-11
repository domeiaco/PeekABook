package model;

import java.util.ArrayList;

public class Autore{
	private int codice;
	private String nome;
	private String cognome;
	private ArrayList<Libro> libri;
	
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
	
	public ArrayList<Libro> getLibri(){
		return libri;
	}
	
	public void setLibri(ArrayList<Libro> libri) {
		this.libri = libri;
	}
}