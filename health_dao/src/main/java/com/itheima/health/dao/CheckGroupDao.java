package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

public interface CheckGroupDao   {

    void add(CheckGroup checkGroup);

    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);


    Page<CheckGroup> findByCondition(String queryString);


    int findSetmealCountByCheckGroupId(int id);


    void deleteById(int id);


    void deleteCheckGroupCheckItem(int id);

    void findById(int id);

}
