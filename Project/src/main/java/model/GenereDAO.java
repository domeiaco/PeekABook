package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenereDAO {
	public List<Genere> doRetrieveGeneri(){
		try(Connection con = ConPool.getConnection()){
			PreparedStatement ps = con.prepareStatement("SELECT Nome FROM Genere");
			ArrayList<Genere> generi = new ArrayList<>();
            ResultSet rs= ps.executeQuery();
            Genere g;
            while(rs.next()){
                g= new Genere();
                g.setNome(rs.getString(1));
                generi.add(g);
            }

            return generi;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
	}
	
	public Genere doRetrieveGenereByLibro(Libro l){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT genere FROM GenLibro WHERE libro=?");
            ps.setInt(1,l.getCodice());
            ResultSet rs= ps.executeQuery();
            Genere g= new Genere();
            if(rs.next()){
                g.setNome(rs.getString("genere"));
            }

            return g;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
	
	public Genere doRetrieveGenereByNome(String nome){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT nome FROM Genere WHERE nome=?");
            ps.setString(1, nome);
            ResultSet rs= ps.executeQuery();
            Genere g= new Genere();
            if(rs.next()){
                g.setNome(rs.getString("nome"));
            }

            return g;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
	
    public int doSaveGenere(Genere genere){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO GENERE VALUES(?)");
            ps.setString(1, genere.getNome());
            int x=ps.executeUpdate();

            return x>0? 1:0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
