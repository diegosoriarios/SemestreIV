/*
create or replace function f_testa_atribuicao
(date,int, out p_dia int, out p_mes int, out p_ano int, out p_diaano int)
as
$$
begin
	p_dia=extract (day from $1);
    
    p_mes = extract(month from nascimento)
    		from pessoas
            where codigo=$2;
    
    select extract(year from nascimento), extract(doy from nascimento)
    from pessoas
    where codigo=$2
    into p_ano,p_diaano;

end;
$$
language 'plpgsql';

select f_testa_atribuicao('1973-04-22',1)

-- ** SOMENTE USAR ESTE TIPO DE ATRIBUICAO QUANDO O RESULTADO É SOMENTE UMA LINHA!
*/

--Exercicio 1
create or replace function maior_cod(out saida int)
as
$$
begin
	saida = max(codigo) from pessoas;
	if (saida is not null) then
		saida = saida+1;
	end if;
end;
$$
language 'plpgsql';

insert into pessoas(codigo, nome, tipo_pessoa) values(1, 'Diego', 'f');

select maior_cod();

--Exercicio 2
create or replace function calcula_idade(date, out idade int)
as
$$
	declare mes int;
	declare dia int;
	declare ano int;
	declare diaHoje int;
	declare mesHoje int;
	declare anoHoje int;
	declare aux int;
begin
	dia = extract (day from $1);
	mes = extract (month from $1);
	ano = extract (year from $1);
	diaHoje = extract (day from current_date);
	mesHoje = extract (month from current_date);
	anoHoje = extract (year from current_date);
	idade = anoHoje - ano;
	if (mes > mesHoje) then
		idade = idade-1;
	else if (dia > diaHoje) then
			idade = idade-1;	
		end if;
	end if;
end;
$$
language 'plpgsql';

select calcula_idade('1995-12-11');

--Exercicio 3
create or replace function retornaCPF_CNPJ(int, out pessoa varchar(14))
as
$$
	declare cliente_tipo char(1);
begin
	cliente_tipo = tipo_pessoa from pessoas where codigo = $1;
	if( cliente_tipo = 'f') then
		pessoa = cpf from pessoas where codigo = $1;
	else
		pessoa = cnpj from pessoas where codigo = $1;
	end if;
end;
$$
language 'plpgsql';

insert into pessoas(codigo, nome, tipo_pessoa, cpf, cnpj) values(5, 'Diego', 'f', '02432220099', '');
insert into pessoas(codigo, nome, tipo_pessoa, cpf, cnpj) values(7, 'Eu', 'j', '', '02432220099');

select retornaCPF_CNPJ(5);