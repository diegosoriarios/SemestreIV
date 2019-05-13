/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.locadora.teste;

import br.edu.ifsul.locadora.model.Locacao;
import br.edu.ifsul.locadora.model.dao.PersistenciaJPA;
import br.edu.ifsul.locadora.model.dao.InterfacePersistencia;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class TestePersistenciaJPA {
    
    @Test
    public void testarPersistenciaLocacao(){
        
        System.out.println("Executando teste na classe TestePersistenciaJPA -> testarPersistenciaFuncionario");
        InterfacePersistencia persistencia = PersistenciaJPA.getInstance();
        if(persistencia.conexaoAberta()){
            System.out.println("Abriu a conexao JPA");
            
            Locacao l = new Locacao();
            
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, 11);
            c.set(Calendar.MONTH, 12);
            c.set(Calendar.YEAR, 2018);
            
            l.setDataLocacao(c);
            
            c.set(Calendar.DAY_OF_MONTH, 25);
            c.set(Calendar.MONTH, 12);
            c.set(Calendar.YEAR, 2018);
            l.setDataDevolucao(c);
            
            l.setValorDiaria(90.0);
            l.setKmInicial(405);
            l.setKmFinal(550);
            
            persistencia.persist(l);
            persistencia.listagem(l);
            /*
            c.reserva(1)
            c.setRota('11/01/2017')
            
            public List<ReservaRota> listagem(ReservaRota rr){
                List<ReservaRota> list;
                
                return list;
            }

            */
            
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

