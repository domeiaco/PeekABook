package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InsertAutore")
public class InsertAutore extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nome =  request.getParameter("name");
		String cognome = request.getParameter("surname");
		
		AutoreDAO autoreDAO = new AutoreDAO();
		Autore autore = new Autore();
		autore.setNome(nome);
		autore.setCognome(cognome);
		autoreDAO.doSaveAutore(autore);
		List<Autore> autori = autoreDAO.doRetrieveAutori(0, 1000);
		request.setAttribute("autori", autori);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage/show-autori.jsp");
		dispatcher.forward(request, response);
    }
}