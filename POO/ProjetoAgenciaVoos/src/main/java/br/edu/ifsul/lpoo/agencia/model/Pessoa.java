
package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//definindo a estratégia: Tabela Única por Hierarquia de Classes
@Table(name = "tb_pessoa")
@DiscriminatorColumn(name = "tipoPessoa", discriminatorType = DiscriminatorType.CHAR)//divisão entre funcionarios e passageiros: coluna tipoPessoa
public class Pessoa implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;
    
    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(length = 20)
    private String rg;
    
    @Column(length = 20)
    private String cpf;
    
    @ManyToOne
    @JoinColumn(name="codigo_cidade", nullable = false)//renomeia a coluna e de preenchimento obrigatorio
    private Cidade cidade;
    
    @Embedded
    private Endereco endereco;
    
    @Column(name = "tipoPessoa",  length = 1, nullable = false)
    private String tipoPessoa;
    
    public Pessoa(){
        
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
