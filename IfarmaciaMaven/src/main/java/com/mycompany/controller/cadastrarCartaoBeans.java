/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.CartaoDeCredito;
import com.mycompany.model.Cliente;
import com.mycompany.model.ValidaBandeiraCartao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Future;
import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author LucasPC
 */
@Named(value = "cadastrarCartaoBeans")
@RequestScoped
public class cadastrarCartaoBeans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO", nullable = false)
    private Long id_cartao;

    @ValidaBandeiraCartao(message = "Não é uma bandeira válida")
    @Column(name = "TXT_BANDEIRA", nullable = false)
    private String bandeira;

    @CreditCardNumber(message = "Não é um cartão válido")
    @Column(name = "TXT_NUMERO", nullable = false)
    private String numero_cartao;

    //PROCURAR DEPOIS
    @Column(name = "DT_EXPIRACAO", nullable = false)
    private String dataExpiracao; 

    public Long getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(Long id_cartao) {
        this.id_cartao = id_cartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumero_cartao() {
        return numero_cartao;
    }

    public void setNumero_cartao(String numero_cartao) {
        this.numero_cartao = numero_cartao;
    }

    public String getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(String dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    /**
     * Creates a new instance of cadastrarCartaoBeans
     */
    public cadastrarCartaoBeans() {
    }

    public String cadastrarCartao() {
      
        Aplicacao aplicacao = new Aplicacao();
        CartaoDeCredito cartao = new CartaoDeCredito();

        cartao.setBandeira(bandeira);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dataExpiracao));
        } catch (ParseException pe) {

        }
        cartao.setDataExpiracao(cal);
        cartao.setNumero_cartao(numero_cartao);
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        cartao.setCliente(cliente);
        cliente.setCartao(cartao);
        aplicacao.inserirCartao(cliente);
        return "Index";
    }

}
