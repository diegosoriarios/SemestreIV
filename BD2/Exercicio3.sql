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