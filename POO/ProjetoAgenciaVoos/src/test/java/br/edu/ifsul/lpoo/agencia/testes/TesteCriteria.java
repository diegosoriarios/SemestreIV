/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.testes;

import br.edu.ifsul.lpoo.agencia.model.Reserva;
import br.edu.ifsul.lpoo.agencia.model.dao.InterfacePersistencia;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJPA;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    
    @Test 
    public void testeConsultaCriteria(){
        
        // criteria query
        CriteriaBuilder builder = jpa.getCriteriaBuilder();
        
        CriteriaQuery<Reserva> query = builder.createQuery(Reserva.class);
         
         // raiz da consulta
        Root<Reserva> raiz = query.from(Reserva.class);
        
        // definindo a raiz da consulta               
        query.select(raiz);
        
        /* 
        
       
       
     
        // criando um predicado para filtrar por duas duas expressoes        
        Predicate predicate = builder.and( builder.like(raiz.<String>get("nome"), "%Mou%"),
                                           builder.equal(raiz.<Integer>get("marca"), 1) );
        // adicionando o predicado ao where
        query.where(predicate);
        // ordenando
        query.orderBy(builder.asc(raiz.get("nome")));
        // lancando uma consulta em uma coleção tipada
        TypedQuery<Produto> q = em.createQuery(query);	
		List<Produto> lista = q.getResultList();
	for (Produto p : lista){
		System.out.println("ID: "+p.getId()+ " Nome: "+p.getNome());
	}   
        */
        
    }
    
    @After
    public void after(){
        jpa.fecharConexao();
    }
    
}
