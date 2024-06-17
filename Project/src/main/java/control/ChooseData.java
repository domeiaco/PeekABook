package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ChooseData")
public class ChooseData extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage/choose-data.jsp");
        dispatcher.forward(request, response);
    }
}
