package net.codejava.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		String o = request.getParameter("op");
		//response.setContentType("text/html");
		//PrintWriter writer = response.getWriter();
		//ServletOutputStream writer = response.getOutputStream();
		int res;
		switch(o)
		{
		case "+":
			res=(Integer.parseInt(num1) +Integer.parseInt(num2));
			break;
		case "-":
			res=(Integer.parseInt(num1)- Integer.parseInt(num2));
			break;
		case "/":
			res=(Integer.parseInt(num1)/Integer.parseInt(num2));
			break;
		case "*":
			res=(Integer.parseInt(num1)*Integer.parseInt(num2));
			break;	
		default :
			res=0;
			
		}
		String str=num1+ o + num2 +"=";
		//writer.println("<h1>"+str  +res+ "</h1>");
		//writer.println(getServletConfig().getInitParameter("n1"));
		//response.sendRedirect("https://www.google.co.in");
		//writer.close();
		request.setAttribute("styles", res);
		RequestDispatcher view = 
				request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
		
		
	}

}
