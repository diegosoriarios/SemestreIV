package br.edu.ifsul.lpoo.agencia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Telmo Junior
 */
@Entity
@Table(name = "tb_reserva")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = true, length = 200)
    private String observacao;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar data_reserva;

    @ManyToOne
    @JoinColumn(name = "codigo_passageiro", nullable = false)
    private Passageiro passageiro;

    @ManyToOne
    @JoinColumn(name = "codigo_funcionario", nullable = false)
    private Funcionario funcionario;

    @OneToMany(mappedBy = "reserva")
    private List<ReservaRota> rotasReservadas;//relacionamento bidirecional

    public Reserva() {

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Calendar getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(Calendar data_reserva) {
        this.data_reserva = data_reserva;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ReservaRota> getRotasReservadas() {
        return rotasReservadas;
    }

    public void setRotaReservada(ReservaRota rotaReservada) {
        //NULLPOINTEREXCEPTION
        if (this.rotasReservadas == null) {
            this.rotasReservadas = new ArrayList<ReservaRota>();
        }
        this.rotasReservadas.add(rotaReservada);
        //o parâmetro rotaReservada deverá ser adicionado na 
        //variável de instancia rotasReservadas
        //No entanto, essa variável pode estar nulla, então
        //é necessário testa-la, incializa-la, caso necessário
        //e por fim adicionar o parâmetro na variável.

    }

}
