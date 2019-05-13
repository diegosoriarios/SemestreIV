
package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_trecho")
public class Trecho implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;
    
    @Column(length = 100)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name="aeroporto_partida", nullable = false)//renomeia a coluna e de preenchimento obrigatorio
    private AeroPorto partida;
    
    @ManyToOne
    @JoinColumn(name="aeroporto_chegada", nullable = false)//renomeia a coluna e de preenchimento obrigatorio
    private AeroPorto chegada;
    
    @Column(length = 10)
    private Integer tempoDuracaoMin;
    
    public Trecho(){
        
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

    public AeroPorto getPartida() {
        return partida;
    }

    public void setPartida(AeroPorto partida) {
        this.partida = partida;
    }

    public AeroPorto getChegada() {
        return chegada;
    }

    public void setChegada(AeroPorto chegada) {
        this.chegada = chegada;
    }

    public Integer getTempoDuracaoMin() {
        return tempoDuracaoMin;
    }

    public void setTempoDuracaoMin(Integer tempoDuracaoMin) {
        this.tempoDuracaoMin = tempoDuracaoMin;
    }
    
}
