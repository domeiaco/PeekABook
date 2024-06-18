package control;

import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddToCart")

public class AddToCart extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod = request.getParameter("articolo");
		String qnt = request.getParameter("quantita");
		Integer codice= Integer.parseInt(cod);
		Integer quantita = Integer.parseInt(qnt);
		HttpSession session = request.getSession();
		synchronized (session){
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			LinkedHashMap<Articolo, Integer> articoli = carrello.getArticoli();
			ArticoloDAO articoloDAO = new ArticoloDAO();
			Articolo a = articoloDAO.doRetrieveArticoliByCodice(codice);
			Articolo dupl = null;
			for(Articolo art : articoli.keySet()) {
				if(art.getCodice()==a.getCodice())
					dupl=art;
			}
		
			if(dupl!=null) {
				Integer oldQnt = articoli.get(dupl);
				articoli.replace(dupl, oldQnt + quantita);
			}
		
			else
				articoli.put(a, quantita);
		
			carrello.setArticoli(articoli);
			session.removeAttribute("carrello");
			session.setAttribute("carrello", carrello);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("aggiunto-carrello.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
	}
}
