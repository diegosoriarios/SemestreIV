/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.model.dao;

import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Telmo Junior
 */
public interface InterfacePersistencia {
    
    public Boolean conexaoAberta();
    public void fecharConexao();
    public void persist(Object o);
    public List<Funcionario> listFuncionario();
    public List<Pais> listPais();
    public void remove(Object o);
    public CriteriaBuilder getCriteriaBuilder();
    public Funcionario login(String login, String senha);
    public Object find(Class c, Object id);
}
