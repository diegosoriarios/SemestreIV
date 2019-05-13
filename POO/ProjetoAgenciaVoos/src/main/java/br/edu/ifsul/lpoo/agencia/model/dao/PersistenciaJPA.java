

package br.edu.ifsul.lpoo.agencia.model.dao;

import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

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
    public CriteriaBuilder getCriteriaBuilder() {
       return entity.getCriteriaBuilder();//assunto para as próximas aulas.
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
        return (Funcionario) entity.createNamedQuery("Funcionario.login").setParameter("paramLogin", login).setParameter("paramSenha", senha).getSingleResult();
        //testar esse metodo para verificar se o getSingleResult( retorna null caso se encontre na tabela
    }

    @Override
    public List<Pais> listPais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object find(Class t, Object id) {
        //irá fazer um select com base na classe e na chave primária
        return entity.find(t, id);
    }
}
