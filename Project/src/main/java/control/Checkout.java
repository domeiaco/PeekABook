package control;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


@WebServlet("/Checkout")
public class Checkout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		synchronized (session) {
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			Utente utente = (Utente) session.getAttribute("utente");
			LinkedHashMap<Articolo, Integer> articoliOrdine = carrello.getArticoli();
			ArticoloDAO articoloDAO = new ArticoloDAO();
			int flag= 1;
			for(Map.Entry<Articolo,Integer> m : articoliOrdine.entrySet()) {
				Articolo a = m.getKey();
				Articolo x = articoloDAO.doRetrieveArticoliByCodice(a.getCodice());
				if(x.getQuantita()<m.getValue() || m.getValue()<1) {				
					request.setAttribute("articolo", x);
					RequestDispatcher dispatcher = request.getRequestDispatcher("order-failed.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			if(flag==1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
