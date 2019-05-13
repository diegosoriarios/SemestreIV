package br.edu.ifsul.lpoo.agencia.model;

import br.edu.ifsul.lpoo.agencia.model.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T11:01:02")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile SingularAttribute<Estado, String> uf;
    public static volatile SingularAttribute<Estado, Integer> codigo;
    public static volatile SingularAttribute<Estado, String> nome;
    public static volatile SingularAttribute<Estado, Pais> pais;

}