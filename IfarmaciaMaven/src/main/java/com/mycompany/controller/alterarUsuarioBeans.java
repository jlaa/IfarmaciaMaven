/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Aplicacao;
import com.mycompany.model.CartaoDeCredito;
import com.mycompany.model.Cliente;
import com.mycompany.model.ValidaEstados;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    @EJB
    private Aplicacao aplicacao;
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
    @NotEmpty(message = "A Rua não pode ser vazia")
    @Size(max = 100, message = "o tamanho maximo é 100 para a Rua")
    private String rua;
    @NotEmpty(message = "O número não pode ser vazio")
    private String numero;
    @NotEmpty(message = "A Cidade não pode ser vazia")
    @Size(max = 100, message = "o tamanho maximo é 100 para a cidade")
    private String cidade;
    @NotEmpty(message = "O bairro não pode ser vazio")
    @Size(max = 100, message = "o tamanho maximo é 100 para o bairro")
    private String bairo;
    @NotEmpty(message = "A ocupação não pode ser vazia")
    @Size(max = 25, message = "o tamanho maximo é 25 para a ocupação")
    private String ocupacao;
    private String nome = "";
    
    List<CartaoDeCredito> cartoes;
    
    List<String> nomecartoes;
    
   

    /**
     * Creates a new instance of loginBeans
     */
    public alterarUsuarioBeans() {

    }

    public String alterarUsuario() {
        
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        nome = primeiroNome + " " + segundoNome;
        cliente.AdicionarEndereco(rua, numero, bairo, cidade, estado);
        cliente.setNome(nome);
        cliente.setOcupacao(ocupacao);
        cliente.setTelefone(telefone);        
        aplicacao.AlterarCliente(cliente);
        
        return "sucessoAlterar";
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
    
    public List<String> getNomeCartoes(){
        
        Cliente cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        cartoes = cliente.getCartaos();
        nomecartoes= new ArrayList();
        for(int i = 0; i<cartoes.size(); i++){
            nomecartoes.add(cartoes.get(i).getBandeira());
        }
        return nomecartoes;
    }

}
