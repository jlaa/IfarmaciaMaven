/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author joao_dev
 */
@Entity
@Table(name = "FORMA_TABLE")
@Access(AccessType.FIELD)
public class FormaDePagamento implements Serializable {

    @Id
    @Column(name = "ID_FORMA_PAGAMENTO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NOME_FORMA", nullable = false)
    private String nomeForma;

    @Column(name = "INFORMACOES_ADICIONAIS", nullable = true)
    private String informacoesAdicionais;

    public FormaDePagamento() {

    }

    public void CadastrarForma(String nomeForma) {
        this.nomeForma = nomeForma;
    }

    public void setNomeForma(String nome) {
        this.nomeForma = nome;
    }

    public String getNomeForma() {
        return this.nomeForma;
    }

    public String getInformacoesAdicionais() 
    {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) 
    {
        this.informacoesAdicionais = informacoesAdicionais;
    }
}
