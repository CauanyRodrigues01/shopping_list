### Lista de Compras

1. **Escopo do Projeto**
    
    **Objetivo:** Desenvolver um aplicativo de lista de compras que permita aos usuários criar e gerenciar uma lista personalizada de itens de compras, armazenando informações detalhadas sobre os itens, como nome, quantidade e status de conclusão. O aplicativo permitirá que o usuário adicione itens, marque-os como comprados, e acompanhe a sua lista de compras de forma simples e eficiente.
    
    **Problema:** Consumidores frequentemente têm dificuldades para organizar e controlar os itens que precisam comprar, resultando em confusão e perda de tempo durante as compras. Além disso, há uma falta de facilidade para acompanhar a conclusão dos itens e gerenciar listas em várias plataformas.
    
    **Funcionalidades:**
    
    - Adicionar novos itens à lista.
    - Remover itens da lista.
    - Marcar itens como comprados.
    - Exibir todos os itens na lista.
    - Acompanhar a quantidade de itens desejados.
    - Gerenciar o status de conclusão de cada item na lista.
2. **Ferramentas e Tecnologias**
    - **IDE:** Eclipse
    - **Controle de Versão:** Git
    - **Linguagem e Frameworks:** Java
3. **Estrutura do Projeto**
    
    **Arquitetura:** O projeto seguirá o padrão de arquitetura **MVC (Model-View-Controller)**, que permite separar a lógica de negócios, a interface do usuário e a interação com dados de maneira clara e eficaz. Além disso, será utilizado o padrão **DAO (Data Access Object)** para gerenciar a persistência dos dados, separando a lógica de acesso aos dados da lógica de negócio.
    
    - **MVC:**
        - **Model:** Representa os dados e a lógica de negócios, incluindo as classes **UserModel** e **ItemModel**.
        - **View:** Interface do usuário, responsável pela interação com o usuário e exibição dos dados.
        - **Controller:** Gerencia a interação entre o modelo e a visualização, recebendo as entradas do usuário e atualizando o modelo e a visualização conforme necessário.
    - **DAO:** O DAO será responsável pela persistência dos dados, realizando operações de CRUD (Criar, Ler, Atualizar, Excluir) no banco de dados, garantindo que a lógica de acesso aos dados seja mantida separada da lógica de negócios.
4. **Diagrama UML de Classe**
    
    A seguir está a estrutura do projeto representada pelas classes **UserModel** e **ItemModel**: