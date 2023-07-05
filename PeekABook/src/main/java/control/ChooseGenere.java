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

@WebServlet ("/ChooseGenere")

public class ChooseGenere extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GenereDAO genereDAO = new GenereDAO();
		List<Genere> generi = genereDAO.doRetrieveGeneri();
		request.setAttribute("generi", generi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("choose-genere.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GenereDAO genereDAO = new GenereDAO();
		List<Genere> generi = genereDAO.doRetrieveGeneri();
		request.setAttribute("generi", generi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("choose-genere.jsp");
        dispatcher.forward(request, response);
	}
}