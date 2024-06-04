package model;

public class Articolo {
	private int codice;
	private String nome;
	private double prezzo;
	private int quantita;
	private String pathImg;
	private int valutazione;

	
	public int getCodice() {
		return codice;
	}
	
	public void setCodice(int codice) {
		this.codice=codice;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }
    
    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }
}
