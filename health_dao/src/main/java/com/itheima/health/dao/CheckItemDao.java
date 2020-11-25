package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;

import java.awt.*;
import java.util.List;

public interface CheckItemDao {
    //查询所有
    List<CheckItem> findAll();


    //添加检查项
    void add(CheckItem checkItem);


    //条件查询
    Page<CheckItem> findByCondition(String queryString);



//判断此检查项是否被检查组使用
    int findCountByCheckItemId(int id);


//删除检查项
    void deleteById(int  id) ;

//查找检查项
    CheckItem findById(int id);

    //更新检查项
    void update(CheckItem checkItem);
}
