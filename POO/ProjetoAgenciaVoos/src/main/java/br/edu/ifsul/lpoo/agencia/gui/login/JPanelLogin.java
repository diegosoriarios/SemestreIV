/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.gui.login;

import br.edu.ifsul.lpoo.agencia.controle.Controle;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Telmo
 */
public class JPanelLogin extends JPanel implements ActionListener {
    
    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txfUsuario;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Controle controle;
    //gerenciador de layout para tabela centralizada.
    private GridBagLayout gridBagLayout;    
    private GridBagConstraints gbc;//posicionador de coluna e linha
    
    public JPanelLogin(Controle c){
        this.controle = c;
        iniComponents();
    }
    
    private void iniComponents(){
        
      lblUsuario = new JLabel("Usuário:");
      lblSenha = new JLabel("Senha:");
      txfUsuario = new JTextField(10);//10 representa numero de colunas (largura)
      psfSenha = new JPasswordField(10);
      btnLogar = new JButton("Autenticar");
      btnLogar.addActionListener(this);//defini o listnner
      btnLogar.setActionCommand("botao_logar");
      
      
      gridBagLayout = new GridBagLayout();
      this.setLayout(gridBagLayout);//seta o gerenciador de layout no JPanelLogin
      
      gbc = new GridBagConstraints();      
      gbc.gridy = 0; //linha zero
      gbc.gridx = 0; //coluna zero
      this.add(lblUsuario, gbc);
      
      gbc = new GridBagConstraints();
      gbc.gridy = 0; //linha zero
      gbc.gridx = 1; //coluna zero      
      this.add(txfUsuario, gbc);
      
      gbc = new GridBagConstraints();
      gbc.gridy = 1; //linha zero
      gbc.gridx = 0; //coluna zero      
      this.add(lblSenha, gbc);
      
      gbc = new GridBagConstraints();
      gbc.gridy = 1; //linha zero
      gbc.gridx = 1; //coluna zero      
      this.add(psfSenha, gbc);
      
      gbc = new GridBagConstraints();
      gbc.gridy = 2; //linha zero
      gbc.gridx = 1; //coluna zero      
      this.add(btnLogar, gbc);
      
    }
    @Override
    public void actionPerformed(ActionEvent e) {       
        //se o evento for do btnLogar
        //esse filtro é necessario pois, é possível ter vários botões
        //com o mesmo listnner
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){
            
            System.out.println("clicou no botão Autenticar");
            String usuario = txfUsuario.getText().trim();
            String senha = new String(psfSenha.getPassword()).trim();
            
            //trim : remove os espaços em branco
            //validar o formulário
            if(usuario.length() > 0 && senha.length() > 0){
                
                //invocar o metodo login da classe PersistenciaJDBC                
                System.out.println("Autenticação: " + controle.autenticar(usuario, senha));
                if(controle.autenticar(usuario, senha)) {
                    controle.mostraCarta("tela_home");
                } else { 
                    JOptionPane.showMessageDialog(this, "Usuário ou senha não cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                //testar o retorno do metodo autenticar
            }else{
                JOptionPane.showMessageDialog(this, "Informe Usuário e/ou Senha!", "Informe", JOptionPane.ERROR_MESSAGE);
            }
        }                
    }
    
}
