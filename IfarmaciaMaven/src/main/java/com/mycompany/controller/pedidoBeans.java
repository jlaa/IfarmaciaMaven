/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author LucasPC
 */
@Named(value = "pedido")
@RequestScoped
public class pedidoBeans {
    @EJB
    private Aplicacao aplicacao;
    private String formaDePagamento;
    private String cidade;
    private String nomeDoRemedio;
    

    /**
     * Creates a new instance of pedidoBeans
     */
    public pedidoBeans() {
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeDoRemedio() {
        return nomeDoRemedio;
    }

    public void setNomeDoRemedio(String nomeDoRemedio) {
        this.nomeDoRemedio = nomeDoRemedio;
    }
    
    public List<String> getCidades() {

        List<String> farmacias = aplicacao.listaFarmacia();      
        return farmacias;
    }

    public List<String> getRemedios() {
        List<String> remedios = aplicacao.listaRemedio();
        return remedios;
    }
}
