/**
 * Confirmação de exclusão de contato
 */

function confirmar(id){
	let resposta = confirm('Confirma a exclusão do contato de ID ' + id + '?')
	if(resposta === true) {
		window.location.href = 'delete?id='+id
	}
}