/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.Cliente;
import com.mycompany.model.ValidaEstados;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author LucasPC
 */
@Named(value = "alterarUsuario")
@RequestScoped
public class alterarUsuarioBeans implements Serializable {

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
    public alterarUsuarioBeans() {

    }

    public String alterarUsuario() {
        Aplicacao aplicacao = new Aplicacao();

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        nome = primeiroNome + " " + segundoNome;
        aplicacao.AlterarCliente(bairo, cidade, estado, numero, rua, nome, ocupacao, telefone, cliente);

        return "Index";
    }

    public String getLogin() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getLogin();
        }
        return this.login;
    }

    public void setLogin(String login) {

        this.login = login;

    }

    public String getSenha() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getSenha();
        }
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPrimeiroNome() {
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        String[] string1 = cliente.getNome().split(" ");
        if (cliente != null) {
            return string1[0];
        }
        return this.primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSegundoNome() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        String[] string1 = cliente.getNome().split(" ");
        if (cliente != null) {
            return string1[1];
        }
        return this.segundoNome;
    }

    public void setSegundoNome(String segundoNome) {
        this.segundoNome = segundoNome;
    }

    public String getEmail() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");

        if (cliente != null) {
            return cliente.getEmail();
        }
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getTelefone();
        }
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getEndereco().getEstado();
        }
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRua() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getEndereco().getRua();
        }
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getEndereco().getNumero();
        }
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getEndereco().getCidade();
        }
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairo() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getEndereco().getBairro();
        }
        return this.bairo;
    }

    public void setBairo(String bairo) {
        this.bairo = bairo;
    }

    public String getOcupacao() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getOcupacao();
        }
        return this.ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getConfirmarSenha() {

        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            return cliente.getSenha();
        }
        return this.confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

}
