
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
@Table(name = "tb_cidade")
public class Cidade implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   
    private Integer codigo;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "codigo_estado", nullable = false)
    private Estado estado;
    
    public Cidade(){
        
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
