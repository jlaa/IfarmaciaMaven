/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author joao_dev
 */
@Entity
@Table(name = "FARMACIA_TABLE")
@Access(AccessType.FIELD)
public class Farmacia implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FARMACIA", nullable = false)
    private long id_farmacia;
    
    @NotBlank
    @Size(max = 30)
    @Column(name = "TXT_NOME_FARMACIA", nullable = false)
    private String nome;
    
    @JoinColumn(name="TXT_FILENAME")
    private String filename;
    
    @Valid
    @Embedded
    private Endereco endereco;
    
    @Valid  
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_FORMA",referencedColumnName="ID_FORMA_PAGAMENTO")
    private FormaDePagamento forma_de_pagamento;
    
    
    
    @Valid 
    @ManyToMany(mappedBy = "farmacia")
    private List<Cliente> clientes;
    
    @Valid
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_FARMACIA_REMEDIOS", joinColumns = {
        @JoinColumn(name = "ID_FARMACIA")},
            inverseJoinColumns = {
                @JoinColumn(name = "ID_REMEDIO")
    })
    private List<Remedio> remedios;
    
    
    
    public Farmacia()
    {
        
    }   
    
    public void VenderRemedio(Remedio remedioVendido, long quantidadeVendida)
    {
        remedioVendido.setQuantidade(remedioVendido.getQuantidade() - quantidadeVendida);
    }
    
    public void CadastrarFarmacia(String nome)
    {
        this.nome = nome;
    }
    
    public void InserirEndereco(String rua,String numero,String bairro,String cidade,String estado)
    {
        this.endereco=new Endereco();
        this.getEndereco().CadastrarEndereco(rua, numero, bairro,cidade,estado);
    }
    
    public void InserirFormaDePagamento(FormaDePagamento forma)
    {
        this.forma_de_pagamento = forma;        
    }
    
    
    public Remedio CadastrarRemedio(String nome, double preco, String faixaEtaria, long quantidade, String tipo)
    {
        Remedio novoRemedio = new Remedio(nome,preco,faixaEtaria,quantidade,tipo);
        
        return novoRemedio;
    }
    
    public void alterarFarmacia(String novoNome, String rua,String numero,String bairro,String cidade,String estado)
    {
        setNome(novoNome);
        InserirEndereco(rua, numero, bairro,cidade,estado);
    }
    
    public void setRemedios(List<Remedio> remedios)
    {
        this.remedios = remedios;
    }
    
    //Getters
    public String getNome() {
        return this.nome;
    }
    
    public Endereco getEndereco() {
        return this.endereco;
    }
    
    public FormaDePagamento getForma()
    {
        return this.forma_de_pagamento;
    }
      public List<Remedio> getRemedios()
      {
          return this.remedios;
      } 
    
    //Cadastro de Nova Farmacia
    public void setNome(String nome)
    {
        this.nome=nome;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
  
    
}
