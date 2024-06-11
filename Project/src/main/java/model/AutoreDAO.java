package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoreDAO {
	public List<Autore> doRetrieveAutori(int limit, int offset){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Autore LIMIT ?,?");
			
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Autore> autori = new ArrayList<>();
			while(rs.next()) {
				Autore a = new Autore();
				a.setCodice(rs.getInt(1));
				a.setNome(rs.getString(2));
				a.setCognome(rs.getString(3));
				autori.add(a);
			}
			
			return autori;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Autore doRetrieveAutoreByLibro(Libro l){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Autore a JOIN Libro l ON a.codiceAutore=l.autore WHERE l.articolo=?");

            ps.setInt(1, l.getCodice());
            ResultSet rs= ps.executeQuery();
            Autore a= new Autore();
            if(rs.next()){
                a.setCodice(rs.getInt("a.codiceAutore"));
                a.setNome(rs.getString("a.nome"));
                a.setCognome(rs.getString("a.cognome"));
            }

            return a;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
	
	public int doUpdateAutore(Autore a){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("UPDATE Autore SET nome=?, cognome=? WHERE codiceAutore=?");

            ps.setString(1, a.getNome());
            ps.setString(2, a.getCognome());
            ps.setInt(3, a.getCodice());
            int x=ps.executeUpdate();
            return x>0? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int doSaveAutore(Autore a){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO Autore(nome, cognome) VALUES(?,?)");

            ps.setString(1, a.getNome());
            ps.setString(2, a.getCognome());
            int x=ps.executeUpdate();
            return x>0? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
