<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="model.entities.Contact" %>
	<%Contact contact = (Contact) request.getAttribute("contact"); %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="imagens/favicon.png" type="image/x-icon">
	<title>Atualização de Dados</title>
	<link rel="stylesheet" href="style.css">
	<script type="text/javascript" src="scripts/validador.js"></script>
</head>
<body>
	
	<main>
		<h1>Atualizar Contato</h1>
		
		<form name="formContact" action="update">
			<input type="text" name="id" id="caixa3" readonly value="<%=contact.getId()%>"/>
			<input type="text" name="name" placeholder="Nome Completo" class="caixa1"value="<%=contact.getName()%>">
			<input type="text" name="phone" placeholder="Fone" class="caixa2" value="<%=contact.getPhone()%>">
			<input type="text" name="email" placeholder="E-mail" class="caixa1" value="<%=contact.getEmail()%>">
			
			<input type="button" class="botao1" value="Alterar" onclick="validateContact()">
		</form>
		
	</main>
</body>
</html>