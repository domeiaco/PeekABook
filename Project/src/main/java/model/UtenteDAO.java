package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
	public int doRetrieveIdByUsername(String user) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT id FROM Utente WHERE username=?");
			ps.setString(1, user);
			
			ResultSet rs = ps.executeQuery();
			int id=-1;
			if(rs.next()) {
				id = rs.getInt("id");
			}
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Utente doRetrieveByUsernamePassword(String username, String password) {
		Utente u = new Utente();
		u.setPassword(password);
		String pass= u.getPassword();
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente WHERE username=? AND passwordHash=?");
			ps.setString(1, username);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setId(rs.getInt(1));
				u.setAdmin(rs.getInt(2));
				u.setEmail(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setNome(rs.getString(6));
				u.setCognome(rs.getString(7));
				u.setVia(rs.getString(8));
				u.setCivico(rs.getInt(9));
				u.setCitta(rs.getString(10));
				u.setCap(rs.getInt(11));
			}
			else {
				return null;
			}
			
			ps= con.prepareStatement("SELECT * FROM Carrello WHERE utente=?");
			ps.setInt(1, u.getId());
			rs = ps.executeQuery();
			Carrello c = new Carrello();
			c.setUtente(u);
			if(rs.next()) {
				c.setTotale(rs.getDouble("totale"));
				c.setNumeroArticoli(rs.getInt("numeroArticoli"));
			}
			
			ps=con.prepareStatement("SELECT * FROM ArticoloSelezionato ars, Articolo a WHERE ars.utente=? AND ars.articolo=a.codice");
			ps.setInt(1, u.getId());
			rs=ps.executeQuery();
			Articolo a;
			while(rs.next()) {
				a=new Articolo();
				a.setCodice(rs.getInt("a.codice"));
				a.setPrezzo(rs.getDouble("a.prezzo"));
				a.setNome(rs.getString("a.nome"));
				a.setQuantita(rs.getInt("a.quantita"));
				a.setPathImg(rs.getString("a.copertina"));
				int qnt = rs.getInt("ars.quantita");
				c.getArticoli().put(a,qnt);
			}
			u.setCarrello(c);
			
			return u;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Utente> doRetrieveAllUtentiExcAdm(Utente adm, int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente WHERE id!=? LIMIT ?,?");
			ps.setInt(1, adm.getId());
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			ResultSet rs = ps.executeQuery();
			ArrayList<Utente> utenti= new ArrayList<>();
			Utente u;
			
			while(rs.next()) {
				u=new Utente();
				u.setId(rs.getInt(1));
				u.setAdmin(rs.getInt(2));
				u.setEmail(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setNome(rs.getString(6));
				u.setCognome(rs.getString(7));
				u.setVia(rs.getString(8));
				u.setCivico(rs.getInt(9));
				u.setCitta(rs.getString(10));
				u.setCap(rs.getInt(11));
				utenti.add(u);
			}
			return utenti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Utente> doRetrieveAllUtenti(int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente LIMIT ?,?");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			ArrayList<Utente> utenti= new ArrayList<>();
			Utente u;
			
			while(rs.next()) {
				u=new Utente();
				u.setId(rs.getInt(1));
				u.setAdmin(rs.getInt(2));
				u.setEmail(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setNome(rs.getString(6));
				u.setCognome(rs.getString(7));
				u.setVia(rs.getString(8));
				u.setCivico(rs.getInt(9));
				u.setCitta(rs.getString(10));
				u.setCap(rs.getInt(11));
				utenti.add(u);
			}
			return utenti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doSaveUtente(Utente u) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("INSERT INTO Utente(adm,email,username,passwordHash, nome, cognome, via, civico, citta, CAP) VALUES(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getUsername());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getNome());
			ps.setString(6, u.getCognome());
			ps.setString(7, u.getVia());
			ps.setInt(8, u.getCivico());
			ps.setString(9, u.getCitta());
			ps.setInt(10, u.getCap());
			
			int rows=ps.executeUpdate();
			return rows>0 ? 1:0;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doDeleteUtente(Utente u) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE id=?");
			ps.setInt(1, u.getId());
			
			int rows= ps.executeUpdate();
			return rows>0 ? 1:0;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doUpdateUtente(Utente u){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE Utente SET nome=?, cognome=?, via=?, civico=?, citta=?, cap=?, email=? WHERE id=?");

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getVia());
            ps.setInt(4, u.getCivico());
            ps.setString(5, u.getCitta());
            ps.setInt(6, u.getCap());
            ps.setString(7, u.getEmail());
            ps.setInt(8, u.getId());

            int rows= ps.executeUpdate();

            return rows>0 ? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
	
	public int doSetAdmin(Utente u) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("UPDATE Utente SET adm=? WHERE id=?");
			ps.setInt(1, 1);
			ps.setInt(2, u.getId());
			int rows=ps.executeUpdate();
			return rows>0 ? 1:0;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doRemoveAdmin(Utente u) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("UPDATE Utente SET adm=? WHERE id=?");
			ps.setInt(1, 0);
			ps.setInt(2, u.getId());
			int rows=ps.executeUpdate();
			return rows>0 ? 1:0;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean doVerifyDuplicate(Utente utente) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT id FROM Utente WHERE username=?");
			ps.setString(1, utente.getUsername());
			ResultSet rs =ps.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		} catch(SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	
	public Utente doRetrieveById(Integer id) {
		Utente u = new Utente();

		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setId(rs.getInt(1));
				u.setAdmin(rs.getInt(2));
				u.setEmail(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setNome(rs.getString(6));
				u.setCognome(rs.getString(7));
				u.setVia(rs.getString(8));
				u.setCivico(rs.getInt(9));
				u.setCitta(rs.getString(10));
				u.setCap(rs.getInt(11));
				return u;
			}
			else {
				return null;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
