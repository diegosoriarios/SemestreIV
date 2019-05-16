
package br.edu.ifsul.lpoo.agencia.model.dao;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Estado;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Telmo Junior
 */
public class PersistenciaJDBC implements InterfacePersistencia{

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "diego";
    private final String SENHA = "diego";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_agencia";
    private Connection con = null;
    
    private static PersistenciaJDBC persistencia;
    
    private PersistenciaJDBC(){
       try{            
            Class.forName(DRIVER);            
            this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);                                      
        }catch(ClassNotFoundException | SQLException e){           
            e.printStackTrace();                   
        }
    }    
    public static InterfacePersistencia getInstance() throws Exception {
        if(persistencia == null)
            persistencia = new PersistenciaJDBC();        
        return persistencia;
    }
    
    @Override
    public Boolean conexaoAberta() {        
        try {
            return !con.isClosed();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false;
    }    
    @Override
    public void fecharConexao(){                 
        try{                               
            this.con.close();            
        }catch(SQLException e){            
            e.printStackTrace();
        }       
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(Object o) {
        try{
            PreparedStatement ps;
            if(o instanceof Funcionario){
                Funcionario f = (Funcionario) o;
                if(f.getCodigo() == null){
                    //insert
                    ps = this.con.prepareStatement("insert into pessoa (nome, login, senha, codigo_cidade, tipoPessoa) values (?,?,?,?,?)");
                    ps.setString(1, f.getNome());
                    //..
                    ps.setString(5, f.getTipoPessoa());
                    ps.execute();                    
                }else{                    
                    //update
                    ps = this.con.prepareStatement("update pessoa set nome = ?, login = ?, senha = ?, codigo_cidade = ?, tipoPessoa = ? where codigo = ? ");
                    //...
                    ps.setInt(6, f.getCodigo());
                    ps.execute();
                }           
            }else if(o instanceof Pais){

            }else if(o instanceof Estado){
                
            }else if(o instanceof Cidade){
                
            }         
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Funcionario> listFuncionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public Funcionario login(String login, String senha) {
        Funcionario f = null;
        try{
            PreparedStatement ps = this.con.prepareStatement("select matricula, login, senha from tb_pessoa where login = ? and senha = ?");
            ps.setString(1, login);
            ps.setString(2, senha);            
            ResultSet rs =  ps.executeQuery();
            if(rs.next()){
                f = new Funcionario();
                f.setMatricula(rs.getString("matricula"));//referencia a coluna
                f.setLogin(rs.getString("login"));
                f.setSenha(rs.getString("senha"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return f;
    }
    

    @Override
    public List<Pais> listPais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object find(Class c, Object id) {
        
        PreparedStatement ps;
        try{
            if(c == Funcionario.class){
                ps = this.con.prepareStatement("select codigo from tb_pessoa where codigo = ?");
                ps.setInt(1, Integer.parseInt(id.toString()));

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Funcionario f= new Funcionario();
                    f.setCodigo(rs.getInt("codigo"));
                    //..
                    return f;
                }

           }else if(c == Cidade.class){

           }
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
        
        return null;
    }




    
}
