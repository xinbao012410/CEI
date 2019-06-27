package cn.javabs.cei.service;

import cn.javabs.cei.entity.Source;

import java.util.List;

public interface SourceService {

    int addSource(Source source);
    int delSource(int id);
    int updateSource(Source source);
   List<Source> findAllSource();
   Source findSourceById(int id);

   Source findSourceByName(String name);
}
