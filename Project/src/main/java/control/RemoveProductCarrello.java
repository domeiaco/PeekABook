package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import java.util.*;
import javax.servlet.http.HttpSession;


@WebServlet("/RemoveProductCarrello")
public class RemoveProductCarrello extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		synchronized (session) {
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			LinkedHashMap<Articolo, Integer> articoli = carrello.getArticoli();
			Set<Articolo> art = articoli.keySet();
			Articolo articolo = null;
			for(Articolo a : art) {
				if(a.getCodice()==id)
					articolo = a;
			}
			articoli.remove(articolo);
			carrello.setArticoli(articoli);
			session.removeAttribute("carrello");
			session.setAttribute("carrello", carrello);
			response.sendRedirect("http://localhost:8080/PeekABook/ShowCarrello");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}