package cn.javabs.cei.dao.daoimpl;

import cn.javabs.cei.dao.SourceDao;
import cn.javabs.cei.entity.Source;
import cn.javabs.cei.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class SourceDaoImpl implements SourceDao {
    QueryRunner qr =  new QueryRunner(DruidUtils.getDataSource());
    @Override
    public int addSource(Source source) {
        try {
            return qr.update("insert into source(id,name,author,path,pageView,creatTime)values(?,?,?,?,?,?)",
                    source.getId(),source.getName(),source.getAuthor(),source.getPath(),source.getPageView(),source.getCreatTime());
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public int delSource(int id) {
        try {
            return qr.update("delete from  source where id = ?",id);
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public int updateSource(Source source) {
        try {
            return qr.update("update source set name=?,author=?,path=?,pageView=?,creatTime=? where id=?",
                    source.getName(),source.getAuthor(),source.getPath(),source.getPageView(),source.getCreatTime(),source.getId() );
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public List<Source> getAllSources() {
        List<Source> sources= null;
        try {
            sources = qr.query("select*from source",new BeanListHandler<Source>(Source.class));
            return sources;
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public Source getSourceById(int id) {
        try {
           Source source = qr.query("select * from source where id = ?", new BeanHandler<Source>(Source.class),id);
            return source;
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public Source getSourceByName(String name) {
        try {
            Source source  = qr.query("select * from source where name = ?", new BeanHandler<Source>(Source.class),name);
            return source;
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
}
