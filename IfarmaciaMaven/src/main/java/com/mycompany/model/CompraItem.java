package com.mycompany.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Borges
 */
@Entity
@Table(name = "COMPRA_ITEM_TABLE")
public class CompraItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "NUM_QUANTIDADE", nullable = false)
    private Integer quantidade;

    @NotNull
    @Column(name = "NUM_PRECO_UNITARIO", nullable = false)
    private BigDecimal precoUnitario;

    private BigDecimal subTotal;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_REMEDIO", referencedColumnName = "ID")
    private Remedio produto;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_COMPRA", referencedColumnName = "ID")
    private CompraTotal compra;

    public CompraItem(){
        
    }
    
    private void calcularSubTotal() {
        BigDecimal quantia= new BigDecimal(this.quantidade);
        this.subTotal = this.precoUnitario.multiply(quantia);
    }
    
    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    public void setCompra(CompraTotal compra) {
        this.compra = compra;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    
    public void setProduto(Remedio produto) {
        this.produto = produto;
    }    
    
    public void setId(Long id) {
        this.id = id;
    } 
    
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    
    public BigDecimal getSubTotal() {
        this.calcularSubTotal();
        return subTotal;
    }

    public Remedio getProduto() {
        return produto;
    }

    public CompraTotal getCompra() {
        return compra;
    }

    public Long getId() {
        return id;
    }   
}
