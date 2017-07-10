/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Farmacia;
import com.mycompany.model.ValidaEstados;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author joao_dev
 */
@Named(value = "farmRegisterBean")
@RequestScoped
public class farmRegisterBean implements Serializable {
    @EJB
    private Aplicacao aplicacao;
    @NotEmpty(message = "O nome não pode ser vazio")
    @Length(message = "O nome não pode ter mais de 30 caracteres", max = 30)
    private String nome;

    @ValidaEstados(message = "Esse estado não existe")
    private String estado;

    @NotEmpty(message = "O bairro não pode ser vazio")
    @Length(message = "O nome não pode ter mais de 150 caracteres", max = 150)
    private String bairro;

    @NotEmpty(message = "O numero não pode ser vazio")
    @Length(message = "O numero não pode ter mais de 9999 caracteres e"
            + " menos de 1 caracteres", max = 9999, min = 1)
    private String numero;

    @NotEmpty(message = "A cidade não pode ser vazio")
    @Length(message = "A cidade não pode ter mais de 50 caracteres", max = 50)
    private String cidade;

    @NotEmpty(message = "A rua não pode ser vazio")
    @Length(message = "A rua não pode ter mais de 150 caracteres", max = 150)
    private String rua;

    private String site;

    /**
     * Creates a new instance of farmRegisterBean
     */
    public farmRegisterBean() {

    }

    public String cadastrarFarmacia() {
        Farmacia farmacia = new Farmacia();

        farmacia.CadastrarFarmacia(nome);
        farmacia.InserirEndereco(rua, numero, bairro, cidade, estado);
        
        SingletonSession.getInstance().setAttribute("farmacia", farmacia);

        return "CadastrarRemedios.xhtml?faces-redirect=true";

    }

    public String mudarIndex() {
        return "Index.xhtmlfaces-redirect=true";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
