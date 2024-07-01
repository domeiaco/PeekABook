package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
	public List<Libro> doRetrieveLibro(int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Libro l JOIN Articolo a ON a.codice=l.articolo LIMIT ?,?");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Libro> libri = new ArrayList<>();
			while(rs.next()) {
				Libro l = new Libro();
				l.setCodice(rs.getInt("a.codice"));
				l.setPrezzo(rs.getDouble("a.prezzo"));
				l.setQuantita(rs.getInt("a.quantita"));
				l.setValutazione(rs.getInt("a.valutazione"));
				l.setPathImg(rs.getString("a.copertina"));
				l.setNome(rs.getString("a.nome"));
				l.setTitolo(rs.getString("l.titolo"));
				l.setAnno(rs.getInt("l.anno"));
                l.setIsbn(rs.getLong("l.ISBN"));
                l.setPagine(rs.getInt("l.pagine"));
                l.setEditore(rs.getString("l.editore"));
                l.setDescrizione(rs.getString("l.descrizione"));
                Autore a= new Autore();
                a.setCodice(rs.getInt("l.autore"));
                l.setAutore(a);

                libri.add(l);
			}
			return libri;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Libro doRetrieveLibroByArticolo(int codice) {
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Libro l JOIN Articolo a ON a.codice=l.articolo WHERE a.codice=?");
			ps.setInt(1, codice);
			
			ResultSet rs = ps.executeQuery();
			Libro l = new Libro();
			if(rs.next()) {
				l.setCodice(rs.getInt("a.codice"));
				l.setPrezzo(rs.getDouble("a.prezzo"));
				l.setQuantita(rs.getInt("a.quantita"));
				l.setValutazione(rs.getInt("a.valutazione"));
				l.setPathImg(rs.getString("a.copertina"));
				l.setNome(rs.getString("a.nome"));
				l.setTitolo(rs.getString("l.titolo"));
				l.setAnno(rs.getInt("l.anno"));
                l.setIsbn(rs.getLong("l.ISBN"));
                l.setPagine(rs.getInt("l.pagine"));
                l.setEditore(rs.getString("l.editore"));
                l.setDescrizione(rs.getString("l.descrizione"));
                Autore a= new Autore();
                a.setCodice(rs.getInt("l.autore"));
                l.setAutore(a);
			}
			return l;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Libro> doRetrieveLibroByGenere(Genere genere, int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Libro l, Articolo a, GenLibro g WHERE l.articolo=a.codice AND g.genere=? AND g.libro=l.articolo LIMIT ?,?");
			ps.setString(1, genere.getNome());
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Libro> libri = new ArrayList<>();
			while(rs.next()) {
				Libro l = new Libro();
				l.setCodice(rs.getInt("a.codice"));
				l.setPrezzo(rs.getDouble("a.prezzo"));
				l.setQuantita(rs.getInt("a.quantita"));
				l.setValutazione(rs.getInt("a.valutazione"));
				l.setPathImg(rs.getString("a.copertina"));
				l.setNome(rs.getString("a.nome"));
				l.setTitolo(rs.getString("l.titolo"));
				l.setAnno(rs.getInt("l.anno"));
                l.setIsbn(rs.getLong("l.ISBN"));
                l.setPagine(rs.getInt("l.pagine"));
                l.setEditore(rs.getString("l.editore"));
                l.setDescrizione(rs.getString("l.descrizione"));
                Autore a= new Autore();
                a.setCodice(rs.getInt("l.autore"));
                l.setAutore(a);
                libri.add(l);
			}
			return libri;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Libro> doRetrieveOtherLibriByGenere(Genere genere, Libro l1, int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Libro l, Articolo a, GenLibro g WHERE l.articolo=a.codice AND l.articolo=g.libro AND g.genere=? AND a.codice!=? ORDER BY a.valutazione DESC LIMIT ?,?");
			ps.setString(1, genere.getNome());
			ps.setInt(2, l1.getCodice());
			ps.setInt(3, limit);
			ps.setInt(4, offset);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Libro> libri = new ArrayList<>();
			while(rs.next()) {
				Libro l = new Libro();
				l.setCodice(rs.getInt("a.codice"));
				l.setPrezzo(rs.getDouble("a.prezzo"));
				l.setQuantita(rs.getInt("a.quantita"));
				l.setValutazione(rs.getInt("a.valutazione"));
				l.setPathImg(rs.getString("a.copertina"));
				l.setNome(rs.getString("a.nome"));
				l.setTitolo(rs.getString("l.titolo"));
				l.setAnno(rs.getInt("l.anno"));
                l.setIsbn(rs.getLong("l.ISBN"));
                l.setPagine(rs.getInt("l.pagine"));
                l.setEditore(rs.getString("l.editore"));
                l.setDescrizione(rs.getString("l.descrizione"));
                Autore a= new Autore();
                a.setCodice(rs.getInt("l.autore"));
                l.setAutore(a);
                libri.add(l);
			}
			return libri;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int doSaveLibro(Libro l) {
		try (Connection con = ConPool.getConnection()){
			ArticoloDAO articoloDAO = new ArticoloDAO();
			articoloDAO.doSaveArticolo(l);
			PreparedStatement ps = con.prepareStatement("INSERT INTO Libro(autore, titolo, ISBN, anno, pagine, editore, descrizione) VALUES(?,?,?,?,?,?,?)");
			ps.setInt(1, l.getAutore().getCodice());
			ps.setString(2, l.getTitolo());
			ps.setLong(3, l.getIsbn());
			ps.setInt(4, l.getAnno());
			ps.setInt(5, l.getPagine());
			ps.setString(6, l.getEditore());
			ps.setString(7, l.getDescrizione());
			
			int x=ps.executeUpdate();
			return x>0? 1:0;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public int doSetGenereLibro(Libro l){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT codice FROM Articolo ORDER BY codice DESC LIMIT 0,1");

            ResultSet rs = ps.executeQuery();
            int cod=-1;
            if(rs.next()){
                cod=rs.getInt("codice");
            }

            ps = con.prepareStatement("INSERT INTO GenLibro VALUES (?,?)");
            ps.setInt(1, cod);
            ps.setString(2, l.getGenere().getNome());
            int rows = ps.executeUpdate();
            return rows>0?1:0;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
	
	public int doUpdateLibro(Libro l){
        try (Connection con = ConPool.getConnection()) {
            ArticoloDAO articoloDAO = new ArticoloDAO();
            articoloDAO.doUpdateArticoloFull(l);

            PreparedStatement ps=con.prepareStatement("UPDATE Libro SET autore=?, titolo=?, ISBN=?, anno=?, pagine=?, editore=?, descrizione=? WHERE articolo=?");

            ps.setInt(1, l.getAutore().getCodice());
            ps.setString(2, l.getTitolo());
            ps.setLong(3, l.getIsbn());
            ps.setInt(4, l.getAnno());
            ps.setInt(5, l.getPagine());
            ps.setString(6, l.getEditore());
            ps.setString(7, l.getDescrizione());
            ps.setInt(8,l.getCodice());

            int x=ps.executeUpdate();
            return x>0? 1:0;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
	
	public int doRemoveLibro(int codice) {
		try (Connection con = ConPool.getConnection()) {
            ArticoloDAO articoloDAO = new ArticoloDAO();
            

            PreparedStatement ps=con.prepareStatement("DELETE FROM genlibro WHERE libro=?");
            ps.setInt(1,codice);
            int x=ps.executeUpdate();
            
            ps=con.prepareStatement("DELETE FROM libro WHERE articolo=?");
            ps.setInt(1, codice);

            x+=ps.executeUpdate();

            
            x+=articoloDAO.doRemoveArticolo(codice);
            return x>2? 1:0;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
	}
}
