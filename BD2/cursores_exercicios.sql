create or replace function pagar_contas(int, date, date)
returns void
as
$$
declare
	cur_contas cursor for
		select * from creditos;
	rec_contas record;
	cliente integer;
	aux_venda integer;
begin
	open cur_contas;
	
	loop
		fetch cur_contas into rec_contas;
		exit when not found;
		if $1 is not null and $2 is null and $3 is null then
			aux_venda = codigo from vendas where pessoa_cliente = $1;
			UPDATE creditos
			SET data_pagamento = current_date
			WHERE rec_contas.venda = aux_venda and rec_contas.data_pagamento is null;
		elsif $1 is null and $2 is not null and $3 is null then
			UPDATE creditos
			SET data_pagamento = current_date
			WHERE data_vencimento >= $2 and rec_contas.data_pagamento is null;
		elsif $1 is null and $2 is null and $3 is not null then
			UPDATE creditos
			SET data_pagamento = current_date
			WHERE data_vencimento >= $3 and rec_contas.data_pagamento is null;
		elsif $1 is null and $2 is not null and $3 is not null then
			UPDATE creditos
			SET data_pagamento = current_date
			WHERE data_vencimento between $2 and $3 and rec_contas.data_pagamento is null;
		else
			cliente = pessoa_cliente from vendas where pessoa_cliente = $1;
			aux_venda = codigo from creditos where venda = cliente and data_vencimento between $2 and $3;
			UPDATE creditos
			SET data_pagamento = current_date
			WHERE rec_contas.venda = aux_venda and data_vencimento between $2 and $3 and rec_contas.data_pagamento is null;
		end if;	
		
	end loop;
	
	close cur_contas;
end;
$$
language plpgsql;

select * from creditos

INSERT INTO creditos(codigo, venda, data_vencimento, valor, data_pagamento)
VALUES (3, 1, '2019-06-07', 50.00, null);
INSERT INTO creditos(codigo, venda, data_vencimento, valor, data_pagamento)
VALUES (4, 1, '2019-07-07', 50.00, null);
INSERT INTO creditos(codigo, venda, data_vencimento, valor, data_pagamento)
VALUES (5, 1, '2019-08-07', 50.00, null);

select pagar_contas(null, '2019-06-05', '2019-12-10')