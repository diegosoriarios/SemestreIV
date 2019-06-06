
package br.edu.ifsul.lpoo.agencia.model.dao;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Estado;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.Pais;
import br.edu.ifsul.lpoo.agencia.model.Reserva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public void persist(Object o) {
        try{
            PreparedStatement ps;
            if(o instanceof Funcionario){
                Funcionario f = (Funcionario) o;
                if(f.getCodigo() == null){
                    //insert
                    ps = this.con.prepareStatement("insert into tb_pessoa (matricula, nome, login, senha, codigo_cidade, tipoPessoa) values (?,?,?,?,?,?)");
                    ps.setString(1, f.getMatricula());
                    ps.setString(2, f.getNome());
                    ps.setString(3, f.getLogin());
                    ps.setString(4, f.getSenha());
                    ps.setInt(5, f.getCidade().getCodigo());
                    ps.setString(6, f.getTipoPessoa());
                    ps.execute();                    
                }else{                    
                    //update
                    ps = this.con.prepareStatement("update pessoa set nome = ?, login = ?, senha = ?, codigo_cidade = ?, tipoPessoa = ? where codigo = ? ");
                    //...
                    ps.setInt(6, f.getCodigo());
                    ps.execute();
                }           
            }else if(o instanceof Pais){
                
                Pais e = (Pais) o;
                if(e.getCodigo() == null){
                    //insert
                    ps = this.con.prepareStatement("insert into tb_pais (nome) values (?,?)");
                    ps.setString(1, e.getNome());
                    ps.execute();
                }else{
                    
                }
            }else if(o instanceof Estado){
                
                Estado e = (Estado) o;
                if(e.getCodigo() == null){
                    //insert
                    ps = this.con.prepareStatement("insert into tb_estado (nome, codigo_pais) values (?,?)");
                    ps.setString(1, e.getNome());
                    ps.setInt(2, e.getPais().getCodigo());
                    ps.execute();
                }else{
                    
                }
            }else if(o instanceof Cidade){
                
                Cidade c = (Cidade) o;
                if(c.getCodigo() == null){
                    //insert
                    ps = this.con.prepareStatement("insert into tb_cidade (nome, codigo_cidade) values (?,?)");
                    ps.setString(1, c.getNome());
                    ps.setInt(2, c.getEstado().getCodigo());
                    ps.execute();
                }else{
                    
                }
                
            }         
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Funcionario> listFuncionario() {
        List<Funcionario> listRetorno = null;
        
        try {
            PreparedStatement ps = this.con.prepareStatement("select p.codigo, "
                    + "p.nome, p.login, p.senha, c.codigo as codCidade, "
                    + "c.nome as nomeCidade from "
                    + "tb_pessoa p, tb_cidade c where p.codigo_cidade = c.codigo"
                    + " and p.tipopessoa = ? order by p.codigo");
            ps.setString(1, "F");
            ResultSet rs = ps.executeQuery();
            listRetorno = new ArrayList();
            while(rs.next()) {
                Funcionario f = new Funcionario();
                f.setCodigo(rs.getInt("codigo"));
                f.setNome(rs.getString("nome"));
                f.setLogin(rs.getString("login"));
                f.setSenha(rs.getString("senha"));
                Cidade cidade = new Cidade();
                cidade.setCodigo(rs.getInt("codCidade"));
                cidade.setNome(rs.getString("nomeCidade"));
                f.setCidade(cidade);
                listRetorno.add(f);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return listRetorno;
    }

    @Override
    public void remove(Object o) {
        try {
            if(o instanceof Funcionario){
                PreparedStatement ps = this.con.prepareStatement("delete from tb_pessoa where codigo = ?");
                
                Funcionario func = (Funcionario) o;
                System.out.println("Funcionario: " + func.getCodigo());
                
                ps.setInt(1, func.getCodigo());
                ps.executeUpdate();
                System.out.println("Aqui");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                ps = this.con.prepareStatement("select codigo from tb_cidade where codigo = ?");
                ps.setInt(1, Integer.parseInt(id.toString()));

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    Cidade city = new Cidade();
                    city.setCodigo(rs.getInt("codigo"));
                    //..
                    return city;
                }
           }
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
        
        return null;
    }

    
    public List<Cidade> listCidade() {
        List<Cidade> list = new ArrayList<Cidade>();
        try {
            PreparedStatement ps = this.con.prepareStatement("select * from tb_cidade");
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            if(rs.next()){
                list.add((Cidade) rs);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reserva> listReservabyFiltro(Reserva r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    
}
