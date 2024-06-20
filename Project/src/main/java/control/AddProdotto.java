package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.*;
import java.util.*;

@WebServlet("/AddProdotto")
public class AddProdotto extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		AutoreDAO autoreDAO = new AutoreDAO();
		GenereDAO genereDAO = new GenereDAO();
		List<Autore> autori = autoreDAO.doRetrieveAutori(0, 1000);
		List<Genere> generi = genereDAO.doRetrieveGeneri();
		request.setAttribute("autori", autori);
		request.setAttribute("generi", generi);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage/add-libro.jsp");
        dispatcher.forward(request, response);
    }
}