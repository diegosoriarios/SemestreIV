
package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_reservarota")
public class ReservaRota implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer codigo;
    
    @Column(nullable = false)
    private Integer numeroAssento;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar data_partida;
    
    @ManyToOne
    @JoinColumn(name="codigo_rota", nullable = false)
    private Rota rota;
       
    @ManyToOne
    @JoinColumn(name="codigo_reserva", nullable = false)    
    private Reserva reserva;//relacionamento bidirecional
    
    
    public ReservaRota(){
        
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNumeroAssento() {
        return numeroAssento;
    }

    public void setNumeroAssento(Integer numeroAssento) {
        this.numeroAssento = numeroAssento;
    }

    public Calendar getData_partida() {
        return data_partida;
    }

    public void setData_partida(Calendar data_partida) {
        this.data_partida = data_partida;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    
}
