package control;

import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Home")

public class Home extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloDAO articoloDAO = new ArticoloDAO();
		List<Articolo> articoli = articoloDAO.doRetrieveArticoliByValutazione(0, 4);
		
		request.setAttribute("articoli", articoli);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
	}
}