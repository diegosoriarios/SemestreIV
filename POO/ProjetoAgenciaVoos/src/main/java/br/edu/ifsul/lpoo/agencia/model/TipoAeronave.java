
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
@Entity //indica que os objetos dessa classe se tornarão "persistíves" na banco de dados.
@Table(name = "tb_tipoaeronave")//indica que os objetos dessa classes serão persistidos no banco de dados através de uma tabela
public class TipoAeronave implements Serializable {
    
    @Id//inidica que essa coluna será uma chave primaria, toda a tabela precisa ter a definição de uma chave primaria
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)//indica que a chave primaria deverá ser populada pelo próprio banco de dados
    //auto increment ou sequence, dependendo do banco de dados.
    //GenerationType.IDENTITY essa estratégia cria um campo serial no postgresql.
    private Integer codigo;
    
    @Column(nullable = false, length = 80) 
    private String descricao;
    
    @Column(nullable = false)
    private Integer qtdLugares;
    
    public TipoAeronave(){
        
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

    public Integer getQtdLugares() {
        return qtdLugares;
    }

    public void setQtdLugares(Integer qtdLugares) {
        this.qtdLugares = qtdLugares;
    }
    
    
}
