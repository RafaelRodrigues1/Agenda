<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entities.Contact"%>
<%@ page import="model.entities.User"%>
<%
@SuppressWarnings("unchecked")
List<Contact> contactList = (List<Contact>) request.getAttribute("list");
User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon">
<title>Agenda de contatos</title>
<style type="text/css">
caption {
	text-align: right;
	font-size: 0.8em;
	font-weight: 700;
	color: #319282;
}
caption a {
	color: #319282;
	text-decoration: none;
}
caption a:hover {
	text-decoration: underline;
}
</style>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<main>
		<h1>Agenda de Contatos</h1>
		<a href="new.html" class="botao1">Novo contato</a>
		

		<table class="tabela">		
			<caption>Olá, <%=user.getName()%>! (<a href="exit">Sair?</a>)</caption>
			<thead>			
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>E-mail</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%for (Contact contact : contactList) {%>
				<tr>
					<td class="id"><%=contact.getId()%></td>
					<td><%=contact.getName()%></td>
					<td><%=contact.getPhone()%></td>
					<td><%=contact.getEmail()%></td>
					<td class="opcoes"><a href="select?id=<%=contact.getId()%>"
						class="botao1 botaoTable">Editar</a> <a
						href="javascript: confirmar(<%=contact.getId()%>)"
						class="botao1 botaoTable" id="botaoExcluir">Excluir</a></td>
				</tr>
				<%}%>
			</tbody>
		</table>
	</main>
	<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</html>