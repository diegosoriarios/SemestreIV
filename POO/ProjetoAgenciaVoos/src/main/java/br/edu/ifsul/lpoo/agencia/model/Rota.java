
package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "tb_rota") 
public class Rota implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Integer codigo;
    
    @Column(nullable = true, length = 100)
    private String descricao;
    
    @Column(nullable = false)    
    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIME)//hora java.sql.time
    private Calendar horaPartida;
    
    @ManyToOne
    @JoinColumn(nullable = false)    
    private Trecho trecho;
    
    @ManyToOne
    @JoinColumn(nullable = false)        
    private TipoAeronave tipoAeronave;
    
    public Rota(){
        
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Calendar getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(Calendar horaPartida) {
        this.horaPartida = horaPartida;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public TipoAeronave getTipoAeronave() {
        return tipoAeronave;
    }

    public void setTipoAeronave(TipoAeronave tipoAeronave) {
        this.tipoAeronave = tipoAeronave;
    }
    
    
}
