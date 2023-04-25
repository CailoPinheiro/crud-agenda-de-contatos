/**
 * Confirmar a esclusao de um contato
 * @author Cailo Pinheiro
 * @param idcon
 */
function confirmarExclusao(idcon) {
    let resposta = confirm("Realmente quer excluir esse contato?");
    if(resposta === true) {
        window.location.href = "delete?idcon=" + idcon;
    }
}