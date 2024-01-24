create database alunos;

use alunos;

create table HistoricoPeso (
id int AUTO_INCREMENT primary key,
cpf int(10) not null,
nome varchar(40) not null,
datanasc date,
data date not null,
peso int not null,
altura int
);

