/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Endereco;
import com.mycompany.model.Farmacia;
import com.mycompany.model.Remedio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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

        List<Farmacia> farmacias = aplicacao.listaFarmacia();
        List<String> nomeCidades = new ArrayList();
        if (farmacias != null) {
            for (int i = 0; i < farmacias.size(); i++) {
                String nomeCidade = farmacias.get(i).getEndereco().getCidade();
                if (!nomeCidades.isEmpty()) {
                    for (int j = 0; j < nomeCidades.size(); j++) {
                        if (!nomeCidades.get(j).equals(nomeCidade)) {
                            nomeCidades.add(nomeCidade);
                            break;
                        }
                    }
                }else 
                {
                    nomeCidades.add(nomeCidade);                    
                }

            }
        }
        return nomeCidades;
    }

    public List<String> getRemedios() {
        List<Remedio> remedios = aplicacao.listaRemedio();
        List<String> nomesRemedios = new ArrayList();
        for (int i = 0; i < remedios.size(); i++) {
            String nomeRemedio = remedios.get(i).getNome();
            if(!nomesRemedios.isEmpty()){
            for (int j = 0; j < nomesRemedios.size(); j++) {
                if (!nomesRemedios.get(j).equals(nomeRemedio)) {
                    nomesRemedios.add(nomeRemedio);
                    break;
                }
            }
            }else 
            {
                nomesRemedios.add(nomeRemedio);
            }
        }
        return nomesRemedios;
    }

}
