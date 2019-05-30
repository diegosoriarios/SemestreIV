
package br.edu.ifsul.lpoo.agencia.gui;

import br.edu.ifsul.lpoo.agencia.controle.Controle;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Telmo Junior
 */
public class JPanelHome extends JPanel{
    
    private Controle controle;    
    private JLabel lblMenssagem;
    private FlowLayout flowLayout;
    
    //criar o construtor
    //criar o metodo initComponents
    //invocar o initComponents no construtor
    public JPanelHome(Controle c){
        
        this.controle = c;
        
        initComponents();
    }
    
    private void initComponents(){
        flowLayout = new FlowLayout();//layout de fluxo: centro superior
        lblMenssagem = new JLabel("Tela de Boas Vindas !!");
        //tema: mudar a cor da letra
        this.setLayout(flowLayout);
        this.add(lblMenssagem);
    }
}
