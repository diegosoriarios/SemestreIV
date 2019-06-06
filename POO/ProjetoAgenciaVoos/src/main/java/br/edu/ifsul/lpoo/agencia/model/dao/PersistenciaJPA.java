

package br.edu.ifsul.lpoo.agencia.model.dao;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import br.edu.ifsul.lpoo.agencia.model.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Telmo Junior
 */
public class PersistenciaJPA implements InterfacePersistencia {
    //introducao ao JPA
    //https://www.caelum.com.br/apostila-java-web/uma-introducao-pratica-ao-jpa-com-hibernate/#usando-o-jpa
    private static InterfacePersistencia persistenciaJPA;
    private EntityManagerFactory factory;
    private EntityManager entity;
    
    private PersistenciaJPA(){
        
          factory = Persistence.createEntityManagerFactory("pu_eclipseLink_Postgresql");
          entity = factory.createEntityManager();
    }
     // static em interface: https://www.guj.com.br/t/uso-de-static-em-interfaces/41923
    //  Design Pattern Singleton
    //  https://www.devmedia.com.br/design-patterns-singleton-parte-3/16782
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
        //em JPA persistir é sinonimo de sincronizar com a fonte de dados 
        //irá persistir o objeto (inserir ou editar)
        //entity.persist(o);
        entity.getTransaction().begin();
        entity.persist(o);
        entity.getTransaction().commit();
    }
    
    @Override
    public List<Funcionario> listFuncionario() {
          //irá executar um namedquery para entao criar o comando sql
          
          return (List<Funcionario>) entity.createNamedQuery("Funcionario.orderbyCodigo").getResultList();
          
          
    }

    @Override
    public void remove(Object o) {
        entity.getTransaction().begin();
        entity.remove(o);//sincroniza com o banco de dados para remover
        entity.getTransaction().commit();
    }

    @Override
    public Funcionario login(String login, String senha) {
       
        //invocar a NamedQuery da classe Funcionario (semelhante ao listFuncionar)
        //passar os dois parametros
        //executar a querie
        //retornar o seu resultao
        Funcionario f = (Funcionario) entity.createNamedQuery("Funcionario.login").setParameter("paramLogin", login).setParameter("paramSenha", senha).getSingleResult();
        return f;
        //tema de casa: testar esse método para verificar se o getSingleResult()
        //retorna nullo caso não encontre registro na tabela.
        
        
        
        
        
    }

    @Override
    public List<Pais> listPais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object find(Class t,Object id) {
        //irá fazer um select com base na classe e na chave primaria
        return entity.find(t, id);
    }

    //Resposta para a Trabalho sobre Criteira da primeira etapa.
    @Override
    public List<Reserva> listReservabyFiltro(Reserva r){
        
        // criteria query
        CriteriaBuilder builder = entity.getCriteriaBuilder();
        
        //cria a query
        CriteriaQuery<Reserva> query = builder.createQuery(Reserva.class);
         
         // define a raiz da consulta
        Root<Reserva> raiz = query.from(Reserva.class);
        
        // definindo a raiz da consulta               
        query.select(raiz);        
        
        // cria uma lista para armazenar os predicados              
        List<Predicate> predicados = new ArrayList();
        
        //adiciona na lista de predicados caso tenha uma observacao
        if(r.getObservacao() != null){       
            predicados.add(builder.and( builder.like(raiz.<String>get("observacao"), r.getObservacao())));
        }
        //adiciona na lista de predicados caso tenha funcionario
        if(r.getFuncionario() != null){
            predicados.add(builder.and( builder.equal(raiz.<Funcionario>get("funcionario"), r.getFuncionario())));
        }
        
        //seta a lista de predicados na query
        query.where(builder.and(predicados.toArray(new Predicate[predicados.size()])));
        
        // ordenando
        query.orderBy(builder.asc(raiz.get("observacao")));
        // lancando uma consulta em uma coleção tipada
        
        //gera uma TypedQuery
        TypedQuery<Reserva> q = entity.createQuery(query);	
        
        //executa a query e retorna a lista
        return q.getResultList();
    } 
}
