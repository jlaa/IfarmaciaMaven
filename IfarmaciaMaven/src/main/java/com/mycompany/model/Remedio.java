package com.mycompany.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
/**
 *
 * @author LucasPC
 */
@Entity
@Table(name="REMEDIO_TABLE")
@Access(AccessType.FIELD)
public class Remedio implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID_REMEDIO", nullable= false)
    private Long id;
    
    @Column(name="TXT_CODIGO", nullable= false)
    private String codigo;
    
    @Column(name="TXT_PRECO", nullable= false)
    private double preco;
    
    @Column(name="TXT_NOME", nullable= false)
    private String nome;
    
    @Column(name= "TXT_QUANTIDADE", nullable= false)
    private long quantidade;
    
    @Column(name="TXT_FAIXA_ETARIA", nullable= false)
    private String faixaEtaria;
    
    @Column(name="TXT_DESCONTO")
    private Integer desconto;
        
    @Column(name="DT_VALIDADE", nullable= false)
    @Temporal(TemporalType.DATE)
    private Calendar dataValidade;    
    
    @Column(name="TXT_TIPO", nullable= false)
    private String tipo;
    
    @ManyToMany(mappedBy = "remedios")
    private List<Farmacia> farmacia;
    
    public Remedio(){
        
    }
    //Cadastro e modificação de remedios
    public Remedio(String nome, double preco, String faixaEtaria, long quantidade, String tipo, Calendar dataValidade,String codigo){
        this.nome= nome;
        this.preco= preco;
        this.faixaEtaria= faixaEtaria;
        this.quantidade= quantidade;
        this.tipo = tipo;
        this.dataValidade= dataValidade;
        this.codigo=codigo;
    }
    
    public Remedio(String nome, double preco, String faixaEtaria, long quantidade, String tipo){
        this.nome= nome;
        this.preco= preco;
        this.faixaEtaria= faixaEtaria;
        this.quantidade= quantidade;
        this.tipo = tipo;
        
    }
    
    public void AumentaPreco(double valorAumento){
        this.preco= preco+valorAumento;
    }
    
    public void DescontaPreco(double valorDesconto){
        this.preco= preco-valorDesconto;
    }
    
    public void aumentaEstoque(long incremento){
        this.quantidade= quantidade+incremento;
    }
    
    public boolean diminuiEstoque(long decremento){
        if((quantidade - decremento) <= 0)
        {
            return false;
        }
        
        this.quantidade= quantidade-decremento;
        return true;
    }
    
//    public void excluirRemedio(String nome, String tipo){
//       for (int i = 0; i < remedio.size(); i++) {
//          if (nome.equals(remedio.get(i).getNome())) {
//               remedio.remove(i);
//          }
//        }
//    }
    
//    public void VerificarData() {
//       Calendar cal = Calendar.getInstance();
//           if (data_validade.before(cal.getTime())) {
//           //produto pode ser vendido
//          }else if(data_validade.after(cal.getTime())){
//               //produto pode ser excluido
//               excluirRemedio(nome,tipo);
//            }
//    }
    
    public long getQuantidade(){
       return this.quantidade;
    }
    public void setQuantidade(long quantidade) {
       this.quantidade=quantidade;
    }
    //Resgate de valores referentes ao remedio;
    public double getPreco(){
       return this.preco;
    }
   
    public void setPreco(double preco){
       this.preco = preco;
    }
    
    public String getNome(){
       return this.nome;
    }
    
  
    
    
    public String getFaixaEtaria(){
       return this.faixaEtaria;
    }
    
    public String getTipoRemedio(){
       return this.tipo;
    }
    
    public Calendar getDataValidade(){
       return this.dataValidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Farmacia> getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(List<Farmacia> farmacia) {
        this.farmacia = farmacia;
    }

    public Long getId() {
        return id;
    }

    public Integer getDesconto() {
        return desconto;
    }

    public void setDesconto(Integer desconto) {
        this.desconto = desconto;
    }

    
    
    
    
    
    
    
}