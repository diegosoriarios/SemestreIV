/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.locadora.model.dao;

import br.edu.ifsul.locadora.model.Locacao;
import br.edu.ifsul.locadora.model.Pessoas;
import br.edu.ifsul.locadora.model.Vendedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 *
 * @author diego.rios
 */
public class PersistenciaJPA implements InterfacePersistencia {
    private static InterfacePersistencia persistenciaJPA;
    private EntityManagerFactory factory;
    private EntityManager entity;
    
    private PersistenciaJPA(){
        
          factory = Persistence.createEntityManagerFactory("pu_eclipseLink_Postgresql");
          entity = factory.createEntityManager();
    }
    
    public static InterfacePersistencia getInstance(){
        
        if(persistenciaJPA == null)
            persistenciaJPA = new PersistenciaJPA();
            
        return persistenciaJPA;
    }

    @Override
    public Boolean conexaoAberta() {
        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        entity.close();
        factory.close();
        persistenciaJPA = null;
    }

    @Override
    public void persist(Object o) {
        entity.getTransaction().begin();
        entity.persist(o);
        entity.getTransaction().commit();
    }

    @Override
    public List<Pessoas> listFuncionario() {
        return (List<Pessoas>) entity.createNamedQuery("Pessoas.orderbyCodigo").getResultList();
    }

    @Override
    public void remove(Object o) {
        entity.getTransaction().begin();
        entity.remove(o);
        entity.getTransaction().commit();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return null;
    }

    @Override
    public Pessoas login(String nomeUsuario, String senha) {
        return (Vendedor) entity.createNamedQuery("Vendedor.nomeUsuario").setParameter("paramNomeUsuario", nomeUsuario).setParameter("paramSenha", senha).getSingleResult();
    }

    @Override
    public Object find(Class c, Object id) {
        return entity.find(c, id);
    }
    
    @Override
    public List<Locacao> listagem(Locacao l) {
        List<Locacao> list;
        CriteriaBuilder builder = entity.getCriteriaBuilder();
        CriteriaQuery<Locacao> query = builder.createQuery(Locacao.class);
        Root<Locacao> from = query.from(Locacao.class);
        CriteriaQuery<Locacao> select = query.select(from);
        
        TypedQuery<Locacao> typedQuery = entity.createQuery(select);
        list = typedQuery.getResultList();
        System.out.println("list");
        return list;
    }
}
