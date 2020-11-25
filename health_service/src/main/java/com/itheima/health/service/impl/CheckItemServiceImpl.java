package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.excption.MyException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

  // interfaceClass  发布的服务接口

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    CheckItemDao checkItemDao;


    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //使用分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //是否有查询条件
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        //调用dao查询
        Page<CheckItem> checkItemPage = checkItemDao.findByCondition(queryPageBean.getQueryString());
        long total = checkItemPage.getTotal();//总记录数
        List<CheckItem> rows = checkItemPage.getResult();//分页结果集

        return   new PageResult<CheckItem>(total,rows);
    }


    //删除检查项

    @Override
    @Transactional
    public void deleteById   (int id)   throws MyException  {
            //判断此检查项是否被检查组使用
        int cnt =checkItemDao.findCountByCheckItemId(id);
        //判断如果被使用了就不能删除
        if (cnt>0){
            //已经被检查组使用了就不能呗删除 要八错
            throw new MyException("该检查项被检查组使用了，无法删除");
        }
        checkItemDao.deleteById(id);
    }
//更具id查找
    @Override
    public CheckItem findById(int id) {

        return  checkItemDao.findById(id);
    }
//更新检查项
    @Override
    public void update(CheckItem checkItem) {
checkItemDao.update(checkItem);
    }

}
