<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="model.Contato" %>
	<%Contato contato = (Contato) request.getAttribute("contato"); %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon">
	<title>Atualização de Dados</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	
	<main>
		<h1>Atualizar Contato</h1>
		
		<form name="formContato" action="update">
			<input type="text" name="id" id="caixa3" readonly value="<%=contato.getId()%>"/>
			<input type="text" name="nome" placeholder="Nome Completo" class="caixa1"value="<%=contato.getNome()%>">
			<input type="text" name="fone" placeholder="Fone" class="caixa2" value="<%=contato.getFone()%>">
			<input type="text" name="email" placeholder="E-mail" class="caixa1" value="<%=contato.getEmail()%>">
			<input type="button" class="botao1" value="Alterar" onclick="validar()">
		</form>
		<script type="text/javascript" src="scripts/validador.js"></script>
	</main>
</body>
</html>