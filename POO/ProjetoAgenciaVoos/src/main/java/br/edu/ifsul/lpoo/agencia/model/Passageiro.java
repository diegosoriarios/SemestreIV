
package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Telmo Junior
 */

@Entity
@DiscriminatorValue(value = "P")
public class Passageiro extends Pessoa implements Serializable {
    
    private String nroCartao;
        
    public Passageiro(){
        
    }

    public String getNroCartao() {
        return nroCartao;
    }

    public void setNroCartao(String nroCartao) {
        this.nroCartao = nroCartao;
    }
    
}
