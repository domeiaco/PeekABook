package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticoloDAO {
	public int doUpdateArticolo(int codice, int quantita, double prezzo) {
		try (Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("UPDATE Articolo SET prezzo=?,quantita=(quantita+?) WHERE codice=?");
			ps.setDouble(1, prezzo);
			ps.setInt(2, quantita);
			ps.setInt(3, codice);
			int x=ps.executeUpdate();
			return x>0? 1:0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doUpdateQuantita(Articolo articolo, int quantita) {
		try (Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("UPDATE Articolo SET quantita=(quantita-?) WHERE codice=?");
			ps.setDouble(1, quantita);
			ps.setInt(2, articolo.getCodice());
			int x=ps.executeUpdate();
			return x>0? 1:0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doUpdatePrezzo(Articolo articolo, double prezzo) {
		try (Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("UPDATE Articolo SET prezzo=? WHERE codice=?");
			ps.setDouble(1, prezzo);
			ps.setInt(2, articolo.getCodice());
			int x=ps.executeUpdate();
			return x>0? 1:0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doUpdateValutazione(Articolo articolo, int valutazione) {
		try (Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("UPDATE Articolo SET valutazione=? WHERE codice=?");
			ps.setDouble(1, valutazione);
			ps.setInt(2, articolo.getCodice());
			int x=ps.executeUpdate();
			return x>0? 1:0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doSaveArticolo(Articolo articolo) {
		
		try(Connection con = ConPool.getConnection()){
		
			PreparedStatement ps = con.prepareStatement("INSERT INTO Articolo(prezzo, valutazione, quantita, copertina, nome) VALUES (?,?,?,?,?)");
		
			ps.setDouble(1, articolo.getPrezzo());
			ps.setInt(2, articolo.getValutazione());
			ps.setInt(3, articolo.getQuantita());
			ps.setString(4, articolo.getPathImg());
			ps.setString(5, articolo.getNome());
		
			int x = ps.executeUpdate();
	
			return x>0 ? 1:0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Articolo> doRetrieveArticoli(int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Articolo LIMIT ?,?");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Articolo> articoli = new ArrayList<>();
			
			while(rs.next()) {
				Articolo a = new Articolo();
				a.setCodice(rs.getInt("codice"));
				a.setPrezzo(rs.getDouble("prezzo"));
				a.setQuantita(rs.getInt("quantita"));
				a.setValutazione(rs.getInt("valutazione"));
				a.setPathImg(rs.getString("copertina"));
				a.setNome(rs.getString("nome"));
				articoli.add(a);
			}
			return articoli;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List<Articolo> doRetrieveArticoliByNomeLike(String nome, int offset, int limit){
		try(Connection con = ConPool.getConnection()){
		
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Articolo WHERE nome LIKE ? LIMIT ?,?");
			ps.setString(1, "%"+nome+"%");
			ps.setInt(3, limit);
			ps.setInt(2, offset);
					
			ResultSet rs = ps.executeQuery();
			ArrayList<Articolo> articoli = new ArrayList<>();
		
			while(rs.next()) {
				Articolo a = new Articolo();
				a.setCodice(rs.getInt("codice"));
				a.setPrezzo(rs.getDouble("prezzo"));
				a.setQuantita(rs.getInt("quantita"));
				a.setValutazione(rs.getInt("valutazione"));
				a.setPathImg(rs.getString("copertina"));
				a.setNome(rs.getString("nome"));
				articoli.add(a);
			}
			return articoli;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Articolo doRetrieveArticoliByCodice(int codice){
		try(Connection con = ConPool.getConnection()){
		
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Articolo WHERE codice=?");
			ps.setInt(1, codice);
		
			ResultSet rs = ps.executeQuery();
			Libro l = new Libro();
			if(rs.next()) {
				LibroDAO libroDAO = new LibroDAO();
				l = libroDAO.doRetrieveLibroByArticolo(rs.getInt("codice"));
			}
			return l;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Articolo> doRetrieveArticoliByValutazione(int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Articolo ORDER BY valutazione DESC LIMIT ?,?");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Articolo> articoli = new ArrayList<>();
			
			while(rs.next()) {
				Articolo a = new Articolo();
				a.setCodice(rs.getInt("codice"));
				a.setPrezzo(rs.getDouble("prezzo"));
				a.setQuantita(rs.getInt("quantita"));
				a.setValutazione(rs.getInt("valutazione"));
				a.setPathImg(rs.getString("copertina"));
				a.setNome(rs.getString("nome"));
				articoli.add(a);
			}
			return articoli;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doUpdateArticolo(Articolo articolo){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Articolo SET prezzo=?, valutazione=?, quantita=?, copertina=? WHERE codice=?");

            ps.setDouble(1,articolo.getPrezzo());
            ps.setInt(2, articolo.getValutazione());
            ps.setInt(3, articolo.getQuantita());
            ps.setString(4, articolo.getPathImg());
            ps.setInt(5, articolo.getCodice());

            int x=ps.executeUpdate();
            return x>0? 1:0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
	
	public List<Articolo> doRetrieveArticoliByNome(String nome, int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Articolo WHERE nome=? LIMIT ?,?");
			ps.setString(1, nome);
			ps.setInt(2, limit);
			ps.setInt(3, offset);
		
			ResultSet rs = ps.executeQuery();
			ArrayList<Articolo> articoli = new ArrayList<>();
		
			while(rs.next()) {
				Articolo a = new Articolo();
				a.setCodice(rs.getInt("codice"));
				a.setPrezzo(rs.getDouble("prezzo"));
				a.setQuantita(rs.getInt("quantita"));
				a.setValutazione(rs.getInt("valutazione"));
				a.setPathImg(rs.getString("copertina"));
				a.setNome(rs.getString("nome"));
				articoli.add(a);
			}
			return articoli;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doRemoveArticolo(int codice) {
		try (Connection con = ConPool.getConnection()) {
            

            PreparedStatement ps=con.prepareStatement("DELETE FROM articolo WHERE codice=?");

         
            ps.setInt(1,codice);

            int x=ps.executeUpdate();
            return x>0? 1:0;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
	}
}
