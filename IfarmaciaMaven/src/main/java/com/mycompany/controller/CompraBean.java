package com.mycompany.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.model.Aplicacao;
import com.mycompany.model.CartaoDeCredito;
import com.mycompany.model.Cliente;
import com.mycompany.model.Remedio;
import com.mycompany.model.ValidaEstados;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author joao_dev
 */
@ManagedBean(name = "compraBean")
@RequestScoped
public class compraBean implements Serializable {

    @EJB
    private Aplicacao aplicacao;

    @Valid
    private List<CartaoDeCredito> cartoes;

    private Cliente cliente;

    @ValidaEstados(message = "Esse estado não existe")
    private String estado;

    @NotEmpty(message = "A cidade não pode ser vazio")
    @Length(message = "A cidade não pode ter mais de 50 caracteres", max = 50)
    private String cidade;

    @NotEmpty(message = "A rua não pode ser vazio")
    @Length(message = "A rua não pode ter mais de 150 caracteres", max = 150)
    private String rua;

    @NotEmpty(message = "O numero não pode ser vazio")
    @Length(message = "O numero não pode ter mais de 9999 caracteres e"
            + " menos de 1 caracteres", max = 9999, min = 1)
    private String numero;

    @Length(message = "A rua não pode ter mais de 150 caracteres", max = 150)
    private String complemento;

    private CartaoDeCredito cartao;

    private List<String> nomeCartoes;

    /**
     * Creates a new instance of CompraBean
     */
    public compraBean() {
        cliente = (Cliente) SingletonSession.getInstance().getAttribute("clienteLogado");
        if (cliente != null) {
            cartoes = cliente.getCartaos();
            estado = cliente.getEndereco().getEstado();
            cidade = cliente.getEndereco().getCidade();
            rua = cliente.getEndereco().getRua();
            numero = cliente.getEndereco().getNumero();
        }
    }

    public List<CartaoDeCredito> getCartoes() {
        return cartoes;
    }

    public List<String> getNomeCartoes() {
        String string;
        nomeCartoes = new ArrayList();
        for (int i = 0; i < cartoes.size(); i++) {
            string = cartoes.get(i).getBandeira();
            nomeCartoes.add(string);
        }
        return nomeCartoes;
    }

    public void setCartoes(List<CartaoDeCredito> cartoes) {
        this.cartoes = cartoes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CartaoDeCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoDeCredito cartao) {
        this.cartao = cartao;
    }

    public String finalizarCompra() {
        List<Remedio> remedios = (List<Remedio>) SingletonSession.getInstance().getAttribute("remedioComprado");
        int tamanho = remedios.size();
        for (int i = 0; i < tamanho; i++) {
            Remedio remedioBanco = aplicacao.getRemedio(remedios.get(i).getId());
            //o que vai ser alterado é o do banco
            remedioBanco.diminuiEstoque(remedios.get(i).getQuantidade());
            aplicacao.AlterarRemedio(remedioBanco);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("remedioComprado");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("WELD_S#12");

        }
        return "compraCompleta";
    }

}
