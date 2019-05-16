
package br.edu.ifsul.lpoo.agencia.testes;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Endereco;
import br.edu.ifsul.lpoo.agencia.model.Estado;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import br.edu.ifsul.lpoo.agencia.model.dao.InterfacePersistencia;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJPA;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Telmo Junior
 */

public class TestePersistenciaJPA {
    
    @Test
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
    
    @Test
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
    @Test
    public void testarPersistenciaCidade(){
        
            Cidade cid = new Cidade();
            cid.setNome("Passo Fundo");
            //cid.setEstado(est);
    }

    @Test
    public void testarPersistenciaFuncionario(){
        System.out.println("Executando teste na classe TestePersistenciaJPA -> testarPersistenciaFuncionario");
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            System.out.println("Abriu a conexao JPA");            
           /* tema de casa para 21/03
           Listar os funcionarios
           Se a lista estiver vazia, inserir um registro
           Se a lista tiver objetos, mostrar, editar e depois remover           
           */
           //chamar o método listFuncionario da classe PersistenciaJPA
           //atribuir o retorno para um lista de funcionario
           //imprimir na saida os objetos retornados
           List<Funcionario> list = persistencia.listFuncionario();
           if(list.isEmpty()){
                //
                Endereco end = new Endereco();
                end.setCep("99010010");
                Funcionario func = new Funcionario();
                //...
                persistencia.persist(func);               
           }else{
            for(Funcionario f : list){
                System.out.println("funcionario listado: "+f.getCodigo()+" - "+f.getNome());
                //alterar
                //remover
            }
           }
          
            Funcionario funcionarioEncontrado = (Funcionario) persistencia.find(Funcionario.class, new Integer(1));
            
            if(funcionarioEncontrado != null){
                System.out.println("Funcionario encontrado pelo find: "+funcionarioEncontrado.getNome());
            }else{
                System.out.println("Nao encontrou o funcionario 1");
            }
         
           
            persistencia.fecharConexao();
            System.out.println("Fechou a conexao !!");
        }else{
            System.out.println("Nao abriu a conexao");
        }
    }
    
}
