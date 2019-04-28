
INSERT INTO classroom VALUES (1, '1º Ano'),
						(2, '2º Ano'),
						(3, '3º Ano'),
						(4, '4º Ano'),
						(5, '5º Ano'),
						(6, '6º Ano'),
						(7, '7º Ano'),
						(8, '8º Ano'),
						(9, '9º Ano');
						
INSERT INTO lesson VALUES (1, 'Português'),
					(2, 'Matemática'),
					(3, 'Geografia'),
					(4, 'História'),
					(5, 'Física'),
					(6, 'Química'),
					(7, 'Redação');


INSERT INTO country VALUES (1,'Brasil');
INSERT INTO state VALUES (1,'Acre','AC',1),(2,'Alagoas','AL',1),(3,'Amapá','AP',1),(4,'Amazonas','AM',1),(5,'Bahia','BA',1),(6,'Ceará','CE',1),(7,'Distrito Federal','DF',1),(8,'Espírito Santo','ES',1),(9,'Goiás','GO',1),(10,'Maranhão','MA',1),(11,'Mato Grosso','MT',1),(12,'Mato Grosso do Sul','MS',1),(13,'Minas Gerais','NG',1),(14,'Pará','PA',1),(15,'Paraíba','PB',1),(16,'Paraná','PR',1),(17,'Pernambuco','PE',1),(18,'Piauí','PI',1),(19,'Rio de Janeiro','RJ',1),(20,'Rio Grande do Norte','RN',1),(21,'Rio Grande do Sul','RS',1),(22,'Rondônia','RO',1),(23,'Roraima','RR',1),(24,'Santa Catarina','SC',1),(25,'São Paulo','SP',1),(26,'Sergipe','SE',1),(27,'Tocantins','TO',1);
INSERT INTO city VALUES (1,'Fortaleza','65774', 6);
INSERT INTO neighborhood VALUES (1, 'Messejana', 1);
INSERT INTO street VALUES (1, 'Rua Inacio Oriá','60840460',null, 'RUA', 1);



INSERT INTO student (name, shortname, cpf, rg, birthday, email, email_notification, phone_1, phone_2, note, is_note, number_home, complement_home, active, number_register, id_city, id_street) VALUES 
('aluno de teste', 'teste','49469391055', '6919812198', '2000-01-01'null, false, null, null, null, false, '12', null, true', null, 1, 1);