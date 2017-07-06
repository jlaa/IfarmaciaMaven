/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Farmacia;
import com.mycompany.model.Remedio;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author joao_dev
 */
@Named(value = "searchBean")
@RequestScoped
public class searchBean {

    @NotEmpty(message = "O Nome do Remedio não pode ser vazio")
    @Length(message = "Não pode ter mais de 20 caracteres", max = 20)
    private String nomeRemedio;

    @NotEmpty(message = "A cidade não pode ser vazio")
    @Length(message = "A cidade não pode ter mais de 50 caracteres", max = 50)
    private String cidade;

    private String nomeFarmacia;

    private String preco;

    private List<Farmacia> farmacias = null;

    private List<Remedio> remedios = new ArrayList();

    /**
     * Creates a new instance of searchBean
     */
    public searchBean() {

    }

    public String pesquisaRemedio() {
        Aplicacao aplicacao = new Aplicacao();
        farmacias = aplicacao.pesquisaRemedio(cidade, nomeRemedio);
        for (int i = 0; i < farmacias.size(); i++) {
            for (int j = 0; j < farmacias.get(i).getRemedios().size(); j++) {
                 if(farmacias.get(i).getRemedios().get(j).getNome().equals(nomeRemedio))
                    {
                        remedios.add(farmacias.get(i).getRemedios().get(j));
                    }
               
            }
        }
        return "Search";
    }

    public String getNomeRemedio() {
        return nomeRemedio;
    }

    public void setNomeRemedio(String nomeRemedio) {
        this.nomeRemedio = nomeRemedio;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeFarmacia() {
        return nomeFarmacia;
    }

    public void setNomeFarmacia(String nomeFarmacia) {
        this.nomeFarmacia = nomeFarmacia;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public List<Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(List<Remedio> remedios) {
        this.remedios = remedios;
    }

    public List<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacia> farmacias) {
        this.farmacias = farmacias;
    }

}
