
package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Column;

/**
 *
 * @author Telmo Junior
 */
@Embeddable
public class Endereco implements Serializable {
    
    @Column(length = 100)
    private String logradouro;
    
    @Column(length = 8)
    private String cep;
    
    @Column(length = 50)
    private String complemento;
    
    @Column(length = 10)
    private String numero;
    
    public Endereco(){
        
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
}
