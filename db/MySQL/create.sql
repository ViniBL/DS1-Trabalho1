drop database if exists Cebola;

create database Cebola;

use Cebola;

create table Usuario(id_usuario bigint not null auto_increment, nome varchar(256) not null, login varchar(100) not null unique, senha varchar(64) not null, papel varchar(10), primary key (id_usuario));

insert into Usuario(nome, login, senha, papel) values ('Administrador', 'admin', 'admin', 'ADMIN');

insert into Usuario(nome, login, senha, papel) values ('Usuario', 'user', 'user', 'USER');

insert into Usuario(nome, login, senha, papel) values ('Agencia', 'agencia', 'agencia', 'AGENCIA');

create table Cliente(id_cliente bigint not null auto_increment, id_usuario bigint not null, cpf varchar(11) not null unique, telefone varchar(14) not null, sexo varchar(1) not null, data_nascimento varchar(20) not null, primary key (id_cliente), foreign key (id_usuario) references Usuario(id_usuario), CONSTRAINT sexo_check CHECK (sexo IN('M', 'F', 'N')));

create table Agencia(id_agencia bigint not null auto_increment,id_usuario bigint not null,nome varchar(256) not null,cnpj varchar(18) not null unique,descricao varchar(256) not null,primary key (id_agencia),foreign key (id_usuario) references Usuario(id_usuario));

create table Destino(id_destino bigint not null auto_increment,cidade varchar(100) not null,estado varchar(100) not null,pais varchar(100) not null,primary key (id_destino));

create table Fotos_destino(id_fotos bigint not null auto_increment, id_destino bigint not null, url_foto varchar(256) not null, primary key (id_fotos), foreign key (id_destino) references Destino(id_destino));

create table Pacote(id_pacote bigint not null auto_increment, id_agencia bigint not null, id_destino bigint not null, data_partida varchar(10) not null, duracao int not null, valor float not null, descricao varchar(256) not null, primary key(id_pacote), foreign key (id_agencia) references Agencia(id_agencia), foreign key (id_destino) references Destino(id_destino));


create table Pacotes_adquiridos(id_pacote_adquirido bigint not null auto_increment, id_cliente bigint not null, id_pacote bigInt not null, status varchar(9), primary key(id_pacote_adquirido), CONSTRAINT status_check CHECK (status IN('CANCELADO', 'ADQUIRIDO')));

insert into Cliente(id_usuario, cpf,  telefone, sexo, data_nascimento) values (2, '12345678910',  '+5516991234567', 'M', '11-09-2001');

insert into Agencia(id_usuario, nome, cnpj, descricao) values ( 3, 'agencia1', '123456789012345678',  'Agencia de Turismo 1');
insert into Agencia(id_usuario, nome, cnpj, descricao) values ( 3, 'agencia2', '123456789012345679',  'Agencia de Turismo 2');
insert into Agencia(id_usuario, nome, cnpj, descricao) values ( 3, 'agencia3', '123456789012345670',  'Agencia de Turismo 3');

insert into Destino(cidade, estado, pais) values ('São Carlos', 'São Paulo', 'Brasil');
insert into Destino(cidade, estado, pais) values ('São Paulo', 'São Paulo', 'Brasil');

insert into Fotos_destino(id_destino, url_foto) values (1,  'SAOCARLOS1');

insert into Fotos_destino(id_destino, url_foto) values (1,  'SAOCARLOS2');

insert into Fotos_destino(id_destino, url_foto) values (2, 'SAOPAULO1');

insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (1, 1, '10-08-2022', 7, 415.56, 'Executivo' );
insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (1, 2, '05-08-2022', 7, 415.56, 'Convencional' );
insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (2, 1, '10-08-2022', 7, 415.56, 'Semi Leito' );
insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (2, 2, '05-08-2022', 7, 415.56, 'Executivo' );
insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (3, 1, '10-08-2022', 7, 415.56, 'Convencional' );
insert into Pacote(id_agencia, id_destino, data_partida, duracao, valor, descricao) values (3, 2, '05-08-2022', 7, 415.56, 'Semi Leito' );


insert into Pacotes_adquiridos(id_cliente, id_pacote, status) values (1, 1, 'ADQUIRIDO');

insert into Pacotes_adquiridos(id_cliente, id_pacote, status) values (1, 2, 'ADQUIRIDO');

/*SELECT * from Pacote p, Agencia a, Destino d, Usuario u where p.id_agencia = a.id_agencia and p.id_destino = d.id_destino and a.id_usuario= u.id_usuario and d.cidade="São Carlos" order by p.id_pacote;


select * from Usuario;
select * from Cliente;
select * from Agencia;
select * from Destino;
select * from Fotos_destino;
select * from Fotos_destino where id_destino=1;
select * from Pacote;

select * from Pacotes_adquiridos;

update Pacotes_adquiridos set status='CANCELADO' WHERE id_pacote=2;


select * from Pacotes_adquiridos;
*/
