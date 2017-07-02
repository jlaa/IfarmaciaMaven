/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.mycompany.model.Aplicacao;
import com.mycompany.model.CartaoDeCredito;
import com.mycompany.model.Cliente;
import com.mycompany.model.Endereco;
import com.mycompany.model.Farmacia;
import com.mycompany.model.ValidaEstados;
import java.util.List;
import javax.ejb.SessionContext;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author LucasPC
 */
@ManagedBean(name = "register")
@RequestScoped
public class registerBeans implements Serializable {
    
    @NotEmpty(message = "O username não pode ser vazio")
    @Length(message = "O login não pode ter mais de 20 caracteres", max = 20)
    private String login;
    
    @NotEmpty(message = "O password não pode ser vazio")
    @Length(message = "A senha não pode ter mais de 20 caracteres e"
            + " menos de 6 caracteres", max = 20, min = 6)
    private String senha;
    
    @NotEmpty(message = "O password não pode ser vazio")
    @Length(message = "A senha não pode ter mais de 20 caracteres e"
            + " menos de 6 caracteres", max = 20, min = 6)    
    private String confirmarSenha;

    @NotEmpty(message = "O primeiro nome não pode ser vazio")
    private String primeiroNome;
    @NotEmpty(message = "O segundo nome não pode ser vazio")
    private String segundoNome;
    @NotEmpty(message = "O email não pode ser vazio")
    @Email(message = "email inválido")
    private String email;
    @NotEmpty(message = "O telefone não pode ser vazio")
    @Size(max = 17, message = "o tamanho maximo é 17 para o telefone")
    private String telefone;
    @ValidaEstados(message = "Esse estado não existe")
    private String estado;

    private String rua;

    private String numero;

    private String cidade;

    private String bairo;

    private String ocupacao;
    private String nome = "";

    /**
     * Creates a new instance of loginBeans
     */
    public registerBeans() {

    }

    public String cadastrarCliente() {
        Aplicacao aplicacao = new Aplicacao();
        Cliente cliente = new Cliente();
        if (senha.equals(confirmarSenha)) {
            cliente.CadastrarCliente(email, senha, login);
            cliente.AdicionarEndereco(rua, numero, bairo, cidade, estado);
            this.nome = primeiroNome+ " " + segundoNome;
            cliente.adicionarInformaçõesCliente(nome, telefone, ocupacao);
            boolean cadastro=aplicacao.CadastrarCliente(cliente);
            if(cadastro==true)
            {
                return "RegisterOK";
            }
        }
        return "Register";
    }
    
    public String redirecionarIndex()
    {
        return "Index";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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

    public String getBairo() {
        return bairo;
    }

    public void setBairo(String bairo) {
        this.bairo = bairo;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

}
