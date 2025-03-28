USE WishList;

-- Inserir um usuário
INSERT INTO User (nome, nick_name, email, password_hash)
VALUES ('Ana Souza', 'anasouza', 'ana@email.com', 'senha_hash');

-- Inserir duas listas de desejos
INSERT INTO WishList (nome, user_id) VALUES 
('Lista de Aniversário', 1),
('Lista de Natal', 1);

-- Inserir desejos na primeira lista
INSERT INTO Wish (nome, description, category, link, price, image, wish_list_id) VALUES
('Smartwatch', 'Monitoramento de saúde', 'Eletrônicos', 'http://loja.com/smartwatch', 500.00, 'img/smartwatch.jpg', 1),
('Livro Java', 'Livro de programação em Java', 'Educação', 'http://loja.com/java', 120.00, 'img/java.jpg', 1);

-- Inserir desejos na segunda lista
INSERT INTO Wish (nome, description, category, link, price, image, wish_list_id) VALUES
('Fones Bluetooth', 'Fones sem fio', 'Eletrônicos', 'http://loja.com/fones', 250.00, 'img/fones.jpg', 2),
('Cafeteira', 'Cafeteira elétrica', 'Eletrodomésticos', 'http://loja.com/cafeteira', 300.00, 'img/cafeteira.jpg', 2);
