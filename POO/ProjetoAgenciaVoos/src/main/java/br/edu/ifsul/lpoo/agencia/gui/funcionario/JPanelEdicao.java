/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.gui.funcionario;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author Telmo
 */
public class JPanelEdicao extends JPanel implements ActionListener {
    
    private JPanelFuncionario f;
    private BorderLayout border;
    
    private JPanel pnlCentro;
    private GridBagLayout grid;
    private JLabel lblCodigo;
    private JTextField txfCodigo;
    private JLabel lblNome;
    private JTextField txfNome;
    private JLabel lblLogin;
    private JTextField txfLogin;
    private JLabel lblSenha;
    private JPasswordField psfSenha;
    private JLabel lblCidade;
    private JComboBox<Cidade> cbxCidade;
    
    private JPanel pnlInferior;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    private GridBagConstraints gbc;
            
    public JPanelEdicao(JPanelFuncionario f){
        this.f = f;
        initComponents();
    }
    
    private void initComponents(){
        
        border = new BorderLayout();
        this.setLayout(border);
        
        pnlCentro = new JPanel();
        grid = new GridBagLayout();
        pnlCentro.setLayout(grid);
        lblCodigo = new JLabel("Código:");
        gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        pnlCentro.add(lblCodigo, gbc);
        
        txfCodigo = new JTextField(20);
        gbc = new GridBagConstraints();
        gbc.gridy = 0;//linha
        gbc.gridx = 1;//coluna
        pnlCentro.add(txfCodigo, gbc);
        
        lblNome = new JLabel("Nome: ");
        gbc = new GridBagConstraints();
        gbc.gridy = 1;//linha
        gbc.gridx = 0;//coluna
        pnlCentro.add(lblNome, gbc);
        
        txfNome = new JTextField(20);
        gbc = new GridBagConstraints();
        gbc.gridy = 1;//linha
        gbc.gridx = 1;//coluna
        pnlCentro.add(txfNome, gbc);
        
        lblLogin = new JLabel("Login: ");
        gbc = new GridBagConstraints();
        gbc.gridy = 2;//linha
        gbc.gridx = 0;//coluna
        pnlCentro.add(lblLogin, gbc);
        
        txfLogin = new JTextField(20);
        gbc = new GridBagConstraints();
        gbc.gridy = 2;//linha
        gbc.gridx = 1;//coluna
        pnlCentro.add(txfLogin, gbc);
        
        lblSenha = new JLabel("Senha:");
        gbc = new GridBagConstraints();
        gbc.gridy = 3;//linha
        gbc.gridx = 0;//coluna
        pnlCentro.add(lblSenha, gbc);
        
        psfSenha = new JPasswordField(20);
        gbc = new GridBagConstraints();
        gbc.gridy = 3;//linha
        gbc.gridx = 1;//coluna
        pnlCentro.add(psfSenha, gbc);
        
        lblCidade = new JLabel("Cidade:");
        gbc = new GridBagConstraints();
        gbc.gridy = 4;//linha
        gbc.gridx = 0;//coluna
        pnlCentro.add(lblCidade, gbc);
        
        cbxCidade = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridy = 4;//linha
        gbc.gridx = 1;//coluna
        pnlCentro.add(cbxCidade, gbc);
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        pnlInferior = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("cmd_salvar");
        btnCancelar.setActionCommand("cmd_cancelar");
        btnCancelar.addActionListener(this);
        pnlInferior.add(btnSalvar);//flowlayout
        pnlInferior.add(btnCancelar);
        
        this.add(pnlInferior, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getActionCommand().equals(btnSalvar.getActionCommand())){
            f.mostraCarta("listagem");
        } else if(e.getActionCommand().equals(btnCancelar.getActionCommand())) {
            f.mostraCarta("listagem");
        }
        
    }
}
