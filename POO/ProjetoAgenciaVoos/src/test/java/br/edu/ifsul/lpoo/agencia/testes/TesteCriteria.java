
package br.edu.ifsul.lpoo.agencia.testes;

import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Reserva;
import br.edu.ifsul.lpoo.agencia.model.dao.InterfacePersistencia;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJPA;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Telmo
 */
public class TesteCriteria {
    
    InterfacePersistencia jpa;
    
    @Before
    public void before(){
        jpa = PersistenciaJPA.getInstance();
    } 
    
     //Resposta para a Trabalho sobre Criteira da primeira etapa.
    @Test 
    public void testeConsultaCriteria(){
        
 
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            
            Reserva r = new Reserva();
            Funcionario f = new Funcionario();
            f.setCodigo(4);//alterar se for preciso
            r.setFuncionario(f);//caso for indicado um funcionario, irá filtrar
            r.setObservacao("nada consta");//caso seja informado a observação irá filtrar
            List<Reserva> lst =  persistencia.listReservabyFiltro(r);
            
            System.out.println("Reservas encontradas:");
            for(Reserva reserva: lst){
                
                System.out.println("Codigo     : "+reserva.getCodigo());
                System.out.println("Observaçâo : "+reserva.getObservacao());
                System.out.println("Funcionario: "+reserva.getFuncionario().getNome());
                System.out.println("Passageiro : "+reserva.getPassageiro().getNome());
                
            }
            
           // persistencia.fecharConexao();
            
        }
        
    }
    
    @After
    public void after(){
        jpa.fecharConexao();
    }
    
}
