
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
    
    //@Test
    public void testarPersistenciaPais() {
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            Pais paisEncontrado = (Pais) persistencia.find(Pais.class, new Integer(1));
            if(paisEncontrado == null){
                Pais p = new Pais();
                p.setNome("Brasil");
                persistencia.persist(p);
                System.out.println("Inseriu no banco de dados o pais: "+p.getCodigo());
            } else {
                System.out.println("Pais encontrado: " + paisEncontrado.getCodigo());
            }
            persistencia.fecharConexao();
        }
    }
    
    //@Test
    public void testarPersistenciaEstado() {
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            Estado estadoEncontrado = (Estado) persistencia.find(Estado.class, new Integer(1));
            if(estadoEncontrado == null){
                Estado est = new Estado();
                est.setNome("Rio Grande do Sul");
                est.setUf("RS");
                est.setPais((Pais) persistencia.find(Pais.class, new Integer(1)));
                persistencia.persist(est);
                System.out.println("Inseriu no banco de dados o estado: " + est.getCodigo());
            }else{
                System.out.println("Estado encontrado: " + estadoEncontrado.getCodigo());
            }
            persistencia.fecharConexao();
        }
    }
    
    //@Test
    public void testarPersistenciaCidade(){
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            Cidade cidadeEncontrada = (Cidade) persistencia.find(Cidade.class, new Integer(1));
            if(cidadeEncontrada == null){
                Cidade cid = new Cidade();
                cid.setNome("Quarai");
                cid.setEstado((Estado) persistencia.find(Estado.class, new Integer(5)));
                persistencia.persist(cid);
                System.out.println("Inseriu no banco de dados a cidade: " + cid.getCodigo());
            }else{
                System.out.println("Encontrou a cidade: " + cidadeEncontrada.getNome());
            }
            persistencia.fecharConexao();
        }
    }
    
    //@Test
    public void testarPersistenciaFuncionario(){
        
        System.out.println("Executando teste na classe TestePersistenciaJPA -> testarPersistenciaFuncionario");
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            System.out.println("Abriu a conexao JPA");
            
            Endereco end = new Endereco();
            end.setCep("99010060");
            
            Funcionario func = new Funcionario();
            func.setNome("Diego");
            func.setEndereco(end);
            func.setCidade((Cidade) persistencia.find(Cidade.class, new Integer(5)));
            func.setLogin("diego");
            func.setMatricula("20172");
            func.setSenha("1234");
            persistencia.persist(func);
            
            //remove fisicamente do banco de dados
            //persistencia.remove(p);
           //p deixa de ser gerenciado pelo JPA 
          
           //chamar o método listFuncionario da classe PersistenciaJPA
           //atribuir o retorno para um lista de funcionario
           //imprimir na saida os objetos retornados
            List<Funcionario> lista = persistencia.listFuncionario();
            if(lista.isEmpty()){
                Funcionario addFunc = new Funcionario();
                addFunc.setNome("Eu");
                addFunc.setEndereco(end);
                addFunc.setCidade((Cidade) persistencia.find(Cidade.class, new Integer(1)));
                addFunc.setLogin("eu");
                addFunc.setMatricula("20172");
                addFunc.setSenha("1234");
                persistencia.persist(addFunc);
            }else{
                for(Funcionario f: lista){
                    System.out.println("Funcionario: " + f.getCodigo());
                }
            }
            
            Funcionario funcionarioEncontrado = (Funcionario) persistencia.find(Funcionario.class, new Integer(1));
            
            if(funcionarioEncontrado != null){
                System.out.println("Funcionario encontrado: " + funcionarioEncontrado.getNome());
            }
           
            persistencia.fecharConexao();
            System.out.println("Fechou a conexao !!");
        }else{
            System.out.println("Nao abriu a conexao");
        }
        
        
        //Listar Funcionario
        //Se a lista estiver vazia - inserir
        //Se a lista não estiver vazia - listar
        //Apos a impressão de cada objeto - remover
    }
    
}
