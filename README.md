# 🛒 Shopping List App

Um projeto Java com interface gráfica Swing e persistência de dados no MySQL para criar e gerenciar listas de compras de forma prática e segura.

## ✨ Funcionalidades

- ✅ Cadastro de itens com nome e quantidade  
- 📝 Edição e remoção de itens  
- 📋 Visualização dos itens em uma tabela  
- 👤 Cadastro e login de usuários com senha  
- 🗂 Organização dos dados por usuário  

## 🔐 Segurança

As senhas dos usuários são protegidas com **hash Argon2**, um dos algoritmos mais seguros atualmente. Isso garante que mesmo se os dados forem acessados indevidamente, as senhas não estarão visíveis em texto puro.

## 🛠️ Tecnologias e Conceitos Utilizados

- **Java 17+**  
- **Java Swing** (interface gráfica)
- **MySQL**
- **Padrão MVC + DAO**
  - `Model`: representa os dados (usuário, item)
  - `View`: interface com o usuário (formulários e tabelas)
  - `Controller`: gerencia a lógica entre os dados e a interface
  - `DAO (Data Access Object)`: responsável por persistir manipular o Banco de Dados 
- **Argon2** para hash de senhas
- 
## 💡 Motivação

O principal objetivo desse projeto foi aprender e aplicar o padrão **MVC + DAO** com Java e Swing. Entender essa arquitetura foi uma das minhas maiores motivações, pois ela é amplamente usada em projetos reais e ajuda a manter o código organizado, reutilizável e escalável.

Além disso, quis aplicar conceitos de segurança de forma simples e prática, implementando **hash de senhas com Argon2** desde o início do projeto.

## 📸 Prévia (GUI)

[em breve]

## 📦 Estrutura do Projeto

```less
src/
├── app/
│ └── Main.java
├── controller/
│ ├── ItemController.java
│ └── UserController.java
├── dao/
│ ├── ConnectionDBFactory.java
│ ├── ConnectionDBSingleton.java
│ ├── ItemDAO.java
│ └── UserDAO.java
├── exceptions/
│ ├── ConnectionException.java
│ ├── DuplicateItemNameException.java
│ ├── ItemNotFoundException.java
│ └── WishException.java
├── model/
│ ├── ItemModel.java
│ └── UserModel.java
├── utils/
│ └── PasswordUtils.java
└── view/
│ ├── AddItemView.java
│ ├── EditItemViewView.java
│ ├── AddUserView.java
│ ├── HomeView.java
│ ├── ItemListView.java
│ ├── LoginView.java
│ ├── RegistrationUserView.java
│ └── UserView.java
```

## **Diagrama UML de Classe**

A seguir está a estrutura do projeto representada pelas classes **UserModel** e **ItemModel**:

![ShoppingList - Page 1](https://github.com/user-attachments/assets/bf82f173-163e-47e2-b5da-10fad7c8f583)

## 🔗 Como executar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/CauanyTodrigues01/shopping_list.git
   ```
2. Abra o projeto no Eclipse, IntelliJ ou outro IDE Java

3. Execute a classe principal em `app/Main.java`

> Requisitos: JDK 17 ou superior

## Desenvolvido por Cauany Rodrigues
📧 [[Linkedin](https://www.linkedin.com/in/cauany-rodrigues-78700b193/)]
🔗 [GitHub](https://github.com/CauanyRodrigues01)
