/**
 * Validação de formulário
 */

function validateContact(){
	let name = formContact.name.value
	let phone = formContact.phone.value
	if(name === ""){
		alert("Preencha o campo Nome Completo")
		formContact.name.focus()
		return false
	}else if(phone === ""){
		alert("Preencha o campo Fone")
		formContact.phone.focus()
		return false
	} else {
		document.forms["formContact"].submit()
	}
}


	