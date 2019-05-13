select * from vendas;
select * from creditos;

alter table vendas add prazo integer not null default 0;
alter table vendas add parcelas integer not null default 1;

create or replace function gera_creditos(p_venda integer)
returns void
as
$$
	declare v_data date;
		    v_valor numeric(10,2);
		    v_prazo integer;
		    v_parcelas integer;
		    v_condicao text;
		    v_count integer;
begin

	select data, valor_total, prazo, parcelas, condicao_pagamento
	from vendas
	where codigo = p_venda
	into v_data, v_valor, v_prazo, v_parcelas, v_condicao;

	if (v_condicao='v') then
		insert into creditos values
        (1,p_venda,v_data, v_valor, v_data);
	else
		v_count=1;
		while (v_count<=v_parcelas) loop
			insert into creditos values 
            (v_count,p_venda,v_data+(v_prazo*v_count),
             v_valor/v_parcelas,null);
			v_count=v_count+1;
		end loop;
	end if;

end;
$$
language 'plpgsql';

select * from vendas;
select * from creditos;

select gera_creditos(2)
from vendas
