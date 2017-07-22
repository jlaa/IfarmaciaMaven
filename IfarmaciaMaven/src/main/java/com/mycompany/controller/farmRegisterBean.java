/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Farmacia;
import com.mycompany.model.ValidaEstados;
import com.mycompany.model.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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

    private List<String> estados = new ArrayList();

    /**
     * Creates a new instance of farmRegisterBean
     */
    public farmRegisterBean() {
        this.estados.add("AC");
        this.estados.add("AL");
        this.estados.add("AM");
        this.estados.add("AP");
        this.estados.add("BA");
        this.estados.add("CE");
        this.estados.add("DF");
        this.estados.add("ES");
        this.estados.add("GO");
        this.estados.add("MA");
        this.estados.add("MG");
        this.estados.add("MS");
        this.estados.add("MT");
        this.estados.add("PA");
        this.estados.add("PB");
        this.estados.add("PE");
        this.estados.add("PI");
        this.estados.add("PR");
        this.estados.add("RJ");
        this.estados.add("RN");
        this.estados.add("RO");
        this.estados.add("RR");
        this.estados.add("RS");
        this.estados.add("SC");
        this.estados.add("SE");
        this.estados.add("SP");
        this.estados.add("TO");
    }

    public String cadastrarFarmacia() {
        List<Cliente> clientes = new ArrayList();
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            clientes.add(cliente);
            Farmacia farmacia = new Farmacia();
            farmacia.CadastrarFarmacia(nome);
            farmacia.InserirEndereco(rua, numero, bairro, cidade, estado);
            farmacia.setClientes(clientes);           
            SingletonSession.getInstance().setAttribute("farmacia", farmacia);

        }
        return "sucessoFarmacia";
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

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

}
