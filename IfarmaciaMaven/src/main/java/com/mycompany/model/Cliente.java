/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author LucasPC
 */
@Entity
@Table(name="CLIENTE_TABLE")
@Access(AccessType.FIELD)
public class Cliente implements Serializable
{    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    private long id;
    
    @NotBlank
    @Size(max = 30)
    @Column(name="TXT_NOME",nullable = false)
    private String nome;
    
    @NotBlank
    @Size(min=6, max=20)
    @Column(name="TXT_SENHA",nullable = false)
    private String senha;
    
    @NotNull
    @Size(max = 20)
    @Column(name="TXT_OCUPAÇAO",nullable = false)
    private String ocupacao;
    
    @NotNull
    @Email
    @Column(name="TXT_EMAIL",nullable = false,unique=true)
    private String email;
    
    @NotBlank
    @Size(max = 20)
    @Column(name="TXT_LOGIN",nullable = false,unique = true)
    private String login; 
    
    @Size(max = 17)
    @Column(name="TXT_TELEFONE",nullable = true)
    private String telefone;   
    
    @Valid
    @Embedded
    private Endereco endereco;    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_FARMACIA_CLIENTE", joinColumns = {
        @JoinColumn(name = "ID_USUARIO")},
            inverseJoinColumns = {
                @JoinColumn(name = "ID_FARMACIA")
    })
    private List<Farmacia> farmacia;
    
   
    @OneToMany(mappedBy="cliente",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)   
    private List<CartaoDeCredito> cartaos;
    
    
    
    public Cliente()
    {
        
    }       
    //PARTE DE INSERÇÃO
    
    //cadastra o cliente , precisa de metodos de verificação se o login , senha e e-mail são válidos
    public void CadastrarCliente(String email,String senha,
            String login)
    {
        //fazer os testes para saber se login, senha e e-mail são válidos,
        //não pode ter dois emails ou dois logins iguais        
       if(!validacaoSenha(senha))
       {  
            System.out.println("Senha não é válida");                     
       }            
       this.login=login;
       this.senha=senha; 
       this.email=email;
        
    }
    
    //adiciona informações do cliente como numero de telefone,ocupação,nome
    public void adicionarInformaçõesCliente(String nome,String telefone,String ocupacao)
    {
         this.nome=nome;
         this.ocupacao=ocupacao;
         this.telefone=telefone;
    }
    //adiciona as informações de endereco
    public void AdicionarEndereco(String rua,String numero,String bairro,String cidade,String estado)
    {
        this.endereco=new Endereco();
        this.getEndereco().CadastrarEndereco(rua, numero, bairro,cidade,estado);
    }   
     public CartaoDeCredito CadastrarCartao(String bandeira,String numero_cartao,Calendar dataExpiracao)
    {
       CartaoDeCredito cartao=new CartaoDeCredito();
       cartao.setBandeira(bandeira);
       cartao.setNumero_cartao(numero_cartao);
       cartao.setDataExpiracao(dataExpiracao);
       
       return cartao;
    }
    //PARTE DE ALTERAÇÃO
    
    public void alterarSenha(String senhaAntiga,String senhaNova)
    {
        //verifique se a senhaAntiga é igual a senha atual,caso verdade altere-a
        this.senha=senhaNova;
    }
    
    //altera o nome
    public void setNome(String nome)
    {
        this.nome=nome;
    }
    //altera a ocupacao
    public void setOcupacao(String ocupacao)
    {
        this.ocupacao=ocupacao;
    }
    
    //altera a telefone
    public void setTelefone(String telefone)
    {
        this.telefone=telefone;
    }
    //altera a e-mail, não pode ter dois e-mails iguais
    public void setEmail(String email)
    {
        this.email=email;
    }
    public void setCartao(CartaoDeCredito cartaos)
    {
        this.cartaos.add(cartaos);
    }
    
    
    
    //getters
    public String getSenha() {
        return this.senha;
    }  
  
  
    public String getOcupacao() {
        return this.ocupacao;
    }
  
    public String getEmail() {
        return this.email;
    }
  
    public String getLogin() {
        return this.login;
    }
   
    public String getTelefone() {
        return this.telefone;
    }
    
    public String getNome() {
        return nome;
    }

 
    public Endereco getEndereco() {
        return endereco;
    }   
    public Long getId()
    {
        return this.id;
    }
   
    
    //validações
    //se a senha tiver um tamanho menor que 6 caracteres não é válida
    private boolean validacaoSenha(String senha)
    {
        //se senha for maior ou igual a 6 é igual a true
        return senha.length() >= 6;
    }   

    public List<CartaoDeCredito> getCartaos() {
        return cartaos;
    }
    
}
