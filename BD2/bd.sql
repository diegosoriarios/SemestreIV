create table pessoas(
codigo integer not null primary key,
nome varchar (50)	not null,
tipo_pessoa char(1) not null,
check (tipo_pessoa='f' or tipo_pessoa='c'));

create table vendas(
codigo integer	not null,
data	date	not null,
condicao_pagamento char(1)	not null,
check(condicao_pagamento='v' or condicao_pagamento='p'),
valor_total numeric(10,2) not null default 0,
pessoa_funcionario	integer not null,
pessoa_cliente integer references pessoas,
pessoa_tecnico integer,
primary key (codigo),
foreign key (pessoa_funcionario) references pessoas(codigo),
foreign key (pessoa_tecnico) references pessoas (codigo));	

create table creditos(
codigo integer	not null,
venda integer not null,
data_vencimento date not null,
valor numeric(10,2)	not null default 0,
data_pagamento date,
primary key (codigo,venda),
foreign key (venda) references vendas (codigo));

create table mercadorias(
codigo integer	not null,
descricao	varchar(100) not null,
preco numeric(10,2) not null default 0,
tipo	char(1) not null, 
check(tipo='p' or tipo='s'),
estoque	double precision not null default 0,
primary key (codigo));

create table vendas_mercadorias(
codigo bigserial not null,
venda integer	not null,
mercadoria integer not null,
qtde	double precision not null default 1,
valor_unitario	numeric(10,2)	not null default 0,
desconto	double precision not null default 0,
valor_total numeric(10,2) not null default 0,
primary key (codigo),
foreign key (venda) references vendas(codigo) on delete cascade,
foreign key (mercadoria) references mercadorias (codigo));

create table fornecedores(
codigo integer	not null,
descricao	varchar(100) not null,
primary key (codigo));

create table compras(
codigo integer	not null,
data	date	not null,
valor_total numeric(10,2) not null,
fornecedor integer not null,
nro_nota	varchar(50) not null,
primary key (codigo),
foreign key (fornecedor) references fornecedores (codigo));

create table compras_mercadorias(
codigo bigserial not null,
compra integer not null,
mercadoria integer not null,
qtde	double precision not null default 1,
valor_unitario numeric(10,2) not null default 0,
desconto	double precision not null default 0,
valor_total numeric(10,2) not null default 0,
primary key (codigo),
foreign key (compra) references compras (codigo) on delete cascade,
foreign key (mercadoria) references mercadorias (codigo));

create table debitos(
codigo integer	not null,
compra	integer	not null,
data_vencimento	date	not null,
valor numeric(10,2)	not null default 0,
data_pagamento	date,
primary key (codigo),
foreign key (compra) references compras (codigo));	
