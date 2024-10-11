package ru.specialist.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet("/time")
public class ServerTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
			out.println("<title>Server time</title>");
		out.println("</head>");
		out.println("<body>");
			out.printf("<h1>Серверное время: %tR</h1>", LocalTime.now());
		out.println("</body>");
		out.println("</html>");
		
		out.println("</html>");
		
	}

}
