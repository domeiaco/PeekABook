package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Set;

public class CarrelloDAO {
	public Carrello doRetrieveCarrelloByUtente(Utente u) {
		return null;
	}
	
	public int doUpdateCarrello(Carrello carrello) {
		return 1;
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
		return 1;
	}
}
