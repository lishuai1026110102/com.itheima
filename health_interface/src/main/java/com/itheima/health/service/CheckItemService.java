package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;

import com.itheima.health.excption.MyException;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    //查询所有的检查项
    List<CheckItem> findAll();


    //添加检查项
    void add(CheckItem checkItem);

    //查看分页
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);


    //删除检查项d
    void deleteById(int id) throws MyException;


    //编辑时需要查找的id
    CheckItem findById(int id);

  //  更新检查项
    void update(CheckItem checkItem);
}
