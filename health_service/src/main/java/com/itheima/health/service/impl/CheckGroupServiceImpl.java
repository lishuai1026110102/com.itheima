package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.excption.MyException;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 添加检查组
        checkGroupDao.add(checkGroup);
        // 获取检查组的id
        Integer checkGroupId = checkGroup.getId();
        // 遍历检查项id, 添加检查组与检查项的关系
        if (null != checkitemIds) {


            for (Integer checkitemId : checkitemIds) {
                //添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId, checkitemId);
            }
        }
    }



    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
//用PageHelper.startPage
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());


        // 有查询条件的处理, 模糊查询
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 后面的查询分页
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult<CheckGroup>(page.getTotal(), page.getResult());
    }



//删除检查组
@Override
    @Transactional

    public void deleteById(int id) throws MyException {
        //检查这个检查组是给被套餐使用了
      int  count =checkGroupDao.findSetmealCountByCheckGroupId(id);
      if (count>0) {
          //被使用
          throw new MyException(MessageConstant.CHECKGROUP_IN_USE);
      }


          checkGroupDao.deleteCheckGroupCheckItem(id);
          // 删除检查组
          checkGroupDao.deleteById(id);
      }




}



