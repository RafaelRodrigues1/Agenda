package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContactDAO;
import model.dao.GenericDAO;
import model.entities.Contact;
import model.entities.User;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete"})
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Contact contact;
	private User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("index.html");
		}
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			buscarContato(request, response);
		} else if (action.equals("/update")) {
			alterarContato(request, response);
		} else if (action.equals("/delete")) {
			excluirContato(request, response);
		}  else {
			response.sendRedirect("index.html");
		}
	}

	private void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user = (User) request.getSession().getAttribute("user");
		
		List<Contact> contactsList = new ContactDAO().findAll(user);
		request.setAttribute("list", contactsList);
		request.getRequestDispatcher("agenda.jsp").forward(request, response);
	}

	private void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		user = (User) request.getSession().getAttribute("user");
		contact = new Contact(request.getParameter("name"), request.getParameter("phone"),
				request.getParameter("email"), user);

		new GenericDAO<Contact>(Contact.class)
							.beginTransaction()
							.save(contact)
							.commitTransaction()
							.closeEntity();;
		response.sendRedirect("main");
	}

	private void buscarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));		
		contact = new GenericDAO<Contact>(Contact.class).findById(id);
		
		request.setAttribute("contact", contact);
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}
	
	private void alterarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		user = (User) request.getSession().getAttribute("user");
		
		Integer id = Integer.parseInt(request.getParameter("id"));		
		contact = new Contact(id, request.getParameter("name"), request.getParameter("phone"),
				request.getParameter("email"), user);
		new GenericDAO<Contact>(Contact.class)
						.beginTransaction()
						.update(contact)
						.commitTransaction()
						.closeEntity();
		
		response.sendRedirect("main");
	}
	
	private void excluirContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		new GenericDAO<Contact>(Contact.class)
							.beginTransaction()
							.delete(id)
							.commitTransaction()
							.closeEntity();
		response.sendRedirect("main");
	}
}





