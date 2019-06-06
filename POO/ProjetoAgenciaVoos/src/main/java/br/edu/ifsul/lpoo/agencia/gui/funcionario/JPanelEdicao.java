/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.gui.funcionario;

import br.edu.ifsul.lpoo.agencia.model.Cidade;
import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    public void setFuncionario(Funcionario func) {
        if(func != null){
            txfCodigo.setText(func.getCodigo().toString());
            txfNome.setText(func.getNome());
            txfLogin.setText(func.getLogin());
            psfSenha.setText(func.getSenha());
            cbxCidade.setSelectedItem(func.getCidade());
        } else {
            txfCodigo.setText("");
            txfNome.setText("");
            txfLogin.setText("");
            psfSenha.setText("");
            cbxCidade.setSelectedItem("");
        }
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
        txfCodigo.setEditable(false);
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
            //Passo 1: validar o formulário
            String codigo= txfCodigo.getText().trim();
            String nome = txfNome.getText().trim();
            String login = txfLogin.getText().trim();
            String senha = new String(psfSenha.getPassword()).trim();
            Integer indCid = cbxCidade.getSelectedIndex();
            //Passo 2: validar a acao
            //if(nome.length() > 0 && login.length() > 0 && senha.length() > 0 && indCid > 0) {
            if(nome.length() > 0 && login.length() > 0 && senha.length() > 0) {
                if(codigo.equals("")) {
                    txfCodigo.setEditable(true);
                    //Passo 3: persistir
                    //insert
                    Funcionario func = new Funcionario();
                    Cidade c = new Cidade();
                    int rand = new Random().nextInt();
                    func.setCodigo(rand);
                    func.setNome(nome);
                    func.setLogin(login);
                    func.setSenha(senha);
                    func.setMatricula("");
                    func.setTipoPessoa("F");
                    c = (Cidade) f.getControle().getPersistencia().find(c.getClass(), indCid);
                    if(c != null) {
                        func.setCidade(c);   
                    } else {
                        c = new Cidade();
                        c.setCodigo(1);
                        func.setCidade(c);
                    }
                    f.getControle().getPersistencia().persist(func);
                } else {
                    //Passo 3: persistir
                    //update
                    Funcionario func = new Funcionario();
                    Cidade c = new Cidade();
                    func.setNome(nome);
                    func.setLogin(login);
                    func.setSenha(senha);
                    c = (Cidade) f.getControle().getPersistencia().find(c.getClass(), Integer.valueOf(indCid));
                    func.setCidade(c);
                    f.getControle().getPersistencia().persist(func);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha os valores", "", JOptionPane.INFORMATION_MESSAGE);
            }
            f.mostraCarta("listagem");
        } else if(e.getActionCommand().equals(btnCancelar.getActionCommand())) {
            f.mostraCarta("listagem");
        }
    }
}
