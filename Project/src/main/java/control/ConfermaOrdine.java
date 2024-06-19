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

@WebServlet("/ConfermaOrdine")
public class ConfermaOrdine extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String via = request.getParameter("address");
		Integer civico = Integer.parseInt(request.getParameter("address2"));
		String citta = request.getParameter("address3");
		Integer cap = Integer.parseInt(request.getParameter("cap"));
		
		String circuito = request.getParameter("circuito");
		String numeroCarta = request.getParameter("numero");
		String metodoDiPagamento = circuito + " *" +numeroCarta.substring(12, 16);
		
		Ordine ordine = new Ordine();
		ordine.setCap(cap);
		ordine.setCitta(citta);
		ordine.setCivico(civico);
		ordine.setMetodoPagamento(metodoDiPagamento);
		ordine.setVia(via);

		
		synchronized (session) {
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			Utente utente = (Utente) session.getAttribute("utente");
			OrdineDAO ordineDAO = new OrdineDAO();
			ordineDAO.doSaveOrdine(utente, carrello, ordine);
			session.removeAttribute("carrello");
			carrello.setArticoli(new LinkedHashMap<>());
			carrello.setNumeroArticoli(0);
			session.setAttribute("carrello", carrello);
			RequestDispatcher dispatcher = request.getRequestDispatcher("order-success.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
