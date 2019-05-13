package br.edu.ifsul.lpoo.agencia.model;

import br.edu.ifsul.lpoo.agencia.model.Reserva;
import br.edu.ifsul.lpoo.agencia.model.Rota;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T11:01:02")
@StaticMetamodel(ReservaRota.class)
public class ReservaRota_ { 

    public static volatile SingularAttribute<ReservaRota, Integer> numeroAssento;
    public static volatile SingularAttribute<ReservaRota, Calendar> data_partida;
    public static volatile SingularAttribute<ReservaRota, Integer> codigo;
    public static volatile SingularAttribute<ReservaRota, Reserva> reserva;
    public static volatile SingularAttribute<ReservaRota, Rota> rota;

}