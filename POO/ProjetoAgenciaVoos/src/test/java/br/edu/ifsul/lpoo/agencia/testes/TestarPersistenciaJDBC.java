/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.agencia.testes;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Endereco;
import br.edu.ifsul.lpoo.agencia.model.Estado;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import br.edu.ifsul.lpoo.agencia.model.dao.InterfacePersistencia;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJDBC;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJPA;
import org.junit.Test;

/**
 *
 * @author 20172PF.CC0095
 */
public class TestarPersistenciaJDBC {
    
    //@Test
    public void testarPersistenciaPais() {
        InterfacePersistencia persistencia = PersistenciaJDBC.getInstance();
        if(persistencia.conexaoAberta()){
            Pais p = new Pais();
            p.setCodigo(1);
            p.setNome("Brasil");
            persistencia.persist(p);
            persistencia.fecharConexao();
        }
    }
    
    //@Test
    public void testarPersistenciaEstado() {
        InterfacePersistencia persistencia = PersistenciaJDBC.getInstance();
        if(persistencia.conexaoAberta()){
            Estado est = new Estado();
            est.setCodigo(5);
            est.setNome("Rio Grande do Sul");
            est.setUf("RS");
            est.setPais((Pais) persistencia.find(Pais.class, new Integer(1)));
            persistencia.persist(est);
            
            persistencia.fecharConexao();
        }
    }
    
    //@Test
    public void testarPersistenciaCidade(){
        InterfacePersistencia persistencia = PersistenciaJDBC.getInstance();
        if(persistencia.conexaoAberta()){
            Cidade cid = new Cidade();
            cid.setCodigo(5);
            cid.setNome("Quarai");
            cid.setEstado((Estado) persistencia.find(Estado.class, new Integer(5)));
            persistencia.persist(cid);
            System.out.println("Inseriu no banco de dados a cidade: " + cid.getCodigo());

            persistencia.fecharConexao();
        }
    }
    
    @Test
    public void testarPersistenciaFuncionario(){
        InterfacePersistencia persistencia = PersistenciaJDBC.getInstance();
        if(persistencia.conexaoAberta()){
            System.out.println("Conexão Aberta");
            //chamar o metodo login
            //Funcionario f = persistencia.login("diego", "1234");
            //if(f != null){
                //System.out.println("Funcionario: " + f.getMatricula());
            //}
            
            Endereco end = new Endereco();
            end.setCep("99010060");
            
            Funcionario func = new Funcionario();
            func.setCodigo(3);
            func.setNome("Diego");
            func.setEndereco(end);
            func.setCidade((Cidade) persistencia.find(Cidade.class, new Integer(5)));
            func.setLogin("diego");
            func.setMatricula("20172");
            func.setSenha("1234");
            persistencia.persist(func);
            
            persistencia.fecharConexao();
            System.out.println("Conexão Fechada");
            //fechar a conexão
            //imprimir o erro
        }else{
            System.out.println("Não foi possivel abrir conexão");
        }
    }
}
