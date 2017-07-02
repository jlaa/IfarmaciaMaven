package com.mycompany.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Borges
 */
@Entity
@Table(name = "ENTREGA_TABLE")
public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_DATA_ENTREGA", nullable = false)
    private Date dataEntrega;
    
    @NotNull
    @Column(name = "BD_TAXA_ENTREGA", nullable = false)
    private BigDecimal taxaEntrega;
    
    @Valid
    @Embedded
    private Endereco enderecoEntrega;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ID_COMPRA", referencedColumnName = "ID")
    private CompraTotal compra;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ID_ENTREGADOR", referencedColumnName = "ID")
    private Farmacia entregador;

    public Entrega(){
        
    }
    
    
    public void setTaxaEntrega(BigDecimal taxa) {
        this.taxaEntrega= taxa;
    }
    
    public void setCompra(CompraTotal compra) {
        this.compra= compra;
    } 
    
    public void setDataEntrega(Date data) {
        this.dataEntrega= data;
    }

    public void setEntregador(Farmacia entregador) {
        this.entregador= entregador;
    }     
    
    public Long getId() {
        return id;
    }
    
    public BigDecimal getTaxaEntrega() {
        return this.taxaEntrega;
    }
    
    public Date getDataEntrega() {
        return this.dataEntrega;
    } 
    
    public CompraTotal getCompra() {
        return this.compra;
    }
    
    public Farmacia getEntregador(){
        return this.entregador;
    }
}
