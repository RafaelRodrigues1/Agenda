package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.GenericDAO;
import model.dao.UserDAO;
import model.entities.User;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet(urlPatterns = {"/UsuarioController", "/login", "/register", "/exit"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/exit")) {
			exit(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getServletPath();
		 if (action.equals("/register")) {
			register(request, response);
		}else if (action.equals("/login")) {
			login(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		LocalDate dataNascimento = LocalDate.parse(request.getParameter("date"));
		String nome = request.getParameter("name");
		String login = request.getParameter("login");
		String senha = request.getParameter("password");
		
		User user = new User(nome, dataNascimento, login, senha);
		new GenericDAO<User>(User.class)
								.beginTransaction()
								.save(user)
								.commitTransaction()
								.closeEntity();
		
		response.sendRedirect("index.html");
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User user = new User(login, password);
		User userFound = null;
		try {
			userFound = new UserDAO().findUser(user);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
				
		if(userFound == null) {
			response.sendRedirect("index.html");
		}else {
			request.getSession().setAttribute("user", userFound);
			response.sendRedirect("main");
		}				
	}
	
	private void exit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		response.sendRedirect("index.html");		
	}
	
	
}
