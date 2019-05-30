package br.edu.ifsul.lpoo.agencia.gui.funcionario;

import br.edu.ifsul.lpoo.agencia.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author Telmo
 */
public class JPanelListagem extends JPanel implements ActionListener {
    
    private JPanelFuncionario f;
    private BorderLayout border;
    private JPanel pnlSuperior;
    private JLabel lblFiltro;
    private JTextField txfFiltro;
    private JButton btnFiltro;
           
    private JPanel pnlCentro;
    private JScrollPane scpPane;
    private JTable tblListagem;
    private DefaultTableModel modeloTabela;
    
    private JPanel pnlInferior;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnExcluir;
    
    public JPanelListagem(JPanelFuncionario f){
            this.f = f;
            initComponents();
            populaListagem();
    }
    
    public void populaListagem() {
        modeloTabela.setNumRows(0);
        
        List<Funcionario> list = f.getControle().getPersistencia().listFuncionario();
        
        for(Funcionario f: list) {
            modeloTabela.addRow(new Object[]{f.getCodigo(), f.getNome(), f.getLogin(), f.getCidade().getNome()});
        }
    }
    
    private void initComponents(){
        border = new BorderLayout();
        this.setLayout(border);
        
        pnlSuperior = new JPanel();
        lblFiltro = new JLabel("Filtrar por nome");
        txfFiltro = new JTextField(10);
        btnFiltro = new JButton("Filtrar");
        btnFiltro.addActionListener(this);
        btnFiltro.setActionCommand("cmd_filtro");
        pnlSuperior.add(lblFiltro);//o flowlayout é o gerenciador padrao
        pnlSuperior.add(txfFiltro);
        pnlSuperior.add(btnFiltro);
        this.add(pnlSuperior, BorderLayout.NORTH);
        
        pnlCentro = new JPanel();
        scpPane = new JScrollPane();        
        modeloTabela = new DefaultTableModel(new Object[] {"Código","Nome","Login", "Cidade"},0);
        tblListagem = new JTable(modeloTabela);
        scpPane.setViewportView(tblListagem);
        pnlCentro.add(scpPane); //provisório: scpPanel adicionado como flow
        this.add(pnlCentro, BorderLayout.CENTER);
        
        pnlInferior = new JPanel();
        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setActionCommand("cmd_novo");
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this);
        btnEditar.setActionCommand("cmd_editar");
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this);
        btnExcluir.setActionCommand("cmd_excluir");
        pnlInferior.add(btnNovo);
        pnlInferior.add(btnEditar);
        pnlInferior.add(btnExcluir);
        this.add(pnlInferior, BorderLayout.SOUTH);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getActionCommand().equals(btnNovo.getActionCommand())){
            f.mostraCarta("edicao");
        } else if (e.getActionCommand().equals(btnEditar.getActionCommand())){
            f.mostraCarta("edicao");
        }
    }
}
