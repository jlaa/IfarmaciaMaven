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
import com.mycompany.model.Cliente;
import com.mycompany.model.Grupo;
import com.mycompany.model.ValidaEstados;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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

    private List<String> estados = new ArrayList();
    private List<String> ocupacaoes = new ArrayList();
    private String nome = "";

    /**
     * Creates a new instance of loginBeans
     */
    public registerBeans() {
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
        
        this.ocupacaoes.add("cliente");
        this.ocupacaoes.add("owner");
        this.ocupacaoes.add("aposentado");
        
    }

    public String cadastrarCliente() {
        Cliente cliente = new Cliente();
        if (senha.equals(confirmarSenha)) {
            cliente.CadastrarCliente(email, senha, login);
            cliente.AdicionarEndereco(rua, numero, bairo, cidade, estado);
            this.nome = primeiroNome + " " + segundoNome;
            cliente.adicionarInformaçõesCliente(nome, telefone, ocupacao);
            Grupo grupo=aplicacao.getGrupo(ocupacao);
            
            List<Cliente> clientes= grupo.getClientes();
            clientes.add(cliente);
            grupo.setClientes(clientes);
            List<Grupo> grupos = new ArrayList();
            grupos.add(grupo);            
            cliente.setGrupos(grupos);
            boolean register=aplicacao.atualizarGrupo(grupo);
            if (register == true) {
                return "sucessoRegister";
            }
        }
        return "falhaRegister";//falta colocar o register ERRO

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

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

    public List<String> getOcupacaoes() {
        return ocupacaoes;
    }

    public void setOcupacaoes(List<String> ocupacaoes) {
        this.ocupacaoes = ocupacaoes;
    }
    
    

}
