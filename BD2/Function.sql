create or replace function F_soma (in p_valor1 int, int, out p_res int)
as
$$
	declare v_valor1 int;
begin
	v_valor1=p_valor1;
	p_res=v_valor1+$2;
end;
$$
language 'plpgsql';

select f_soma(10,20);

-- 1 --

create or replace function mediaAt (in p_valor1 int, int, out media float)
as
$$
	declare soma int;
begin
	soma=p_valor1+$2;
	media=soma/2;
end;
$$
language 'plpgsql';

select mediaAt(10,20);

-- 2 --

create or replace function numSinal (int, out res text)
as
$$
begin
	if $1 = 0 then
		res = 'zero';
	elsif $1 > 0 then
		res = 'maior que zero';
	else
		res = 'menor que zero';
	end if;
end;
$$
language 'plpgsql';

select numSinal(1);

-- 3 --
create or replace function parOuImpar (int, out res text)
as
$$
begin
	if $1 % 2 = 0 then
		res = 'par';
	else
		res = 'impar';
	end if;
end;
$$
language 'plpgsql';

select parOuImpar(589105);