package cn.javabs.cei.service.serviceimpl;

import cn.javabs.cei.dao.SourceDao;
import cn.javabs.cei.dao.daoimpl.SourceDaoImpl;
import cn.javabs.cei.entity.Source;
import cn.javabs.cei.service.SourceService;

import java.util.List;

public class SourceServiceImpl implements SourceService {
    SourceDao sourceDao=new SourceDaoImpl();
    @Override
    public int addSource(Source source) {
        return sourceDao.addSource( source);
    }

    @Override
    public int delSource(int id) {
        return sourceDao.delSource(id);
    }

    @Override
    public int updateSource(Source source) {
        return sourceDao.updateSource(source);
    }

    @Override
    public List<Source> findAllSource() {
        return sourceDao.getAllSources();
    }

    @Override
    public Source findSourceById(int id) {
        return sourceDao.getSourceById(id);
    }

    @Override
    public Source findSourceByName(String name) {
        return sourceDao.getSourceByName(name);
    }
}
