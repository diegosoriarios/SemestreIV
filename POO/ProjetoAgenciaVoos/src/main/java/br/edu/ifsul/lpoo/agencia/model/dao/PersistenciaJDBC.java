/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.agencia.model.dao;

import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Estado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author 20172PF.CC0095
 */
public class PersistenciaJDBC implements InterfacePersistencia {
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "diego";
    private final String SENHA = "diego";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_agencia";
    private Connection con = null;
    
    private static PersistenciaJDBC persistencia;
    
    private PersistenciaJDBC(){
        try {
            Class.forName(DRIVER);
            this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    
    public static InterfacePersistencia getInstance(){
        if(persistencia == null){
            persistencia = new PersistenciaJDBC();
        }
        return persistencia;
    }

    @Override
    public Boolean conexaoAberta() {
        try {
            return !con.isClosed();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void persist(Object o) {
        try {
            PreparedStatement ps;
            if(o instanceof Funcionario){
                Funcionario f = (Funcionario) o;
                if(f.getCodigo() == null){
                    //insert
                    System.out.println("Funcionado " + f.getTipoPessoa() + " adicionado");
                    ps = this.con.prepareStatement("insert into pessoa (nome, login, senha, codigo_cidade, tipoPessoa) values (?, ?, ?, ?, ?)");
                    ps.setString(1, f.getNome());
                    ps.setString(2, f.getLogin());
                    ps.setString(3, f.getSenha());
                    ps.setInt(4, f.getCidade().getCodigo());
                    ps.setString(5, f.getTipoPessoa());
                    ps.execute();
                } else {
                    //update
                    System.out.println("Funcionario " + f.getTipoPessoa() + " alterado");
                    ps = this.con.prepareStatement("update pessoa set nome = ?, "
                            + "login = ?, senha = ?, codigo_cidade = ?, tipoPessoa = ? where codigo = ? ");
                    ps.setString(1, f.getNome());
                    ps.setString(2, f.getLogin());
                    ps.setString(3, f.getSenha());
                    ps.setInt(4, f.getCidade().getCodigo());
                    ps.setString(5, f.getTipoPessoa());
                    ps.setInt(6, f.getCodigo());
                    ps.execute();
                }
            } else if(o instanceof Pais){
                Pais p = (Pais) o;
                if(p.getCodigo() == null){
                    //insert
                    System.out.println("Pais " + p.getCodigo() + " adicionado");
                    ps = this.con.prepareStatement("insert into pais (nome) values (?)");
                    ps.setString(1, p.getNome());
                    ps.execute();
                } else {
                    //update
                    System.out.println("Pais " + p.getCodigo() + " alterado");
                    ps = this.con.prepareStatement("update pais set nome = ? where codigo = ? ");
                    ps.setString(1, p.getNome());
                    ps.setInt(2, p.getCodigo());
                    ps.execute();
                }
            } else if(o instanceof Cidade){
                Cidade c = (Cidade) o;
                if(c.getCodigo() == null){
                    //insert
                    System.out.println("Cidade " + c.getCodigo() + " adicionado");
                    ps = this.con.prepareStatement("insert into cidade (nome, codigo_estado) values (?, ?)");
                    ps.setString(1, c.getNome());
                    ps.setInt(2, c.getEstado().getCodigo());
                    ps.execute();
                } else {
                    //update
                    System.out.println("Cidade " + c.getCodigo() + " alterado");
                    ps = this.con.prepareStatement("update cidade set nome = ?, codigo_estado = ? where codigo = ?");
                    ps.setString(1, c.getNome());
                    ps.setInt(2, c.getEstado().getCodigo());
                    ps.setInt(3, c.getCodigo());
                    ps.execute();
                }
            } else if(o instanceof Estado) {
                Estado e = (Estado) o;
                if(e.getCodigo() == null){
                    //insert
                    System.out.println("Estado " + e.getCodigo() + " adicionado");
                    ps = this.con.prepareStatement("insert into estado (nome, uf, pais_codigo) values (?, ?, ?)");
                    ps.setString(1, e.getNome());
                    ps.setString(2, e.getUf());
                    ps.setInt(3, e.getPais().getCodigo());
                    ps.execute();
                } else {
                    //update
                    System.out.println("Estado " + e.getCodigo() + " alterado");
                    ps = this.con.prepareStatement("update estado set nome = ?, uf = ?, pais_codigo = ? where codigo = ?");
                    ps.setString(1, e.getNome());
                    ps.setString(2, e.getUf());
                    ps.setInt(3, e.getPais().getCodigo());
                    ps.setInt(4, e.getCodigo());
                    ps.execute();
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Funcionario> listFuncionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pais> listPais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario login(String login, String senha) {
        Funcionario f = null;
        try{
            PreparedStatement pst = con.prepareStatement("select matricula, login, senha from tb_pessoa "
                + "where login = ? and senha= ?");
            pst.setString(1, login);
            pst.setString(2, senha);
            
            ResultSet res = pst.executeQuery();
            if(res.next()){
                f = new Funcionario();
                f.setMatricula(res.getString("matricula"));
                f.setLogin(res.getString("login"));
                f.setSenha(res.getString("senha"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public Object find(Class c, Object id) {
        PreparedStatement ps = null;
        try{
            if(c == Funcionario.class) {
                ps = this.con.prepareCall("select * from tb_pessoa where codigo = ?");
                ps.setInt(1, Integer.parseInt(id.toString()));
            } else if (c == Cidade.class) {
                ps = this.con.prepareCall("select * from tb_cidade where codigo = ?");
                ps.setInt(1, Integer.parseInt(id.toString()));
            } else if (c == Pais.class) {
                ps = this.con.prepareCall("select * from tb_pais where codigo = ?");
                ps.setInt(1, Integer.parseInt(id.toString()));
            } else if (c == Estado.class) {
                ps = this.con.prepareCall("select * from tb_estado where codigo = ?");
                ps.setInt(1, Integer.parseInt(id.toString()));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ps;
    }
}
