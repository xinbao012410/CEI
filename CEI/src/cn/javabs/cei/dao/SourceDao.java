package cn.javabs.cei.dao;

import cn.javabs.cei.entity.Source;

import java.util.List;

public interface SourceDao {
    int addSource(Source source);

    int delSource(int id);

    int updateSource(Source source);

    List<Source> getAllSources();

    Source getSourceById(int id);

    Source getSourceByName(String name);
}
