package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPage/admin.jsp");
		dispatcher.forward(request, response);
	}
}