drop database if exists Cebola;

create database Cebola;

use Cebola;
create table Usuario(
  id_usuario bigint not null auto_increment, 
  login varchar(100) not null unique, 
  nome varchar(256) not null,
  senha varchar(64) not null, 
  perfil varchar(10) default 'USER', 
  primary key (id_usuario, nome),
  CONSTRAINT perfil_check CHECK (perfil IN('ADMIN', 'USER', 'AGENCIA'))
  );

insert into Usuario(nome, login, senha, perfil) values ('Administrador', 'admin', 'admin', 'ADMIN');

insert into Usuario(nome, login, senha, perfil) values ('Usuario', 'user', 'user', 'USER');

insert into Usuario(nome, login, senha, perfil) values ('Agencia', 'agencia', 'agencia', 'AGENCIA');

create table Cliente(
  id_cliente bigint not null auto_increment,
  id_usuario bigint not null,
  nome varchar(256) not null,
  cpf varchar(11) not null unique,
  telefone varchar(14) not null, 
  sexo varchar(1) not null,
  data_nascimento date not null,
  primary key (id_cliente),
  foreign key (id_usuario, nome) references Usuario(id_usuario, nome),
  CONSTRAINT sexo_check CHECK (sexo IN('M', 'F', 'N'))
);

create table Agencia(
  id_agencia bigint not null auto_increment,
  nome varchar(256) not null,
  id_usuario bigint not null,
  cnpj varchar(18) not null unique,
  descricao varchar(256) not null,
  primary key (id_agencia),
  foreign key (id_usuario, nome) references Usuario(id_usuario, nome)
);

create table Destino(
  id_destino bigint not null auto_increment,
  cidade varchar(100) not null,
  estado varchar(100) not null,
  pais varchar(100) not null,
  primary key (id_destino)
);

create table Fotos_destino(
  id_fotos bigint not null auto_increment,
  id_destino bigint not null,
  url_foto varchar(256) not null,
  primary key (id_fotos),
  foreign key (id_destino) references Destino(id_destino)
);

create table Pacote(
  id_pacote bigint not null auto_increment,
  id_agencia bigint not null,
  id_destino bigint not null,
  data_partida date not null,
  duracao int not null,
  valor decimal(8,2) not null,
  descricao varchar(256) not null,
  primary key(id_pacote),
  foreign key (id_agencia) references Agencia(id_agencia),
  foreign key (id_destino) references Destino(id_destino)
);



/*Pacote.data_partida, 
Pacote.duracao, 
Pacote.valor, 
Pacote.descricao,
Destino.cidade, 
Destino.estado, 
Destino.pais, 
Usuario.nome --NOME AGENCIA*/


create table Pacotes_adquiridos(
    id_pacote_adquirido bigint not null auto_increment, 
    id_cliente bigint not null,
    id_pacote bigInt not null,
    status varchar(9),
    primary key(id_pacote_adquirido),
    CONSTRAINT status_check CHECK (status IN('CANCELADO', 'ADQUIRIDO'))
);

insert into Cliente(id_usuario, nome, cpf,  telefone, sexo, data_nascimento) values (2, 'Usuario', '12345678910',  '+5516991234567', 'M', '2001-09-11');

insert into Agencia(id_usuario, nome, cnpj, descricao) values ( 3, 'Agencia', '123456789012345678',  'Agencia de Turismo Um, a numero 1 do Brasil');

insert into Destino(cidade, estado, pais) values ('São Carlos', 'São Paulo', 'Brasil');
insert into Destino(cidade, estado, pais) values ('São Paulo', 'São Paulo', 'Brasil');

insert into Fotos_destino(id_destino, url_foto) values (1,  'SAOCARLOS1');

insert into Fotos_destino(id_destino, url_foto) values (1,  'SAOCARLOS2');

insert into Fotos_destino(id_destino, url_foto) values (2, 'SAOPAULO1');

insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (1, 1, STR_TO_DATE('30/07/2022','%d/%m/%Y') , 7, 415.56, 'Viagem no ano que vem para São Carlos' );

insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (1, 2, STR_TO_DATE('11/02/2022','%d/%m/%Y'),  7, 50000.54, 'Viagem no ano que vem para São Paulo' );


insert into Pacotes_adquiridos(id_cliente, id_pacote, status) values (1, 1, 'ADQUIRIDO');

insert into Pacotes_adquiridos(id_cliente, id_pacote, status) values (1, 2, 'ADQUIRIDO');

/*select * from Usuario;
select * from Cliente;
select * from Agencia;
select * from Destino;
select * from Fotos_destino;
select * from Fotos_destino where id_destino=1;


select * from Pacote;
select * from Pacotes_adquiridos;
update Pacotes_adquiridos set status='CANCELADO' WHERE id_pacote=2;
select * from Pacotes_adquiridos;*/


/*consultas que serão utilizadas no sistema*/
/*requisito 4*/
select Agencia.nome, Pacote.data_partida, Pacote.duracao, Pacote.descricao, Destino.cidade, Destino.estado, Destino.pais, Pacote.valor FROM ((Pacote INNER JOIN Destino ON Pacote.id_destino=Destino.id_destino) INNER JOIN Agencia ON Pacote.id_agencia = Agencia.id_agencia);

select Agencia.nome, Pacote.data_partida, Pacote.duracao, Pacote.descricao, Destino.cidade, Destino.estado, Destino.pais, Pacote.valor FROM ((Pacote INNER JOIN Destino ON Pacote.id_destino=Destino.id_destino) INNER JOIN Agencia ON Pacote.id_agencia = Agencia.id_agencia) WHERE Destino.cidade ='São Carlos';

select Agencia.nome, Pacote.data_partida, Pacote.duracao, Pacote.descricao, Destino.cidade, Destino.estado, Destino.pais, Pacote.valor FROM ((Pacote INNER JOIN Destino ON Pacote.id_destino=Destino.id_destino) INNER JOIN Agencia ON Pacote.id_agencia = Agencia.id_agencia) WHERE Agencia.nome ='Agencia';

select Agencia.nome, Pacote.data_partida, Pacote.duracao, Pacote.descricao, Destino.cidade, Destino.estado, Destino.pais, Pacote.valor FROM ((Pacote INNER JOIN Destino ON Pacote.id_destino=Destino.id_destino) INNER JOIN Agencia ON Pacote.id_agencia = Agencia.id_agencia)  WHERE Pacote.data_partida > DATE'2001-09-11';



/*requisito 6*/


select Cliente.nome, Agencia.nome, Pacote.data_partida, Pacote.duracao, Pacote.descricao, Destino.cidade, Destino.estado, Destino.pais, Pacote.valor, Pacotes_adquiridos.status FROM ((((Pacote INNER JOIN Destino ON Pacote.id_destino=Destino.id_destino) INNER JOIN Agencia ON Pacote.id_agencia = Agencia.id_agencia) INNER JOIN Pacotes_adquiridos ON Pacotes_adquiridos.id_pacote = Pacote.id_pacote) INNER JOIN Cliente ON Pacotes_adquiridos.id_cliente = Cliente.id_cliente);


/* requisito 7 */

select Agencia.nome, Pacote.data_partida, Pacote.duracao, Pacote.descricao, Destino.cidade, Destino.estado, Destino.pais, Pacote.valor FROM ((Pacote INNER JOIN Destino ON Pacote.id_destino=Destino.id_destino) INNER JOIN Agencia ON Pacote.id_agencia = Agencia.id_agencia);/*colcocar WHERE Pacote.data_partida > getdate() do Java*/


/* requisito 8 */


/* consulta para verificar se pacote está há mais de 5 dias de distancia da data atual*/
select Pacote.data_partida FROM (Pacote INNER JOIN Pacotes_adquiridos ON Pacote.id_pacote=Pacotes_adquiridos.id_pacote);

update Pacotes_adquiridos set status='CANCELADO' WHERE id_pacote=2 ;