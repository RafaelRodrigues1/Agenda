/**
 * 
 */

function validaUsuario(){
	let name = formUser.name.value
	let date = formUser.date.value
	let login = formUser.login.value
	let password = formUser.password.value
	let repeatPass = formUser.repeatPass.value

	if(name === "" || date === "" || login === "" || password === "" || repeatPass === ""){
		alert("Preencha todos os campos")
	} else if(password !== repeatPass){
		document.getElementById("errorPass").style.display = 'block';
	/*} else if(String.toString(senha).length < 8 || String.toString(senha).length > 16 ){
	console.log(String.toString(senha).length)
	alert("Senha precisa ter mais de 8 caracteres e menos que 16")*/
	} else {
		document.getElementById("errorPass").style.display = 'none';
		document.forms["formUser"].submit()
	}
}