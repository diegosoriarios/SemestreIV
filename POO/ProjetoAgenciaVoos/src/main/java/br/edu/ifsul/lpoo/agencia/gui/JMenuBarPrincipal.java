/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.gui;

import br.edu.ifsul.lpoo.agencia.controle.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Telmo Junior
 */
public class JMenuBarPrincipal extends JMenuBar implements ActionListener {
    //gerar um evento para o clique no menuitem sair
    //fechar a conexao e finalizar o processo.
    //System.exit(0);
    private Controle controle;
    
    private JMenu menuArquivo;
    private JMenuItem menuItemSair;
    
    private JMenu menuCadastros;
    private JMenuItem menuItemFuncionario;
    
    public JMenuBarPrincipal(Controle c){        
        this.controle = c;
        initComponents();
    }
    
    private void initComponents(){
        
        menuArquivo = new JMenu("Arquivo");
        menuItemSair = new JMenuItem("Sair");
        
        menuItemSair.addActionListener(this);//adiciona para o sair o seu "Ouvidor"
        menuItemSair.setActionCommand("menu_sair");//defini o comando de acao
        
        menuArquivo.add(menuItemSair);//adiciona o menuItem no "Pai"
        this.add(menuArquivo);//adiciona o menu pai na barra
        
        menuCadastros = new JMenu("Cadastros");
        menuItemFuncionario = new JMenuItem("Funcionário");
        //adicionar o listerner e setar o comando de acao para o menuItemFuncionario
        menuItemFuncionario.addActionListener(this);
        menuItemFuncionario.setActionCommand("menu_funcionario");
  
        menuCadastros.add(menuItemFuncionario);
        this.add(menuCadastros);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
     if(e.getActionCommand().equals(menuItemSair.getActionCommand())){
            //fechar a conexao com o BD
            //matar o processo
            controle.fecharConexao();
            System.exit(0);
     }else if(e.getActionCommand().equals(menuItemFuncionario.getActionCommand())){
         
            controle.mostraCarta("tela_funcionario");
     }
        
    }
}
