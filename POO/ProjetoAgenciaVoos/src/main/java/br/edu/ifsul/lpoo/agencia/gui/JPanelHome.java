/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.agencia.gui;

import br.edu.ifsul.lpoo.agencia.controle.Controle;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author diego
 */
public class JPanelHome extends JPanel {

    private Controle controle;
    private JLabel lblWelcome;
    private FlowLayout flowLayout;
    
    public JPanelHome(Controle c) {
        this.controle = c;
        initComponents();
    }
    
    private void initComponents() {
        lblWelcome = new JLabel("Tela de Boas Vindas!!");
        lblWelcome.setForeground(Color.red);
        flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        this.add(lblWelcome);
    }
}
