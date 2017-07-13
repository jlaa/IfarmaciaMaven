package com.mycompany.model;

import com.mycompany.model.CompraTotal;
import com.mycompany.model.Endereco;
import com.mycompany.model.Farmacia;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-13T03:29:37")
@StaticMetamodel(Entrega.class)
public class Entrega_ { 

    public static volatile SingularAttribute<Entrega, CompraTotal> compra;
    public static volatile SingularAttribute<Entrega, Date> dataEntrega;
    public static volatile SingularAttribute<Entrega, BigDecimal> taxaEntrega;
    public static volatile SingularAttribute<Entrega, Endereco> enderecoEntrega;
    public static volatile SingularAttribute<Entrega, Long> id;
    public static volatile SingularAttribute<Entrega, Farmacia> entregador;

}