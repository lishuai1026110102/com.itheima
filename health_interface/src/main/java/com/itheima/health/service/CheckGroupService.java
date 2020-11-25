package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.excption.MyException;
import com.itheima.health.pojo.CheckGroup;

public interface CheckGroupService {




    void add(CheckGroup checkGroup, Integer[] checkitemIds);




    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);


    void deleteById(int id) throws MyException;


}
