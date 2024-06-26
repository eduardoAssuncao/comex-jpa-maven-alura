INSERT INTO tb_cliente (nome, cpf, email, ativo) VALUES ('Eduardo Assunção', '61907738842', 'eduardo@gmail.com', true);
INSERT INTO tb_cliente (nome, cpf, email, ativo) VALUES ('Ana Paula Rabelo', '87876546542', 'ana@gmail.com', true);
INSERT INTO tb_cliente (nome, cpf, email, ativo) VALUES ('Matheus Rhazek', '45345543213', 'rhazek@gmail.com', true);
INSERT INTO tb_cliente (nome, cpf, email, ativo) VALUES ('Elivelton B.', '90874635213', 'elivelton@gmail.com', true);
INSERT INTO tb_cliente (nome, cpf, email, ativo) VALUES ('Caian K.', '87848776762', 'caian@gmail.com', true);

INSERT INTO tb_categoria (nome, descricao, ativo) VALUES ('ELETRÔNICO', 'Produtos eletrônicos', true);
INSERT INTO tb_categoria (nome, descricao, ativo) VALUES ('LAZER', 'Produtos para fins de diversão e descanso', true);
INSERT INTO tb_categoria (nome, descricao, ativo) VALUES ('CASA', 'Produtos de toda categoria para casa', true);

INSERT INTO tb_produto (nome, descricao, valor, ativo) VALUES ('Placa de vídeo', 'Placa de vídeo gráfica Nvidia', 4500.0, true);
INSERT INTO tb_produto (nome, descricao, valor, ativo) VALUES ('Processador', 'Processador AMD', 2900.0, true);
INSERT INTO tb_produto (nome, descricao, valor, ativo) VALUES ('Memória', 'Memória DDR4 16Gb', 500.0, true);
INSERT INTO tb_produto (nome, descricao, valor, ativo) VALUES ('Mouse', 'Mouse Logitech Gpro', 450.0, true);
INSERT INTO tb_produto (nome, descricao, valor, ativo) VALUES ('Teclado', 'Teclado mecânico Logitech Switch Red', 348.0, true);
INSERT INTO tb_produto (nome, descricao, valor, ativo) VALUES ('Headset', 'Headset HyperX', 520.0, true);

INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (1, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (2, 3);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (3, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (4, 2);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (5, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (6, 1);