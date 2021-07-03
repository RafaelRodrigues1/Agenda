package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contato;
import model.ContatoDao;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete"})
public class ContatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Contato contato;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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

		List<Contato> listaContatos = ContatoDao.getInstance().listarContatos();
		request.setAttribute("lista", listaContatos);
		request.getRequestDispatcher("agenda.jsp").forward(request, response);
	}

	private void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato = new Contato(request.getParameter("nome"), request.getParameter("fone"),
				request.getParameter("email"));

		ContatoDao.getInstance().inserirContato(contato);
		response.sendRedirect("main");
	}

	private void buscarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		contato = ContatoDao.getInstance().buscarPorId(id);
		
		request.setAttribute("contato", contato);
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}
	
	private void alterarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		contato = new Contato(id, request.getParameter("nome"), request.getParameter("fone"),
				request.getParameter("email"));
		
		ContatoDao.getInstance().alterarContato(contato);
		response.sendRedirect("main");
	}
	
	private void excluirContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		ContatoDao.getInstance().excluirContato(id);
		response.sendRedirect("main");
	}
}




