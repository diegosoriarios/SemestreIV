create or replace function attValores() returns trigger as $$
declare
	valor double precision;
begin
	select preco from mercadorias where codigo = new.mercadoria into valor;
	new.valor_unitario = valor;
	new.valor_total = new.qtde*new.valor_unitario;
	return new;
end;
$$
language 'plpgsql';

create or replace function attEstoque() returns trigger as $$
declare
begin
	if(tg_op='INSERT') then
		if(new.qtde is not null) then
			update mercadorias set estoque = (estoque - new.qtde) where codigo = new.mercadoria;
		end if;
		update vendas set valor_total = (valor_total + new.valor_total) where codigo = new.venda;
	end if;
	
	if(tg_op='UPDATE') then
		if(new.qtde is not null) then
			update mercadorias set estoque = estoque + old.qtde - new.qtde where codigo = new.mercadoria;
		end if;
		update vendas set valor_total = (valor_total - old.valor_total + new.valor_total) where codigo = new.venda;
		return new;
	end if;
	
	if(tg_op='DELETE') then
		if(old.qtde is not null and old.qtde > 0) then
			update mercadorias set estoque = estoque + old.qtde where codigo = old.mercadoria;
		end if;
		update vendas set valor_total = (valor_total - old.valor_total) where codigo = old.venda;
		return old;
	end if;
end;
$$
language 'plpgsql';

--INSERT INTO mercadorias(codigo, descricao, preco, tipo, estoque)
--VALUES (2, 'Mouse', 15.00, 's', 10);

create trigger vendas_mercadorias_biu
before insert or update ON vendas_mercadorias
for each row execute procedure attValores();

--select * from public.vendas
--order by codigo asc

create or replace function removeEstoque() returns trigger as $$
declare
begin
	if(tg_op='INSERT') then
		if(new.qtde is not null) then
			update mercadorias set estoque = (estoque + new.qtde) where codigo = new.mercadoria;
		end if;
		update compras set valor_total = (valor_total + new.valor_total) where codigo = new.compra;
	end if;
	
	if(tg_op='UPDATE') then
		if(new.qtde is not null) then
			update mercadorias set estoque = estoque - old.qtde + new.qtde where codigo = new.mercadoria;
		end if;
		update compras set valor_total = (valor_total - old.valor_total + new.valor_total) where codigo = new.compra;
		return new;
	end if;
	
	if(tg_op='DELETE') then
		if(old.qtde is not null and old.qtde > 0) then
			update mercadorias set estoque = estoque - old.qtde where codigo = old.mercadoria;
		end if;
		update compras set valor_total = (valor_total - old.valor_total) where codigo = old.compra;
		return old;
	end if;
end;
$$
language 'plpgsql';

create trigger remove_do_estoque
before insert or update ON compras_mercadorias
for each row execute procedure removeEstoque();