
package br.edu.ifsul.lpoo.agencia.gui;

import br.edu.ifsul.lpoo.agencia.controle.Controle;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Telmo Junior
 */
public class JFramePrincipal extends JFrame{
    
    private CardLayout cardLayout;
    private JPanel pnlCard;//esse painel é necessario pois não é possivel setar
    //diretamente no JFrame o cardLayout
    private Controle controle;
    
    
    
    public JFramePrincipal(){
        initComponents();
    }
    
    private void initComponents(){        
        //customização do JFrame
        this.setTitle("Sisteminha para a Reserva de Voos");
        //definir uma propriedade para maximizar o Frame quando o mesmo
        //for rederizado.
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        //ao fechar o JFrame o processo será finalizado.
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame)e.getSource();
 
                int result = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to exit the application?",
                    "Exit Application",
                    JOptionPane.YES_NO_OPTION);
 
                if (result == JOptionPane.NO_OPTION)
                    controle.getPersistencia().fecharConexao();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
                
        cardLayout = new CardLayout();
        pnlCard = new JPanel();        
        pnlCard.setLayout(cardLayout);
        this.getContentPane().add(pnlCard);        
    }
    
    public void adicionaCarta(Component c, String nome){
        pnlCard.add(c, nome);
    }
    public void mostraCarta(String nome){
        cardLayout.show(pnlCard, nome);
    }
    
}




