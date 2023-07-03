package model;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class OrdineDAO{
	
	public ArrayList<Ordine> doRetrieveOrdiniByUtente(Utente u){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Ordine WHERE utente=?");
			ps.setInt(1, u.getId());
			ResultSet rs = ps.executeQuery();
			ArrayList<Ordine> ordini = new ArrayList<>();
			Ordine o;
			while(rs.next()) {
				o=new Ordine();
				o.setUtente(u);
				o.setNumero(rs.getInt(2));
				o.setVia(rs.getString(3));
				o.setCivico(rs.getInt(4));
				o.setCitta(rs.getString(5));
				o.setCap(rs.getInt(6));
				o.setDataOrdine(rs.getObject(7,LocalDate.class));
				o.setDataConsegna(rs.getObject(8,LocalDate.class));
				o.setStato(rs.getString(9));
				ordini.add(o);
			}
			
			u.setOrdini(ordini);
			return ordini;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Ordine> doRetrieveAllOrdini(int offset, int limit){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Ordine LIMIT ?,?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();
			ArrayList<Ordine> ordini = new ArrayList<>();
			Ordine o;
			while(rs.next()) {
				o=new Ordine();
				Utente u = new Utente();
				u.setId(rs.getInt(1));
				o.setUtente(u);
				o.setNumero(rs.getInt(2));
				o.setVia(rs.getString(3));
				o.setCivico(rs.getInt(4));
				o.setCitta(rs.getString(5));
				o.setCap(rs.getInt(6));
				o.setDataOrdine(rs.getObject(7,LocalDate.class));
				o.setDataConsegna(rs.getObject(8,LocalDate.class));
				o.setStato(rs.getString(9));
				ordini.add(o);
			}
		
			return ordini;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doUpdateOrdine(Ordine o) {
		try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE Ordine SET dataConsegna=?, stato=? WHERE numero=?");
            ps.setObject(1, java.sql.Date.valueOf(o.getDataConsegna()));
            ps.setString(2, o.getStato());
            ps.setInt(1, o.getNumero());
            int rows= ps.executeUpdate();

            return rows>0 ? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
	}
	
	public int doSaveOrdine(Utente utente, Carrello carrello) {
		try (Connection con = ConPool.getConnection()) {
			ArticoloDAO articoloDAO= new ArticoloDAO();
			Set<Articolo> keys = carrello.getArticoli().keySet();
            int y=0;
            for(Articolo key: keys) {
                y+=articoloDAO.doUpdateQuantita(key, carrello.getArticoli().get(key));
            }

            PreparedStatement ps = con.prepareStatement("INSERT INTO Ordine (utente,via,civico,citta,CAP,dataOrdine,dataConsegna,stato) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, utente.getId());
            ps.setString(2, utente.getVia());
            ps.setInt(3, utente.getCivico());
            ps.setString(4, utente.getCitta());
            ps.setInt(5, utente.getCap());
            ps.setObject(6, java.sql.Date.valueOf(LocalDate.now()));
            ps.setObject(7, java.sql.Date.valueOf(LocalDate.now().plusDays(2)));
            ps.setString(8, "in arrivo");
            int rows= ps.executeUpdate();

            CarrelloDAO c = new CarrelloDAO();
            int x = c.doClearCarrello(utente);
            
            return (rows>0 && x>0)==true ? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
	}
	
	public Ordine doRetrieveLastOrdine(Utente utente) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Ordine WHERE utente=? ORDER BY numero DESC LIMIT 1");
			ps.setInt(1, utente.getId());
			ResultSet rs = ps.executeQuery();
			Ordine o = new Ordine();
			if(rs.next()) {
				o.setUtente(utente);
				o.setNumero(rs.getInt(2));
				o.setVia(rs.getString(3));
				o.setCivico(rs.getInt(4));
				o.setCitta(rs.getString(5));
				o.setCap(rs.getInt(6));
				o.setDataOrdine(rs.getObject(7,LocalDate.class));
				o.setDataConsegna(rs.getObject(8,LocalDate.class));
				o.setStato(rs.getString(9));
			}
			return o;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Ordine> doRetrieveOrdiniByData(Date start, Date end){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Ordine WHERE dataordine BETWEEN ? AND ?;");
			ps.setDate(1, start);
			ps.setDate(2, end);
			ps.setDate(1, start);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Ordine> ordini = new ArrayList<>();
			Ordine o;
			UtenteDAO utente = new UtenteDAO();
			while(rs.next()) {
				o=new Ordine();
				o.setUtente(utente.doRetrieveById(rs.getInt(1)));
				o.setNumero(rs.getInt(2));
				o.setVia(rs.getString(3));
				o.setCivico(rs.getInt(4));
				o.setCitta(rs.getString(5));
				o.setCap(rs.getInt(6));
				o.setDataOrdine(rs.getObject(7,LocalDate.class));
				o.setDataConsegna(rs.getObject(8,LocalDate.class));
				o.setStato(rs.getString(9));
				ordini.add(o);
			}
			
			return ordini;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}