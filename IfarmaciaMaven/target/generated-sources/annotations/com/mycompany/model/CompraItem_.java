package com.mycompany.model;

import com.mycompany.model.CompraTotal;
import com.mycompany.model.Remedio;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-29T11:17:45")
@StaticMetamodel(CompraItem.class)
public class CompraItem_ { 

    public static volatile SingularAttribute<CompraItem, Long> id;
    public static volatile SingularAttribute<CompraItem, BigDecimal> precoUnitario;
    public static volatile SingularAttribute<CompraItem, CompraTotal> compra;
    public static volatile SingularAttribute<CompraItem, Integer> quantidade;
    public static volatile SingularAttribute<CompraItem, Remedio> produto;
    public static volatile SingularAttribute<CompraItem, BigDecimal> subTotal;

}