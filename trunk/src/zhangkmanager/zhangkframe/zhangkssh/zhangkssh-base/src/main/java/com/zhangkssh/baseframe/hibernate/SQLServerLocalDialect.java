package com.zhangkssh.baseframe.hibernate;
import org.hibernate.Hibernate;   
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;   
  
public class SQLServerLocalDialect extends SQLServerDialect {   
    public SQLServerLocalDialect(){   
        super();   
        registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING, "convert(?1,?2,?3)") );   
    }   
} 