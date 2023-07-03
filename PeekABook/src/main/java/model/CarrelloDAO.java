package model;

import java.sql.*;
import java.util.*;

public class CarrelloDAO {
	
	public Carrello doRetrieveCarrelloVuotoByUtente(Utente u) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Carrello c WHERE c.utente=?");
			ps.setInt(1, u.getId());
			
			ResultSet rs = ps.executeQuery();
			Carrello carrello = new Carrello();
			if(rs.next()) {
				carrello.setUtente(u);
				carrello.setTotale(rs.getDouble("c.totale"));
				carrello.setNumeroArticoli(rs.getInt("c.numeroArticoli"));
			}
			return carrello;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Carrello doRetrieveCarrelloByUtente(Utente u) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Carrello c, ArticoloSelezionato as, Articolo a WHERE c.utente=as.utente AND as.articolo=a.codice AND c.utente=?");
			ps.setInt(1, u.getId());
			
			ResultSet rs = ps.executeQuery();
			Carrello carrello = new Carrello();
			carrello.setUtente(u);
			carrello.setTotale(0);
			carrello.setNumeroArticoli(0);
			LinkedHashMap<Articolo, Integer> articoli = new LinkedHashMap<>();
			Articolo a;
			int flag=1;
			
			while(rs.next()) {
				if(flag==1) {
					carrello.setUtente(u);
					carrello.setTotale(rs.getDouble("c.totale"));
					carrello.setNumeroArticoli(rs.getInt("c.numeroArticoli"));
					flag=0;
				}
				a=new Articolo();
				a.setCodice(rs.getInt("a.codice"));
				a.setNome(rs.getString("a.nome"));
				a.setPrezzo(rs.getDouble("a.prezzo"));
				a.setQuantita(rs.getInt("a.quantita"));
				a.setPathImg(rs.getString("a.copertina"));
				
				articoli.put(a, rs.getInt("as.quantita"));
			}
			
			carrello.setArticoli(articoli);
			return carrello;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doUpdateCarrello(Carrello carrello) {
		try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE Carrello SET totale=?, numeroArticoli=? WHERE utente=?");

            ps.setDouble(1, carrello.getTotale());
            ps.setInt(2, carrello.getNumeroArticoli());
            ps.setInt(3, carrello.getUtente().getId());
            
            int x = ps.executeUpdate();
            
            ps= con.prepareStatement("DELETE FROM ArticoloSelezionato WHERE utente=?");
            ps.setInt(1,carrello.getUtente().getId());
            
            x+=ps.executeUpdate();
            
            LinkedHashMap<Articolo, Integer> articoli = carrello.getArticoli();
            Set<Articolo> keys = articoli.keySet();
            
            for(Articolo key : keys) {
            	ps = con.prepareStatement("INSERT INTO ArticoloSelezionato(utente,articolo,quantita) VALUES(?,?,?)");
            	ps.setInt(1, carrello.getUtente().getId());
            	ps.setInt(2, key.getCodice());
            	ps.setInt(3, articoli.get(key));
            	x+=ps.executeUpdate();
            }

            return x>0 ? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
	}
	
	public int doCreateCarrello(Utente u) {
		try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("INSERT INTO Carrello(utente,totale,numeroArticoli) VALUES (?,?,?)");
            ps.setDouble(2, 0);
            ps.setInt(1, u.getId());
            ps.setInt(3, 0);
            
            int x = ps.executeUpdate();

            return x>0 ? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
	}
	
	public int doClearCarrello(Utente u) {
		try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE Carrello SET totale=?, numeroArticoli=? WHERE utente=?");

            ps.setDouble(1, 0);
            ps.setInt(2, 0);
            ps.setInt(3, u.getId());
            
            int x = ps.executeUpdate();
            
            ps= con.prepareStatement("DELETE FROM ArticoloSelezionato WHERE utente=?");
            ps.setInt(1, u.getId());
            
            x+=ps.executeUpdate();

            return x>0 ? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
	}
}