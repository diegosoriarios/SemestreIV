
package br.edu.ifsul.lpoo.agencia.testes;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Estado;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import br.edu.ifsul.lpoo.agencia.model.Passageiro;
import br.edu.ifsul.lpoo.agencia.model.Reserva;
import br.edu.ifsul.lpoo.agencia.model.TipoAeronave;
import br.edu.ifsul.lpoo.agencia.model.dao.InterfacePersistencia;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJPA;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Telmo Junior
 */

public class TestePersistenciaJPA {
    
    //@Test
    public void testarPersistenciaPais(){
    
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            
            //sequencia do teste
            //utilizando o find, encontre o registro
            //caso não exista, insira.
            Pais p = (Pais) persistencia.find(Pais.class, new Integer(1));
            if(p == null){
                p = new Pais();
                p.setNome("Argentina");
                persistencia.persist(p);//inserir um país
            }else{
                System.out.println("Pais cadastrado : "+p.getCodigo());
            }
            persistencia.fecharConexao();
        }
    }
    
    //@Test
    public void testarPersistenciaEstado(){
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){            
            //sequencia do teste
            //find para localizar o pais
            //find, para localizar o estado, caso nao exista, inserir
            //caso ele exista, apenas mostrar
            Estado est = (Estado) persistencia.find(Estado.class, new Integer(1));
            if(est == null){
                est = new Estado();
                est.setNome("Rio Grande do Sul");
                est.setUf("RS");
                est.setPais((Pais) persistencia.find(Pais.class, new Integer(1)));
                persistencia.persist(est);
            }
            persistencia.fecharConexao();
        }
    }
    
    
    //@Test
    public void testarPersistenciaTipoAeronave(){
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();        
        if(persistencia.conexaoAberta()){            
            
            TipoAeronave ta = (TipoAeronave) persistencia.find(TipoAeronave.class, 1);
            if(ta == null){
                ta = new TipoAeronave();
                ta.setDescricao("Jato");
                ta.setQtdLugares(100);
                persistencia.persist(ta);
            }
        }
    
    }
    
    //@Test
    public void testarPersistenciaReserva(){
    
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){            
            
            Reserva res = (Reserva) persistencia.find(Reserva.class, 1);
            if(res == null){
            
                res = new Reserva();
                res.setData_reserva(Calendar.getInstance());
                res.setFuncionario( (Funcionario) persistencia.find(Funcionario.class, 4));
                res.setPassageiro((Passageiro) persistencia.find(Passageiro.class, 2));
                
                persistencia.persist(res);
                
            }
        }
        
    }
    
    //@Test
    public void testarPersistenciaCidade(){
        
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){            
            
            Cidade cid = (Cidade) persistencia.find(Cidade.class, 1);
            if(cid == null){
                
                cid = new Cidade();
                cid.setNome("Gentil");
                cid.setEstado((Estado) persistencia.find(Estado.class, 2));
                persistencia.persist(cid);
            }
        }
    
    }

    //@Test
    public void testarPersistenciaPassageiro(){
        System.out.println("Executando teste na classe TestePersistenciaJPA -> testarPersistenciaFuncionario");
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            System.out.println("Abriu a conexao JPA");            
           
            Passageiro pss = (Passageiro) persistencia.find(Passageiro.class, 3);
            if(pss == null){
                pss = new Passageiro();
                pss.setCidade((Cidade) persistencia.find(Cidade.class, 2));
                pss.setNome("teste de passageiro");
                
                persistencia.persist(pss);
            }
           
            persistencia.fecharConexao();
            System.out.println("Fechou a conexao !!");
        }else{
            System.out.println("Nao abriu a conexao");
        }
    }

}
