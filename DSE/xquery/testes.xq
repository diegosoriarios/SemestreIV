xquery version "1.0";
<resultado>
{
    (: doc("/home/diego/Documentos/aula/DSE/xquery")/bookstore/book/title :)
    (: doc("/home/diego/Documentos/aula/DSE/xquery")/bookstore/book[price<30] :)
    
    (:  for $x in doc("/home/diego/Documentos/aula/DSE/xquery")/bookstore/book
        where $x/price>=30
        return $x
    :)

    (:  ORDENANDO
        for $x in doc("/home/diego/Documentos/aula/DSE/xquery")/bookstore/book
        where $x/price>=30
        order by $x/price
        return $x
    :)

    (:  LISTAR CATEGORIA WEB
        for $x in doc("/home/diego/Documentos/aula/DSE/xquery")/bookstore/book
        where $x/@category="WEB"
        return $x/author
    :)

    (:  LET
        {
            let $p := doc("/home/diego/Documentos/aula/DSE/xquery")//book/price
            return
                <dados>
                    <quantidade>{count($p)}</quantidade>
                    <vmin>{min($p)}</vmin>
                    <vmax>{max($p)}</vmax>
                    <vmedia>{avg($p)}</vmedia>
                    <vtotal>{sum($p)}</vtotal>
                </dados>
        }

        {
            for $x in doc("/home/diego/Documentos/aula/DSE/xquery")/bookstore/book
            let $p := $x/price - ($x/price * 0.05)
            return
                <livro>
                    <titulo> {$x/title/text()}</titulo>
                    <preco-normal> {$x/price/text()} </preco-normal>
                    <preco-desconto> {$p} </preco-desconto>
                </livro>
        }
    :)

    (:  LISTAR AUTORES QUE COMEÇAM COM A LETRA J
        for $b in doc("/home/diego/Documentos/aula/DSE/xquery/bookstore.xml")/bookstore/book        where some $a in $b/author
        satisfies starts-with($a, 'J')
        return $b/title
    :)

    (:  LISTAR LIVROS QUE TODOS AUTORES COMEÇAM COM A LETRA J
        for $b in doc("/home/diego/Documentos/aula/DSE/xquery/bookstore.xml")/bookstore/book
        where every $a in $b/author
        satisfies starts-with($a, 'J')
        return $b/title
    :)

    (:  ORDENAR DECRESCENTE
        for $b in doc("/home/diego/Documentos/aula/DSE/xquery/bookstore.xml")/bookstore/book
        order by $x/price descending
        return $x
    :)

    (:  RETORNAR UMA LISTA DE ELEMENTOS
        for $x in doc("/home/diego/Documentos/aula/DSE/xquery/bookstore.xml")/bookstore/book
        return
            <livro>
                {$x/title, $x/year, $x/price}
            </livro>
    :)
}
</resultado>