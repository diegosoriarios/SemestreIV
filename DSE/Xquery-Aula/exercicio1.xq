xquery version "1.0";
<resultado>
{
    for $x in doc("/home/diego/Documentos/aula/DSE/Xquery-Aula/Exer_Query.xml")//filmes/filme
    where $x/codigo="00001"
    return 
        <filme>
            {$x/titulo, $x/exemplares}
        </filme>
}
{
    for $x in doc("/home/diego/Documentos/aula/DSE/Xquery-Aula/Exer_Query.xml")//filmes/filme
    let $codigo := $x/codigo - ($x/price * 0.05)
        return
            <livro>
                <titulo> {$x/titulo}</titulo>
                <preco-normal> {$x/price/text()} </preco-normal>
            </livro>
    
}
</resultado>