package br.edu.ifsul.lpoo.agencia.model;

import br.edu.ifsul.lpoo.agencia.model.DiaSemana;
import br.edu.ifsul.lpoo.agencia.model.TipoAeronave;
import br.edu.ifsul.lpoo.agencia.model.Trecho;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T11:01:02")
@StaticMetamodel(Rota.class)
public class Rota_ { 

    public static volatile SingularAttribute<Rota, Integer> codigo;
    public static volatile SingularAttribute<Rota, DiaSemana> diaSemana;
    public static volatile SingularAttribute<Rota, TipoAeronave> tipoAeronave;
    public static volatile SingularAttribute<Rota, Trecho> trecho;
    public static volatile SingularAttribute<Rota, Calendar> horaPartida;
    public static volatile SingularAttribute<Rota, String> descricao;

}