package com.mycompany.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David Borges
 */
@Entity
@Table(name = "COMPRA_TABLE")
public class CompraTotal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private BigDecimal valorTotal;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_COMPRA", nullable = false)
    private Date dataCompra;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FARMACIA", referencedColumnName = "ID")
    private Farmacia vendedor;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    private Cliente cliente;
    
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FORMA_PAGAMENTO", referencedColumnName = "ID")
    private FormaDePagamento formaPagamento;

    @NotNull
    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraItem> itens;

    @Column(name = "COMPRA_CANCELADA", columnDefinition="tinyint(1) default 1")
    private Boolean cancelada;
    
    public CompraTotal(){
        
    }

    private void calculaValorCompra() {
        BigDecimal compraTotal = new BigDecimal(BigInteger.ZERO);
        for (CompraItem item : this.itens) {
            compraTotal.add(item.getSubTotal());
        }
        this.valorTotal = compraTotal;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente= cliente;
    }
    
    public void setItensCompra(List<CompraItem> itensCompra) {
        this.itens = itensCompra;
    }  
    
    public void setDataCompra(Date data){
        this.dataCompra= data;
    }
    
    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }
    
    public void setVendedor(Farmacia vendedor) {
        this.vendedor = vendedor;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Date getDataCompra() {
        return this.dataCompra;
    }
    
    public List<CompraItem> getItensCompra() {
        return itens;
    }
    
    public BigDecimal getValorTotal() {
        this.calculaValorCompra();
        return valorTotal;
    }
    
    public Farmacia getVendedor() {
        return this.vendedor;
    }
    
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public Boolean getCancelada() {
        return cancelada;
    }        
}
