/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.locadora.model.dao;

import br.edu.ifsul.locadora.model.Locacao;
import br.edu.ifsul.locadora.model.Pessoas;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author diego.rios
 */
public interface InterfacePersistencia {
    public Boolean conexaoAberta();
    public void fecharConexao();
    public void persist(Object o);
    public List<Pessoas> listFuncionario();
    public void remove(Object o);
    public CriteriaBuilder getCriteriaBuilder();
    public Pessoas login(String login, String senha);
    public Object find(Class c, Object id);
    public List<Locacao> listagem(Locacao l);
}
