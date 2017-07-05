package com.mycompany.model;

import com.mycompany.model.Cliente;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T11:17:45")
@StaticMetamodel(CartaoDeCredito.class)
public class CartaoDeCredito_ { 

    public static volatile SingularAttribute<CartaoDeCredito, Calendar> dataExpiracao;
    public static volatile SingularAttribute<CartaoDeCredito, Cliente> cliente;
    public static volatile SingularAttribute<CartaoDeCredito, Long> id_cartao;
    public static volatile SingularAttribute<CartaoDeCredito, String> numero_cartao;
    public static volatile SingularAttribute<CartaoDeCredito, String> bandeira;

}