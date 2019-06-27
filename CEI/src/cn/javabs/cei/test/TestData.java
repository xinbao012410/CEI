package cn.javabs.cei.test;

import cn.javabs.cei.entity.Source;
import cn.javabs.cei.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class TestData {
    QueryRunner qr=new QueryRunner(DruidUtils.getDataSource());
    @Test
    public void fun(){
        Source source = new Source();
        List<Source> sources= null;
        try {
            sources = qr.query("select*from source",new BeanListHandler<Source>(Source.class));
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
        System.out.println(sources);
    }
}
