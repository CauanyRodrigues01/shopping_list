## Lista de Desejos

### 1. Escopo do Projeto

**Objetivo:**
Desenvolver um aplicativo de lista de desejos de compras que permita aos usuários criar e gerenciar múltiplas listas personalizadas, armazenando itens de lojas online com informações detalhadas. O aplicativo facilitará o acompanhamento de desejos, a organização por categorias e o compartilhamento com outras pessoas.

**Problema:**
Consumidores têm dificuldade em organizar e acompanhar itens desejados em diversas plataformas online, resultando em perda de controle, dificuldade de comparação de preços e compartilhamento limitado.

### **Funcionalidades:**
- Adicionar novos itens à lista.
- Remover itens da lista.
- Marcar itens como concluídos.
- Exibir todos os itens.
- Categorizar itens na lista.
- Armazenar links e preços de itens de lojas online.
- Compartilhar a lista de desejos.
- Criar múltiplas listas de desejos por usuário.

### 2. Ferramentas e Tecnologias

- **IDE:** Eclipse
- **Controle de Versão:** Git
- **Linguagem e Frameworks:**
  - Java

### 3. Estrutura do Projeto

**Arquitetura:**
Adotaremos o padrão de arquitetura MVC (Model-View-Controller) para organizar o projeto, garantindo a separação entre a lógica de negócios, a interface do usuário e a interação com dados.

### 4. Análise de Requisitos

**Entendimento do Escopo:**
O aplicativo permite que o usuário crie uma conta, adicione itens à sua lista de desejos, e, ao navegar pelas lojas online, armazene links e preços desses itens na lista. O usuário poderá categorizar itens, removê-los, marcar como concluídos e compartilhar a lista com outros usuários. Além disso, cada usuário poderá criar múltiplas listas de desejos independentes.

**Funcionalidades Principais:**

1. **Gerenciamento de Usuários:**
   - Registro de novos usuários.
   - Autenticação (login) de usuários existentes.
   - Gerenciamento de informações do perfil.

2. **Gerenciamento de Listas de Desejos:**
   - Criação de múltiplas listas de desejos por usuário.
   - Adicionar, editar e excluir itens em qualquer lista.
   - Categorizar itens dentro das listas.
   - Marcar itens como concluídos.

3. **Compartilhamento de Listas:**
   - Compartilhar listas com outros usuários.
   - Gerar um link exclusivo para cada lista compartilhável.

### 5. Diagrama UML de Classe

![](https://github.com/CauanyRodrigues01/Wish-list/blob/main/WishList.svg)