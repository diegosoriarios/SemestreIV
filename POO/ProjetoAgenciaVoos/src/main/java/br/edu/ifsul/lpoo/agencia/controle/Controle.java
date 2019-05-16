/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.controle;

import br.edu.ifsul.lpoo.agencia.gui.JFramePrincipal;
import br.edu.ifsul.lpoo.agencia.gui.JMenuBarPrincipal;
import br.edu.ifsul.lpoo.agencia.gui.JPanelHome;
import br.edu.ifsul.lpoo.agencia.gui.login.JPanelLogin;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import br.edu.ifsul.lpoo.agencia.model.dao.InterfacePersistencia;
import br.edu.ifsul.lpoo.agencia.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Telmo Junior
 */
public class Controle {    
    
    private JFramePrincipal frame;
    private InterfacePersistencia persistencia;
    private JPanelLogin telaLogin;
    private JPanelHome telaHome;
    private JMenuBarPrincipal menu;
    
    
    public Controle(){        
        initComponents();
    }
    
    public boolean autenticar(String login, String senha){        
        Funcionario f = persistencia.login(login, senha);
        if(f != null){
            return true;
        }
        return false;
    }
    
    private void initComponents(){
        //inicia a criação dos objetos que irá compor a interfa gráfica
        //do programa
        //inicializar o objeto persistenica e testar a conexao com o BD
        //em caso de sucesso, inicializar o frame
        //em caso de insucesso, mostrar mensagem de erro.
        try{        
            persistencia = PersistenciaJDBC.getInstance();   
            frame = new JFramePrincipal();
            
            telaLogin = new JPanelLogin(this);
            telaHome = new JPanelHome(this);
            menu = new JMenuBarPrincipal(this);
            
            frame.adicionaCarta(telaLogin, "tela_login");
            frame.adicionaCarta(new JPanel(), "tela_white");
            frame.adicionaCarta(telaHome, "tela_home");
            
            frame.mostraCarta("tela_white");
            frame.setVisible(true);
            if(persistencia.conexaoAberta()){
                JOptionPane.showMessageDialog(frame, "Conectou com Sucesso em "+PersistenciaJDBC.URL, "Conexão", JOptionPane.INFORMATION_MESSAGE);
                frame.mostraCarta("tela_login");

            }else{
                JOptionPane.showMessageDialog(frame, "Erro ao conectar em "+PersistenciaJDBC.URL, "Conexão", JOptionPane.ERROR_MESSAGE);
            }
        
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(frame, "Erro ao conectar em "+PersistenciaJDBC.URL+" : "+e.getLocalizedMessage(), "Conexão", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    public void mostraCarta(String page) {
        if(page.equals("tela_home")){
            frame.setJMenuBar(menu);
        }
        frame.mostraCarta(page);
    }
    
    public void fecharConexao(){
        persistencia.fecharConexao();
    }
    
}
