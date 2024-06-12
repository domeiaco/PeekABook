package control;

import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ShowAll")

public class ShowAll extends HttpServlet{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticoloDAO articoloDAO = new ArticoloDAO();
		List<Articolo> articoli = articoloDAO.doRetrieveArticoli(0, 100);
		
		request.setAttribute("articoli", articoli);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("show-all.jsp");
        dispatcher.forward(request, response);
	}

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doGet(request, response);
}
}