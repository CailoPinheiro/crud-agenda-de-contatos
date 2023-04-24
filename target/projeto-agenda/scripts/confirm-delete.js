function confirmarExclusao(idcon) {
    let resposta = confirm("Realmente quer excluir esse contato?");
    if(resposta === true) {
        window.location.href = "delete?idcon=" + idcon;
    }
}