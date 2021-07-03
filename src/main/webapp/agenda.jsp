<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Contato"%>
<%
@SuppressWarnings("unchecked")
List<Contato> listaContato = (List<Contato>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon">
<title>Agenda de contatos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<main>
		<h1>Agenda de Contatos</h1>
		<a href="new.html" class="botao1">Novo contato</a>

		<table class="tabela">
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
				<%for (Contato contato : listaContato) {%>
				<tr>
					<td class="id"><%=contato.getId()%></td>
					<td><%=contato.getNome()%></td>
					<td><%=contato.getFone()%></td>
					<td><%=contato.getEmail()%></td>
					<td class="opcoes"><a href="select?id=<%=contato.getId()%>"
						class="botao1 botaoTable">Editar</a> <a
						href="javascript: confirmar(<%=contato.getId()%>)"
						class="botao1 botaoTable" id="botaoExcluir">Excluir</a></td>
				</tr>
				<%}%>
			</tbody>
		</table>
	</main>
	<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</html>