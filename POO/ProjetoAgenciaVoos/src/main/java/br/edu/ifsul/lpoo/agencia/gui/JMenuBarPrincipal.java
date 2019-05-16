/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.agencia.gui;

import br.edu.ifsul.lpoo.agencia.controle.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class JMenuBarPrincipal extends JMenuBar implements ActionListener {
    //gerar um evento para o clique no menuitem sair
    //fechar a conexao e finalizar o processo
    
    private Controle controle;
    private JMenu menuArquivo;
    private JMenuItem menuItemSair;
    
    public JMenuBarPrincipal(Controle c) {
        this.controle = c;
        initComponents();
    }
    
    private void initComponents() {
        menuArquivo = new JMenu("Arquivo");
        menuItemSair = new JMenuItem("Sair");
        menuItemSair.addActionListener(this);
        //menuItemSair.addActionCommand("");
        
        menuArquivo.add(menuItemSair);
        this.add(menuArquivo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(menuItemSair.getActionCommand())){
            
            System.out.println("Não estou me sentindo bem Sr Stark");
            controle.fecharConexao();
            System.exit(0);
        }                
    }
}
