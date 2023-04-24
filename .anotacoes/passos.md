## Etapas do Projeto ##

 ##### Passo 1: #####
Quando o usuário clica em novo contato, o usuário vai direto pra página **novo.html**.
##### Passo 2: #####
Dentro do documento **novo.html** o usuário vai colocar em um formulário, os dados do novo contato. Os campos nome e telefone são obrigatórios. Ao apertar no botão do form, antes do formulário enviar a requisição para a camada controller **(Controller.java)**, será feita uma validação dos campos obrigatórios com um documento **validador.js**.
##### Passo 3: #####
O documento **validador.js** faz a validação e retorna ao documento **novo.html**.
##### Passo 4: #####
Após a validação, os dados preenchidos no formulário são enviados para o servlet da camada controller **(Controller.java)**.
##### Passo 5: #####
A camada controller recebe a requisição e os dados e encaminha para camada model e armazena na **classe JavaBeans** os dados (nome, telefone e email) nas variáveis.
##### Passo 6: #####
Depois de enviar os dados pra classe JavaBeans, a camada controller invoca dentro da camada model e **dentro da classe DAO** o método que irá inserir o novo contato no banco de dados.
##### Passo 7: #####
A classe JavaBeans retorna esses dados ao método da classe DAO.
##### Passo 8: #####
Tendo recebeido os dados do novo contato, o método executa a inserção desses dados no banco.
##### Passo 9: #####
Após adicionar o novo contato no banco de dados, a camada controller redireciona para o documento **agenda.jsp**.


