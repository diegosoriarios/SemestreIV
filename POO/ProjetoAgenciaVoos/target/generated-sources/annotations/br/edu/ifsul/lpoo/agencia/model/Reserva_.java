package br.edu.ifsul.lpoo.agencia.model;

import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Passageiro;
import br.edu.ifsul.lpoo.agencia.model.ReservaRota;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T11:01:02")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Integer> codigo;
    public static volatile SingularAttribute<Reserva, String> observacao;
    public static volatile ListAttribute<Reserva, ReservaRota> rotasReservadas;
    public static volatile SingularAttribute<Reserva, Calendar> data_reserva;
    public static volatile SingularAttribute<Reserva, Funcionario> funcionario;
    public static volatile SingularAttribute<Reserva, Passageiro> passageiro;

}