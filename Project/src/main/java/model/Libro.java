package model;

public class Libro extends Articolo{
	private String titolo;
	private long isbn;
	private String editore;
	private int anno;
	private int pagine;
	private String descrizione;
	private Genere genere;
	private Autore autore;
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public long getIsbn() {
		return isbn;
	}
	
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	
	public String getEditore() {
		return editore;
	}
	
	public void setEditore(String editore) {
		this.editore = editore;
	}
	
	public int getAnno() {
		return anno;
	}
	
	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	public int getPagine() {
		return pagine;
	}
	
	public void setPagine(int pagine) {
		this.pagine = pagine;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Genere getGenere() {
		return genere;
	}
	
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	
	public Autore getAutore() {
		return autore;
	}
	
	public void setAutore(Autore autore) {
		this.autore = autore;
	}
}
