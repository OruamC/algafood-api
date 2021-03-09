create table algafood.estado(
	id bigint not null auto_increment,
	nome varchar(80) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;

insert into algafood.estado (nome) select distinct 
algafood.cidade.nome_estado from algafood.cidade;

alter table algafood.cidade add column estado_id bigint not null;

update algafood.cidade set algafood.cidade.estado_id = (select algafood.estado.id from algafood.estado where algafood.estado.nome = algafood.cidade.nome_estado);

alter table algafood.cidade add constraint fk_cidade_estado
foreign key (estado_id) references algafood.estado (id);

alter table algafood.cidade drop column nome_estado;

alter table algafood.cidade change nome_cidade nome varchar(80) not null;