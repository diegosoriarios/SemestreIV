
package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_pais")
public class Pais implements Serializable{
    
    @Id//defini a chave primaria da tabela e o autoincremento com um campo serial
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;
    
    @Column(nullable = false)//seta a obritoriedade para o campo nome
    private String nome;
    
    public Pais(){
        
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
    
}
