package control;

import model.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowOrdiniData")

public class ShowOrdiniData extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrdineDAO ordineDAO = new OrdineDAO();
		
		Date start = Date.valueOf(request.getParameter("startDate"));
		Date end = Date.valueOf(request.getParameter("endDate"));
		
		List<Ordine> ordini = ordineDAO.doRetrieveOrdiniByData(start, end);
		
		request.setAttribute("ordini", ordini);
		request.setAttribute("numeroOrdini", ordini.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPage/show-ordini.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }
}
