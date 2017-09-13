package emp_database;
import java.util.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Emp_crud
 */
@WebServlet(urlPatterns={"//*"})
public class Emp_crud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Empdao t1;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Emp_crud() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 t1= new Empdao("jdbc:mysql://localhost:3306/emp_db","root","1234");
		 System.out.println("init");
		 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
		System.out.println("running now");
		String action = request.getServletPath();
		System.out.println("printing action");
		System.out.println(action);
		HttpSession session=request.getSession();
		//session.setMaxInactiveInterval(120);
		if(session.isNew())
		{
			System.out.println("new");
			session.setAttribute("login", "invalid");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login1.jsp");
	        dispatcher.forward(request, response);
	        return;
		}
		if(action.equals("/login"))
			validate(request,response);
		
		if(session.getAttribute("login").equals("valid"))
		try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertEmp(request, response);
                break;
            case "/delete":
                deleteEmp(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateEmp(request, response);
                break;
            case "/login":
            	validate(request,response);
            	break;
            case "/logout":
            	session.invalidate();
            	RequestDispatcher dispatcher = request.getRequestDispatcher("login1.jsp");
    	        dispatcher.forward(request, response);
            	break;
            default:
            	if(session.getAttribute("login").equals("valid"))
                    listemp(request, response);
            	
            		
              //  session.invalidate();
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
		else{
    		System.out.println("nice try");
    		RequestDispatcher dispatch = request.getRequestDispatcher("login1.jsp");
	        dispatch.forward(request, response);
	        return;
    	}
			
	}
	private void validate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println(request.getParameter("uname"));
		if((request.getParameter("uname").equals("admin")))
		{
			HttpSession session= request.getSession();
			session.setAttribute("login", "valid");
			
			try{
				listemp(request, response);
			}catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
			
			
		}
		else {
			System.out.println("wrong data");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login2.jsp");
	        dispatcher.forward(request, response);
		}
			
			
	}
	private void listemp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<h1>"+"hello world"+ "</h1>");
		System.out.println("default function");
        List<Emp> empList = t1.listAll();
        request.setAttribute("empList",empList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        return;
    }
	 private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 System.out.println("new form");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Emp e= t1.getEmp(id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
	        request.setAttribute("emp", e);
	        dispatcher.forward(request, response);
	 
	    }
	 private void insertEmp(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
		 System.out.println("here");
	        //int id = Integer.parseInt(request.getParameter("id"));
		 //int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        String cno = request.getParameter("cno");
	        String mid = request.getParameter("mid");
	        try{
	        if((Double.parseDouble(cno)>7000000000f)&&(Double.parseDouble(cno)<10000000000f))
	        {
	        	System.out.println("ph no is valid");
	        	request.setAttribute("cnocheck", "true");
	        }
	        else {
	        	System.out.println("invalid ph no");
	        	throw new NumberFormatException();
	        }
	        }
	        catch(Exception e){
		        	System.out.println("invalid  no");
		        	  RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		        	Emp wronge=new Emp(25,name,cno,mid);
		        	request.setAttribute("emp", wronge);
		        	request.setAttribute("cnocheck", "fal");
		  	        dispatcher.forward(request, response);
			        
	        }
	        Emp e=new Emp(25,name,cno,mid);
	        t1.addEmployee(e);	              
	        response.sendRedirect("list");
	    }
	 private void deleteEmp(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	 
	        Emp e = new Emp(id);
	        t1.deleteEmp(e);
	        response.sendRedirect("list");
	 }
	 private void updateEmp(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        String cno = request.getParameter("cno");
	        String mid = request.getParameter("mid");
	        try{
		        if((Double.parseDouble(cno)>7000000000f)&&(Double.parseDouble(cno)<10000000000f))
		        {
		        	System.out.println("ph no is valid");
		        	request.setAttribute("cnocheck", "tr");
		        }
		        else {
		        	System.out.println("invalid ph no");
		        	throw new NumberFormatException();
		        }
		        }
		        catch(Exception e){
			        	System.out.println("invalid  no");
			        	  RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
			        	Emp wronge=new Emp(25,name,cno,mid);
			        	request.setAttribute("emp", wronge);
			        	request.setAttribute("cnocheck", "fal");
			  	        dispatcher.forward(request, response);
			  	        return;
				        
		        }
	 
	        Emp e = new Emp(id, name, cno, mid);
	        System.out.println(cno);
	      if(  t1.updateEmp(e))
	    	  System.out.println("update success");
	      else
	    	  System.out.println("some thing went wrong");
	        System.out.println("udating sql database");
	        response.sendRedirect("list");
	    }
	 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
