package br.edu.ifsul.lpoo.agencia.model;

import br.edu.ifsul.lpoo.agencia.model.AeroPorto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T11:01:02")
@StaticMetamodel(Trecho.class)
public class Trecho_ { 

    public static volatile SingularAttribute<Trecho, Integer> tempoDuracaoMin;
    public static volatile SingularAttribute<Trecho, Integer> codigo;
    public static volatile SingularAttribute<Trecho, AeroPorto> chegada;
    public static volatile SingularAttribute<Trecho, String> descricao;
    public static volatile SingularAttribute<Trecho, AeroPorto> partida;

}