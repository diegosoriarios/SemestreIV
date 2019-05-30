/*
 * 
 */
package br.edu.ifsul.lpoo.agencia.gui.funcionario;

import br.edu.ifsul.lpoo.agencia.controle.Controle;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Telmo
 */
public class JPanelFuncionario extends JPanel {
    private Controle controle;
    private CardLayout cardLayout;
    private JPanelListagem listagem;
    private JPanelEdicao edicao;
    //receber uma instancia de controle
    //o gerenciador de layout será o cardlayout
    //adicionar as duas cartas (criar dos atributos de JPanelListagem e JPanelEdicao)
    //mostrar inicialmente a carta da listagem
    public JPanelFuncionario(Controle c){
        this.controle = c;
        initComponents();
    }    
    private void initComponents(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);        
        listagem = new JPanelListagem(this);
        edicao = new JPanelEdicao(this);        
        adicionaCarta(listagem, "listagem");
        adicionaCarta(edicao, "edicao");        
        mostraCarta("listagem");
    }    
    public void adicionaCarta(Component c, String nome){  
        this.add(c, nome);
    }
    public void mostraCarta(String nome){ 
        cardLayout.show(this, nome);
    }
    public Controle getControle() {
        return controle;
    }
}
