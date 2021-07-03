/**
 * Validação de formulário
 */

function validar(){
	let nome = formContato.nome.value
	let fone = formContato.fone.value
	if(nome === ""){
		alert("Preencha o campo Nome Completo")
		formContato.nome.focus()
		return false
	}else if(fone === ""){
		alert("Preencha o campo Fone")
		formContato.fone.focus()
		return false
	} else {
		document.forms["formContato"].submit()
	}
}