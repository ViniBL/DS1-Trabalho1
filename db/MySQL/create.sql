drop database if exists Cebola;

create database Cebola;

use Cebola;

create table Usuario(
  id bigint not null auto_increment, 
  nome varchar(256) not null, 
  login varchar(20) not null unique, 
  senha varchar(64) not null, 
  papel varchar(10), 
  primary key (id));

insert into Usuario(nome, login, senha, papel) values ('Administrador', 'admin', 'admin', 'ADMIN');

insert into Usuario(nome, login, senha, papel) values ('Usuario', 'user', 'user', 'USER');

create table Cliente(
  id bigint not null auto_increment,
  email varchar(100) not null unique,
  senha varchar(64) not null,
  cpf varchar(11) not null unique,
  nome varchar(256) not null,
  telefone varchar(14) not null, 
  sexo varchar(1) not null,
  data_nascimento date not null,
  primary key (id)
);

create table Agencia(
  id bigint not null auto_increment,
  email varchar(100) not null unique,
  senha varchar(64) not null,
  cnpj varchar(18) not null unique,
  nome varchar (256) not null,
  descricao varchar(256) not null,
  primary key (id)
);

create table Destino(
  id bigint not null auto_increment,
  cidade varchar(100) not null,
  estado varchar(100) not null,
  pais varchar(100) not null,
  primary key (id)
);

create table Fotos_destino(
  id bigint not null auto_increment,
  id_destino bigint not null,
  nome varchar(100) not null,
  url_foto varchar(256) not null,
  primary key (id),
  foreign key (id_destino) references Destino(id)
);

create table Pacote(
  id bigint not null auto_increment,
  cnpj_agencia varchar(18) not null,
  id_destino bigint not null,
  data_partida date not null,
  duracao int not null,
  valor float not null,
  descricao varchar(256) not null,
  primary key(id),
  foreign key (cnpj_agencia) references Agencia(cnpj),
  foreign key (id_destino) references Destino(id)
);


insert into Cliente(email, senha, cpf, nome, telefone, sexo, data_nascimento) values ('aaa@hotmail.com', 'aaa', '12345678910', 'Aaa', '+5516991234567', 'M', '2001-09-11');

insert into Agencia(email, senha, cnpj, nome, descricao) values ('agencia1@hotmail.com', 'agencia1', '123456789012345678', 'Agencia Um', 'Agencia de Turismo Um, a numero 1 do Brasil"');

insert into Destino(cidade, estado, pais) values ('São Carlos', 'São Paulo', 'Brasil');
insert into Destino(cidade, estado, pais) values ('São Paulo', 'São Paulo', 'Brasil');

insert into Fotos_destino(id_destino, nome, url_foto) values (1, 'foto1_saocarlos', 'https://www.viajoteca.com/wp-content/uploads/2020/11/sao-carlos-SP-1.jpeg');

insert into Fotos_destino(id_destino, nome, url_foto) values (1, 'foto2_saocarlos', 'https://emc.acidadeon.com/dbimagens/sao_carlos_790x444_09092020090902.jpg');

insert into Fotos_destino(id_destino, nome, url_foto) values (2, 'foto1_saopaulo', 'https://viagemeturismo.abril.com.br/wp-content/uploads/2017/12/istock-842960000.jpg');

insert into Pacote(cnpj_agencia, id_destino, data_partida, duracao, valor, descricao) values ('123456789012345678', 1, '2023-07-28', 7, 415.56, 'Viagem no ano que vem para São Carlos' );

insert into Pacote(cnpj_agencia, id_destino, data_partida, duracao, valor, descricao) values ('123456789012345678', 2, '2023-07-28', 7, 415.56, 'Viagem no ano que vem para São Paulo' );


/*select * from Usuario;
select * from Cliente;
select * from Agencia;
select * from Destino;
select * from Fotos_destino;
select * from Fotos_destino where id_destino=1;
select * from Pacote;*/
