
package br.edu.ifsul.lpoo.agencia.testes;

import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.dao.InterfacePersistencia;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJDBC;
import org.junit.Test;

/**
 *
 * @author Telmo Junior
 */
public class TestePersistenciaJDBC {
    
     @Test
    public void testarPersistenciaFuncionario(){
        //abrir a conexao jdbc
        //em caso de sucesso, chamar o metodo login 
        //(implementa-lo com java.sql.PrepareStatemment)
        //imprmir o código do funcionario encontrado/nao encontrado
        //fechar a conexao
        //se nao abrir a conexao, imprimir o erro
       
        InterfacePersistencia persistencia = PersistenciaJDBC.getInstance();
        if(persistencia.conexaoAberta()) {
            System.out.println("Abriu conexao via JDBC");
            Funcionario f = persistencia.login("", "");
            if(f != null){
                System.out.println("matricula do func logado: "+f.getMatricula());
            }else{
                System.out.println("Nao localizou funcionario!"); 
            }
            persistencia.fecharConexao();
            System.out.println("Fechou conexao via JDBC");
        }else{
            System.out.println("Nao conseguiu abrir conexao via jdbc");
        }
    }
    
}
