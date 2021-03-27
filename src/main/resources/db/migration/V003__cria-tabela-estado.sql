create table estado(
	id bigint not null auto_increment,
	nome varchar(80) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;

insert into estado (nome) select distinct 
cidade.nome_estado from cidade;

alter table cidade add column estado_id bigint not null;

update cidade set cidade.estado_id = (select estado.id from estado where estado.nome = cidade.nome_estado);

alter table cidade add constraint fk_cidade_estado
foreign key (estado_id) references estado (id);

alter table cidade drop column nome_estado;

alter table cidade change nome_cidade nome varchar(80) not null;