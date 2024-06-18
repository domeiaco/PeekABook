package control;

import java.io.IOException;
import java.util.*;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateCarrello")
public class UpdateCarrello extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer quantita = Integer.parseInt(request.getParameter("quantita"));
		HttpSession session = request.getSession();
		synchronized (session) {
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			LinkedHashMap<Articolo, Integer> articoli = carrello.getArticoli();
			Set<Articolo> art = articoli.keySet();
			for(Articolo a : art) {
				if(a.getCodice()==id)
					articoli.replace(a, quantita);
			}
			carrello.setArticoli(articoli);
			session.removeAttribute("carrello");
			session.setAttribute("carrello", carrello);
			response.sendRedirect("http://localhost:8080/PeekABook/ShowCarrello");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}